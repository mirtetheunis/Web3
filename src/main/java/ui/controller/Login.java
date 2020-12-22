package ui.controller;

import domain.db.DbException;
import domain.model.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            System.out.println(request.getParameter("userId"));
            String userId = request.getParameter("userId").trim();
            Member member = service.get(userId);
            System.out.println("get userId");

            if (member != null && member.isCorrectPassword(request.getParameter("password"))) {
                System.out.println("Ww checken");
                HttpSession session = request.getSession();
                session.setAttribute("user", member);
                String gelukt = "Successfully logged in.";
                request.setAttribute("gelukt", gelukt);
            } else {
                request.setAttribute("fout", "Het wachtwoord is fout");
                request.setAttribute("userIdPrevious", userId);
            }
        } catch (DbException e) {
            request.setAttribute("fout", "User id bestaat niet");
        }
        try {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
