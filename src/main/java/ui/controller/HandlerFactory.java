package ui.controller;

import domain.service.MemberService;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, MemberService model) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina kon niet gevonden worden.");
        }
        return handler;
    }
}
