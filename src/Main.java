import javax.swing.*;
import java.awt.*;

public class Main {
    private JFrame fNL = new JFrame("Лабораторная работа №4");
    private JPanel sP, cP = centrePanel();
    private JButton[] buttons;
    private int interfaceWight = 1100, interfaceHight = 600, buttonKey = 0;

    private JPanel circle = null; //buttonkey == 1
    private Circle[] circles = null; //buttonkey == 11
    private JPanel ellipse = null; //buttonkey == 2
    private Ellipse[] ellipses = null; //buttonkey == 12

    private JPanel square = null; //buttonkey == 4
    private Square[] squares = null; //buttonkey == 14
    private JPanel rectangle = null; //buttonkey == 5
    private Rectangle[] rectangles = null; //buttonkey == 15
    private JPanel romb = null; //buttonkey == 6
    private Romb[] rombs = null; //buttonkey == 16
    private JPanel trapeze = null; //buttonkey == 7
    private Trapeze[] trapezes = null; //buttonkey == 17

    private JPanel line = null; //buttonkey == 8
    private Line[] lines = null; //buttonkey == 18
    private JPanel triangle = null; //buttonkey == 9
    private Triangle[] triangles = null; //buttonkey == 19

    private boolean visionCircle, visionCircles, visionEllipse, visionEllipses, visionRing, visionRings;
    private boolean visionSquare, visionSquares, visionRectangle, visionRectangles, visionTrapeze, visionTrapezes, visionRomb, visionRombs;
    private boolean visionLine, visionLines, visionTriangle, visionTriangles;

    private int cntCircles = 10, cntEllipses = 10, cntRings = 10;
    private int cntSquares = 10, cntRectangles = 10, cntRoms = 10, cntTrapezes = 10;
    private int cntLines = 10, cntTriangles = 10;

    private int a, b, c, d, e, f;


    private Main() {
        buttons = new JButton[]{
                new JButton("Назад"), //0
                new JButton("Круг"), //1
                new JButton("Овал"), //2
                new JButton("Кольцо"), //3
                new JButton("Квадрат"), //4
                new JButton("Прямоугольник"), //5
                new JButton("Ромб"), //6
                new JButton("Трапеция"), //7
                new JButton("Линия"), //8
                new JButton("Треугольник"), //9
                new JButton("Создать"), //10
                new JButton("Переместить"), //11
                new JButton("Повернуть на 45"), //12
                new JButton("Изменить радиус"), //13
                new JButton("Изменить размер"), //14
                new JButton("Сделать видимым/невидимым"), //15
                new JButton("Удалить"), //16
                new JButton("Массив") //17
        };
        sP = southPanel();

        //создаем основное окно
        fNL.setLayout(new BorderLayout());
        fNL.setSize(1200,700);
        fNL.add(cP, BorderLayout.CENTER);
        fNL.add(sP, BorderLayout.SOUTH);
        fNL.setResizable(false);
        fNL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fNL.setVisible(true);

        visionCircle = visionCircles = visionEllipses = visionEllipse = visionRings = visionRing = true;
        visionLine = visionLines = visionTriangle = visionTriangles = true;
        visionSquare = visionSquares = visionRectangle = visionRectangles = visionTrapeze = visionTrapezes = visionRomb = visionRombs = true;
    }

    //холст
    private JPanel centrePanel() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(Color.WHITE);
        return p;
    }

    //стартовая панель с кнопками
    private JPanel southPanel() {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setBackground(Color.LIGHT_GRAY);
        for (int i = 1; i <= 9; i++) {
            p.add(buttons[i]);
        }
        p.remove(buttons[3]);

        //вернуться назад
        buttons[0].addActionListener(e -> back());

        //окружность
        buttons[1].addActionListener(e -> createPanel(1, new int[] {0,10,11,13,15,16,17}));
        //овал
        buttons[2].addActionListener(e -> createPanel(2, new int[] {0,10,11,13,15,16,17}));

        //квадрат
        buttons[4].addActionListener(e -> createPanel(4, new int[] {0,10,11,14,15,16,17}));
        //прямоугольник
        buttons[5].addActionListener(e -> createPanel(5, new int[] {0,10,11,14,15,16,17}));
        //ромб
        buttons[6].addActionListener(e -> createPanel(6, new int[] {0,10,11,14,15,16,17}));
        //трапеция
        buttons[7].addActionListener(e -> createPanel(7, new int[] {0,10,11,14,15,16,17}));

        //линия
        buttons[8].addActionListener(e -> createPanel(8, new int[] {0,10,11,12,15,16,17}));
        //треугольник
        buttons[9].addActionListener(e -> createPanel(9, new int[] {0,10,11,12,15,16,17}));

        //создание геометрического примитива и рисование его на холсте
        buttons[10].addActionListener(e -> createObject());
        //перемещение примитива в пределах холста
        buttons[11].addActionListener(e -> moveToObject());
        //вращение объектов: ЛИНИЯ и ТРЕУГОЛЬНИК
        buttons[12].addActionListener(e -> rotationObject());
        //изменение радиуса: ОКРУЖНОСТЬ, ОВАЛ
        buttons[13].addActionListener(e -> changeRadius());
        //изменение размера: КВАДРАТ, ПРЯМОУГОЛЬНИК, РОМБ, ТРАПЕЦИЯ, ТРЕУГОЛЬНИК
        buttons[14].addActionListener(e -> changeSize());
        //изменение видимости: ДЛЯ ВСЕХ ФИГУР
        buttons[15].addActionListener(e -> changeVisiability());
        //удаление объекта/ов: ДЛЯ ВСЕХ ФИГУР
        buttons[16].addActionListener(e -> deleteObject());
        //работа с массивом: ДЛЯ ВСЕХ ОБЪЕКТОВ
        buttons[17].addActionListener(e -> arrayObjects());

        return p;
    }

    //панель с кнопками при работе с массивом объектов
    private void arrayObjects() {
        sP.removeAll();
        JOptionPane.showMessageDialog(fNL, "Следующие действия будут выполнены для массива объектов");
        int[] numbers = new int[]{0,10,11,15,16};
        int[] buttonMapping = {0, 13, 13, 13, 14, 14, 14, 14, 12, 12};
        for (int number: numbers){
            sP.add(buttons[number]);
        }
        if (buttonKey >= 1 && buttonKey <= 9) {
            sP.add(buttons[buttonMapping[buttonKey]]);
            buttonKey += 10;
        } else{ System.out.println("Error!!!!");}
        sP.revalidate();
        sP.repaint();
    }
    private void back() {
        if (buttonKey >= 1 && buttonKey <= 9) {
            sP.removeAll();
            for (int i = 1; i <= 9; i++) { sP.add(buttons[i]); }
            sP.remove(buttons[3]);
        }
        if (buttonKey >= 11 && buttonKey <= 19) {
            if (buttonKey == 11) { buttonKey = 1; }
            if (buttonKey == 12) { buttonKey = 2; }
            if (buttonKey == 14) { buttonKey = 4; }
            if (buttonKey == 15) { buttonKey = 5; }
            if (buttonKey == 16) { buttonKey = 6; }
            if (buttonKey == 17) { buttonKey = 7; }
            if (buttonKey == 18) { buttonKey = 8;}
            if (buttonKey == 19) { buttonKey = 9; }
            sP.add(buttons[17]);
        }
        sP.revalidate();
        sP.repaint();
    }

    private void deleteObject() {
        if (buttonKey == 1){
            if (circle != null){
                cP.remove(circle);
                circle = null;
            }
        } else if(buttonKey == 11){
            if (circles != null){
                for (int i=0; i<cntCircles; i++) {
                    cP.remove(circles[i]);
                }
                circles = null;
            }
        } else if(buttonKey == 2){
            if(ellipse != null){
                cP.remove(ellipse);
                ellipse = null;
            }
        } else if (buttonKey == 12){
            if (ellipses != null){
                for (int i=0; i<cntEllipses; i++) {
                    cP.remove(ellipses[i]);
                }
                ellipses = null;
            }
        } else if(buttonKey == 4){
            if(square != null){
                cP.remove(square);
                square = null;
            }
        } else if (buttonKey == 14){
            if (squares != null){
                for (int i=0; i<cntSquares; i++) {
                    cP.remove(squares[i]);
                }
                squares = null;
            }
        } else if(buttonKey == 5){
            if(rectangle != null){
                cP.remove(rectangle);
                rectangle = null;
            }
        } else if (buttonKey == 15){
            if (rectangles != null){
                for (int i=0; i<cntRectangles; i++) {
                    cP.remove(rectangles[i]);
                }
                rectangles = null;
            }
        } else if(buttonKey == 6){
            if(romb != null){
                cP.remove(romb);
                romb = null;
            }
        } else if (buttonKey == 16){
            if (rombs != null){
                for (int i=0; i<cntRoms; i++) {
                    cP.remove(rombs[i]);
                }
                rombs = null;
            }
        } else if(buttonKey == 7){
            if(trapeze != null){
                cP.remove(trapeze);
                trapeze = null;
            }
        } else if (buttonKey == 17){
            if (trapezes != null){
                for (int i=0; i<cntTrapezes; i++) {
                    cP.remove(trapezes[i]);
                }
                trapezes = null;
            }
        } else if(buttonKey == 8){
            if(line != null){
                cP.remove(line);
                line = null;
            }
        } else if (buttonKey == 18){
            if (lines != null){
                for (int i=0; i<cntLines; i++) {
                    cP.remove(lines[i]);
                }
                lines = null;
            }
        } else if(buttonKey == 9){
            if(triangle != null){
                cP.remove(triangle);
                triangle = null;
            }
        } else if (buttonKey == 19){
            if (triangles != null){
                for (int i=0; i<cntTriangles; i++) {
                    cP.remove(triangles[i]);
                }
                triangles = null;
            }
        }

        cP.revalidate();
        cP.repaint();
    }

    private void changeVisiability() {
        if (buttonKey == 1){
            if (circle != null){
                visionCircle = !visionCircle;
                ((Circle) circle).Show(visionCircle);
                cP.revalidate(); cP.repaint();
            } else { System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 11){
            if (circles != null){
                visionCircles = !visionCircles;
                for (int i=0; i<cntCircles; i++) {
                    circles[i].Show(visionCircles);
                    cP.repaint();
                }
                cP.revalidate();
            } else { System.out.println("Фигур данного типа нет на холсте.");}
        } else if(buttonKey == 2){
            if(ellipse != null){
                visionEllipse = !visionEllipse;
                ((Ellipse) ellipse).Show(visionEllipse);
                cP.revalidate(); cP.repaint();
            } else { System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 12){
            if (ellipses != null){
                visionEllipses = !visionEllipses;
                for (int i=0; i<cntEllipses; i++) {
                    ellipses[i].Show(visionEllipses);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 4){
            if(square != null){
                visionSquare = !visionSquare;
                ((Square) square).Show(visionSquare);
                cP.revalidate(); cP.repaint();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 14){
            if (squares != null){
                visionSquares = !visionSquares;
                for (int i=0; i<cntSquares; i++) {
                    squares[i].Show(visionSquares);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 5){
            if(rectangle != null){
                visionRectangle = !visionRectangle;
                ((Rectangle) rectangle).Show(visionRectangle);
                cP.revalidate(); cP.repaint();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 15){
            if (rectangles != null){
                visionRectangles = !visionRectangles;
                for (int i=0; i<cntRectangles; i++) {
                    rectangles[i].Show(visionRectangles);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 6){
            if(romb != null){
                visionRomb = !visionRomb;
                ((Romb) romb).Show(visionRomb);
                cP.revalidate(); cP.repaint();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 16){
            if (rombs != null){
                visionRombs = !visionRombs;
                for (int i=0; i<cntRoms; i++) {
                    rombs[i].Show(visionRomb);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 7){
            if(trapeze != null){
                visionTrapeze = !visionTrapeze;
                ((Trapeze) trapeze).Show(visionTrapeze);
                cP.revalidate(); cP.repaint();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 17){
            if (trapezes != null){
                visionTrapezes = !visionTrapezes;
                for (int i=0; i<cntTrapezes; i++) {
                    trapezes[i].Show(visionTrapezes);
                    cP.repaint();
                }
                cP.revalidate();
            } else{ System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 8){
            if(line != null){
                visionLine = !visionLine;
                ((Line) line).Show(visionLine);
                cP.revalidate(); cP.repaint();
            } else {System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 18){
            if (lines != null){
                visionLines = !visionLines;
                for (int i=0; i<cntLines; i++) {
                    lines[i].Show(visionLines);
                    cP.repaint();
                }
                cP.revalidate();
            } else {System.out.println("Фигур данного типа нет на холсте."); }
        } else if(buttonKey == 9){
            if(triangle != null){
                visionTriangle = !visionTriangle;
                ((Triangle) triangle).Show(visionTriangle);
                cP.revalidate(); cP.repaint();
            } else {System.out.println("Фигур данного типа нет на холсте."); }
        } else if (buttonKey == 19){
            if (triangles != null){
                visionTriangles = !visionTriangles;
                for (int i=0; i<cntTriangles; i++) {
                    triangles[i].Show(visionTriangles);
                    cP.repaint();
                }
                cP.revalidate();
            } else {System.out.println("Фигур данного типа нет на холсте."); }
        }
    }

    private void changeSize() {
        int x = (int) (Math.random() * 200) - 10;
        int y = (int) (Math.random() * 100) - 10;
        if (buttonKey == 5) {
            if (rectangle != null) { ((Rectangle) rectangle).chSize(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден"); }
        }
        else if (buttonKey == 15) {
            if (rectangles != null) {
                for (int i = 0; i < cntRectangles; i++) { rectangles[i].chSize(x, y); }
            } else { JOptionPane.showMessageDialog(fNL, "Массив прямоугольников не создан"); }
        }
        else if (buttonKey == 4) {
            if (square != null) { ((Square) square).chSize(x); }
            else { JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден"); }
        }
        else if (buttonKey == 14) {
            if (squares != null) {
                for (int i = 0; i < cntSquares; i++) { squares[i].chSize(x); }
            } else { JOptionPane.showMessageDialog(fNL, "Массив прямоугольников не создан"); }
        }
        if (buttonKey == 6) {
            if (romb != null) { ((Romb) romb).chSize(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Ромб не найден"); }
        }
        else if (buttonKey == 16) {
            if (rombs != null) {
                for (int i = 0; i < cntRoms; i++) { rombs[i].chSize(x, y); }
            } else { JOptionPane.showMessageDialog(fNL, "Массив ромбов не создан"); }
        }
        if (buttonKey == 7) {
            if (trapeze != null) { ((Trapeze) trapeze).chSize(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Трапеция не найден"); }
        }
        else if (buttonKey == 17) {
            if (trapezes != null) {
                for (int i = 0; i < cntTrapezes; i++) { trapezes[i].chSize(x, y);}
            } else { JOptionPane.showMessageDialog(fNL, "Массив трапеций не создан"); }
        }
        cP.revalidate();
        cP.repaint();
    }

    private void changeRadius()  {
        int x = 10 + (int) (Math.random() * 150);
        if (buttonKey == 1) {
            if (circle != null) { ((Circle) circle).chengeR(x); }
            else { JOptionPane.showMessageDialog(fNL, "Окружность не найдена"); }
        }
        else if (buttonKey == 11) {
            if (circles != null) {
                for (int i = 0; i < cntCircles; i++) {
                    circles[i].chengeR(x);
                    x = 10 + (int) (Math.random() * 150);
                }
            } else { JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден"); }
        } if (buttonKey == 2) {
            int y = 10 + (int) (Math.random() * 150);
            if (ellipse != null) { ((Ellipse) ellipse).chengeD(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Овал не найден"); }
        }
        else if (buttonKey == 12) {
            if (ellipses != null) {
                int y = 10 + (int) (Math.random() * 150);
                for (int i = 0; i < cntEllipses; i++) {
                    ellipses[i].chengeD(x, y);
                    x = 10 + (int) (Math.random() * 150);
                    y = 10 + (int) (Math.random() * 150);

                }
            } else { JOptionPane.showMessageDialog(fNL, "Массив овалов не найден"); }
        }
        cP.revalidate();
        cP.repaint();
    }

    private void rotationObject()  {
        if (buttonKey == 8) {
            if (line != null) { ((Line) line).rotate(); }
            else { JOptionPane.showMessageDialog(fNL, "Линия не найдена"); }
        }
        else if (buttonKey == 18) {
            if (lines != null) {
                for (int i = 0; i < cntLines; i++) { lines[i].rotate(); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив не создан"); }
        }
        else if (buttonKey == 9) {
            if (triangle != null) { ((Triangle) triangle).rotate(); }
            else { JOptionPane.showMessageDialog(fNL, "Треугольник не найден"); }
        }
        else if (buttonKey == 19) {
            if (triangles != null) {
                for (int i = 0; i < cntTriangles; i++) { triangles[i].rotate();}
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив треугольников не создан"); }
        }
        cP.revalidate();
        cP.repaint();
    }

    private void moveToObject()  {

        int x = (int) (Math.random() * 600) - 300;
        int y = (int) (Math.random() * 600) - 300;

        if (buttonKey == 1){
            if (circle != null) { ((Circle) circle).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Окружность не найдена"); }
        } else if (buttonKey == 11) {
            if (circles != null) {
                for (int i=0; i<cntCircles; i++) { circles[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив окружностей не найден"); }
        } else if(buttonKey == 2){
            if (ellipse != null) { ((Ellipse) ellipse).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Овал не найден"); }
        } else if (buttonKey == 12) {
            if (ellipses != null) {
                for (int i=0; i<cntEllipses; i++) { ellipses[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив овал не найден"); }
        } else if(buttonKey == 4){
            if (square != null) { ((Square) square).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Квадрат не найден"); }
        } else if (buttonKey == 14) {
            if (squares != null) {
                for (int i=0; i<cntSquares; i++) { squares[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив квадратов не найден"); }
        } else if (buttonKey == 5) {
            if (rectangle != null) { ((Rectangle) rectangle).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Прямоугольник не найден"); }
        } else if (buttonKey == 15) {
            if (rectangles != null) {
                for (int i=0; i<cntRectangles; i++) { rectangles[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив прямоугольников не найден"); }
        } else if (buttonKey == 6) {
            if (romb != null) { ((Romb) romb).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Ромб не найден"); }
        } else if (buttonKey == 16) {
            if (rombs != null) {
                for (int i=0; i<cntRoms; i++) { rombs[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив ромбов не найден"); }
        } else if (buttonKey == 7) {
            if (trapeze != null) { ((Trapeze) trapeze).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Трапеция не найдена"); }
        } else if (buttonKey == 17) {
            if (trapezes != null) {
                for (int i=0; i<cntTrapezes; i++) { trapezes[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив трапеций не найден"); }
        } else if (buttonKey == 8) {
            if (line != null) { ((Line) line).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Линия не найдена"); }
        } else if (buttonKey == 18) {
            if (lines != null) {
                for (int i=0; i<cntLines; i++) { lines[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив линий не найден");}
        } else if (buttonKey == 9) {
            if (triangle != null) { ((Triangle) triangle).MoveTo(x, y); }
            else { JOptionPane.showMessageDialog(fNL, "Треугольник не найден"); }
        } else if (buttonKey == 19) {
            if (triangles != null) {
                for (int i=0; i<cntTriangles; i++) { triangles[i].MoveTo(x, y); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Массив Треугольников не найден"); }
        }
        cP.repaint();
        cP.revalidate();
    }

    private void createPanel(int key, int[] numbers) {
        sP.removeAll();
        for (int number: numbers){ sP.add(buttons[number]); }
        sP.revalidate();
        sP.repaint();
        buttonKey = key;
    }

    private void createObject(){
        int tag;
        if (buttonKey >= 1 && buttonKey <=9){
            try{
                tag = Integer.parseInt(JOptionPane.showInputDialog("Хотите ввести параметры вручную? (0-нет, 1-да)"));
                if (tag != 0 && tag != 1) {
                    JOptionPane.showMessageDialog(null, "Ошибка: Введите 0 или 1.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (tag == 1){ create(); }
                    else { createRandom(); }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ошибка: некорректный ввод.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else{ createRandom(); }
    }

    /*
    Для создания фигуры возможен ручной и компьютерный ввод
    Пример компьютерного ввода абсолютно для всех можно посмотреть в ЛР 1.1 и ЛР 2.1
    В следующих лабораторных работах для экономии памяти будет оставлен ручной ввод
    ТОЛЬКО ДЛЯ КРУГА и массива кругов, остающиеся фигуры будут создаваться при помощи случайных чисел
    */

    private void create() {
        if (buttonKey == 1){
            if (circle == null){
                int x  = Integer.parseInt(JOptionPane.showInputDialog(""));
                if (check(x, 1)){
                    int y = Integer.parseInt(JOptionPane.showInputDialog(""));
                    if (check(y, 2)){
                        int r = Integer.parseInt(JOptionPane.showInputDialog(""));
                        if (check(y+r, 2) && check(x+r, 1)){
                            circle = new Circle(x,y,r, Color.BLACK);
                            cP.add(circle, BorderLayout.CENTER);
                            cP.revalidate();
                        }
                        else { JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры."); }
                    }
                    else { JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры."); }
                }
                else { JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры."); }
            }
            else { JOptionPane.showMessageDialog(fNL, "Некорректный ввод: выход за пределы холста при создании фигуры."); }
        } else if(buttonKey == 11) {
            JOptionPane.showMessageDialog(fNL, "Фигуры будут созданы по сгенерированным значениям.");
            cntCircles = Integer.parseInt(JOptionPane.showInputDialog("Сколько фигур необходимо создать?"));
            if (cntCircles <= 0){
                JOptionPane.showMessageDialog(fNL, "Некорректный ввод. Будет создано 10 фигур.");
                cntCircles = 10;
            }
            createRandom();
        } else {
            JOptionPane.showMessageDialog(fNL, "!!!Создание будет по сгенерированным числам!!!");
            createRandom();
        }
    }

    private boolean check(int a, int key) {
        if (key == 1) {
            if (a <= 0 || a >= 1100) {
                return false;
            }
        } else if (key == 2) {
            if (a <= 0 || a >= 600) {
                return false;
            }
        } else {
            System.out.println("Передан некорректный ключ символа. Проверка не была произведена.");
            return false;
        }
        return true;
    }

    private void createRandom() {
        a = (int) (Math.random() * 300) + 1;
        b = (int) (Math.random() * 300) + 1;
        c = (int) (Math.random() * 300) + 1;
        d = (int) (Math.random() * 300) + 1;
        e = (int) (Math.random() * 300) + 1;
        f = (int) (Math.random() * 300) + 1;
        if (buttonKey == 1){
            if(circle == null){
                circle = new Circle(a, b, c, Color.BLACK);
                cP.add(circle,BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Окружность уже нарисована"); }
        } else if(buttonKey == 11){
            if(circles == null){
                circles = new Circle[cntCircles];
                for (int i = 0; i < cntCircles; i++){
                    circles[i] = new Circle(a, b, c, Color.BLUE);
                    cP.add(circles[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив окружностей уже создан"); }
        } else if (buttonKey == 2){
            if(ellipse == null){
                ellipse = new Ellipse(a, b, c, d, Color.GRAY);
                cP.add(ellipse,BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Овал уже нарисована"); }
        } else if(buttonKey == 12){
            if(ellipses == null){
                ellipses = new Ellipse[cntEllipses];
                for (int i = 0; i < cntEllipses; i++){
                    ellipses[i] = new Ellipse(a, b, c, d, Color.BLUE);
                    cP.add(ellipses[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    d = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив овалов уже создан"); }
        } else if (buttonKey == 4){
            if(square == null){
                square = new Square(a, b, c);
                cP.add(square, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Квадрат уже нарисована"); }
        } else if(buttonKey == 14){
            if(squares == null){
                squares = new Square[cntSquares];
                for (int i = 0; i < cntSquares; i++){
                    squares[i] = new Square(a, b, c);
                    cP.add(squares[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив квадратов уже создан"); }
        } else if (buttonKey == 5){
            if(rectangle == null){
                rectangle = new Rectangle(a, b, c, e);
                cP.add(rectangle, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Прямоугольник уже нарисована"); }
        } else if(buttonKey == 15){
            if(rectangles == null){
                rectangles = new Rectangle[cntRectangles];
                for (int i = 0; i < cntRectangles; i++){
                    rectangles[i] = new Rectangle(a, b, c, e);
                    cP.add(rectangles[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    e = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив прямоугольников уже создан"); }
        } else if (buttonKey == 6){
            if(romb == null){
                romb = new Romb(a, b, c, e);
                cP.add(romb, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Ромб уже нарисован"); }
        } else if(buttonKey == 16){
            if(rombs == null){
                rombs = new Romb[cntRoms];
                for (int i = 0; i < cntRoms; i++){
                    rombs[i] = new Romb(a, b, c, e);
                    cP.add(rombs[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    e = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив ромбов уже создан"); }
        } else if (buttonKey == 7){
            if(trapeze == null){
                trapeze = new Trapeze(a, b, c, d, e, f);
                cP.add(trapeze, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Трапеция уже нарисована"); }
        } else if(buttonKey == 17){
            if(trapezes == null){
                trapezes = new Trapeze[cntTrapezes];
                for (int i = 0; i < cntTrapezes; i++){
                    trapezes[i] = new Trapeze(a, b, c, d, e, f);
                    cP.add(trapezes[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    d = (int) (Math.random() * 300) + 1;
                    e = (int) (Math.random() * 300) + 1;
                    f = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив трапеций уже создан"); }
        } else if (buttonKey == 8){
            if(line == null){
                line = new Line(a, b, c, e);
                cP.add(line, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Линия уже нарисована"); }
        } else if(buttonKey == 18){
            if(lines == null){
                lines = new Line[cntLines];
                for (int i = 0; i < cntLines; i++){
                    lines[i] = new Line(a, b, c, e);
                    cP.add(lines[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    e = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив линий уже создан"); }
        } else if(buttonKey == 9){
            if(triangle == null){
                triangle = new Triangle(a, b, c, d, e, f);
                cP.add(triangle, BorderLayout.CENTER);
            }
            else{ JOptionPane.showMessageDialog(fNL, "Треугольник уже нарисована"); }
        } else if(buttonKey == 19){
            if(triangles == null){
                triangles = new Triangle[cntTriangles];
                for (int i = 0; i < cntTriangles; i++){
                    triangles[i] = new Triangle(a, b, c, d, e, f);
                    cP.add(triangles[i], BorderLayout.CENTER);
                    a = (int) (Math.random() * 300) + 1;
                    b = (int) (Math.random() * 300) + 1;
                    c = (int) (Math.random() * 300) + 1;
                    e = (int) (Math.random() * 300) + 1;
                    d = (int) (Math.random() * 300) + 1;
                    f = (int) (Math.random() * 300) + 1;
                    cP.validate();
                    cP.repaint();
                }
            }
            else{ JOptionPane.showMessageDialog(fNL, "Массив треугольников уже создан"); }
        }
        cP.validate();
        cP.repaint();
        cP.revalidate();
    }

    public static void main (String[] argc) {
        SwingUtilities.invokeLater(Main::new);
    }


}