package sample;

import com.sun.scenario.effect.impl.state.GaussianRenderState;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.util.Duration;

import javax.swing.*;

public class Equality {
    public Group grTr1;
    public void onClicCombine(ActionEvent actionEvent) {
        TranslateTransition tr=new TranslateTransition(Duration.seconds(10),grTr1);
        tr.setFromX(0);
        tr.setFromY(0);
        tr.setToX(300);
        tr.setToY(0);
        tr.setCycleCount(1);
        tr.setInterpolator(Interpolator.LINEAR);


        RotateTransition rt2 = new RotateTransition(Duration.seconds(10.0),grTr1);
        rt2.setAutoReverse(true);
        rt2.setFromAngle(0.0);
        rt2.setToAngle(-30.0);
        rt2.setCycleCount(1);
        rt2.setInterpolator(Interpolator.LINEAR);

        ParallelTransition pt = new ParallelTransition(grTr1);
        pt.getChildren().addAll(tr, rt2);
        pt.play();

    }
}

