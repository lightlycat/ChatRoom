package clu.agile.chatApp;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import clu.agile.core.CLUChatApplication;

public class CLUChatServlet extends HttpServlet
        implements CLUChatApplication {

    // To store messages. This can be
    MessageStore store = new MessageStore();

    // all client sockets are stored in this collection.
    Vector socketClients = new Vector();



    // Returns next message. Waits until there is one.
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        // Return the next message (blocking)
        out.println(getNextMessage());
    }

    //Broadcast message currently available to
    // the currently listening HTTP and socket clients.
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Accept the new message as the "message" parameter
        String message = req.getParameter("message");

        // Broadcast it to all listening clients
        if (message != null) broadcastMessage(message);

        // Set the status code to indicate there will be no response
        res.setStatus(res.SC_NO_CONTENT);
    }

    // getNextMessage() returns the next new message.
    // It blocks until there is one.
    public String getNextMessage() {
        // Create a message sink to wait for a new message from the
        // message source.
        return new MessageBroadcaster().getNextMessage(store);
    }

    // broadcastMessage() informs all currently listening clients that there
    // is a new message. Causes all calls to getNextMessage() to unblock.
    public void broadcastMessage(String message) {
        // Send the message to all the HTTP-connected clients by giving the
        // message to the message source
        store.sendMessage(message);

        // Directly send the message to all the socket-connected clients
        Iterator iterator = socketClients.iterator();
        while (iterator.hasNext()) {
            Socket client = null;
            try {
                client = (Socket)iterator.next();
                PrintStream out = new PrintStream(client.getOutputStream());
                out.println(message);
            }
            catch (IOException e) {
                // Problem with a client, close and remote it
                try {
                    if (client != null) client.close();
                }
                catch (IOException ignored) { }
                socketClients.removeElement(client);
            }
        }
    }



    protected int getSocketPort() {
        //
        return 2248;
    }

    public void addUser(Socket client) {
        // in future we need socketClients per chat room.
        socketClients.addElement(client);
    }

}