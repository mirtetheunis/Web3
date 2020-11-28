package ui.controller;

import domain.model.DomainException;
import domain.model.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Delete extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Member> members = service.getAll();
        String userId = request.getParameter("userid").trim();

        for (Member m : members) {
            if (m.getUserid().equals(userId)) {
                service.delete(userId);
                response.sendRedirect("Controller?command=Overview");
            }
        }

        throw new DomainException("Deze userID bestaat niet");
    }
}
