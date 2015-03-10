package clu.ai.gameoflife;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM3:02
 * To change this template use File | Settings | File Templates.
 */
import java.util.EventListener;

/**
 * Listener interface for GameOfLifeControls.
 * The idea behind this interface is that the controls can be replaced by something else for
 * e.g. smart phones and PDA's.
 * @see GameOfLifeControls
 * @author Edwin Martin
 */
public interface GameOfLifeControlsListener extends EventListener {
    /**
     * The Start/Stop button is clicked.
     * @param e event object
     */
    public void startStopButtonClicked( GameOfLifeControlsEvent e );

    /**
     * The Next button is clicked.
     * @param e event object
     */
    public void nextButtonClicked( GameOfLifeControlsEvent e );
    /**
     * The Save button is clicked.
     * @param e event object
     */
    public void saveButtonClicked( GameOfLifeControlsEvent e );
    /**
     * The Load button is clicked.
     * @param e event object
     */
    public void loadButtonClicked( GameOfLifeControlsEvent e );
    /**
     * The clean button is clicked.
     * @param e event object
     */
    public void cleanButtonClicked( GameOfLifeControlsEvent e );

    /**
     * A new speed is selected.
     * @param e event object
     */
    public void speedChanged( GameOfLifeControlsEvent e );

    /**
     * A new cell size is selected.
     * @param e event object
     */
    public void zoomChanged( GameOfLifeControlsEvent e );

    /**
     * A new shape is selected.
     * @param e event object
     */
    public void shapeSelected( GameOfLifeControlsEvent e );
    /**
     * A new rule is selected.
     * @param e event object
     */
    public void ruleChanged( GameOfLifeControlsEvent e );
    /**
     * A new rule is selected.
     * @param e event object
     */
    public void modeChanged( GameOfLifeControlsEvent e );
}