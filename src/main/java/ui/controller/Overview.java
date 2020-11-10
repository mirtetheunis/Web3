package ui.controller;

import domain.model.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Overview");
        List<Member> members = service.getAll();
        System.out.println("getall");
        request.setAttribute("persons", members);
        return "personenoverview.jsp";
    }
}
