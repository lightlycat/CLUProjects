package clu.ai.gameoflife;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM3:04
 * To change this template use File | Settings | File Templates.
 */
import java.awt.Event;


public class GameOfLifeControlsEvent extends Event {
    private int speed;
    private int zoom;
    private String shapeName;
    private String ruleName;

    public boolean isWrapped() {
        return isWrapped;
    }

    public void setWrapped(boolean wrapped) {
        isWrapped = wrapped;
    }

    private boolean isWrapped = false;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public static GameOfLifeControlsEvent getModeChangedEvent( Object source, String w) {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent(source);
        if(w.equals("wrapped")){
            event.setWrapped(true);
        }else{
            event.setWrapped(false);
        }

        return event;
    }
    /**
     * Constructs a event due to the speed changed.
     * @param source source of the event
     * @param rule new rule
     * @return new event object
     */
    public static GameOfLifeControlsEvent getRuleChangedEvent( Object source, String rule ) {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent(source);
        event.setRuleName(rule);
        return event;
    }
    /**
     * Construct a GameOfLifeControls.ControlsEvent
     * @param source source of event
     */
    public GameOfLifeControlsEvent(Object source) {
        super(source, 0, null);
    }

    /**
     * Constructs a event due to the speed changed.
     * @param source source of the event
     * @param speed new speed
     * @return new event object
     */
    public static GameOfLifeControlsEvent getSpeedChangedEvent( Object source, int speed ) {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent(source);
        event.setSpeed(speed);
        return event;
    }

    /**
     * Constructs a event due to the zoom changed.
     * @param source source of the event
     * @param zoom new zoom (cell size in pixels)
     * @return new event object
     */
    public static GameOfLifeControlsEvent getZoomChangedEvent( Object source, int zoom ) {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent(source);
        event.setZoom(zoom);
        return event;
    }

    /**
     * Constructs a event due to the shape changed.
     * @param source source of the event
     * @param shapeName name of selected shape
     * @return new event object
     */
    public static GameOfLifeControlsEvent getShapeSelectedEvent( Object source, String shapeName ) {
        //System.out.println("getShapeSelectedEvent");
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent(source);
        event.setShapeName(shapeName);
        return event;
    }

    /**
     * Gets speed of Game
     * @return speed (10 is fast, 1000 is slow)
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets speed of Game
     * @param speed (10 is fast, 1000 is slow)
     */
    public void setSpeed( int speed ) {
        this.speed = speed;
    }

    /**
     * Gets size of cell
     * @return speed (10 is big, 2 is small)
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * Sets zoom of Game
     * @param zoom size of cell in pixels
     */
    public void setZoom( int zoom ) {
        this.zoom = zoom;
    }

    /**
     * Gets name of shape
     * @return name of selected shape
     */
    public String getShapeName() {
        return shapeName;
    }

    /**
     * Sets name of shape
     * @param shapeName name of shape
     */
    public void setShapeName( String shapeName ) {
        //System.out.println("setShapeName");
        this.shapeName = shapeName;
    }

}