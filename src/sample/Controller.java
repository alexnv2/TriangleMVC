package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

public class  Controller extends View {

    private int accMedianaA=0;//счетчик для точек для медиан  и середииных перепендикуляров
    private int accMedianaB=0;
    private int accMedianaC=0;
    private int accCircle=0;//счетчик для центра вписанной окружности и серединного перпендикуляра
    @FXML
    public Circle A, B, C, mA, mB, mC, hA, hB, hC, bA, bB, bC, iC, ic, vc, vC;
    public Line a, b, c, ma, mb, mc, ha, hb, hc, ba, bb, bic, spa, spb, spc;
    public Text poindA, poindB, poindC,poindE,poindD,poindF, poindG, poindH, poindI;
    public Text poindJ,poindK,poindL, poindO, poindS;
   @FXML
    public Arc arcA, arcB, arcC;
    public CheckMenuItem menuMedianaA, menuMedianaB, menuMedianaC;
    public CheckMenuItem menuHigthA, menuHigthB, menuHigthC;
    public CheckMenuItem menuBisectorA, menuBisectorB, menuBisectorC;
    public CheckMenuItem menuMiddlePerpendicularAB, menuMiddlePerpendicularBC, menuMiddlePerpendicularAC;
    public CheckMenuItem menuCircleIn, menuCircleOut;
    public RadioMenuItem menuOxygon, menuObtuse, menuRegular, menuIsosceles, menuEquilateral;
    @FXML
    public WebView webViewLeft,webViewBotton;


    public Pane BP;
    //Таблица свойств
    @FXML
    public TableView<PropTreangle> TableTreangle;
    @FXML
    public TableColumn<PropTreangle, String> vertex;
    @FXML
    public TableColumn<PropTreangle, Double> coordinatesX;
    @FXML
    public TableColumn<PropTreangle, Double> coordinatesY;
    @FXML
    public TableColumn<PropTreangle, Double> angle;
    private ObservableList<PropTreangle> propView;

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
    vertex.setCellValueFactory(new PropertyValueFactory<>("propVert"));
    coordinatesX.setCellValueFactory(new PropertyValueFactory<>("propCoordX"));
    coordinatesY.setCellValueFactory(new PropertyValueFactory<>("propCoordY"));
    angle.setCellValueFactory(new PropertyValueFactory<>("propAngle"));
        //Добавление списка и выделение первой стоки
        TableTreangle.setItems(super.propView);
        //Заполнение списка полей
        onClickOxygon();
    }

    private void initData() {
        super.propView.add(new PropTreangle("A",A.getCenterX(),A.getCenterY(),60));
        super.propView.add(new PropTreangle("B",B.getCenterX(),B.getCenterY(),60));
        super.propView.add(new PropTreangle("C",C.getCenterX(),C.getCenterY(),60));
    }

    public void onClickMedianaA() {
        if(menuMedianaA.isSelected()){
            accMedianaA++;
            ma.setVisible(true);
            mA.setVisible(true);
            poindD.setVisible(true);
            model.median(B,C,A,mA,ma);
            model.mestopolojenie(mA,A,B,poindD);//Точка D

        }else {
            ma.setVisible(false);
            accMedianaA--;
            if(accMedianaA==0) {
                mA.setVisible(false);
                poindD.setVisible(false);
            }
        }
    }

    public void onClickMedianaB() {
        if(menuMedianaB.isSelected()){

        accMedianaB++;
        mb.setVisible(true);
        mB.setVisible(true);
        poindE.setVisible(true);
        model.median(A,C,B,mB,mb);
        model.mestopolojenie(mB,A,B,poindE);//Точка Е
        }else {
            mb.setVisible(false);
            accMedianaB--;
            if(accMedianaB==0) {
                mB.setVisible(false);
                poindE.setVisible(false);
            }
        }
    }

    public void onClickMedianaC() {
        if(menuMedianaC.isSelected()){
            accMedianaC++;
            mc.setVisible(true);
            mC.setVisible(true);
            poindF.setVisible(true);
            model.median(A,B,C,mC,mc);
            model.mestopolojenie(mC,B,C,poindF);//Точка F
        }else {
            mc.setVisible(false);
            accMedianaC--;
            if(accMedianaC==0) {
                mC.setVisible(false);
                poindF.setVisible(false);
            }
        }
    }

    public void onClickBisectorA() {
        if(menuBisectorA.isSelected()){
            ba.setVisible(true);
            bA.setVisible(true);
            poindG.setVisible(true);
            model.bisectorAll(B,A,C,bA,ba);
            model.mestopolojenie(bA,A,B,poindG);//Точка G
        }else {
            ba.setVisible(false);
            bA.setVisible(false);
            poindG.setVisible(false);
        }
    }
    public void onClickBisectorB() {
        if(menuBisectorB.isSelected()){
            bb.setVisible(true);
            bB.setVisible(true);
            poindH.setVisible(true);
            model.bisectorAll(A,B,C,bB,bb);
            model.mestopolojenie(bB,A,B,poindH);//Точка H
        }else {
            bb.setVisible(false);
            bB.setVisible(false);
            poindH.setVisible(false);
        }
    }

    public void onClickBisectorC() {
        if(menuBisectorC.isSelected()){
            bic.setVisible(true);
            bC.setVisible(true);
            poindI.setVisible(true);
            model.bisectorAll(A,C,B,bC,bic);
            model.mestopolojenie(bC,B,C,poindI);//Точка I
        }else {
            bic.setVisible(false);
            bC.setVisible(false);
            poindI.setVisible(false);
        }
    }

    public void onClickHigthA() {
        if(menuHigthA.isSelected()){
            ha.setVisible(true);
            hA.setVisible(true);
            poindJ.setVisible(true);
            model.HightTreangle(A,C,B,hA,ha);
            model.mestopolojenie(hA,A,B,poindJ);//Точка J
       }else {
            ha.setVisible(false);
            hA.setVisible(false);
            poindJ.setVisible(false);
        }

    }

    public void onClcckHigthB() {
        if(menuHigthB.isSelected()){
            hb.setVisible(true);
            hB.setVisible(true);
            poindK.setVisible(true);
            model.HightTreangle(B,A,C,hB,hb);
            model.mestopolojenie(hB,A,B,poindK);//Точка K
        }else {
            hb.setVisible(false);
            hB.setVisible(false);
            poindK.setVisible(false);
        }
    }

    public void onClickHigthC() {
        if(menuHigthC.isSelected()){
            hc.setVisible(true);
            hC.setVisible(true);
            poindL.setVisible(true);
            model.HightTreangle(C,A,B,hC,hc);
            model.mestopolojenie(hC,B,C,poindL);//Точка L
        }else {
            hc.setVisible(false);
            hC.setVisible(false);
            poindL.setVisible(false);
        }
    }

    public void onClickMiddlePerpendicularAB() {
        if(menuMiddlePerpendicularAB.isSelected()){
            accMedianaC++;
            accCircle++;
            spc.setVisible(true);
            mC.setVisible(true);
            vc.setVisible(true);
            poindF.setVisible(true);
            poindS.setVisible(true);
            model.middlePerpendicularAll(A,B,mC,C,vc,spc,poindS);
            model.mestopolojenie(mC,B,C,poindF);//Точка F
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

    public void onClickMiddlePerpendicularBC() {
        if(menuMiddlePerpendicularBC.isSelected()){
            accMedianaA++;
            accCircle++;
            spa.setVisible(true);
            mA.setVisible(true);
            vc.setVisible(true);
            poindD.setVisible(true);
            poindS.setVisible(true);
            model.middlePerpendicularAll(B,C,mA,A,vc,spa,poindS);
            model.mestopolojenie(mA,A,B,poindD);//Точка D
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
    public void onClickMiddlePerpendicularAC() {
        if(menuMiddlePerpendicularAC.isSelected()){
            accMedianaB++;
            accCircle++;
            spb.setVisible(true);
            mB.setVisible(true);
            vc.setVisible(true);
            poindE.setVisible(true);
            poindS.setVisible(true);
            model.middlePerpendicularAll(A,C,mB,B,vc,spb,poindS);
            model.mestopolojenie(mB,A,B,poindE);//Точка E
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
    public void onClickCircleIn() {
        if(menuCircleIn.isSelected()){
            iC.setVisible(true);
            ic.setVisible(true);
            poindO.setVisible(true);
            model.inCircle(iC,A,B,C,ic,poindO);

        }else {
            iC.setVisible(false);
            ic.setVisible(false);
            poindO.setVisible(false);
        }
    }
    public void onClickCircleOut() {
        if(menuCircleOut.isSelected()){
            accCircle++;
            vC.setVisible(true);
            vc.setVisible(true);
            poindS.setVisible(true);
            model.outCircle(vC,A,B,C,vc,poindS);
        }else {
            accCircle--;
            if(accCircle==0) {
                vc.setVisible(false);
                poindS.setVisible(false);
            }
            vC.setVisible(false);

        }
    }

    public void mouseDragExited() {

    }

    public void angleAccess(){
     System.out.println(C.getCenterX()+"   "+arcC.getLength());
        super.propView.set(0, new PropTreangle("A",A.getCenterX(),A.getCenterY(),arcA.getLength()));
        super.propView.set(1, new PropTreangle("B",B.getCenterX(),B.getCenterY(),arcB.getLength()));
        super.propView.set(2, new PropTreangle("C",C.getCenterX(),C.getCenterY(),arcC.getLength()));
    }

    public void mouseDraggen(MouseEvent mouseEvent) {
        model.setVerX(mouseEvent.getX());
        model.setVerY(mouseEvent.getY());

        //Стороны
        if (mouseEvent.getSource() == A) {
            model.sideAll(A,B,C,c,b);
            model.TextGo(poindA);
            model.arcVertex(A,B,C,arcA);
            model.arcVertex(B,C, A, arcB);
            model.arcVertex(C, A, B, arcC);
            model.mestopolojenie(A,B,C,poindA); //Угол А
            angleAccess();
            model.tableGo(TableTreangle);
        }
        if (mouseEvent.getSource() == B) {
            model.sideAll(B,A,C,c,a);
           //дуги
            model.arcVertex(B,C, A, arcB);
            model.arcVertex(C, A, B, arcC);
            model.arcVertex(A,B,C,arcA);
            model.mestopolojenie(B,C,A,poindB); //Угол В
            angleAccess();
            model.tableGo(TableTreangle);
        }
        if (mouseEvent.getSource() == C) {
            model.sideAll(C,A,B,b,a);
            //дуги
            model.arcVertex(C, A, B, arcC);
            model.arcVertex(A,B,C,arcA);
            model.arcVertex(B,C, A, arcB);
            model.mestopolojenie(C,A,B,poindC);//Угол С
            angleAccess();
            model.tableGo(TableTreangle);
        }
        //Медианы
        if(menuMedianaA.isSelected()) {
            model.median(B,C,A,mA,ma);
            model.mestopolojenie(mA,A,B,poindD);//Точка D
        }
        if(menuMedianaB.isSelected()) {
            model.median(A,C,B,mB,mb);
            model.mestopolojenie(mB,A,B,poindE);//Точка Е
        }
        if(menuMedianaC.isSelected()){
            model.median(A,B,C,mC,mc);
            model.mestopolojenie(mC,B,C,poindF);//Точка F
        }
        //Биссектрисы
        if(menuBisectorA.isSelected()){
            model.bisectorAll(B,A,C,bA,ba);
            model.mestopolojenie(bA,A,B,poindG);//Точка G
        }
        if(menuBisectorB.isSelected()){
            model.bisectorAll(A,B,C,bB,bb);
            model.mestopolojenie(bB,A,B,poindH);//Точка H
        }
        if(menuBisectorC.isSelected()){
            model.bisectorAll(A,C,B,bC,bic);
            model.mestopolojenie(bC,B,C,poindI);//Точка I
        }
        //Высоты
        if(menuHigthA.isSelected()) {
            model.HightTreangle(A,C,B,hA,ha);
            model.mestopolojenie(hA,A,B,poindJ);//Точка J
        }
        if(menuHigthB.isSelected()){
            model.HightTreangle(B,A,C,hB,hb);
            model.mestopolojenie(hB,A,B,poindK);//Точка K
        }
        if(menuHigthC.isSelected()){
            model.HightTreangle(C,A,B,hC,hc);
            model.mestopolojenie(hC,B,C,poindL);//Точка L
        }
        //Серединные перпендикуляры
        if(menuMiddlePerpendicularAB.isSelected()){
            model.middlePerpendicularAll(A,B,mC,C,vc,spc,poindS);
            model.mestopolojenie(mC,B,C,poindF);//Точка F
        }
        if(menuMiddlePerpendicularBC.isSelected()){
            model.middlePerpendicularAll(B,C,mA,A,vc,spa,poindS);
            model.mestopolojenie(mA,A,B,poindD);//Точка D
        }
        if(menuMiddlePerpendicularAC.isSelected()){
            model.middlePerpendicularAll(A,C,mB,B,vc,spb,poindS);
            model.mestopolojenie(mB,A,B,poindE);//Точка E
        }
        //Вписанная окружность
        if(menuCircleIn.isSelected()){
            model.inCircle(iC,A,B,C,ic,poindO);
        }
        //Описанная окружность
        if(menuCircleOut.isSelected()){
            model.outCircle(vC,A,B,C,vc,poindS);
        }
    }

    public void mouseEntered() {
        A.setCursor(Cursor.OPEN_HAND);
        B.setCursor(Cursor.OPEN_HAND);
        C.setCursor(Cursor.OPEN_HAND);
    }

    public void mousePressed(MouseEvent mouseEvent) {
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

    public void mouseReleased(MouseEvent mouseEvent) {
        A.setMouseTransparent(false);
        A.setCursor(Cursor.DEFAULT);
        B.setMouseTransparent(false);
        B.setCursor(Cursor.DEFAULT);
        C.setMouseTransparent(false);
        C.setCursor(Cursor.DEFAULT);
        mouseEvent.consume();

    }
    //Виды треугольников
    //Остроугольный
    public void onClickOxygon() {
        A.setCenterX(80);
        A.setCenterY(320);
        B.setCenterX(260);
        B.setCenterY(70);
        C.setCenterX(360);
        C.setCenterY(290);
        side();
        model.webViewBotton(webViewBotton,0);
        model.webViewLeftString(webViewLeft,0);
        model.setColorGo(Color.BLACK);
        model.ColorGo(a);
        model.setColorGo(Color.BLACK);
        model.ColorGo(b);
        model.setColorGo(Color.BLACK);
        model.ColorGo(c);
        model.arcVertex(A,B,C,arcA);
        model.arcVertex(B,C, A, arcB);
        model.arcVertex(C, A, B, arcC);
        angleAccess();
        model.tableGo(TableTreangle);
    }
//Тупоугольный
    public void onClickObtuse(ActionEvent actionEvent) {
        arcB.setVisible(true);
        A.setCenterX(240);
        A.setCenterY(250);
        B.setCenterX(60);
        B.setCenterY(70);
        C.setCenterX(530);
        C.setCenterY(230);
        side();
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
        angleAccess();
        model.tableGo(TableTreangle);

    }
//Прямоугольный
    public void onClickRegular(ActionEvent actionEvent) {
        arcB.setVisible(true);
        A.setCenterX(110);
        A.setCenterY(370);
        B.setCenterX(110);
        B.setCenterY(80);
        C.setCenterX(500);
        C.setCenterY(370);
        side();
        model.webViewBotton(webViewBotton,2);
        model.ColorGo(a);
        model.setColorGo(Color.BLACK);
        model.ColorGo(b);
        model.setColorGo(Color.BLACK);
        model.ColorGo(c);
        model.arcVertex(A,B,C,arcA);
        model.arcVertex(B,C, A, arcB);
        model.arcVertex(C, A, B, arcC);
        angleAccess();
        model.tableGo(TableTreangle);
    }
//равнобедренный
    public void onClickIsosceles(ActionEvent actionEvent) {
        A.setCenterX(40);
        A.setCenterY(430);
        B.setCenterX(280);
        B.setCenterY(124);
        C.setCenterX(520);
        C.setCenterY(430);
        side();
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
        arcB.setVisible(false);
        model.arcVertex(C, A, B, arcC);
        angleAccess();
        model.tableGo(TableTreangle);
    }
//Равносторонний
    public void onClickEquilateral(ActionEvent actionEvent) {
        arcB.setVisible(true);
        A.setCenterX(100);
        A.setCenterY(480);
        B.setCenterX(349);
        B.setCenterY(47);
        C.setCenterX(598);
        C.setCenterY(480);
        side();
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
    //

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
        model.mestopolojenie(A,B,C,poindA);
        model.mestopolojenie(B,C,A,poindB);
        model.mestopolojenie(C,A,B,poindC);

    }
}
