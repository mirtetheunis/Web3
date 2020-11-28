package ui.controller;

import domain.service.ContactTracingService;
import org.openqa.selenium.json.JsonOutput;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {
    protected ContactTracingService service;

    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;

    public void setService(ContactTracingService contactTracingService) {
        System.out.println("setService");
        this.service = contactTracingService;
        System.out.println("geset");
    }

    public ContactTracingService getService() {
        return service;
    }
}
