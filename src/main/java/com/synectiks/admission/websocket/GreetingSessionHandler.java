package com.synectiks.admission.websocket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.Session;

public class GreetingSessionHandler {
	private final Set<Session> sessions = new HashSet<>();
    private final Set<Greeting> greeting = new HashSet<>();
    
    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    public List<Greeting> getGreetings() {
        return new ArrayList<>(greeting);
    }

    public void addGreeting(Greeting greeting) {
    }

    public void removeGreeting(int id) {
    }

    public void toggleGreeting(int id) {
    }

    private Greeting getGreetingById(int id) {
        return null;
    }

//    private JsonObject createAddMessage(Greeting device) {
//        return null;
//    }
//
//    private void sendToAllConnectedSessions(JsonObject message) {
//    }
//
//    private void sendToSession(Session session, JsonObject message) {
//    }
    
}
