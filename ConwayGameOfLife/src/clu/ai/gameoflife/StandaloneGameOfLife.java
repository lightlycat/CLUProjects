package clu.ai.gameoflife;

import clu.ai.util.AlertBox;
import clu.ai.util.EasyFile;
import clu.ai.util.LineEnumerator;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;
/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM2:58
 * Create a window to display Conway's Game of Life
 */


public class StandaloneGameOfLife extends GameOfLife {
    private Frame appletFrame;
    private String[] args;
    private GameOfLifeGridIO gridIO;
    /**
     * main() for standalone version.
     * @param args Not used.
     */
    public static void main(String args[]) {
        StandaloneGameOfLife gameOfLife = new StandaloneGameOfLife();
        gameOfLife.args = args;
        new AppletFrame( "Ying-Wen's GOL Assignment", gameOfLife );
    }

    /**
     * Initialize UI.
     * @param parent Parent frame.
     * @see java.applet.Applet#init()
     */
    public void init( Frame parent ) {
        appletFrame = parent;
        getParams();

        // set background colour
        setBackground(new Color(0x999999));

        // TODO: casten naar interface
        // create StandAloneGameOfLifeGrid
        gameOfLifeGrid = new GameOfLifeGrid( cellCols, cellRows);
        gridIO = new GameOfLifeGridIO( gameOfLifeGrid );

        // create GameOfLifeCanvas
        gameOfLifeCanvas = new CellGridCanvas(gameOfLifeGrid, cellSize);

        try {
            // Make GameOfLifeCanvas a drop target
            DropTarget dt = new DropTarget( gameOfLifeCanvas, DnDConstants.ACTION_COPY_OR_MOVE, new MyDropListener() );
        } catch (NoClassDefFoundError e) {
            // Ignore. Older Java version don't support dnd
        }

        // create GameOfLifeControls
        controls = new GameOfLifeControls();
        controls.addGameOfLifeControlsListener( this );

        // put it all together
        GridBagLayout gridbag = new GridBagLayout();
        setLayout(gridbag);

        GridBagConstraints controlsContraints = new GridBagConstraints();
        GridBagConstraints canvasContraints = new GridBagConstraints();
        controlsContraints.gridx = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(controls, controlsContraints);
        canvasContraints.gridy = 0;
        add(controls);


        canvasContraints.fill = GridBagConstraints.BOTH;
        canvasContraints.weightx = 1;
        canvasContraints.weighty = 1;
        canvasContraints.gridx = GridBagConstraints.REMAINDER;
        canvasContraints.gridy = 1;
        canvasContraints.anchor = GridBagConstraints.CENTER;
        gridbag.setConstraints(gameOfLifeCanvas, canvasContraints);
        add(gameOfLifeCanvas);


        canvasContraints.gridx = GridBagConstraints.REMAINDER;

        setVisible(true);
        validate();
    }

    /**
     * Set the shape.
     *
     * This is not done in init(), because the window resize in GameOfLifeGridIO.setShape(Shape)
     * needs a fully opened window to do new size calculations.
     */
    public void readShape() {
        if ( args.length > 0 ) {
            gridIO.openShape(args[0]);
            reset();
        } else {
            try {
                setShape( ShapeCollection.getShapeByName( "Glider" ) );
            } catch (ShapeException e) {
                // Ignore. It's not going to happen here.
            }
        }
    }

    /**
     * Override method, called by applet.
     * @see java.applet.Applet#getParameter(java.lang.String)
     */
    public String getParameter( String parm ) {
        return System.getProperty( parm );
    }

    /**
     * Shows an alert
     * @param s text to show
     */
    public void alert( String s ) {
        new AlertBox( appletFrame, "Alert", s );
    }

    /**
     * Do not use showStatus() of the applet.
     * @see java.applet.Applet#showStatus(java.lang.String)
     */
    public void showStatus( String s ) {
        // do nothing
    }

    /**
     * get GameOfLifeGridIO
     * @return GameOfLifeGridIO object
     */
    protected GameOfLifeGridIO getGameOfLifeGridIO() {
        return gridIO;
    }

    /**
     * Handles drag and drops to the canvas.
     *
     * This class does handle the dropping of files and URL's to the canvas.
     * The code is based on the dnd-code from the book Professional Java Programming by Brett Spell.
     *
     * @author Edwin Martin
     *
     */
    class MyDropListener implements DropTargetListener {
        private final DataFlavor urlFlavor = new DataFlavor("application/x-java-url; class=java.net.URL", "Game of Life URL");

        /**
         * The canvas only supports Files and URL's
         * @see java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent)
         */
        public void dragEnter(DropTargetDragEvent event) {
            if ( event.isDataFlavorSupported( DataFlavor.javaFileListFlavor ) || event.isDataFlavorSupported( urlFlavor ) ) {
                return;
            }
            event.rejectDrag();
        }

        /**
         * @see java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
         */
        public void dragExit(DropTargetEvent event) {
        }

        /**
         * @see java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent)
         */
        public void dragOver(DropTargetDragEvent event) {
        }

        /**
         * @see java.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.DropTargetDragEvent)
         */
        public void dropActionChanged(DropTargetDragEvent event) {
        }

        /**
         * The file or URL has been dropped.
         * @see java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
         */
        public void drop(DropTargetDropEvent event) {
            // important to first try urlFlavor
            if ( event.isDataFlavorSupported( urlFlavor ) ) {
                try {
                    event.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable trans = event.getTransferable();
                    URL url = (URL)( trans.getTransferData( urlFlavor ) );
                    String urlStr = url.toString();
                    gridIO.openShape( url );
                    reset();
                    event.dropComplete(true);
                } catch (Exception e) {
                    event.dropComplete(false);
                }
            } else if ( event.isDataFlavorSupported( DataFlavor.javaFileListFlavor ) ) {
                try {
                    event.acceptDrop(DnDConstants.ACTION_COPY);
                    Transferable trans = event.getTransferable();
                    java.util.List list = (java.util.List)( trans.getTransferData( DataFlavor.javaFileListFlavor ) );
                    File droppedFile = (File) list.get(0); // More than one file -> get only first file
                    gridIO.openShape( droppedFile.getPath() );
                    reset();
                    event.dropComplete(true);
                } catch (Exception e) {
                    event.dropComplete(false);
                }
            }
        }
    }

    /**
     * File open and save operations for GameOfLifeGrid.
     */
    class GameOfLifeGridIO {
        public final String FILE_EXTENSION = ".cells";
        private GameOfLifeGrid grid;
        private String filename;

        /**
         * Contructor.
         * @param grid grid to read/write files from/to
         */
        public GameOfLifeGridIO( GameOfLifeGrid grid ) {
            this.grid = grid;
        }

        /**
         * Load shape from disk
         */
        public void openShape() {
            System.out.println("this is openShape-0");
            openShape( (String)null );
        }

        /**
         * Load shape from disk
         * @param filename filename to load shape from, or null when no filename given.
         */
        public void openShape( String filename ) {
            System.out.println("this is openShape-1");
            int col = 0;
            int row = 0;
            boolean cell;
            // Cope with different line endings ("\r\n", "\r", "\n")
            boolean nextLine = false;
            EasyFile file;
            try {
                if ( filename != null ) {
                    file = new EasyFile( filename );
                } else {
                    file = new EasyFile( appletFrame, "Open Game of Life file" );
                }
                openShape( file );
            } catch (FileNotFoundException e) {
                new AlertBox( appletFrame, "File not found", "Couldn't open this file.\n"+e.getMessage());
            } catch (IOException e) {
                new AlertBox( appletFrame, "File read error", "Couldn't read this file.\n"+e.getMessage());
            }
        }

        /**
         * Open shape from URL.
         * @param url URL pointing to GameOfLife-file
         */
        public void openShape( URL url ) {
            System.out.println("this is openShape-2");
            int col = 0;
            int row = 0;
            boolean cell;
            // Cope with different line endings ("\r\n", "\r", "\n")
            boolean nextLine = false;
            EasyFile file;
            String text;
            try {
                if ( url != null ) {
                    file = new EasyFile( url );
                    openShape( file );
                }
            } catch (FileNotFoundException e) {
                new AlertBox( appletFrame, "URL not found", "Couldn't open this URL.\n"+e.getMessage());
            } catch (IOException e) {
                new AlertBox( appletFrame, "URL read error", "Couldn't read this URL.\n"+e.getMessage());
            }
        }

        /**
         * Use EasyFile object to read GameOfLife-file from.
         * @param file EasyFile-object
         * @throws IOException
         * @see clu.ai.util.EasyFile
         */
        public void openShape( EasyFile file ) throws IOException {
            System.out.println("this is openShape-3");
            Shape shape = makeShape( file.getFileName(), file.readText() );
            setShape( shape );
        }

        /**
         * Set a shape and optionally resizes window.
         * @param shape Shape to set
         */
        public void setShape( Shape shape ) {
            int width, height;
            Dimension shapeDim = shape.getDimension();
            Dimension gridDim = grid.getDimension();
            if ( shapeDim.width > gridDim.width || shapeDim.height > gridDim.height ) {
                // Window has to be made larger
                Toolkit toolkit = getToolkit();
                Dimension screenDim =  toolkit.getScreenSize();
                Dimension frameDim = appletFrame.getSize();
                int cellSize = getCellSize();
                // Calculate new window size
                width = frameDim.width + cellSize*(shapeDim.width - gridDim.width);
                height = frameDim.height + cellSize*(shapeDim.height - gridDim.height);
                // Does it fit on the screen?
//                if ( width > screenDim.width || height > screenDim.height ) {
//                    // With current cellSize, it doesn't fit on the screen
//                    // GameOfLifeControls.SIZE_SMALL corresponds with GameOfLifeControls.SMALL
//                    int newCellSize = GameOfLifeControls.SIZE_SMALL;
//                    width = frameDim.width + newCellSize*shapeDim.width - cellSize*gridDim.width;
//                    height = frameDim.height + newCellSize*shapeDim.height - cellSize*gridDim.height;
//                    // a little kludge to prevent de window from resizing twice
//                    // setNewCellSize only has effect at the next resize
//                    gameOfLifeCanvas.setAfterWindowResize( shape, newCellSize );
//                    // The UI has to be adjusted, too
//                    controls.setZoom( GameOfLifeControls.SMALL );
//                } else {
//                    // Now resize the window (and optionally set the new cellSize)
//                    gameOfLifeCanvas.setAfterWindowResize( shape, cellSize );
//                }
                if ( width < 400 )
                    width = 400;
                appletFrame.setSize( width, height );
                return;
            }
            try {
                gameOfLifeCanvas.setShape( shape );
            } catch (ShapeException e) {
                // ignore
            }
        }

        /**
         * "Draw" the shape on the grid. (Okay, it's not really drawing).
         * The lines of text represent the cells of the shape.
         *
         * @param name name of shape
         * @param text lines of text
         */
        public Shape makeShape( String name, String text ) {
            System.out.println("start to clean!");
            int col = 0;
            int row = 0;
            boolean cell;
            // Cope with different line endings ("\r\n", "\r", "\n")
            int[][] cellArray;
            Vector cells = new Vector();

            if (text.length() == 0)
                return null;

            grid.clear();

            Enumeration em = new LineEnumerator(text);
            while (em.hasMoreElements()) {
                String line = (String) em.nextElement();
                if (line.startsWith("#") || line.startsWith("!"))
                    continue;

                char[] ca = line.toCharArray();
                for (col = 0; col < ca.length; col++) {
                    switch (ca[col]) {
                        case '*':
                        case 'O':
                        case 'o':
                        case 'X':
                        case 'x':
                        case '1':
                            cell = true;
                            break;
                        default:
                            cell = false;
                            break;
                    }
                    if (cell)
                        cells.addElement(new int[]{col, row});
                }
                row++;
            }

            cellArray = new int[cells.size()][];
            for (int i = 0; i < cells.size(); i++)
                cellArray[i] = (int[]) cells.get(i);
            return new Shape(name, cellArray);
        }

    }
}
