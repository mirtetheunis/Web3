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
            System.out.println(request.getParameter("userId"));
            String userId = request.getParameter("userId").trim();
            Member member = getService().get(userId);
            System.out.println("get userId");

            if (member != null && member.isCorrectPassword(request.getParameter("password"))) {
                System.out.println("Ww checken");
                HttpSession session = request.getSession();
                session.setAttribute("user", member);
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
