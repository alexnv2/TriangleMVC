package sample;

//Класс контролер для equality.fxml
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class Equality {
    public Group grTr1;
    public Button bntCombine;

    //Анимация для совмещения треугольников
    public void onClicCombine() {
        //Перемещениу
        TranslateTransition tr=new TranslateTransition(Duration.seconds(10),grTr1);
        tr.setFromX(0);
        tr.setFromY(0);
        tr.setToX(300);
        tr.setToY(0);
        tr.setCycleCount(1);
        tr.setInterpolator(Interpolator.LINEAR);
        //Поворот
        RotateTransition rt2 = new RotateTransition(Duration.seconds(10.0),grTr1);
        rt2.setAutoReverse(true);
        rt2.setFromAngle(0.0);
        rt2.setToAngle(-30.0);
        rt2.setCycleCount(1);
        rt2.setInterpolator(Interpolator.LINEAR);
        //Паралельное выполнение
        ParallelTransition pt = new ParallelTransition(grTr1);
        pt.getChildren().addAll(tr, rt2);
        pt.play();

    }
}

