package sample;
//Класс Представление для вывода на экран
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

class View implements Observer{
    //Список для таблицы
    public   ObservableList<PropTreangles> propView = FXCollections.observableArrayList();
    //Список для второй таблицы
    public  ObservableList<PropLineTreangle> propLineTr =FXCollections.observableArrayList();
    //Объявляем класс пересчетв мировых координат в в окнл просмотра
   // WView k = new WView();
    //Объявляем класс сетки
    GridView gridViews=new GridView();
    //Обявляем класс модели
    Model model=new Model();
    View(){
        model.registerObserver(this);
    }

    @Override
    //Какую информацию надо вывести
    public  void  notification(String message){
        switch (message) {
            case "VertexGo" -> this.vertexGo(model.getVertex());//перемещение вершин
            case "SideGo" -> this.sideGo(model.getSideAll());//отрисовка сторон
            case "TextGo" -> this.TextGo(model.getTextGo());//буквы
            case "ColorGo" -> this.SrokeColor(model.getColorLine());//цвет
            case "ArcGo" -> this.arcGo(model.getArcGo());//дуги
            case "ArcColorGo"->this.ArcColor(model.getArcGo());
            case "TableGo"->this.tableGo(model.getMTableView());//таблица
            case "WebView"->this.webViewGo(model.getWebView());//Заполнение слева и внизу
            case "ToolTip"->this.toolTipGo(model.getOToolTip());//Подсказка
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
        text.setX(model.getTextX());
        text.setY(model.getTextY());
    }
    //Дуги углов треугольника
    private void arcGo(Arc arc){
        arc.setCenterX(model.getVerX());
        arc.setCenterY(model.getVerY());
        arc.setRadiusX(model.getArcRadius());
        arc.setRadiusY(model.getArcRadius());
        arc.setStartAngle(model.getAngleStart());
        arc.setLength(model.getAngleLength());
    }
    //Заполнение таблицы
    private void tableGo(TableView tableView){
        tableView.setItems(propView);
    }
    //Изменение цвета линий
    private void SrokeColor(Line line){
       Color c=model.getColorGo();
        line.setStroke(c);
    }
    //Изменение цвета линий
    private void ArcColor(Arc arc){
        Color c=model.getColorGo();
        arc.setStroke(c);
    }
    //Заполнение web страниц слева и внизу
    private void webViewGo(WebView webView) {
         webView.setContextMenuEnabled(false);
         WebEngine w=webView.getEngine();
         w.loadContent(model.getStringWebView());
       // w.load("https://yandex.ru");
    }
    //Подсказка
    private void toolTipGo(Object o){
        Tooltip t = new Tooltip();
        t.setShowDelay(Duration.millis(100.0));
        t.setText(model.getSToolTip());
        Tooltip.install((Node) o,t);

    }
}
