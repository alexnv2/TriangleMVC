package sample;
//слушатель View
interface Observer {
    void notification(String message);
}
//наблюдатель Model
interface  Observable{
    void registerObserver(Observer o);
    void  notifyObservers(String message);
}
