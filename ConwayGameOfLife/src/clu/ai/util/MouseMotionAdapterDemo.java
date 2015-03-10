package clu.ai.util;

/**
 * Created with IntelliJ IDEA.
 * User: lightlycat
 * Date: 2014/6/22
 * Time: AM11:09
 * To change this template use File | Settings | File Templates.
 */
import java.awt.*;
import java.awt.event.*;

public class MouseMotionAdapterDemo extends MouseMotionAdapter {
    Frame frame;
    Panel panel;
    Label text;

    public static void main(String[] args) {
        new MouseMotionAdapterDemo();
    }

    public MouseMotionAdapterDemo() {
        frame = new Frame("AWTDemo");
        frame.addWindowListener(new AdapterDemo());
        frame.setSize(600, 400);

        panel = new Panel();
        panel.addMouseMotionListener(this);

        text = new Label("something happened..");

        panel.add(text);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // from MouseMotionListener
    public void mouseDragged(MouseEvent e) {
        text.setText("x: " + e.getX() + ", y: " + e.getY());
        System.out.println("mouseDragged");
    }

    public void mouseMoved(MouseEvent e) {
        text.setText("x: " + e.getX() + ", y: " + e.getY());
        System.out.println("mouseMoved");
    }
}

class AdapterDemo extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}