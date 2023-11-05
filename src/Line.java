import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Line extends TFigure {

    private Point O2 = new Point();
    private Color color;
    private boolean VISION = true;
    private int interfaceWidth = 1000, interfaceHeight = 500;


    public Line(int x1, int y1, int x2, int y2, Color c) {
        super(x1, x2);
        O2.setXY(x2,y2);
        color = c;
        this.tagFigure = 2;
        System.out.println("Объект линия(Line) создан");
    }

    public Line(int x1, int y1, int x2, int y2) {
        super(x1, x2);
        O2.setXY(x2,y2);
        color = Color.BLACK;
        this.tagFigure = 2;
        System.out.println("Объект линия(Line) создан");
    }

    public void Show(boolean VISION) {
        this.VISION= VISION;
        this.repaint();
    }

    public void rotate() {
        double c1, c2;
        c1 = (O2.getX() - super.getPointX()) * Math.cos(Math.toRadians(45.0)) - (O2.getY() - super.getPointY()) * Math.sin(Math.toRadians(45.0)) + super.getPointX();
        c2 = (O2.getX() - super.getPointX()) * Math.sin(Math.toRadians(45.0)) + (O2.getY() - super.getPointY()) * Math.cos(Math.toRadians(45.0)) + super.getPointY();
        if (c1 >= interfaceWidth || c2 >= interfaceHeight || c1 <= 0 || c2 <= 0) {
            c1 = (super.getPointX() - O2.getX()) * Math.cos(Math.toRadians(45.0)) - (super.getPointY()- O2.getY()) * Math.sin(Math.toRadians(45.0)) + O2.getX();
            c2 = (super.getPointX() - O2.getX()) * Math.sin(Math.toRadians(45.0)) + (super.getPointY() - O2.getY()) * Math.cos(Math.toRadians(45.0)) + O2.getY();
            JOptionPane.showMessageDialog(null, "Turn the other way", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        O2.setXY((int)c1,(int)c2);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (VISION) {
            g.setColor(color);
            g.drawLine(super.getPointX(), super.getPointY(), O2.getX(), O2.getY());
        }
    }
}