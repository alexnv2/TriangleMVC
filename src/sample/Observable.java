package sample;

//наблюдатель Model
interface  Observable{
    void registerObserver(Observer o);
    void  notifyObservers(String message);
}