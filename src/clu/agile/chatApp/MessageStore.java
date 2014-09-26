package clu.agile.chatApp;
import java.util.Observable;

//This stores the message and notify all
class MessageStore extends Observable {
    public void sendMessage(String message) {
        setChanged();
        notifyObservers(message);
    }
}
