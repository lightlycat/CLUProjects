package clu.ai.gameoflife;

import javax.swing.*;
import java.applet.Applet;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM1:22
 * Create a grid and function buttons
 */


    public class GameOfLife extends Applet implements Runnable, GameOfLifeControlsListener {
        protected CellGridCanvas gameOfLifeCanvas;
        protected GameOfLifeGrid gameOfLifeGrid;
        protected int cellSize;
        protected int cellCols;
        protected int cellRows;
        protected int genTime;
        protected GameOfLifeControls controls;
        protected static Thread gameThread = null;


        /**
         * Initialize UI.
         * @see java.applet.Applet#init()
         */
        public void init() {
            //set cell size, col, row
            getParams();

            // set background colour
            setBackground(new Color(0x999999));

            // create gameOfLifeGrid
            gameOfLifeGrid = new GameOfLifeGrid(cellCols, cellRows);
            gameOfLifeGrid.clear();

            // create gameOfLifeCanvas
            gameOfLifeCanvas = new CellGridCanvas(gameOfLifeGrid, cellSize);

            // create GameOfLifeControls
            controls = new GameOfLifeControls();
            controls.addGameOfLifeControlsListener( this );

            // put it all together
            GridBagLayout gridbag = new GridBagLayout();
            setLayout(gridbag);
            GridBagConstraints gameOfLifeCanvasContraints = new GridBagConstraints();

            GridBagConstraints controlsContraints = new GridBagConstraints();
            gameOfLifeCanvasContraints.gridy = 1;
            gameOfLifeCanvasContraints.gridx = 0;
            controlsContraints.gridx = GridBagConstraints.REMAINDER;
            gridbag.setConstraints(controls, controlsContraints);
            add(controls);

            gameOfLifeCanvasContraints.fill = GridBagConstraints.BOTH;
            gameOfLifeCanvasContraints.gridx = GridBagConstraints.REMAINDER;
            gameOfLifeCanvasContraints.gridy = 0;
            gameOfLifeCanvasContraints.weightx = 1;
            gameOfLifeCanvasContraints.weighty = 1;
            gameOfLifeCanvasContraints.anchor = GridBagConstraints.CENTER;
            gridbag.setConstraints(gameOfLifeCanvas, gameOfLifeCanvasContraints);
            add(gameOfLifeCanvas);



            try {
                // Start with a shape (My girlfriend clicked "Start" on a blank screen and wondered why nothing happened).
                setShape( ShapeCollection.getShapeByName( "Glider" ) );
            } catch (ShapeException e) {
                // Ignore. Not going to happen.
            }
            setVisible(true);
            validate();
        }

        /**
         * Get params (cellSize, cellCols, cellRows, genTime) from applet-tag
         */
        protected void getParams() {
            cellSize = getParamInteger( "cellsize", 11 );
            cellCols = getParamInteger( "cellcols", 80 );
            cellRows = getParamInteger( "cellrows", 80 );
            genTime  = getParamInteger( "gentime", 100 );
        }

        /**
         * Read applet parameter (int) or, when unavailable, get default value.
         * @param name name of parameter
         * @param defaultParam default when parameter is unavailable
         * @return value of parameter
         */
        protected int getParamInteger( String name, int defaultParam ) {
            String param;
            int paramInt;

            param = getParameter( name );
            if ( param == null )
                paramInt = defaultParam;
            else
                paramInt = Integer.valueOf(param).intValue();
            return paramInt;
        }

        /**
         * Starts creating new generations.
         * No start() to prevent starting immediately.
         */
        public synchronized void start2() {
            controls.start();
            if (gameThread == null) {
                gameThread = new Thread(this);
                gameThread.start();
            }
        }

        /**
         * @see java.applet.Applet#stop()
         */
        public void stop() {
            controls.stop();
            gameThread = null;
        }

        /**
         * @see java.lang.Runnable#run()
         */
        public synchronized void run() {
            while (gameThread != null) {
                nextGeneration(); //run conway's rule
                try {
                    Thread.sleep(genTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Is the applet running
         * @return true: applet is running
         */
        public boolean isRunning() {
            return gameThread != null;
        }

        /**
         * Run Conway's rule
         */
        public void nextGeneration() {
            gameOfLifeGrid.next();   //live, death
            gameOfLifeCanvas.repaint();
            showGenerations();
        }

        /**
         * Set the new shape
         * @param shape name of shape
         */
        public void setShape( Shape shape ) {
            System.out.println(shape.getName());
            if ( shape == null )
                return;

            try {
                gameOfLifeCanvas.setShape( shape );
                reset();
            } catch (ShapeException e) {
                alert( e.getMessage() );
            }
        }

        /**
         * Resets applet (after loading new shape)
         */
        public void reset() {
            stop(); // might otherwise confuse user
            gameOfLifeCanvas.repaint();
            showGenerations();
            showStatus( "" );
        }

        /**
         * @see java.applet.Applet#getAppletInfo()
         */
        public String getAppletInfo() {
            return "Game Of Life v. 1.5\nCopyright 1996-2004 Edwin Martin";
        }

        /**
         * Show number of generations.
         */
        private void showGenerations() {
            controls.setGeneration( gameOfLifeGrid.getGenerations() );
        }
        /**
         * Set rule of new generations.
         * @param rule generations per second
         */
        public void setRule( String rule ) {
            gameOfLifeGrid.setRule(rule);
        }
        /**
         * Set speed of new generations.
         * @param fps generations per second
         */
        public void setSpeed( int fps ) {
            genTime = fps;
        }
        /**
         * Set mode of wrapped
         *
         */
        public void setWrappedMode(boolean isW) {
            gameOfLifeGrid.setMode(isW);

        }
        /**
         * Sets cell size.
         * @param p size of cell in pixels
         */
        public void setCellSize( int p ) {
            cellSize = p;
            gameOfLifeCanvas.setCellSize( cellSize );
        }

        /**
         * Gets cell size.
         * @return size of cell
         */
        public int getCellSize() {
            return cellSize;
        }

        /**
         * Shows an alert
         * @param s text to show
         */
        public void alert( String s ) {
            showStatus( s );
        }

        /** Callback from GameOfLifeControlsListener
         *
         */
        public void startStopButtonClicked( GameOfLifeControlsEvent e ) {
            if ( isRunning() ) {
                stop();
            } else {
                start2();
            }
        }

        /** Callback from GameOfLifeControlsListener
         *
         */
        public void saveButtonClicked(GameOfLifeControlsEvent e) {
            gameOfLifeGrid.saveCurrentShape();
        }
        /** Callback from GameOfLifeControlsListener
         *
         */
        public void loadButtonClicked(GameOfLifeControlsEvent e) {
            loadSavedShape();
        }
        /**
         * load the saved shape
         * @return
         */
        public void loadSavedShape(){
            String path = "/User/lightlycat/AI/111.txt";  //Default file
            String fileName ;
            //show a pop up window for saving
            JFrame parentFrame = new JFrame();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to open");

            int userSelection = fileChooser.showOpenDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();
                fileName = fileToOpen.getName();
                path = fileToOpen.getPath();
            }
            System.out.println( "open file: " + path );
            FileInputStream fis = null;
            BufferedReader br = null;
            String dis = null;
            //Read content in a selected file
            try {
                fis = new FileInputStream(path);
                // Here BufferedInputStream is added for fast reading.
                br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
                ArrayList<int[]> tmp = new ArrayList<int[]>();
                while ((dis = br.readLine()) != null) {

                    // this statement reads the line from the file and print it to
                    // the console.
                    String[] adis =   dis.split(",");
                    System.out.println(adis[0] + "," + adis[1]  );
                    int[] disa = new int[2];

                    disa[0] = (Integer.parseInt(adis[0])); //X
                    disa[1] = (Integer.parseInt(adis[1])); //Y
                    tmp.add(disa);


                }

                //close file reading
                fis.close();
                br.close();
                int[][] loadshape = new int[tmp.size()][];

                for(int i = 0; i < tmp.size(); i++){
                    loadshape[i] = tmp.get(i);
                    System.out.println("x-y:" + loadshape[i][0] + "," + loadshape[i][1] );
                }

                //set up loading file
                Shape s = new Shape("fileName", loadshape);

                //Paint
                try {
                    gameOfLifeCanvas.setLoadedShape( s );
                    reset();
                } catch (ShapeException e) {
                    alert( e.getMessage() );
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        /** Callback from GameOfLifeControlsListener
         *
         */
        public void nextButtonClicked(GameOfLifeControlsEvent e) {
            nextGeneration();
        }
        /** Callback from GameOfLifeControlsListener
         *
         */
        public void cleanButtonClicked(GameOfLifeControlsEvent e) {
            Shape shape;
            try {
                shape = ShapeCollection.getShapeByName("Clear");
                setShape(shape);
            } catch (ShapeException e1) {

            }
        }
        /** Callback from GameOfLifeControlsListener
         *
         */
        public void speedChanged(GameOfLifeControlsEvent e) {
            setSpeed( e.getSpeed() );
        }
        /** Callback from GameOfLifeControlsListener
        *
        */
        public void ruleChanged(GameOfLifeControlsEvent e) {

            setRule(e.getRuleName());
        }
        /** Callback from GameOfLifeControlsListener
         *
         */
        public void modeChanged(GameOfLifeControlsEvent e) {

            setWrappedMode(e.isWrapped());
        }
        //modeChanged
        /** Callback from GameOfLifeControlsListener
         *
         */
        public void zoomChanged(GameOfLifeControlsEvent e) {
            setCellSize( e.getZoom() );
        }

        /** Callback from GameOfLifeControlsListener
         *
         */
        public void shapeSelected(GameOfLifeControlsEvent e) {
            String shapeName = (String) e.getShapeName();
            Shape shape;
            try {
                shape = ShapeCollection.getShapeByName( shapeName );
                setShape( shape );
            } catch (ShapeException e1) {
                // Ignore. Not going to happen.
            }
        }
        /**
        *  Solve wrapped model: take care negative issue
        */
        private static int mod(int x,int base){
            int ans =x%base;
            if (x>=0)
                return ans;
            else
                return base + ans;
        }
    }


