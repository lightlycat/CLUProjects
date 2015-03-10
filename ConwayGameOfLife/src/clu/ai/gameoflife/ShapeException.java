package clu.ai.gameoflife;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM3:17
 * To change this template use File | Settings | File Templates.
 */
public class ShapeException extends Exception {
    /**
     * Constructs a ShapeException.
     */
    public ShapeException() {
        super();
    }
    /**
     * Constructs a ShapeException with a description.
     */
    public ShapeException( String s ) {
        super( s );
    }
}
