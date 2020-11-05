package ui.controller;

import domain.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected MemberService service;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    public void setModel(MemberService service) {
        this.service = service;
    }

    public MemberService getService() {
        return service;
    }
}
