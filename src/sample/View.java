package sample;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

class View implements Observer{


    Model model=new Model();

    View(){
        model.registerObserver(this);
    }

    @Override
    //Какую информацию надо вывести
    public  void  notification(String message){
        switch (message) {
            case "VertexGo" -> this.vertexGo(model.getVerTex());
            case "SideGo" -> this.sideGo(model.getSideAll());
            case "TextGo" -> this.TextGo(model.getTextGo());
            case "ColorGo" -> this.SrokeColor(model.getColorLine());
        }
    }

    //Перемещение вершин
    private void vertexGo(Circle ver){
       ver.setCenterX(model.getVerX());
       ver.setCenterY(model.getVerY());
    }
    //Перемещение сторон
    private void sideGo(Line side){
        side.setStartX(model.getVerX());
        side.setStartY(model.getVerY());
        side.setEndX(model.getVerX1());
        side.setEndY(model.getVerY1());
    }
    //Перемещение Текста
    private void TextGo(Text text){
        text.setX(model.getVerX()+model.getDx());
        text.setY(model.getVerY()+model.getDy());
    }
    //Изменение цвета линий
    private void SrokeColor(Line line){
         line.setStroke(javafx.scene.paint.Color.RED);
    }
}
