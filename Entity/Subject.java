package Entity;

public interface Subject {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notif();

}
