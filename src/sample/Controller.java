package sample;
//Класс Контролер (управление) sample.fxml

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

//Конструктор класса
public class  Controller extends View {
//Переменные
    private int accMedianaA=0;//счетчик для точек для медиан  и середииных перепендикуляров
    private int accMedianaB=0;
    private int accMedianaC=0;
    private int accCircle=0;//счетчик для центра вписанной окружности и серединного перпендикуляра
    @FXML
    //Точки вершин A,B,C, медиан mA,mB,mC, биссектрис bA,bB,bC, высот hA,hB,hC
    //центр вписанной окружности ic, вписанная окружность iС,
    //центр описанной окружности vс, описанная окружность vС
    public Circle A, B, C, mA, mB, mC, hA, hB, hC, bA, bB, bC, iC, ic, vc, vC;
    //Стороны треугольник а,в,с медианы ma,mb,mc, биссектрисы ba,bb,bic
    //серединные перпендикуляры spa,spb,spc
    public Line a, b, c, ma, mb, mc, ha, hb, hc, ba, bb, bic, spa, spb, spc;
    //Название вершин треуголника, а также точек медиан, высот, биссектрис, цетров окружностей
    public Text poindA, poindB, poindC,poindE,poindD,poindF, poindG, poindH, poindI;
    public Text poindJ,poindK,poindL, poindO, poindS;
    //Линии для пересещения высот в одной точке
    //la,la1-от угла А, lb,lb1-от угла B, lc,lc1-от угла C
    public Line la, lb,lc, la1, lb1, lc1;
    //Линии для вывода прямого угола в вершинах треугольника
    public Line angle90Ax, angle90Ay, angle90Bx, angle90By, angle90Cx, angle90Cy;
   //Дуги в вершинах треугольника
    public Arc arcA, arcB, arcC;
    //Пункты Меню
    public CheckMenuItem menuMedianaA, menuMedianaB, menuMedianaC;
    public CheckMenuItem menuHigthA, menuHigthB, menuHigthC;
    public CheckMenuItem menuBisectorA, menuBisectorB, menuBisectorC;
    public CheckMenuItem menuMiddlePerpendicularAB, menuMiddlePerpendicularBC, menuMiddlePerpendicularAC;
    public CheckMenuItem menuCircleIn, menuCircleOut;
    public RadioMenuItem menuOxygon, menuObtuse, menuRegular, menuIsosceles, menuEquilateral;
    public MenuItem menuEqualTr;
   //Web браузер для вывода данных
    public WebView webViewLeft,webViewBotton;
    //Контейнер Pane
    public Pane BP, setka;
    public VBox vBox;
    public SplitPane splitPane;

    //Таблица свойств
    @FXML
    public TableView<PropTreangles> TableTreangle;
    public TableColumn<PropTreangles, String> vertex;
    public TableColumn<PropTreangles, Double> coordinatesX;
    public TableColumn<PropTreangles, Double> coordinatesY;
    public TableColumn<PropTreangles, Double> angle;
    //Taблица 2 длины
    public TableView<PropLineTreangle>LineTreangle;
    public TableColumn<PropLineTreangle,String>LineS;
    public TableColumn<PropTreangles,Double>LineD;

    @FXML
    private void initialize(){
        model.webViewLeftString(webViewLeft,0);
        //Реализация радиоменю
        ToggleGroup group = new ToggleGroup();
        menuOxygon.setToggleGroup(group);
        menuObtuse.setToggleGroup(group);
        menuRegular.setToggleGroup(group);
        menuIsosceles.setToggleGroup(group);
        menuEquilateral.setToggleGroup(group);

    //Реализация интерфейса обратного вызова для заполнения колонок таблицы
    initData();
    vertex.setCellValueFactory(new PropertyValueFactory<>("propVertex"));
    coordinatesX.setCellValueFactory(new PropertyValueFactory<>("propCoordinatesX"));
    coordinatesY.setCellValueFactory(new PropertyValueFactory<>("propCoordinatesY"));
    angle.setCellValueFactory(new PropertyValueFactory<>("propAngle"));
        //Добавление списка и выделение первой стоки
        TableTreangle.setItems(super.propView);
        //Заполнение списка полей
        onClickOxygon();

        //Pane для сетки белого цвета
        setka.setStyle("-fx-background-color: white;");
        reshetka();

        //Реализация интерфейса обратного вызова для заполнения колонок 2 таблицы
        initDataLine();
        LineS.setCellValueFactory(new PropertyValueFactory<>("propLine"));
        LineD.setCellValueFactory(new PropertyValueFactory<>("propRazmer"));
        LineTreangle.setItems(super.propLineTr);
    }

    private void initData() {
        super.propView.add(new PropTreangles("A",A.getCenterX(),A.getCenterY(),60));
        super.propView.add(new PropTreangles("B",B.getCenterX(),B.getCenterY(),60));
        super.propView.add(new PropTreangles("C",C.getCenterX(),C.getCenterY(),60));

    }//Длина в см (делитель 40)
    private void initDataLine() {
        super.propLineTr.add(new PropLineTreangle("AB",model.distance(A.getCenterX(),A.getCenterY(),B.getCenterX(),B.getCenterY())/40));
        super.propLineTr.add(new PropLineTreangle("AC",model.distance(A.getCenterX(),A.getCenterY(),C.getCenterX(),C.getCenterY())/40));
        super.propLineTr.add(new PropLineTreangle("BC",model.distance(B.getCenterX(),B.getCenterY(),C.getCenterX(),C.getCenterY())/40));

    }

    //Меню Медианы->Из угла А
    public void onClickMedianaA() {
        if(menuMedianaA.isSelected()){
            accMedianaA++;
            ma.setVisible(true);
            mA.setVisible(true);
            poindD.setVisible(true);
            model.webViewLeftString(webViewLeft,3);
            model.webViewBotton(webViewBotton,6);
            super.propLineTr.add(new PropLineTreangle("AD", model.distance(A.getCenterX(), A.getCenterY(), mA.getCenterX(), mA.getCenterY())/40));
        }else {
            ma.setVisible(false);
            accMedianaA--;
            if(accMedianaA==0) {
                mA.setVisible(false);
                poindD.setVisible(false);
                super.propLineTr.remove(indexTr("AD"));
            }
        }
    }
    //Меню Медианы ->Из угла В
    public void onClickMedianaB() {
        if(menuMedianaB.isSelected()){
        accMedianaB++;
        mb.setVisible(true);
        mB.setVisible(true);
        poindE.setVisible(true);
        model.webViewLeftString(webViewLeft,3);
        super.propLineTr.add(new PropLineTreangle("BE", model.distance(B.getCenterX(), B.getCenterY(), mB.getCenterX(), mB.getCenterY())/40));
        }else {
            mb.setVisible(false);
            accMedianaB--;
            if(accMedianaB==0) {
                mB.setVisible(false);
                poindE.setVisible(false);
                super.propLineTr.remove(indexTr("BE"));
            }
        }
    }
    //Меню Медианы ->Из угла С
    public void onClickMedianaC() {
        if(menuMedianaC.isSelected()){
            accMedianaC++;
            mc.setVisible(true);
            mC.setVisible(true);
            poindF.setVisible(true);
            model.webViewLeftString(webViewLeft,3);
            super.propLineTr.add(new PropLineTreangle("CF", model.distance(C.getCenterX(), C.getCenterY(), mC.getCenterX(), mC.getCenterY())/40));
        }else {
            mc.setVisible(false);
            accMedianaC--;
            if (accMedianaC == 0) {
                mC.setVisible(false);
                poindF.setVisible(false);
                super.propLineTr.remove(indexTr("CF"));
            }
        }
    }
    //Возвращает индекс отрезка из класса PropLineTriangle
    public int indexTr(String s) {
        int i = 0;
        for (PropLineTreangle e : propLineTr) {
          //  System.out.println(e.getPropLine());
            if (e.getPropLine().equals(s)) {break;}
            i++;
        }
        return i;//индекс отрезка
    }
    //Меню Биссектрисы ->Из угла А
    public void onClickBisectorA() {
        if(menuBisectorA.isSelected()){
            ba.setVisible(true);
            bA.setVisible(true);
            poindG.setVisible(true);
            model.webViewLeftString(webViewLeft,2);
            model.webViewBotton(webViewBotton,5);
            super.propLineTr.add(new PropLineTreangle("AG", model.distance(A.getCenterX(), A.getCenterY(), bA.getCenterX(), bA.getCenterY())/40));
        }else {
            ba.setVisible(false);
            bA.setVisible(false);
            poindG.setVisible(false);
            super.propLineTr.remove(indexTr("AG"));
        }
    }
    //Меню Биссектрисы ->Из угла В
    public void onClickBisectorB() {
        if(menuBisectorB.isSelected()){
            bb.setVisible(true);
            bB.setVisible(true);
            poindH.setVisible(true);
            model.webViewLeftString(webViewLeft,2);
            super.propLineTr.add(new PropLineTreangle("BH", model.distance(B.getCenterX(), B.getCenterY(), bB.getCenterX(), bB.getCenterY())/40));
        }else {
            bb.setVisible(false);
            bB.setVisible(false);
            poindH.setVisible(false);
            super.propLineTr.remove(indexTr("BH"));
        }
    }
    //Меню Биссектрисы ->Из угла С
    public void onClickBisectorC() {
        if(menuBisectorC.isSelected()){
            bic.setVisible(true);
            bC.setVisible(true);
            poindI.setVisible(true);
            model.webViewLeftString(webViewLeft,2);
            super.propLineTr.add(new PropLineTreangle("CI", model.distance(C.getCenterX(), C.getCenterY(), bC.getCenterX(), bC.getCenterY())/40));
        }else {
            bic.setVisible(false);
            bC.setVisible(false);
            poindI.setVisible(false);
            super.propLineTr.remove(indexTr("CI"));
        }
    }
    //Меню Высоты ->Из угла А
    public void onClickHigthA() {
        if(menuHigthA.isSelected()){
            ha.setVisible(true);
            hA.setVisible(true);
            la.setVisible(true);
            la1.setVisible(true);
            poindJ.setVisible(true);
            model.webViewLeftString(webViewLeft,4);
            model.webViewBotton(webViewBotton,7);
            super.propLineTr.add(new PropLineTreangle("AJ", model.distance(A.getCenterX(), A.getCenterY(), hA.getCenterX(), hA.getCenterY())/40));
       }else {
            ha.setVisible(false);
            hA.setVisible(false);
            la.setVisible(false);
            la1.setVisible(false);
            poindJ.setVisible(false);
            super.propLineTr.remove(indexTr("AJ"));
        }
    }
    //Меню Высоты ->Из угла В
    public void onClickHigthB() {
        if(menuHigthB.isSelected()){
            hb.setVisible(true);
            hB.setVisible(true);
            lb.setVisible(true);
            lb1.setVisible(true);
            poindK.setVisible(true);
            model.webViewLeftString(webViewLeft,4);
            super.propLineTr.add(new PropLineTreangle("BK", model.distance(B.getCenterX(), B.getCenterY(), hB.getCenterX(), hB.getCenterY())/40));
        }else {
            hb.setVisible(false);
            hB.setVisible(false);
            lb.setVisible(false);
            lb1.setVisible(false);
            poindK.setVisible(false);
            super.propLineTr.remove(indexTr("BK"));
        }
    }
    //Меню Высоты ->Из угла С
    public void onClickHigthC() {
        if(menuHigthC.isSelected()){
            hc.setVisible(true);
            hC.setVisible(true);
            lc.setVisible(true);
            lc1.setVisible(true);
            poindL.setVisible(true);
            model.webViewLeftString(webViewLeft,4);
            super.propLineTr.add(new PropLineTreangle("CL", model.distance(C.getCenterX(), C.getCenterY(), hC.getCenterX(), hC.getCenterY())/40));
        }else {
            hc.setVisible(false);
            hC.setVisible(false);
            lc.setVisible(false);
            lc1.setVisible(false);
            poindL.setVisible(false);
            super.propLineTr.remove(indexTr("CL"));
        }
    }
    //Меню Серединные перпендикуляры -> К стороне АВ
    public void onClickMiddlePerpendicularAB() {
        if(menuMiddlePerpendicularAB.isSelected()){
            accMedianaC++;
            accCircle++;
            spc.setVisible(true);
            mC.setVisible(true);
            vc.setVisible(true);
            poindF.setVisible(true);
            poindS.setVisible(true);
        }else {
            spc.setVisible(false);
            accMedianaC--;
            accCircle--;
            if(accMedianaC==0) {
                mC.setVisible(false);
                poindF.setVisible(false);
            }
            if(accCircle==0) {
                vc.setVisible(false);
                poindS.setVisible(false);
            }
        }
    }
    //Меню Серединные перпендикуляры -> К стороне ВС
    public void onClickMiddlePerpendicularBC() {
        if(menuMiddlePerpendicularBC.isSelected()){
            accMedianaA++;
            accCircle++;
            spa.setVisible(true);
            mA.setVisible(true);
            vc.setVisible(true);
            poindD.setVisible(true);
            poindS.setVisible(true);
        }else {
            spa.setVisible(false);
            accMedianaA--;
            accCircle--;
            if(accMedianaA==0) {
                mA.setVisible(false);
                poindD.setVisible(false);
            }
            if(accCircle==0) {
                vc.setVisible(false);
                poindS.setVisible(false);
            }
        }
    }
    //Меню Серединные перпендикуляры -> К стороне АС
    public void onClickMiddlePerpendicularAC() {
        if(menuMiddlePerpendicularAC.isSelected()){
            accMedianaB++;
            accCircle++;
            spb.setVisible(true);
            mB.setVisible(true);
            vc.setVisible(true);
            poindE.setVisible(true);
            poindS.setVisible(true);
           }else {
            spb.setVisible(false);
            accMedianaB--;
            accCircle--;
            if(accMedianaB==0) {
                mB.setVisible(false);
                poindE.setVisible(false);
            }
            if(accCircle==0) {
                vc.setVisible(false);
                poindS.setVisible(false);
            }
        }
    }
    //Меню Окружности -> Вписанная окружность
    public void onClickCircleIn() {
        if(menuCircleIn.isSelected()){
            iC.setVisible(true);
            ic.setVisible(true);
            poindO.setVisible(true);
        }else {
            iC.setVisible(false);
            ic.setVisible(false);
            poindO.setVisible(false);
        }
    }
    //Меню Окружности -> Описанная окружность
    public void onClickCircleOut() {
        if(menuCircleOut.isSelected()){
            accCircle++;
            vC.setVisible(true);
            vc.setVisible(true);
            poindS.setVisible(true);
        }else {
            accCircle--;
            if(accCircle==0) {
                vc.setVisible(false);
                poindS.setVisible(false);
            }
            vC.setVisible(false);
        }
    }
    //Меню Треугольники->Остроугольный
    public void onClickOxygon() {
        //Координаты треугольника по умолчанию
        A.setCenterX(80);
        A.setCenterY(320);
        B.setCenterX(260);
        B.setCenterY(70);
        C.setCenterX(360);
        C.setCenterY(290);
        side();//вывод на экран
        visibleLine();
        arcB.setVisible(true);//разрешение на отображение дуги угла В
        arcC.setVisible(true);
        model.webViewBotton(webViewBotton,0);//Определения остроугольного треугольника
        model.webViewLeftString(webViewLeft,0);//Определения
        //Задаем цвет сторон
        model.setColorGo(Color.BLACK);
        model.ColorGo(a);
        model.setColorGo(Color.BLACK);
        model.ColorGo(b);
        model.setColorGo(Color.BLACK);
        model.ColorGo(c);
        //Рисуем дуги углов
        model.arcVertex(A,B,C,arcA);
        model.arcVertex(B,C, A, arcB);
        model.arcVertex(C, A, B, arcC);
        //формуруем и выводим таблицу
        angleAccess();
        model.tableGo(TableTreangle);
    }
    //Меню Треугольники->Тупоугольный
    public void onClickObtuse() {
        A.setCenterX(210);
        A.setCenterY(370);
        B.setCenterX(60);
        B.setCenterY(70);
        C.setCenterX(630);
        C.setCenterY(230);
        side();
        visibleLine();
        arcB.setVisible(true);//разрешение на отображение дуги угла В
        model.webViewLeftString(webViewLeft,0);
        model.webViewBotton(webViewBotton,1);
        model.setColorGo(Color.BLACK);
        model.ColorGo(a);
        model.setColorGo(Color.BLACK);
        model.ColorGo(b);
        model.setColorGo(Color.BLACK);
        model.ColorGo(c);
        model.arcVertex(A,B,C,arcA);
        model.arcVertex(B,C, A, arcB);
        model.arcVertex(C, A, B, arcC);
        arcB.setVisible(false);
        arcC.setVisible(false);
        angleAccess();
        model.tableGo(TableTreangle);
    }
    //Меню Треугольники->Прямоугольный
    public void onClickRegular() {
        A.setCenterX(110);
        A.setCenterY(80);
        B.setCenterX(490);
        B.setCenterY(370);
        C.setCenterX(110);
        C.setCenterY(370);
        side();
        visibleLine();
        arcB.setVisible(true);//разрешение на отображение дуги угла В
        arcC.setVisible(true);
        model.webViewLeftString(webViewLeft,0);
        model.webViewBotton(webViewBotton,2);
        model.setColorGo(Color.BLACK);
        model.ColorGo(a);
        model.setColorGo(Color.BLACK);
        model.ColorGo(b);
        model.setColorGo(Color.BLACK);
        model.ColorGo(c);
        model.arcVertex(A,B,C,arcA);
        model.arcVertex(B,C,A,arcB);
        model.arcVertex(C,A,B,arcC);
        arcC.setVisible(false);
        angle90Cy.setVisible(true);
        angle90Cx.setVisible(true);
        model.rectangle90(C,bC,B,A,angle90Cx, angle90Cy);
        angleAccess();
        model.tableGo(TableTreangle);
    }
    //Меню Треугольники->Равнобедренный
    public void onClickIsosceles() {
        A.setCenterX(40);
        A.setCenterY(430);
        B.setCenterX(280);
        B.setCenterY(124);
        C.setCenterX(520);
        C.setCenterY(430);
        side();
        visibleLine();
        arcB.setVisible(true);//разрешение на отображение дуги угла В
        arcC.setVisible(true);
        model.webViewBotton(webViewBotton,3);
        model.webViewLeftString(webViewLeft,1);
        model.setColorGo(Color.RED);
        model.ColorGo(a);
        model.setColorGo(Color.BLACK);
        model.ColorGo(b);
        model.setColorGo(Color.RED);
        model.ColorGo(c);
        model.arcVertex(A,B,C,arcA);
        model.arcVertex(B,C, A, arcB);
        arcB.setVisible(false);//запрет отображения дуги угла В
        model.arcVertex(C, A, B, arcC);
        angleAccess();
        model.tableGo(TableTreangle);
    }
    //Меню Треугольники->Равносторонний
    public void onClickEquilateral() {
        A.setCenterX(100);
        A.setCenterY(480);
        B.setCenterX(349);
        B.setCenterY(47);
        C.setCenterX(598);
        C.setCenterY(480);
        side();
        visibleLine();
        model.webViewLeftString(webViewLeft,0);
        model.webViewBotton(webViewBotton,4);
        arcB.setVisible(true);//разрешение на отображение дуги угла В
        arcC.setVisible(true);
        model.setColorGo(Color.RED);
        model.ColorGo(a);
        model.setColorGo(Color.RED);
        model.ColorGo(b);
        model.setColorGo(Color.RED);
        model.ColorGo(c);
        model.arcVertex(A,B,C,arcA);
        model.arcVertex(B,C, A, arcB);
        model.arcVertex(C, A, B, arcC);
        angleAccess();
        model.tableGo(TableTreangle);
    }
    //Меню Треугольники->Признаки равенства->Первый признак равенства треугольников
    public void onClickEquil() {
        model.setWindShow(0);
        TwofxmlLoader();
    }
    //Меню Треугольники->Признаки равенства->Второй признак равенства треугольников
    public void onClickSecond() {
        model.setWindShow(1);
        TwofxmlLoader();
    }
    //Меню Треугольники->Признаки равенства->Третий признак равенства треугольников

    public void onClickTread() {
        model.setWindShow(2);
        TwofxmlLoader();
    }
    public void TwofxmlLoader(){
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("equality.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Признаки равенства треугольников");
            stage.setResizable(false);

            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void mouseDragExited() {
    }
    //Внести изменения в таблицу
    public void angleAccess(){
        //угол
        super.propView.set(0, new PropTreangles("A",A.getCenterX(),A.getCenterY(),arcA.getLength()));
        super.propView.set(1, new PropTreangles("B",B.getCenterX(),B.getCenterY(),arcB.getLength()));
        super.propView.set(2, new PropTreangles("C",C.getCenterX(),C.getCenterY(),arcC.getLength()));
        }
    public void lineAccess(String s, int index){
        //длина
        switch (s) {
            case "AB"-> super.propLineTr.set(index, new PropLineTreangle("AB", model.distance(A.getCenterX(), A.getCenterY(), B.getCenterX(), B.getCenterY()) / 40));
            case "AC"-> super.propLineTr.set(index, new PropLineTreangle("AC", model.distance(A.getCenterX(), A.getCenterY(), C.getCenterX(), C.getCenterY()) / 40));
            case "BC"-> super.propLineTr.set(index, new PropLineTreangle("BC", model.distance(B.getCenterX(), B.getCenterY(), C.getCenterX(), C.getCenterY()) / 40));
            case "AD"-> super.propLineTr.set(index, new PropLineTreangle("AD", model.distance(A.getCenterX(), A.getCenterY(), mA.getCenterX(), mA.getCenterY())/40));
            case "BE"-> super.propLineTr.set(index, new PropLineTreangle("BE", model.distance(B.getCenterX(), B.getCenterY(), mB.getCenterX(), mB.getCenterY())/40));
            case "CF"-> super.propLineTr.set(index, new PropLineTreangle("CF", model.distance(C.getCenterX(), C.getCenterY(), mC.getCenterX(), mC.getCenterY())/40));
            case "AG"-> super.propLineTr.set(index, new PropLineTreangle("AG", model.distance(A.getCenterX(), A.getCenterY(), bA.getCenterX(), bA.getCenterY())/40));
            case "BH"-> super.propLineTr.set(index, new PropLineTreangle("BH", model.distance(B.getCenterX(), B.getCenterY(), bB.getCenterX(), bB.getCenterY())/40));
            case "CI"-> super.propLineTr.set(index, new PropLineTreangle("CI", model.distance(C.getCenterX(), C.getCenterY(), bC.getCenterX(), bC.getCenterY())/40));
            case "AJ"-> super.propLineTr.set(index, new PropLineTreangle("AJ", model.distance(A.getCenterX(), A.getCenterY(), hA.getCenterX(), hA.getCenterY())/40));
            case "BK"-> super.propLineTr.set(index, new PropLineTreangle("BK", model.distance(B.getCenterX(), B.getCenterY(), hB.getCenterX(), hB.getCenterY())/40));
            case "CL"-> super.propLineTr.set(index, new PropLineTreangle("CL", model.distance(C.getCenterX(), C.getCenterY(), hC.getCenterX(), hC.getCenterY())/40));
        }
    }

        //Перемещение вершин
    public void mouseDraggen(MouseEvent mouseEvent) {
        model.setVerX(mouseEvent.getX());
        model.setVerY(mouseEvent.getY());
        //Стороны
        if (mouseEvent.getSource() == A) {
            model.sideAll(A,B,C,c,b);//две смежных стороны с и b
            model.TextGo(poindA);//вершина угла
            model.arcVertex(A,B,C,arcA);//дуга угла А
            model.arcVertex(B,C, A, arcB);//дуга угла В
            model.arcVertex(C, A, B, arcC); //Дуга угла С
            model.mestopolojenie(A, bA,poindA); //Обозначение угла А
            angleAccess(); //занесение координат и углов в таблицу
            model.tableGo(TableTreangle);//вывод в таблицу
          //  lineAccess();
            model.tableGo(LineTreangle);
        }
        if (mouseEvent.getSource() == B) {
            model.sideAll(B,A,C,c,a);
            model.arcVertex(B,C, A, arcB);
            model.arcVertex(C, A, B, arcC);
            model.arcVertex(A,B,C,arcA);
            model.mestopolojenie(B,bB,poindB); //Угол В
            angleAccess();
            model.tableGo(TableTreangle);
          //  lineAccess();
            model.tableGo(LineTreangle);
        }
        if (mouseEvent.getSource() == C) {
            model.sideAll(C,A,B,b,a);
            model.arcVertex(C, A, B, arcC);
            model.arcVertex(A,B,C,arcA);
            model.arcVertex(B,C, A, arcB);
            model.mestopolojenie(C,bC,poindC);//Угол С
            angleAccess();
            model.tableGo(TableTreangle);
           // lineAccess();
            model.tableGo(LineTreangle);
        }
        //Изменение всех длин в таблице
        for (PropLineTreangle e : propLineTr) {
           // System.out.println(e.getPropLine());
            lineAccess(e.getPropLine(),indexTr(e.getPropLine()));
        }
        visibleLine();
        visibleRectangle90();
        splitPane.widthProperty().addListener((obj,oldValue,newValue)-> System.out.println(newValue));


    }
    //Проверка на прямой угол
    public void visibleRectangle90(){
        if (arcC.getLength()==arcA.getLength()+arcB.getLength()){
            arcC.setVisible(false);
            model.rectangle90(C,bC,A,B, angle90Cx, angle90Cy);
            angle90Cy.setVisible(true);
            angle90Cx.setVisible(true);
        }else {
            arcC.setVisible(true);
            angle90Cx.setVisible(false);
            angle90Cy.setVisible(false);
        }
        if (arcA.getLength()==arcC.getLength()+arcB.getLength()){
            arcA.setVisible(false);
            model.rectangle90(A,bA,B,C, angle90Ax, angle90Ay);
            angle90Ay.setVisible(true);
            angle90Ax.setVisible(true);
        }else {
            arcA.setVisible(true);
            angle90Ax.setVisible(false);
            angle90Ay.setVisible(false);
        }
        if (arcB.getLength()==arcA.getLength()+arcC.getLength()){
            arcB.setVisible(false);
            model.rectangle90(B,bB,C,A, angle90Bx, angle90By);
            angle90By.setVisible(true);
            angle90Bx.setVisible(true);
        }else {
            arcB.setVisible(true);
            angle90Bx.setVisible(false);
            angle90By.setVisible(false);
        }
    }
    //При наведении на вершину меняется курсор
    public void mouseEntered(@NotNull MouseEvent mouseEvent) {
        A.setCursor(Cursor.HAND);
        B.setCursor(Cursor.HAND);
        C.setCursor(Cursor.HAND);
        //Подсказки
        //System.out.println(mouseEvent.getSource());
        if (mouseEvent.getSource()==arcA) {
            arcA.setCursor(Cursor.HAND);
            model.setSToolTip("Угол А=" + arcA.getLength() + " гр.");
            model.oToolTip(arcA);
        }
        if (mouseEvent.getSource()==arcB) {
            model.setSToolTip("Угол B=" + arcB.getLength() + " гр.");
            model.oToolTip(arcB);
        }
        if (mouseEvent.getSource()==arcC) {
            model.setSToolTip("Угол C=" + arcC.getLength() + " гр.");
            model.oToolTip(arcC);
        }
        if (mouseEvent.getSource()==A) {
            model.setSToolTip("Вершина A(" + A.getCenterX() +" , "+A.getCenterY()+ ")");
            model.oToolTip(A);
        }
        if (mouseEvent.getSource()==a) {
            model.setSToolTip("Сторона а или ВС, длина - " + model.distance(B.getCenterX() ,B.getCenterY(),C.getCenterX(),C.getCenterY()));
            model.oToolTip(a);
        }
        if (mouseEvent.getSource()==ma) {
            model.setSToolTip("Медиана АD, длина - " + model.distance(A.getCenterX() ,A.getCenterY(),mA.getCenterX(),mA.getCenterY()));
            model.oToolTip(ma);
        }
    }
    //При нжатии левой кнопки меняется курсор
    //Начало операции перемещения
    public void mousePressed(@NotNull MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == A) {
            A.setMouseTransparent(true);
            A.setCursor(Cursor.CLOSED_HAND);
        }
        if (mouseEvent.getSource() == B) {
            B.setMouseTransparent(true);
            B.setCursor(Cursor.CLOSED_HAND);
        }
        if (mouseEvent.getSource() == C) {
            C.setMouseTransparent(true);
            C.setCursor(Cursor.CLOSED_HAND);
        }
        mouseEvent.consume();
    }
    //Окончание операции перемещения и курсор в исходный вид
    public void mouseReleased(MouseEvent mouseEvent) {
        A.setMouseTransparent(false);
        A.setCursor(Cursor.DEFAULT);
        B.setMouseTransparent(false);
        B.setCursor(Cursor.DEFAULT);
        C.setMouseTransparent(false);
        C.setCursor(Cursor.DEFAULT);
        mouseEvent.consume();
    }
    //Вывод треугольников по умолчанию
    private void side(){
        a.setStartX(B.getCenterX());
        a.setStartY(B.getCenterY());
        a.setEndX(C.getCenterX());
        a.setEndY(C.getCenterY());
        b.setStartX(A.getCenterX());
        b.setStartY(A.getCenterY());
        b.setEndX(C.getCenterX());
        b.setEndY(C.getCenterY());
        c.setStartX(A.getCenterX());
        c.setStartY(A.getCenterY());
        c.setEndX(B.getCenterX());
        c.setEndY(B.getCenterY());
        model.mestopolojenie(A, bA,poindA);
        model.mestopolojenie(B,bB,poindB);
        model.mestopolojenie(C,bC,poindC);
        angle90Cy.setVisible(false);
        angle90Cx.setVisible(false);
    }
    //Выполняется перерасчет все линий и точек
    public void visibleLine(){
        //МедианaА
        model.median(B,C,A,mA,ma);//медиана из угла А
        model.mestopolojenie(mA,A,poindD);//Точка D
        //MedianaB
        model.median(A,C,B,mB,mb);
        model.mestopolojenie(mB,B,poindE);//Точка Е
        //MedianaC
        model.median(A,B,C,mC,mc);
        model.mestopolojenie(mC,C,poindF);//Точка F
        //BisectorA
        model.bisectorAll(B,A,C,bA,ba);
        model.mestopolojenie(bA,A,poindG);//Точка G
        //BisectorB
        model.bisectorAll(A,B,C,bB,bb);
        model.mestopolojenie(bB,B,poindH);//Точка H
        //BisectorC
        model.bisectorAll(A,C,B,bC,bic);
        model.mestopolojenie(bC,C,poindI);//Точка I
        //Высоты
        //HigthA
        model.HeightTreangle(A,B,C,hA,ha, la,la1);
        model.mestopolojenie(hA,A,poindJ);//Точка J
        //HigthB
        model.HeightTreangle(B,C,A,hB,hb, lb,lb1);
        model.mestopolojenie(hB,A,poindK);//Точка K
        //HigthC
        model.HeightTreangle(C,A,B,hC,hc,lc,lc1);
        model.mestopolojenie(hC,B,poindL);//Точка L
        //Серединные перпендикуляры
        //MiddlePerpendicularAB
        model.middlePerpendicularAll(A,B,mC,C,vc,spc,poindS);
        model.mestopolojenie(mC,C,poindF);//Точка F
        //MiddlePerpendicularBC
        model.middlePerpendicularAll(B,C,mA,A,vc,spa,poindS);
        model.mestopolojenie(mA,A,poindD);//Точка D
        //MiddlePerpendicularAC
        model.middlePerpendicularAll(C,A,mB,B,vc,spb,poindS);
        model.mestopolojenie(mB,B,poindE);//Точка E
        //Вписанная окружность
        //CircleIn
        model.inCircle(iC,A,B,C,ic,poindO);
        //Описанная окружность
        //CircleOut
        model.outCircle(vC,A,B,C,vc,poindS);
    }
    public void reshetka(){
        Group group=new Group();
        double w=vBox.getPrefWidth();
        double h=vBox.getPrefHeight();
        int z1,z2;
        z1= (int) (w/20);
        z2= (int) (h/20);
        Shape[] shapes=new Shape[z1];
        Shape[] shapes1=new Shape[z2];
        for(int i=0; i<z1;i++){
            shapes[i]=new Line(i*20,0,i*20,h);
            shapes[i].setStroke(Color.LIGHTBLUE);
            group.getChildren().add(shapes[i]);
        }
        for(int i=0; i<z2;i++){
            shapes1[i]=new Line(0,i*20,w,i*20);
            shapes1[i].setStroke(Color.LIGHTBLUE);
            shapes1[i].setStrokeWidth(1);
            group.getChildren().add(shapes1[i]);
        }
        setka.getChildren().add(group);
        System.out.println("Setca "+w+"  "+h);
    }


    public void onClickSplit() {
        double[] spg=splitPane.getDividerPositions();
        System.out.println(spg[0]);
    }
}