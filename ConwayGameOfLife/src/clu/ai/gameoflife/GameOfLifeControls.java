package clu.ai.gameoflife;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/20
 * Time: PM3:06
 * To change this template use File | Settings | File Templates.
 */
public class GameOfLifeControls extends Panel {
    private Label genLabel;
    private final String genLabelText = "Counting: ";
    private final String nextLabelText = "One Step";
    private final String startLabelText = "Start";
    private final String stopLabelText = "Stop";

//    public static final String SLOW = "Slow";
//    public static final String FAST = "Fast";
//    public static final String HYPER = "Hyper";
//    public static final String BIG = "Big";
//    public static final String MEDIUM = "Medium";
//    public static final String SMALL = "Small";
//    public static final int SIZE_BIG = 11;
//    public static final int SIZE_MEDIUM = 7;
//    public static final int SIZE_SMALL = 3;
    public static final String CONWAY = "Conways";
    public static final String RULE1 = "Rule1";
    public static final String RULE2 = "Rule2";
    private Button startstopButton;
    private Button nextButton;

    private Vector listeners;
    private Choice shapesChoice;
    private Choice zoomChoice;
    //clean a grid
    private Choice ruleChoice;
    private Button cleanButton;
    private Button loadButton;
    private Button saveButton;
    private JRadioButton toroidal;
    private final String cleanLabelText = "Clean";
    private final String loadLabelText = "Load";
    private final String saveLabelText = "Save";


    /**
     * Contructs the controls.
     */
    public GameOfLifeControls() {
        listeners = new Vector();

        // pulldown menu with shapes
        shapesChoice = new Choice();

        // Put names of shapes in menu
        Shape[] shapes = ShapeCollection.getShapes();
        for ( int i = 0; i < shapes.length; i++ )
            shapesChoice.addItem( shapes[i].getName() );

        // when shape is selected
        shapesChoice.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        shapeSelected( (String) e.getItem() );
                    }
                }
        );
        //Setting different rules for Game of Life
        ruleChoice = new Choice();

        // add rules
        ruleChoice.addItem(CONWAY);
        ruleChoice.addItem(RULE1);
        ruleChoice.addItem(RULE2);

        // when item is selected
        //
        ruleChoice.addItemListener(
                new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        String arg = (String) e.getItem();
                        if (CONWAY.equals(arg))
                            ruleChanged(CONWAY);
                        else if (RULE1.equals(arg))
                            ruleChanged(RULE1);
                        else if (RULE2.equals(arg))
                            ruleChanged(RULE2);
                    }
                }
        );


        // pulldown menu with speeds
//        Choice speedChoice = new Choice();
//
//        // add speeds
//        speedChoice.addItem(SLOW);
//        speedChoice.addItem(FAST);
//        speedChoice.addItem(HYPER);
//
//        // when item is selected
//        speedChoice.addItemListener(
//                new ItemListener() {
//                    public void itemStateChanged(ItemEvent e) {
//                        String arg = (String) e.getItem();
//                        if (SLOW.equals(arg)) // slow
//                            speedChanged(1000);
//                        else if (FAST.equals(arg)) // fast
//                            speedChanged(100);
//                        else if (HYPER.equals(arg)) // hyperspeed
//                            speedChanged(10);
//                    }
//                }
//        );

        // pulldown menu with speeds
//        zoomChoice = new Choice();
//
//        // add speeds
//        zoomChoice.addItem(BIG);
//        zoomChoice.addItem(MEDIUM);
//        zoomChoice.addItem(SMALL);
//
//        // when item is selected
//        zoomChoice.addItemListener(
//                new ItemListener() {
//                    public void itemStateChanged(ItemEvent e) {
//                        String arg = (String) e.getItem();
//                        if (BIG.equals(arg))
//                            zoomChanged(SIZE_BIG);
//                        else if (MEDIUM.equals(arg))
//                            zoomChanged(SIZE_MEDIUM);
//                        else if (SMALL.equals(arg))
//                            zoomChanged(SIZE_SMALL);
//                    }
//                }
//        );

        // load and save buttom
        loadButton = new Button(loadLabelText);
        saveButton = new Button(saveLabelText);
        toroidal = new JRadioButton("Wrapped");
        toroidal.setForeground(Color.white);
        toroidal.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event)
                    {
                        if(toroidal.isSelected()) {
                            modeChanged("wrapped");
                        }else{
                            modeChanged("No WRapped!");
                        }
                    }
                }
        );
        // number of generations
        genLabel = new Label(genLabelText+"         ");
        genLabel.setForeground(Color.white);

        // start and stop buttom
        startstopButton = new Button(startLabelText);

        // when start/stop button is clicked
        startstopButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        startStopButtonClicked();
                    }
                }
        );

        // next generation button
        nextButton = new Button(nextLabelText);
        nextButton.setForeground(Color.blue);
        // clean generation button
        cleanButton = new Button(cleanLabelText);
        cleanButton.setForeground(Color.red);

        // when next button is clicked
        nextButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        nextButtonClicked();
                    }
                }
        );
        // when clean button is clicked
        cleanButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cleanButtonClicked();
                    }
                }
        );
        saveButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        saveButtonClicked();
                    }
                }
        );
        loadButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loadButtonClicked();
                    }
                }
        );


        // create panel with controls
        //this.add(shapesChoice);
        //this.add(speedChoice);
        //this.add(zoomChoice);
        this.add(ruleChoice);
        this.add(loadButton);
        this.add(saveButton);
        this.add(toroidal);
        this.add(startstopButton);
        this.add(nextButton);
        this.add(cleanButton);
        this.add(genLabel);
        this.validate();
    }


    /**
     * Add listener for this control
     * @param listener Listener object
     */
    public void addGameOfLifeControlsListener( GameOfLifeControlsListener listener ) {
        listeners.addElement( listener );
    }

    /**
     * Remove listener from this control
     * @param listener Listener object
     */
    public void removeGameOfLifeControlsListener( GameOfLifeControlsListener listener ) {
        listeners.removeElement( listener );
    }

    /**
     * Set the number of generations in the control bar.
     * @param generations number of generations
     */
    public void setGeneration( int generations ) {
        genLabel.setText(genLabelText + generations + "         ");
    }

    /**
     * Start-button is activated.
     */
    public void start() {
        startstopButton.setLabel(stopLabelText);
        nextButton.disable();
        shapesChoice.disable();
    }

    /**
     * Stop-button is activated.
     */
    public void stop() {
        startstopButton.setLabel(startLabelText);
        nextButton.enable();
        shapesChoice.enable();
    }

    /**
     * Called when the start/stop-button is clicked.
     * Notify event-listeners.
     */
    public void startStopButtonClicked() {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent( this );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )
            ((GameOfLifeControlsListener) e.nextElement()).startStopButtonClicked(event);
    }

    /**
     * Called when the next-button is clicked.
     * Notify event-listeners.
     */
    public void nextButtonClicked() {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent( this );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).nextButtonClicked(event);
        }
    }
    /**
     * Called when the clean-button is clicked.
     * Notify event-listeners.
     */
    public void cleanButtonClicked() {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent( this );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).cleanButtonClicked(event);
        }

    }
    /**
     * Called when the save-button is clicked.
     * Notify event-listeners.
     *
     */
    public void saveButtonClicked() {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent( this );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).saveButtonClicked(event);
        }
    }
    /**
     * Called when the load-button is clicked.
     * Notify event-listeners.
     *
     */
    public void loadButtonClicked() {
        GameOfLifeControlsEvent event = new GameOfLifeControlsEvent( this );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).loadButtonClicked(event);
        }
    }
    /**
     * Called when a new speed from the speed pull down is selected.
     * Notify event-listeners.
     */
    public void speedChanged( int speed ) {
        GameOfLifeControlsEvent event = GameOfLifeControlsEvent.getSpeedChangedEvent( this, speed );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).speedChanged(event);
        }
    }
    /**
     * Called when a new rule from the rule pull down is selected.
     * Notify event-listeners.
     */
    public void ruleChanged(String rule ) {
        GameOfLifeControlsEvent event = GameOfLifeControlsEvent.getRuleChangedEvent( this, rule );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).ruleChanged(event);
        }
    }
    /**
     * Called when a new mode from the rule pull down is selected.
     * Notify event-listeners.
     */
    public void modeChanged(String w) {
        GameOfLifeControlsEvent event = GameOfLifeControlsEvent.getModeChangedEvent(this,w);
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).modeChanged(event);
        }
    }
    /**
     * Called when a new zoom from the zoom pull down is selected.
     * Notify event-listeners.
     */
    public void zoomChanged( int zoom ) {
        GameOfLifeControlsEvent event = GameOfLifeControlsEvent.getZoomChangedEvent( this, zoom );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).zoomChanged(event);
        }
    }

    /**
     * Called when a new shape from the shape pull down is selected.
     * Notify event-listeners.
     */
    public void shapeSelected( String shapeName ) {
        GameOfLifeControlsEvent event = GameOfLifeControlsEvent.getShapeSelectedEvent( this, shapeName );
        for ( Enumeration e = listeners.elements(); e.hasMoreElements(); )

        {
            ((GameOfLifeControlsListener) e.nextElement()).shapeSelected(event);
        }
    }

    /**
     * Called when a new cell size from the zoom pull down is selected.
     * Notify event-listeners.
     */
    public void setZoom( String n ) {
        zoomChoice.select(n);
    }


}
