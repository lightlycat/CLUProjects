package clu.ai.gameoflife;

import javax.swing.*;
import java.awt.Dimension;
import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM3:12
 * To change this template use File | Settings | File Templates.
 */
public class GameOfLifeGrid implements CellGrid {
    private int cellRows;
    private int cellCols;
    private int generations;
    private String specRule = "Conway";
    private boolean isWrapped = false;

    /**
     * Contains the current, living shape.
     *
     */
    private Hashtable currentShape;
    private Hashtable nextShape;
    /**
     * Every cell on the grid is a Cell object. This object can become quite large.
     */
    private Cell[][] grid;

    /**
     * Contructs a GameOfLifeGrid.
     *
     * @param cellCols number of columns
     * @param cellRows number of rows
     */
    public GameOfLifeGrid(int cellCols, int cellRows) {
        this.cellCols = cellCols;
        this.cellRows = cellRows;
        currentShape = new Hashtable();
        nextShape = new Hashtable();

        grid = new Cell[cellCols][cellRows];
        for ( int c=0; c<cellCols; c++)
            for ( int r=0; r<cellRows; r++ )
                grid[c][r] = new Cell( c, r );
    }

    /**
     * Clears grid.
     */
    public synchronized void clear() {
        generations = 0;
        currentShape.clear();
        nextShape.clear();
    }

    /**
     * Create next generation of shape.
     */
    public synchronized void next() {
        Cell cell;
        int col, row;
        int neighbours;
        Enumeration e;

        //counting
        generations++;
        //prepare for saving current shape
        nextShape.clear();

        // Get the current keys
        e = currentShape.keys();
        while ( e.hasMoreElements() ) {
            cell = (Cell) e.nextElement();
            cell.neighbour = 0;
        }


        // Add neighbours
        e = currentShape.keys();
        while ( e.hasMoreElements() ) {
            cell = (Cell) e.nextElement();
            col = cell.col;
            row = cell.row;
            //upper left to down right
            addNeighbour( col-1, row-1 );
            addNeighbour( col, row-1 );
            addNeighbour( col+1, row-1 );
            addNeighbour( col-1, row );
            addNeighbour( col+1, row );
            addNeighbour( col-1, row+1 );
            addNeighbour( col, row+1 );
            addNeighbour( col+1, row+1 );
        }

//        // Kill the dead cells
//        e = currentShape.keys();
//        while ( e.hasMoreElements() ) {
//            cell = (Cell) e.nextElement();
//            // Here is the Game Of Life rule (1):
//            if ( cell.neighbour != 3 && cell.neighbour != 2 ) {
//                currentShape.remove( cell );
//            }
//        }
//        // Bring out the new borns
//        e = nextShape.keys();
//        while ( e.hasMoreElements() ) {
//            cell = (Cell) e.nextElement();
//            // Here is the Game Of Life rule (2):
//            if ( cell.neighbour == 3 ) {
//                setCell( cell.col, cell.row, true );
//            }
//        }

        runRule(specRule,e);

    }
    public void setRule(String rule) {

        specRule = rule;

    }

    public void setMode(boolean isW) {
        System.out.println("gameOfLifeGrid isWrapped:" + isW);
        isWrapped = isW;

    }
    public void runRule(String rule, Enumeration e){

        //System.out.println("Let's test different rules!!");
        Cell cell;
        if(rule.equals("Conway")){
            // Kill the dead cells
            e = currentShape.keys();
            while ( e.hasMoreElements() ) {
                cell = (Cell) e.nextElement();
                // Here is the Game Of Life rule (1):
                if ( cell.neighbour != 3 && cell.neighbour != 2 ) {
                    currentShape.remove( cell );
                }
            }
            // Bring out the new borns
            e = nextShape.keys();
            while ( e.hasMoreElements() ) {
                cell = (Cell) e.nextElement();
                // Here is the Game Of Life rule (2):
                if ( cell.neighbour == 3 ) {
                    setCell( cell.col, cell.row, true );
                }
            }

        }else if(rule.equals("Rule1")){
            // Kill the dead cells
            e = currentShape.keys();
            while ( e.hasMoreElements() ) {
                cell = (Cell) e.nextElement();
                //
                if ( cell.neighbour != 2 && cell.neighbour != 1 ) {
                    currentShape.remove( cell );
                }
            }
            // Bring out the new borns
            e = nextShape.keys();
            while ( e.hasMoreElements() ) {
                cell = (Cell) e.nextElement();
                //
                if ( cell.neighbour == 2 ) {
                    setCell( cell.col, cell.row, true );
                }
            }
        }else if(rule.equals("Rule2")){
            // Kill the dead cells
            e = currentShape.keys();
            while ( e.hasMoreElements() ) {
                cell = (Cell) e.nextElement();
                //
                if ( cell.neighbour > 5  ) {
                    currentShape.remove( cell );
                }
            }
            // Bring out the new borns
            e = nextShape.keys();
            while ( e.hasMoreElements() ) {
                cell = (Cell) e.nextElement();
                //
                if ( cell.neighbour == 3 ) {
                    setCell( cell.col, cell.row, true );
                }
            }
        }
    }
    /**
     * Adds a new neighbour to a cell.
     *
     * @param col Cell-column
     * @param row Cell-row
     */
    public synchronized void addNeighbour(int col, int row) {

        if(isWrapped == true){
            System.out.println("get into wrapped mode ");
                if(row == -1){
                    row = cellRows-1;
                    col = col%80;
                }
                if(col == 80){
                    col = col%80;
                    row = row %80;
                }
                if(col == -1){
                    col = cellCols-1;
                    row = row % 80;
                }
                if(row == 80){
                    row = row %80;
                    col = col%80;
                }
        }

        try {
            Cell cell = (Cell) nextShape.get(grid[col][row]);

            System.out.println("out f -addN : " + col + ","+row);

            if (cell == null) {
                // Cell is not in hashtable, then add it
                Cell c = grid[col][row];
                c.neighbour = 1;
                nextShape.put(c, c);
                System.out.println("f1-addN : " + col + ","+row);
            } else {
                // Else, increments neighbour count
                cell.neighbour++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }
    }

    /**
     * Get enumeration of Cell's
     *
     */
    public Enumeration getEnum() {
        return currentShape.keys();
    }

    /**
     * Get value of cell.
     * @param col x-coordinate of cell
     * @param row y-coordinate of cell
     * @return value of cell
     */
    public synchronized boolean getCell( int col, int row ) {
        try {
            return currentShape.containsKey(grid[col][row]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }
        return false;
    }

    /**
     * Set value of cell.
     * @param col x-coordinate of cell
     * @param row y-coordinate of cell
     * @param c value of cell
     */
    public synchronized void setCell( int col, int row, boolean c ) {

        System.out.println("Using isWrapped mode:" + isWrapped);

        System.out.println("let's do wrapped mode - row : " + col + "-"+row );

        try {
            Cell cell = grid[col][row];
            if (c) {
                currentShape.put(cell, cell);
            } else {
                currentShape.remove(cell);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            // ignore
        }

    }

    /**
     * save the current shape
     * @return
     */
    public void saveCurrentShape(){
        String path = "/User/lightlycat/AI/111.txt";  //Default file
        //show a pop up window for saving
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            path = fileToSave.getAbsolutePath();
        }

        try {
            File file = new File(path);
            BufferedWriter output = new BufferedWriter(new FileWriter(file));

            //Save content
            Enumeration<Cell> enumKey = currentShape.keys();
            System.out.println( "Live cells numbers: " + currentShape.size() );
            while(enumKey.hasMoreElements()) {
                Cell key = enumKey.nextElement();
                Cell val = (Cell)currentShape.get(key);
                System.out.println( val.col + "," + val.row );
                output.write(val.col + "," + val.row);
                output.newLine();
            }
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

    /**
     * Get number of generations.
     * @return number of generations
     */
    public int getGenerations() {
        return generations;
    }

    /**
     * Get dimension of grid.
     * @return dimension of grid
     */
    public Dimension getDimension() {
        return new Dimension( cellCols, cellRows );
    }

    /**
     * Resize grid. Reuse existing cells.
     *
     */
    public synchronized void resize(int cellColsNew, int cellRowsNew) {
        if ( cellCols==cellColsNew && cellRows==cellRowsNew )
            return; // Not really a resize

        // Create a new grid, reusing existing Cell's
        Cell[][] gridNew = new Cell[cellColsNew][cellRowsNew];
        for ( int c=0; c<cellColsNew; c++)
            for ( int r=0; r<cellRowsNew; r++ )
                if ( c < cellCols && r < cellRows )
                    gridNew[c][r] = grid[c][r];
                else
                    gridNew[c][r] = new Cell( c, r );

        // Copy existing shape to center of new shape
        int colOffset = (cellColsNew-cellCols)/2;
        int rowOffset = (cellRowsNew-cellRows)/2;
        Cell cell;
        Enumeration e;
        nextShape.clear();
        e = currentShape.keys();
        while ( e.hasMoreElements() ) {
            cell = (Cell) e.nextElement();
            int colNew = cell.col + colOffset;
            int rowNew = cell.row + rowOffset;
            try {
                nextShape.put( gridNew[colNew][rowNew], gridNew[colNew][rowNew] );
            } catch ( ArrayIndexOutOfBoundsException err ) {
                // ignore
            }
        }

        // Copy new grid and hashtable to working grid/hashtable
        grid = gridNew;
        currentShape.clear();
        e = nextShape.keys();
        while ( e.hasMoreElements() ) {
            cell = (Cell) e.nextElement();
            currentShape.put( cell, cell );
        }

        cellCols = cellColsNew;
        cellRows = cellRowsNew;
    }
}

