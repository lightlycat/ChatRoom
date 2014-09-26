package clu.agile.chatApp;

import java.util.Observable;
import java.util.Observer;



//MessageBroadcaster acts as the receiver of new messages.
//It listens to the source.
public class MessageBroadcaster implements Observer {

    String message = null;  // set by update() and read by getNextMessage()


    synchronized public void update(Observable o, Object arg) {
        message = (String)arg;
        notify();
    }

    synchronized public String getNextMessage(MessageStore source) {

        source.addObserver(this);

// Wait until our update() method receives a messagee
        while (message == null) {
            try { wait(); } catch (Exception ignored) { }
        }

        // Tell source to stop telling us about new messages
        source.deleteObserver(this);

        // Now return the message we received
        // But first set the message instance variable to null
        // so update() and getNextMessage() can be called again.
        String messageCopy = message;
        message = null;
        return messageCopy;
    }
}
