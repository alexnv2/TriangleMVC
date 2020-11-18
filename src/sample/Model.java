package sample;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.LinkedList;
import java.util.List;
import static java.lang.StrictMath.*;
import static java.lang.StrictMath.abs;

class Model implements  Observable {

    public List<Observer>  observers;
    private Circle vertex;
    private Line sideAll;
    private Text textGo;
    private Line colorLine;
    private String ColorGo;
    private double verX;
    private double verY;
    private double verX1;
    private double verY1;
    private double dx,dy;//смещение букв

Model() {
    observers = new LinkedList<>();
}
//регистрация слушателя
    @Override
    public void registerObserver(Observer o) {
    observers.add(o);
    }
//уведомление слушателя
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.notification(message);
        }
    }
    //Нахождение середины отрезка
    private double midpoint(double x0, double x1) {
        return (x0 + x1) / 2;
    }
    //Растояние между точками (координаты x1,y1,x2,y2)
    private double distance(double x1, double y1, double x2, double y2) {
        return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
    }
    //Точка пересечения бессекрисы со стороной
    private void bisector(double x1,double y1, double x2, double y2, double x3, double y3) {
        double ab = distance(x1, y1, x2, y2);
        double ac = distance(x3, y3, x2, y2);
        double ra = ab / ac;
        setVerX((x1 + ra * x3) / (1 + ra));
        setVerY((y1 + ra * y3) / (1 + ra));
    }
    //Точка пересечения двух прямых под 90 градусов
    private void intersection(double x10, double y10, double x11, double y11, double x12, double y12) {
        double a1 = y12 - y11;
        double b1 = x11 - x12;
        double c1 = x11 * y12 - x12 * y11;
        double c2 = -x10 * (x12 - x11) + y10 * (y11 - y12);
        //Вычисление главного определителя
        double o = -pow(a1, 2) - pow(b1, 2);
            setVerX((-c1 * a1 - c2 * b1)/o);
            setVerY((a1 * c2 - b1 * c1)/o);
    }
    //Точка пересечения серединных перпендикуляров
    private void middlePerpendicular(double x1,double y1,double x2,double y2,double x3,double y3) {
        double smAx = midpoint(x2, x3);
        double smAy = midpoint(y2, y3);
        double smCx = midpoint(x1, x2);
        double smCy = midpoint(y1, y2);
        double sxAB = x1 - x2;
        double syAB = -(y2 - y1);
        double sAB = smCx * sxAB + smCy * syAB;
        double sxBC = x2 - x3;
        double syBC = y2 - y3;
        double sBC = smAx * sxBC + smAy * syBC;
        double d = sxAB * syBC - sxBC * syAB;
        double dx = sAB * syBC - sBC * syAB;
        double dy = sxAB * sBC - sxBC * sAB;
        setVerX((dx / d));
        setVerY((dy / d));
    }
    //Площадь треугольника
    private double areaTriangle(double x1, double y1, double x2, double y2, double x3, double y3){
    return ((x2-x1)*(y3-y1)-(x3-x1)*(y2-y1))/2;
    }
    //Радиус вписанной окружности
    private double radiusInCircle(double x1, double y1, double x2, double y2, double x3, double y3){
    double s=areaTriangle(x1, y1, x2, y2,x3,y3);
    double ab=distance(x1,y1,x2,y2);
    double ac=distance(x1,y1,x3,y3);
    double bc=distance(x2,y2,x3,y3);
    double p=abs((ab+ac+bc)/2);
    return abs(s/p);
    }
    //Координаты центра вписанной окружности
    private void  inCircleX(double x1, double y1, double x2, double y2, double x3, double y3){
        double ab=distance(x1,y1,x2,y2);
        double ac=distance(x1,y1,x3,y3);
        double bc=distance(x2,y2,x3,y3);
        setVerX((bc*x1+ac*x2+ab*x3)/(ab+bc+ac));
    }
    private void  inCircleY(double x1, double y1, double x2, double y2, double x3, double y3){
        double ab=distance(x1,y1,x2,y2);
        double ac=distance(x1,y1,x3,y3);
        double bc=distance(x2,y2,x3,y3);
        setVerY((bc*y1+ac*y2+ab*y3)/(ab+bc+ac));
    }
    //Радиус описанной окружности
    private  double radiusOutCircle(double x1, double y1, double x2, double y2, double x3, double y3){
        double ab=distance(x1,y1,x2,y2);
        double ac=distance(x1,y1,x3,y3);
        double bc=distance(x2,y2,x3,y3);
        double s= areaTriangle(x1, y1, x2, y2, x3, y3);
        return abs((ab*bc*ac)/(s*4));
    }
    //Стороны треугольника
    public void sideAll(Circle o1, Circle o2, Circle o3, Line l1, Line l2, Text t){
    //Угол
    System.out.println(angleTriangle(o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY()));
    //Вот сюда добавить метод для расчета смещения букв от вершины
        mestopolojenie(o1,o2,o3);
        VertexGo(o1);
        TextGo(t);
        setVerX1(o2.getCenterX());
        setVerY1(o2.getCenterY());
        SideGo(l1);
        setVerX1(o3.getCenterX());
        setVerY1(o3.getCenterY());
        SideGo(l2);
    }
    //Нахождение углов в треугольнике АВС координаты А, В, С
    private double angleTriangle(double x1, double y1, double x2, double y2, double x3, double y3){
    double ab=distance(x1,y1,x2,y2);
    double ac=distance(x1,y1,x3,y3);
    double bc=distance(x2,y2,x3,y3);
    return toDegrees(acos((pow(ab,2)+pow(ac,2)-pow(bc,2))/(2*ab*ac)));

    }

    private void mestopolojenie(Circle o1, Circle o2, Circle o3){
    double dx1=o1.getCenterX()-o2.getCenterX();
    double dx2=o1.getCenterX()-o3.getCenterX();
    double dy1=o1.getCenterY()-o2.getCenterY();
    double dy2=o1.getCenterY()-o3.getCenterY();
     if(dx1<0 ){
        setDx(-20);
    }else {
        setDx(20);
    }
        if(dy1<0) {
          setDy(-20);
    }else {
            setDy(20);
        }

}

    //Медианы
    public void median(Circle o1,Circle o2, Circle o3, Circle o4, Line l, Text t){
        setVerX(midpoint(o1.getCenterX(),o2.getCenterX()));
        setVerY(midpoint(o1.getCenterY(),o2.getCenterY()));
        VertexGo(o4);
        mestopolojenie(o1,o2,o3);
        TextGo(t);
        setVerX1(o3.getCenterX());
        setVerY1(o3.getCenterY());
        SideGo(l);
    }
    //Биссектрисы
    public void bisectorAll(Circle o1,Circle o2, Circle o3, Circle o4, Line l, Text t){
        bisector(o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY(), o3.getCenterX(),o3.getCenterY());
        VertexGo(o4);
        mestopolojenie(o1,o2,o3);
        TextGo(t);
        setVerX1(o2.getCenterX());
        setVerY1(o2.getCenterY());
        SideGo(l);
    }
    //Высоты
    public void HightTreangle(Circle o1, Circle o2, Circle o3, Circle o4, Line l,Text t){
        intersection(o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY());
        VertexGo(o4);
        mestopolojenie(o1,o2,o3);
        TextGo(t);
        setVerX1(o1.getCenterX());
        setVerY1(o1.getCenterY());
        SideGo(l);
    }
    //Серединные перпендикуляры
    public void middlePerpendicularAll(Circle o1, Circle o2,Circle o3, Circle o4,Circle o5,Line l, Text t){
        setVerX(midpoint(o1.getCenterX(),o2.getCenterX()));
        setVerY(midpoint(o1.getCenterY(),o2.getCenterY()));
        VertexGo(o3);
        mestopolojenie(o1,o2,o3);
        TextGo(t);
        setVerX1(getVerX());
        setVerY1(getVerY());
        middlePerpendicular(o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY(), o4.getCenterX(),o4.getCenterY());
        VertexGo(o5);
        SideGo(l);
    }
    //Вписанная окружность
    public void inCircle(Circle o1, Circle o2, Circle o3, Circle o4, Circle o5,Text t){
        o1.setRadius(radiusInCircle(o2.getCenterX(),o2.getCenterY(), o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY()));
        inCircleX(o2.getCenterX(),o2.getCenterY(),o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY());
        inCircleY(o2.getCenterX(),o2.getCenterY(),o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY());
        VertexGo(o1);
        mestopolojenie(o1,o2,o3);
        TextGo(t);
        inCircleX(o2.getCenterX(),o2.getCenterY(),o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY());
        inCircleY(o2.getCenterX(),o2.getCenterY(),o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY());
        VertexGo(o5);
    }
    //Описанная окружность
    public void outCircle(Circle o1, Circle o2, Circle o3, Circle o4, Circle o5, Text t){
        o1.setRadius(radiusOutCircle(o2.getCenterX(),o2.getCenterY(), o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY()));
        middlePerpendicular(o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY(), o4.getCenterX(),o4.getCenterY());
        VertexGo(o1);
        mestopolojenie(o1,o2,o3);
        TextGo(t);
        middlePerpendicular(o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY(), o4.getCenterX(),o4.getCenterY());
        VertexGo(o5);
    }
    //Задаем операцию
    //Перемещение вершин треугольника
    void VertexGo(Circle o){
        vertex=o;
        notifyObservers("VertexGo");
        }
    //Перемещение сторон
    void SideGo(Line o){
        sideAll=o;
        notifyObservers("SideGo");
    }
    //Перемещение букв
    void TextGo(Text o){
        textGo=o;
        notifyObservers("TextGo");
    }
    //Изменение цвета
    void ColorGo(Line c){
        colorLine=c;
        notifyObservers("ColorGo");
    }
//Хранятся переменные
   void setVerX(double s){
    verX=s;
   }
   void setVerY(double s){
    verY=s;
   }
   void  setVerX1(double s){verX1=s;}
   void  setVerY1(double s){verY1=s;}
   void setColorGo(String c) {ColorGo = c; }
   void setDx(double s){dx=s;}
   void setDy(double s){dy=s;}
//Отдаются переменные
    Line getSideAll(){return sideAll;}
    Circle getVerTex() {
    return  vertex;
    }
    Text getTextGo(){return textGo;}
    Line getColorLine(){return  colorLine;}
    double getVerX(){
    return verX;
    }
    double getVerY(){
        return verY;
    }
    double getVerX1(){return  verX1;}
    double getVerY1(){return  verY1;}
    double getDx(){return  dx;}
    double getDy() {return dy;}

}

