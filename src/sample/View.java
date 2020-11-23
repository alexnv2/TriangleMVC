package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

class View implements Observer{

    public   ObservableList<PropTreangle> propView = FXCollections.observableArrayList();

    Model model=new Model();

    View(){
        model.registerObserver(this);
    }

    @Override
    //Какую информацию надо вывести
    public  void  notification(String message){
        switch (message) {
            case "VertexGo" -> this.vertexGo(model.getVerTex());//перемещение вершин
            case "SideGo" -> this.sideGo(model.getSideAll());//отрисовка сторон
            case "TextGo" -> this.TextGo(model.getTextGo());//буквы
            case "ColorGo" -> this.SrokeColor(model.getColorLine());//цвет
            case "ArcGo" -> this.arcGo(model.getArcGo());//дуги
            case "TableGo"->this.tableGo(model.getTableView(), propView);
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
    //Дуги углов треугольника
    private void arcGo(Arc arc){
        arc.setCenterX(model.getVerX());
        arc.setCenterY(model.getVerY());
     //   System.out.println(model.getVerX()+"  "+model.getVerY());
        arc.setRadiusX(model.getArcRadius());
        arc.setRadiusY(model.getArcRadius());
        arc.setStartAngle(model.getAngleStart());
        arc.setLength(model.getAngleLength());
    }
    //Заполнение таблицы
    private void tableGo(TableView tableView, ObservableList propView){
        tableView.setItems(propView);

    }
    //Изменение цвета линий
    private void SrokeColor(Line line){
         line.setStroke(javafx.scene.paint.Color.RED);
    }
}
