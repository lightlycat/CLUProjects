package clu.ai.gameoflife;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM3:35
 * To change this template use File | Settings | File Templates.
 */
public class Cell {
    public final short col;
    public final short row;
    /**
     * Number of neighbours of this cell.
     *
     * Determines the next state.
     */
    public byte neighbour; // Neighbour is International English

    /**
     * HASHFACTOR must be larger than the maximum number of columns (that is: the max width of a monitor in pixels).
     * It should also be smaller than 65536. (sqrt(MAXINT)).
     */
    private final int HASHFACTOR = 5000;

    /**
     * Constructor
     * @param col column of cell
     * @param row row or cell
     */
    public Cell( int col, int row ) {
        this.col = (short)col;
        this.row = (short)row;
        neighbour = 0;
    }

    /**
     * Compare cell-objects for use in hashtables
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if (!(o instanceof Cell) )
            return false;
        return col==((Cell)o).col && row==((Cell)o).row;
    }

    /**
     * Calculate hash for use in hashtables
     *
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return HASHFACTOR*row+col;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Cell at ("+col+", "+row+") with "+neighbour+" neighbour"+(neighbour==1?"":"s");
    }
}
