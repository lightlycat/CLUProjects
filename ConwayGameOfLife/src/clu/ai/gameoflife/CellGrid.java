package clu.ai.gameoflife;

import java.awt.Dimension;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM3:08
 * To change this template use File | Settings | File Templates.
 */
public interface CellGrid {
    /**
     * Get status of cell (alive or dead).
     * @param col x-position
     * @param row y-position
     * @return living or not
     */
    public boolean getCell( int col, int row );

    /**
     * Set status of cell (alive or dead).
     * @param col x-position
     * @param row y-position
     * @param cell living or not
     */
    public void setCell( int col, int row, boolean cell );

    /**
     * Get dimension of cellgrid.
     * @return dimension
     */
    public Dimension getDimension();

    /**
     * Resize the cell grid.
     * @param col new number of columns.
     * @param row new number of rows.
     */
    public void resize( int col, int row );

    /**
     * Get cell-enumerator. Enumerates over all living cells (type Cell).
     * @return Enumerator over Cell.
     * @see Cell
     */
    public Enumeration getEnum();

    /**
     * Clears grid.
     */
    public void clear();
}
