package clu.ai.gameoflife;

import java.awt.*;


/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/21
 * Time: PM8:49
 * To change this template use File | Settings | File Templates.
 */
public class AppletFrame extends Frame {

    protected GameOfLife applet;

    public AppletFrame(String title, StandaloneGameOfLife applet) {
        super( title );
        this.applet = applet;

        enableEvents(Event.WINDOW_DESTROY);

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints appletContraints = new GridBagConstraints();
        setLayout(gridbag);
        appletContraints.fill = GridBagConstraints.BOTH;
        appletContraints.weightx = 1;
        appletContraints.weighty = 1;
        gridbag.setConstraints(applet, appletContraints);
        setResizable(true);
        add(applet);

        Toolkit screen = getToolkit();
        Dimension screenSize = screen.getScreenSize();
        // Java in Windows opens windows in the upper left corner, which is ugly! Center instead.
        if ( screenSize.width >= 640 && screenSize.height >= 480 )
            setLocation((screenSize.width-550)/2, (screenSize.height-400)/2);
        applet.init( this );
        applet.start();
        pack();
        // Read shape after initialization
        applet.readShape();
        // Bring to front. Sometimes it stays behind other windows.
        show();
        toFront();
    }

    /**
     * Close window
     * java.awt.Component#processEvent(java.awt.AWTEvent)
     */
    public void processEvent( AWTEvent e ) {
        if ( e.getID() == Event.WINDOW_DESTROY )
            System.exit(0);
    }

}

