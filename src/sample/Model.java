package sample;

import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import java.io.File;
import java.text.Normalizer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.StrictMath.*;
import static my.sample.StringConstant.*;


class Model implements  Observable {

    public List<Observer>  observers;
    private Circle vertex;
    private Line sideAll;
    private Text textGo;
    private Line colorLine;
    private Color ColorGo;
    private Arc arcGo;
    private TableView mTableView;
    private WebView webView;
    private Rectangle ang90;
 
    private double verX;
    private double verY;
    private double verX1;
    private double verY1;
    private double dx,dy;//координаты букв
    private double arcRadius;//радиус дуги
    private double angleStart;//начало дуги гр.
    private double angleLength;//длина дуги гр.
    private String stringWebView;//text left
 

    //Конструктор
    Model(){
    observers=new LinkedList<>();
}

    //Список переменных для представления View
    //Заносятся переменные для переменные для View
    void setVerX(double d){verX=d;}//координаты вершины от мышки
    void setVerY(double d){verY=d;}
    void setVerX1(double d){verX1=d;}//2 координата для сотороны
    void setVerY1(double d){verY1=d;}
    void setColorGo(Color c) {ColorGo = c; }//цвет
    void setDx(double d){dx=d;}//смещение букв
    void setDy(double d){dy=d;}
    void setArcRadius(double d) {arcRadius = d;}//радиус дуги
    void setAngleStart(double d) {angleStart = d;}//начало дуги
    void setAngleLength(double d) {angleLength = d;}//конец дуги
    void setStringWebView(String s){stringWebView =s;}//текс для определений


    //Отдаются переменные для View
    Line getSideAll(){return sideAll;} //Объекты линия
    Circle getVerTex() {return  vertex;} //Точка
    Text getTextGo(){return textGo;} //Буквы
    Line getColorLine(){return  colorLine;} //Цвет для линий
    Arc getArcGo(){return arcGo;}// Дуги
    TableView getTableView(){return mTableView;}
    WebView getWebView(){return webView;}//Текст определений
    Color getColorGo(){return ColorGo;}
    Rectangle getAng90(){return ang90;}

    double getVerX(){return verX;}
    double getVerY(){return verY;}
    double getVerX1(){return  verX1;}
    double getVerY1(){return  verY1;}
    double getDx(){return  dx;}
    double getDy() {return dy;}
    public double getArcRadius() {return arcRadius;}//радиус дуги
    public double getAngleStart() {return angleStart;}//начало дуги Х
    public double getAngleLength() {return angleLength;}//длина дуги Y
    public String getStringWebView(){return stringWebView;}//текст определений


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
    //Текст для левой части
    public void webViewLeftString(WebView o, int c){
       String puthImages= new File(".").getAbsolutePath()+"\\src\\Images\\formula_bis.jpg";
       String puthImg1="<img src=file:\\"+puthImages+" width=360 height=105>";

    switch (c) {
        case 0: setStringWebView(WEB_HTML+TR_OPR + TR_ANGLE + TR_NERAVENSVO+ TR_SUNANGLE+WEB_END);break;
        case 1: setStringWebView(WEB_HTML+TR_TEOREMA33+TR_TEOREMA34+WEB_END);break;
        case 2: setStringWebView(WEB_HTML+TR_BISSECTOR+TR_BISSEC_FOR+puthImg1+WEB_END);break;
        case 3: setStringWebView((WEB_HTML+TR_MEDIANA+WEB_END));break;
        case 4: setStringWebView(WEB_HTML+TR_HIGTH+TR_ORTOSENTR+WEB_END);break;
     //   default:
     //       throw new IllegalStateException("Неожиданное значение: " + c);
    }
    webViewGo(o);//на вывод
    }
    //Текст для нижней части
    public void webViewBotton(WebView o, int c){
    switch (c) {
        case 0: setStringWebView(WEB_HTML+TR_OXYGEN+WEB_END);break;
        case 1: setStringWebView(WEB_HTML+TR_OBTUSE+WEB_END);break;
        case 2: setStringWebView(WEB_HTML+TR_REGULAR+WEB_END);break;
        case 3: setStringWebView(WEB_HTML+TR_ISOSCELETES+WEB_END);break;
        case 4: setStringWebView(WEB_HTML+TR_Equilateral+WEB_END);break;
        case 5: setStringWebView(WEB_HTML+TR_BISSECTOR_AG+WEB_END);break;
        case 6: setStringWebView((WEB_HTML+TR_MEDIANA_AD+WEB_END));break;
        case 7: setStringWebView(WEB_HTML+TR_HIGTH_AJ+WEB_END);break;
     //   default:
     //       throw new IllegalStateException("Неожиданное значение: " + c);
    }
    webViewGo(o);
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
    //Точка пересечения двух прямых
    private void crossing(double x1, double y1, double x2, double y2, double x3, double y3,double x4, double y4){
        double a1=y2-y1;
        double a2=y4-y3;
        double b1=x1-x2;
        double b2=x3-x4;
        double c1=x1*y2-x2*y1;
        double c2=x3*y4-x4*y3;
//вычисление главного определителя
        double o=a1*b2-a2*b1;
        double oy=0, ox=0;
        if (o!=0) { //прямые пересекаются
            //вычисление определетелей
            oy = a1 * c2 - a2 * c1;
            ox = c1 * b2 - c2 * b1;
        }
//вычисление координат точек пересечения
            setVerX1(ox/o);
            setVerY1(oy/o);
    }
    //Точка пересечения двух прямых под 90 градусов
   private void intersection(double x1, double y1, double x2, double y2, double x3, double y3) {
        double a1 = y3 - y2;
        double b1 = x2 - x3;
        double c1 = x2 * y3 - x3 * y2;
        double c2 = -x1 * (x3 - x2) + y1 * (y2 - y3);
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
    //Выводит две смежных стороны треугольника
    public void sideAll(Circle o1, Circle o2, Circle o3, Line l1, Line l2){
        VertexGo(o1);
        setVerX1(o2.getCenterX());
        setVerY1(o2.getCenterY());
        SideGo(l1);
        setVerX1(o3.getCenterX());
        setVerY1(o3.getCenterY());
        SideGo(l2);
    }
    //Рисуем дуги
    public void arcVertex(Circle o1, Circle o2, Circle o3,Arc a1){
        double angleABC=angleTriangle(o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY());
        setAngleLength(angleABC);
        setArcRadius(30);
        double arcLight=angleTriangle(o1.getCenterX(),o1.getCenterY(), o1.getCenterX()+200, o1.getCenterY(), o3.getCenterX(), o3.getCenterY());

        if(o1.getCenterY()<o3.getCenterY()){
            arcLight=360-arcLight;
        }
        setAngleStart(arcLight);
        setVerX(o1.getCenterX());
        setVerY(o1.getCenterY());
     //   System.out.println(angleABC+" "+arcLight);
        ArcGo(a1);
    }
    //Прямой угол
    public void rectangle90(Circle o1,Circle o2, Circle o3, Circle o4, Line l1, Line l2){
        double tx=o2.getCenterX()-o1.getCenterX();
        double ty=o2.getCenterY()-o1.getCenterY();
        double t= 0.15;
        double ax=o1.getCenterX()+tx*t;
        double ay=o1.getCenterY()+ty*t;
        intersection(ax,ay,o1.getCenterX(),o1.getCenterY(),o3.getCenterX(),o3.getCenterY());
        setVerX1(ax);
        setVerY1(ay);
        SideGo(l1);
        intersection(ax,ay,o1.getCenterX(),o1.getCenterY(),o4.getCenterX(),o4.getCenterY());
        setVerX1(ax);
        setVerY1(ay);
        SideGo(l2);
       //System.out.println(ax+" "+ay+"  "+getVerX()+"  "+getVerY() );
    }

    //Нахождение углов в треугольнике АВС координаты А, В, С
    private double angleTriangle(double x1, double y1, double x2, double y2, double x3, double y3){
    double ab=distance(x1,y1,x2,y2);
    double ac=distance(x1,y1,x3,y3);
    double bc=distance(x2,y2,x3,y3);
    return round(toDegrees(acos((pow(ab,2)+pow(ac,2)-pow(bc,2))/(2*ab*ac))));
    }
    //Положение букв
    public void mestopolojenie(Circle o1, Circle o2, Text t){
        double xP=o1.getCenterX()+((o1.getCenterX()-o2.getCenterX()))*0.1;
        double yP=o1.getCenterY()+((o1.getCenterY()-o2.getCenterY()))*0.1;
        setDx(xP);
        setDy(yP);
        TextGo(t);
}

    //Медианы
    public void median(Circle o1,Circle o2, Circle o3, Circle o4, Line l){
        setVerX(midpoint(o1.getCenterX(),o2.getCenterX()));
        setVerY(midpoint(o1.getCenterY(),o2.getCenterY()));
        VertexGo(o4);
        setVerX1(o3.getCenterX());
        setVerY1(o3.getCenterY());
        SideGo(l);
    }
    //Биссектрисы
    public void bisectorAll(Circle o1,Circle o2, Circle o3, Circle o4, Line l){
        bisector(o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY(), o3.getCenterX(),o3.getCenterY());
        VertexGo(o4);
        setVerX1(o2.getCenterX());
        setVerY1(o2.getCenterY());
        SideGo(l);
    }
    //Высоты
    public void HightTreangle(Circle o1, Circle o2, Circle o3, Circle o4, Line l1, Line l2, Line l3){
        intersection(o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY());
        VertexGo(o4);
        setVerX1(o1.getCenterX());
        setVerY1(o1.getCenterY());
        SideGo(l1);
        setVerX1(o2.getCenterX());
        setVerY1(o2.getCenterY());
        SideGo(l2);
        intersection(o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY(), o1.getCenterX(), o1.getCenterY());
        crossing(o1.getCenterX(),o1.getCenterY(), o4.getCenterX(), o4.getCenterY(),o2.getCenterX(),o2.getCenterY(),getVerX(),getVerY());
        setVerX(o4.getCenterX());
        setVerY(o4.getCenterY());
        SideGo(l3);
    }
    //Серединные перпендикуляры
    public void middlePerpendicularAll(Circle o1, Circle o2,Circle o3, Circle o4,Circle o5,Line l, Text t){
        setVerX(midpoint(o1.getCenterX(),o2.getCenterX()));
        setVerY(midpoint(o1.getCenterY(),o2.getCenterY()));
        VertexGo(o3);
        setVerX1(getVerX());
        setVerY1(getVerY());
        middlePerpendicular(o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY(), o4.getCenterX(),o4.getCenterY());
        //intersection(o4.getCenterX(),o4.getCenterY(),o1.getCenterX(), o1.getCenterY(), o2.getCenterX(), o2.getCenterY());
        //intersection(o2.getCenterX(), o2.getCenterY(), o4.getCenterX(), o4.getCenterY(), o1.getCenterX(),o1.getCenterY());
        VertexGo(o5);
        SideGo(l);
        setDx(o5.getCenterX()+10);
        setDy(o5.getCenterY()+5);
        TextGo(t);
    }
    //Вписанная окружность
    public void inCircle(Circle o1, Circle o2, Circle o3, Circle o4, Circle o5, Text t){
        o1.setRadius(radiusInCircle(o2.getCenterX(),o2.getCenterY(), o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY()));
        inCircleX(o2.getCenterX(),o2.getCenterY(),o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY());
        inCircleY(o2.getCenterX(),o2.getCenterY(),o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY());
        VertexGo(o1);
        inCircleX(o2.getCenterX(),o2.getCenterY(),o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY());
        inCircleY(o2.getCenterX(),o2.getCenterY(),o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY());
        VertexGo(o5);
        setDx(o5.getCenterX()-20);
        setDy(o5.getCenterY()+5);
        TextGo(t);
    }
    //Описанная окружность
    public void outCircle(Circle o1, Circle o2, Circle o3, Circle o4, Circle o5, Text t){
        o1.setRadius(radiusOutCircle(o2.getCenterX(),o2.getCenterY(), o3.getCenterX(),o3.getCenterY(),o4.getCenterX(),o4.getCenterY()));
        middlePerpendicular(o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY(), o4.getCenterX(),o4.getCenterY());
        //intersection(o4.getCenterX(),o4.getCenterY(),o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY());
        VertexGo(o1);
        //intersection(o4.getCenterX(),o4.getCenterY(),o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY());
        middlePerpendicular(o2.getCenterX(), o2.getCenterY(), o3.getCenterX(), o3.getCenterY(), o4.getCenterX(),o4.getCenterY());
        VertexGo(o5);
        setDx(o5.getCenterX()+10);
        setDy(o5.getCenterY()+5);
        TextGo(t);
    }
    //Задаем операцию для View
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
    //Дуги
    void ArcGo(Arc o){
        arcGo=o;
        notifyObservers("ArcGo");
    }
    void tableGo(TableView o){
        mTableView=o;
        notifyObservers("tableGo");
    }
    void webViewGo(WebView o){
        webView =o;
        notifyObservers("WebView");
    }
    void ang90Go(Rectangle o){
        ang90=o;
        notifyObservers("Angle90");

    }
}

