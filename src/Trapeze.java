import java.awt.*;

public class Trapeze extends Square {
    /*
    Для построения трапеции будем запрашивать у пользователя две точки,
    которые являются началами каждого из оснований, а так же
    длинны оснований w1 и w2
    дополнительно см рис в приложении к лабораторной
    */
    private int w1, w2, x1, x4, y1, y4;

    public Trapeze(int x1, int y1, int x4, int y4, int w1, int w2, Color color) {
        super(x1, y1, x1 + w1, y1, x4 + w2, y4, x4, y4, color, false);
        x1 = super.getXi(0); y1 = super.getYi(1); x4 = super.getXi(5); y1 = super.getYi(6);
        w1 = super.getXi(2) - super.getXi(0);
        w2 = super.getYi(3) - super.getYi(6);
        this.w1 = w1; this.w2 = w2; this.x1 = x1; this.x4 = x4; this.y1 = y1; this.y4 = y4;
        System.out.println("Трапеция. x1 = " + x1 + ", y1 = " + y1 + ", w1 = " + w1 + ", x4 = " + x4 + ", y4 = " + y4 + ", w2 = " + w2);
        System.out.println("Объект Trapeze создан");
    }

    public Trapeze(int x1, int y1, int x4, int y4, int w1, int w2) {
        super(x1, y1, x1 + w1, y1, x4 + w2, y4, x4, y4, Color.BLACK, false);
        x1 = super.getXi(0); y1 = super.getYi(1); x4 = super.getXi(5); y1 = super.getYi(6);
        w1 = super.getXi(2) - super.getXi(0);
        w2 = super.getYi(3) - super.getYi(6);
        this.w1 = w1; this.w2 = w2; this.x1 = x1; this.x4 = x4; this.y1 = y1; this.y4 = y4;
        System.out.println("Трапеция. x1 = " + x1 + ", y1 = " + y1 + ", w1 = " + w1 + ", x4 = " + x4 + ", y4 = " + y4 + ", w2 = " + w2);
        System.out.println("Объект Trapeze создан");
    }

    public void chSize(int dw, int dw2) {
        x1 = coordinates[0]; y1 = coordinates[1]; x4 = coordinates[6]; y4 = coordinates[7];
        w1 = super.getXi(2) - super.getXi(0);
        w2 = super.getYi(3) - super.getYi(7);
        int testw1 = w1, testw2 = w2;
        if (testw1 + dw > 0 && testw2 + dw2 > 0 && testw1 + dw < interfaceWidth && testw2 + dw2 < interfaceWidth){
            this.w1 += dw;
            this.w2 += dw2;
            super.sets(x1, y1, x1 + w1, y1, x4 + w2, y4, x4, y4);
            System.out.println("Изменен размер трапеции: w1 = " + w1 + ", w2 = " + w2);
        } else if (testw1 - dw > 0 && testw2 - dw2 > 0 && testw1 - dw < interfaceWidth && testw2 - dw2 < interfaceWidth){
            this.w1 -= dw;
            this.w2 -= dw2;
            super.sets(x1, y1, x1 + w1, y1, x4 + w2, y4, x4, y4);
            System.out.println("Изменен размер трапеции: w1 = " + w1 + ", w2 = " + w2);
        } else if (testw1 + dw > 0 && testw2 - dw2 > 0 && testw1 + dw < interfaceWidth && testw2 - dw2 < interfaceWidth){
            this.w1 += dw;
            this.w2 -= dw2;
            super.sets(x1, y1, x1 + w1, y1, x4 + w2, y4, x4, y4);
            System.out.println("Изменен размер трапеции: w1 = " + w1 + ", w2 = " + w2);
        } else if (testw1 - dw > 0 && testw2 + dw2 > 0 && testw1 - dw < interfaceWidth && testw2 + dw2 < interfaceWidth){
            this.w1 -= dw;
            this.w2 += dw2;
            super.sets(x1, y1, x1 + w1, y1, x4 + w2, y4, x4, y4);
            System.out.println("Изменен размер трапеции: w1 = " + w1 + ", w2 = " + w2);
        } else{
            this.w1 = 5;
            this.w2 = 10;
            super.sets(x1, y1, x1 + w1, y1, x4 + w2, y4, x4, y4);
            System.out.println("Изменен размер трапеции: w1 = " + w1 + ", w2 = " + w2);
        }
    }

}