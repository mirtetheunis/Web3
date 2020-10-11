package ui.controller;

import domain.db.DbException;
import domain.model.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        try {
            String userId = request.getParameter("userId").trim();
            Member person = getService().get(userId);

            if (person != null && person.isCorrectPassword(request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("user", person);
            } else {
                request.setAttribute("fout", "Het wachtwoord is fout");
                request.setAttribute("userIdPrevious", userId);
            }
        } catch (DbException e) {
            request.setAttribute("fout", "User id bestaat niet");
        }

        return "index.jsp";
    }
}
