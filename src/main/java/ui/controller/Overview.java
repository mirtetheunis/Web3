package ui.controller;

import domain.model.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Member> members = service.getAll();
        request.setAttribute("persons", members);
        return "personenoverview.jsp";
    }
}
