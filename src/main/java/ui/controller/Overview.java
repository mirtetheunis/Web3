package ui.controller;

import domain.model.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Overview extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            System.out.println("Overview");
            List<Member> members = service.getAll();
            System.out.println("getall");
            request.setAttribute("persons", members);
            request.getRequestDispatcher("personenoverview.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
