package sample;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class  Controller extends View {
    @FXML
    public Circle A, B, C, mA, mB, mC, hA, hB, hC, bA, bB, bC, iC, ic, vc, vC;
    public Line a, b, c, ma, mb, mc, ha, hb, hc, ba, bb, bic, spa, spb, spc;
    public Text poindA, poindB, poindC,poindE,poindD,poindF, poindG, poindH, poindI;
    public Text poindJ,poindK,poindL, poindO, poindS, poindM, poindN, poindP;
   @FXML
    public Arc arcA, arcB, arcC;
    public CheckMenuItem menuMedianaA, menuMedianaB, menuMedianaC;
    public CheckMenuItem menuHigthA, menuHigthB, menuHigthC;
    public CheckMenuItem menuBisectorA, menuBisectorB, menuBisectorC;
    public CheckMenuItem menuMiddlePerpendicularAB, menuMiddlePerpendicularBC, menuMiddlePerpendicularAC;
    public CheckMenuItem menuCircleIn, menuCircleOut;
    public Pane BP;
    @FXML
    public TreeTableView<PropTreangleGroup> treeTableTreangle;
    @FXML
    public TreeTableColumn<PropTreangleGroup, String> group;
    @FXML
    public TreeTableColumn<PropTreangleGroup, String> vertex;
    @FXML
    public TreeTableColumn<PropTreangleGroup, String> coordinatesX;
    @FXML
    public TreeTableColumn<PropTreangleGroup, String> coordinatesY;
    @FXML
    public TreeTableColumn<PropTreangleGroup, String> angle;
    @FXML
    private void initialize(){
//Реализация интерфейса обратного вызова для заполнения колонок таблицы
    group.setCellValueFactory(new TreeItemPropertyValueFactory<>("propGroup"));
    vertex.setCellValueFactory(new TreeItemPropertyValueFactory<>("propVert"));
    coordinatesX.setCellValueFactory(new TreeItemPropertyValueFactory<>("propCoordX"));
    coordinatesY.setCellValueFactory(new TreeItemPropertyValueFactory<>("propCoordY"));
    angle.setCellValueFactory(new TreeItemPropertyValueFactory<>("propAngle"));
    var rootItem=new TreeItem<>(new PropTreangleGroup("..."));
    rootItem.setExpanded(true);
    var group1=new TreeItem<>(new PropTreangleGroup("Вершины треугольника"));
    group1.setExpanded(true);
    group1.getChildren().add(new TreeItem<>(new PropTreangleGroup("A","100","100","45")));
    group1.getChildren().add(new TreeItem<>(new PropTreangleGroup("B","100","100","45")));
    group1.getChildren().add(new TreeItem<>(new PropTreangleGroup("C","100","100","45")));
    rootItem.getChildren().add(group1);
    treeTableTreangle.setRoot(rootItem);
    }


    public void onClickMedianaA() {
        if(menuMedianaA.isSelected()){
            ma.setVisible(true);
            mA.setVisible(true);
            poindD.setVisible(true);
            model.median(B,C,A,mA,ma,poindD);
        }else {
            ma.setVisible(false);
            mA.setVisible(false);
            poindD.setVisible(false);
        }
    }

    public void onClickMedianaB() {
        if(menuMedianaB.isSelected()){
        mb.setVisible(true);
        mB.setVisible(true);
        poindE.setVisible(true);
        model.median(A,C,B,mB,mb,poindE);
        }else {
            mb.setVisible(false);
            mB.setVisible(false);
            poindE.setVisible(false);
        }
    }

    public void onClickMedianaC() {
        if(menuMedianaC.isSelected()){
            mc.setVisible(true);
            mC.setVisible(true);
            poindF.setVisible(true);
            model.median(A,B,C,mC,mc,poindF);
        }else {
            mc.setVisible(false);
            mC.setVisible(false);
            poindF.setVisible(false);
        }
    }

    public void onClickBisectorA() {
        if(menuBisectorA.isSelected()){
            ba.setVisible(true);
            bA.setVisible(true);
            poindG.setVisible(true);
            model.bisectorAll(B,A,C,bA,ba,poindG);
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
            model.bisectorAll(A,B,C,bB,bb,poindH);
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
            model.bisectorAll(A,C,B,bC,bic,poindI);
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
            model.HightTreangle(A,C,B,hA,ha,poindJ);
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
            model.HightTreangle(B,A,C,hB,hb,poindK);
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
            model.HightTreangle(C,A,B,hC,hc,poindL);
        }else {
            hc.setVisible(false);
            hC.setVisible(false);
            poindL.setVisible(false);
        }
    }

    public void onClickMiddlePerpendicularAB() {
        if(menuMiddlePerpendicularAB.isSelected()){
            spc.setVisible(true);
            mC.setVisible(true);
            vc.setVisible(true);
            poindM.setVisible(true);
            model.middlePerpendicularAll(A,B,mC,C,vc,spc,poindM);
        }else {
            spc.setVisible(false);
            mC.setVisible(false);
            vc.setVisible(false);
            poindM.setVisible(false);
        }
    }

    public void onClickMiddlePerpendicularBC() {
        if(menuMiddlePerpendicularBC.isSelected()){
            spa.setVisible(true);
            mA.setVisible(true);
            vc.setVisible(true);
            poindN.setVisible(true);
            model.middlePerpendicularAll(B,C,mA,A,vc,spa,poindN);
       }else {
            spa.setVisible(false);
            mA.setVisible(false);
            vc.setVisible(false);
            poindN.setVisible(false);
        }
    }
    public void onClickMiddlePerpendicularAC() {
        if(menuMiddlePerpendicularAC.isSelected()){
            spb.setVisible(true);
            mB.setVisible(true);
            vc.setVisible(true);
            poindP.setVisible(true);
            model.middlePerpendicularAll(A,C,mB,B,vc,spb,poindP);
        }else {
            spb.setVisible(false);
            mB.setVisible(false);
            vc.setVisible(false);
            poindP.setVisible(false);
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
            vC.setVisible(true);
            vc.setVisible(true);
            poindS.setVisible(true);
            model.outCircle(vC,A,B,C,vc, poindS);
        }else {
            vC.setVisible(false);
            vc.setVisible(false);
            poindS.setVisible(false);
        }
    }

    public void mouseDragExited() {

    }

    public void mouseDraggen(MouseEvent mouseEvent) {
        model.setVerX(mouseEvent.getSceneX());
        model.setVerY(mouseEvent.getSceneY());


        //Стороны
        if (mouseEvent.getSource() == A) {
            model.sideAll(A,B,C,c,b,poindA);
            //дуги
            model.arcVertex(A,B,C,arcA);
            model.arcVertex(B,C, A, arcB);
            model.arcVertex(C, A, B, arcC);
            model.setColorGo("RED");
            model.ColorGo(a);

        }
        if (mouseEvent.getSource() == B) {
            model.sideAll(B,A,C,c,a,poindB);
           //дуги
            model.arcVertex(B,C, A, arcB);
            model.arcVertex(C, A, B, arcC);
            model.arcVertex(A,B,C,arcA);

        }
        if (mouseEvent.getSource() == C) {
            model.sideAll(C,A,B,b,a,poindC);
            //дуги
            model.arcVertex(C, A, B, arcC);
            model.arcVertex(A,B,C,arcA);
            model.arcVertex(B,C, A, arcB);

        }
        //Медианы
        if(menuMedianaA.isSelected()) {
            model.median(B,C,A,mA,ma,poindD);
        }
        if(menuMedianaB.isSelected()) {
            model.median(A,C,B,mB,mb,poindE);
        }
        if(menuMedianaC.isSelected()){
            model.median(A,B,C,mC,mc,poindF);
        }
        //Биссектрисы
        if(menuBisectorA.isSelected()){
            model.bisectorAll(B,A,C,bA,ba, poindG);
        }
        if(menuBisectorB.isSelected()){
            model.bisectorAll(A,B,C,bB,bb,poindH);
        }
        if(menuBisectorC.isSelected()){
            model.bisectorAll(A,C,B,bC,bic,poindI);
        }
        //Высоты
        if(menuHigthA.isSelected()) {
            model.HightTreangle(A,C,B,hA,ha,poindJ);
        }
        if(menuHigthB.isSelected()){
            model.HightTreangle(B,A,C,hB,hb,poindK);
        }
        if(menuHigthC.isSelected()){
            model.HightTreangle(C,A,B,hC,hc,poindL);
        }
        //Серединные перпенжикуляры
        if(menuMiddlePerpendicularAB.isSelected()){
            model.middlePerpendicularAll(A,B,mC,C,vc,spc,poindM);
        }
        if(menuMiddlePerpendicularBC.isSelected()){
            model.middlePerpendicularAll(B,C,mA,A,vc,spa,poindN);
        }
        if(menuMiddlePerpendicularAC.isSelected()){
            model.middlePerpendicularAll(A,C,mB,B,vc,spb,poindP);
        }
        //Вписанная окружность
        if(menuCircleIn.isSelected()){
            model.inCircle(iC,A,B,C,ic,poindO);
        }
        //Описанная окружность
        if(menuCircleOut.isSelected()){
            model.outCircle(vC,A,B,C,vc, poindS);
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
}
