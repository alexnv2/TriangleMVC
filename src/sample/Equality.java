package sample;

//Класс контролер для equality.fxml

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Equality extends View {
    public Group groupTriangles;
    public Button bntCombine;
    public Text namePr;

    @FXML
    private void initialize(){
     System.out.println(super.EqualesTriangle);

        if(model.getWindShow()==0) {
           namePr.setText("Первый признак равенства треугольников.");
           model.setTextX(220);
           model.setTextY(30);
           model.TextGo(namePr);
       }
        if(model.getWindShow()==1) {
            namePr.setText("Второй признак равенства треугольников.");
            model.setTextX(220);
            model.setTextY(30);
            model.TextGo(namePr);
        }
        if(model.getWindShow()==2) {
            namePr.setText("Третий признак равенства треугольников.");
            model.setTextX(220);
            model.setTextY(30);
            model.TextGo(namePr);
        }

    }



    //Анимация для совмещения треугольников
    public void onClicCombine() {
        //Перемещениe

        TranslateTransition tr=new TranslateTransition(Duration.seconds(10), groupTriangles);
        tr.setFromX(0);
        tr.setFromY(0);
        tr.setToX(300);
        tr.setToY(0);
        tr.setCycleCount(1);
        tr.setInterpolator(Interpolator.LINEAR);
        //Поворот
        RotateTransition rt2 = new RotateTransition(Duration.seconds(10.0), groupTriangles);
        rt2.setAutoReverse(true);
        rt2.setFromAngle(0.0);
        rt2.setToAngle(-30.0);
        rt2.setCycleCount(1);
        rt2.setInterpolator(Interpolator.LINEAR);
        //Паралельное выполнение
        ParallelTransition pt = new ParallelTransition(groupTriangles);
        pt.getChildren().addAll(tr, rt2);
        pt.play();

    }


}

