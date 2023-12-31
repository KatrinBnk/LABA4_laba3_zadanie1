import javax.swing.*;

abstract class TFigure extends JPanel {

    private Point point = new Point();
    protected int tagFigure;

    /*
    код фигуры:
    0 - точка
    1 - окружность
    2 - линия
    3 - треугольник
    4 - четырехугольник
     */
    protected int x1, y1, x2, y2, x3, y3, x4, y4, r1, r2;
    protected boolean VISION = true;
    protected int interfaceWidth = 1000, interfaceHeight = 500;


    public TFigure(int x, int y) {
        point.setXY(x,y); x1 = x; y1 = y;
        setLayout(null);
        setOpaque(false);
        System.out.println("Координаты TFigure инициализированы");
    }

    abstract void Show(boolean VISION);

    protected void MoveTo(int dx, int dy){
        this.Show(false);
        if(tagFigure == 0){
            if(check(0,dx,dy) ){
                point.setXY(x1 + dx, y1 + dy); x1 += dx; y1 += dy;
                System.out.println("Точка перемещена на новые координаты: (" + x1 + "; " + y1+ ").");
            } else if(check(0,-dx,-dy) ){
                point.setXY(x1 - dx, y1 - dy); x1 -= dx; y1 -= dy;
                System.out.println("Точка перемещена на новые координаты: (" + x1 + "; " + y1+ ").");
            } else if(check(0,dx,-dy) ){
                point.setXY(x1 + dx, y1 - dy); x1 += dx; y1 -= dy;
                System.out.println("Точка перемещена на новые координаты: (" + x1 + "; " + y1+ ").");
            } else if(check(0,-dx,dy) ){
                point.setXY(x1 - dx, y1 + dy); x1 -= dx; y1 += dy;
                System.out.println("Точка перемещена на новые координаты: (" + x1 + "; " + y1+ ").");
            } else{ System.out.println("Перемещение не выполнено: выход за пределы холста."); }
        } else if (tagFigure == 1) {
            if(check(1,dx,dy)){
                point.setXY(x1 + dx, y1 + dy); x1 += dx; y1 += dy;
                System.out.println("Окружность перемещена на новые координаты: (" + x1 + "; " + y1 + ").");
            } else if (check(1, -dx, -dy)) {
                point.setXY(x1 - dx, y1 - dy); x1 -= dx; y1 -= dy;
                System.out.println("Окружность перемещена на новые координаты: (" + x1 + "; " + y1 + ").");
            } else if (check(1, dx, -dy)) {
                point.setXY(x1 + dx, y1 - dy); x1 += dx; y1 -= dy;
                System.out.println("Окружность перемещена на новые координаты: (" + x1 + "; " + y1 + ").");
            } else if (check(1, -dx, dy)) {
                point.setXY(x1 - dx, y1 + dy); x1 -= dx; y1 += dy;
                System.out.println("Окружность перемещена на новые координаты: (" + x1 + "; " + y1 + ").");
            } else { System.out.println("Перемещение не выполнено: выход за пределы холста."); }
        } else if (tagFigure == 2){
            if(check(2,dx,dy)){
                point.setXY(x1 + dx, y1 + dy); x1 += dx; y1 += dy;
                x2 += dx; y2 +=dy;
                System.out.println("Линия перемещена на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" );
            } else if (check(1, -dx, -dy)) {
                point.setXY(x1 - dx, y1 - dy); x1 -= dx; y1 -= dy;
                x2 -= dx; y2 -=dy;
                System.out.println("Линия перемещена на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" );
            } else if (check(1, dx, -dy)) {
                point.setXY(x1 + dx, y1 - dy); x1 += dx; y1 -= dy;
                x2 += dx; y2 -=dy;
                System.out.println("Линия перемещена на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" );
            } else if (check(1, -dx, dy)) {
                point.setXY(x1 - dx, y1 + dy); x1 -= dx; y1 += dy;
                x2 -= dx; y2 +=dy;
                System.out.println("Линия перемещена на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" );
            } else{ System.out.println("Перемещение не выполнено: выход за пределы холста."); }
        } else if (tagFigure == 3) {
            if(check(3,dx,dy)){
                point.setXY(x1 + dx, y1 + dy); x1 += dx; y1 += dy;
                x2 += dx; y2 +=dy; x3 += dx; y3 += dy;
                System.out.println("Треугольник перемещен на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" + "); (" + x3 + ", " + y3 + ")" );
            } else if (check(1, -dx, -dy)) {
                point.setXY(x1 - dx, y1 - dy); x1 -= dx; y1 -= dy;
                x2 -= dx; y2 -=dy; x3 -= dx; y3 -= dy;
                System.out.println("Треугольник перемещен на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" + "); (" + x3 + ", " + y3 + ")" );
            } else if (check(1, dx, -dy)) {
                point.setXY(x1 + dx, y1 - dy); x1 += dx; y1 -= dy;
                x2 += dx; y2 -=dy; x3 += dx; y3 -= dy;
                System.out.println("Треугольник перемещен на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" + "); (" + x3 + ", " + y3 + ")" );
            } else if (check(1, -dx, dy)) {
                point.setXY(x1 - dx, y1 + dy); x1 -= dx; y1 += dy;
                x2 -= dx; y2 +=dy; x3 -= dx; y3 += dy;
                System.out.println("Треугольник перемещен на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" + "); (" + x3 + ", " + y3 + ")" );
            } else{ System.out.println("Перемещение не выполнено: выход за пределы холста."); }
        } else if (tagFigure == 4) {
            if(check(4,dx,dy)){
                point.setXY(x1 + dx, y1 + dy); x1 += dx; y1 += dy;
                x2 += dx; y2 +=dy; x3 += dx; y3 += dy; x4 += dx; y4 +=dy;
                System.out.println("Четырехугольник перемещен на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" + "); (" + x3 + ", " + y3 + "); (" + x4 + ", " + y4 + ")" );
            } else if (check(1, -dx, -dy)) {
                point.setXY(x1 - dx, y1 - dy); x1 -= dx; y1 -= dy;
                x2 -= dx; y2 -=dy; x3 -= dx; y3 -= dy; x4 -= dx; y4 -=dy;
                System.out.println("Четырехугольник перемещен на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" + "); (" + x3 + ", " + y3 + "); (" + x4 + ", " + y4 + ")" );
            } else if (check(1, dx, -dy)) {
                point.setXY(x1 + dx, y1 - dy); x1 += dx; y1 -= dy;
                x2 += dx; y2 -=dy; x3 += dx; y3 -= dy; x4 += dx; y4 -=dy;
                System.out.println("Четырехугольник перемещен на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" + "); (" + x3 + ", " + y3 + "); (" + x4 + ", " + y4 + ")" );
            } else if (check(1, -dx, dy)) {
                point.setXY(x1 - dx, y1 + dy); x1 -= dx; y1 += dy;
                x2 -= dx; y2 +=dy; x3 -= dx; y3 += dy; x4 -= dx; y4 +=dy;
                System.out.println("Четырехугольник перемещен на новые координаты: (" + x1 + ", " + y1 + "); (" + x2 + ", " + y2 + ")" + "); (" + x3 + ", " + y3 + "); (" + x4 + ", " + y4 + ")" );
            } else{ System.out.println("Перемещение не выполнено: выход за пределы холста."); }
        } else if (tagFigure == 5) {
            if(check(1,dx,dy)){
                point.setXY(x1 + dx, y1 + dy); x1 += dx; y1 += dy;
                System.out.println("Кольцо перемещено на новые координаты: (" + x1 + "; " + y1 + ").");
            } else if (check(1, -dx, -dy)) {
                point.setXY(x1 - dx, y1 - dy); x1 -= dx; y1 -= dy;
                System.out.println("Кольцо перемещено на новые координаты: (" + x1 + "; " + y1 + ").");
            } else if (check(1, dx, -dy)) {
                point.setXY(x1 + dx, y1 - dy); x1 += dx; y1 -= dy;
                System.out.println("Кольцо перемещено на новые координаты: (" + x1 + "; " + y1 + ").");
            } else if (check(1, -dx, dy)) {
                point.setXY(x1 - dx, y1 + dy); x1 -= dx; y1 += dy;
                System.out.println("Кольцо перемещено на новые координаты: (" + x1 + "; " + y1 + ").");
            } else { System.out.println("Перемещение не выполнено: выход за пределы холста."); }
        } else { System.out.println("Перемещение не выполнено: передан неверный код фигуры."); }
        this.Show(true);
    }


    protected void setPointXY(int x, int y){
        point.setXY(x,y);
    }
    protected int getPointX() {
        return point.getX();
    }
    protected int getPointY() {
        return point.getY();
    }

    private boolean check(int cntPoint, int dx, int dy){
        if (cntPoint == 0) {
            return (x1 + dx > 0 && x1 + dx < interfaceWidth && y1 + dy > 0 && y1 + dy < interfaceHeight );
        }else if (cntPoint == 1){
            return (x1 + dx - r1 > 0 && x1 + dx + r1 < interfaceWidth && y1 + dy - r2 > 0 && y1 + dy + r2 < interfaceHeight);
        } else if (cntPoint == 2) {
            return (x1 + dx > 0 && x1 + dx < interfaceWidth && y1 + dy > 0 && y1 + dy < interfaceHeight &&
                    x2 + dx > 0 && x2 + dx < interfaceWidth && y2 + dy > 0 && y2 + dy < interfaceHeight);
        } else if (cntPoint == 3) {
            return (x1 + dx > 0 && x1 + dx < interfaceWidth && y1 + dy > 0 && y1 + dy < interfaceHeight &&
                    x2 + dx > 0 && x2 + dx < interfaceWidth && y2 + dy > 0 && y2 + dy < interfaceHeight &&
                    x3 + dx > 0 && x3 + dx < interfaceWidth && y3 + dy > 0 && y3 + dy < interfaceHeight);
        } else if (cntPoint == 4) {
            return (x1 + dx > 0 && x1 + dx < interfaceWidth && y1 + dy > 0 && y1 + dy < interfaceHeight &&
                    x2 + dx > 0 && x2 + dx < interfaceWidth && y2 + dy > 0 && y2 + dy < interfaceHeight &&
                    x3 + dx > 0 && x3 + dx < interfaceWidth && y3 + dy > 0 && y3 + dy < interfaceHeight &&
                    x4 + dx > 0 && x4 + dx < interfaceWidth && y4 + dy > 0 && y4 + dy < interfaceHeight);
        }
        return false;
    }
}
