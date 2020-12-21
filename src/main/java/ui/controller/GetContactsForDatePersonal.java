package ui.controller;

import domain.model.Contact;
import domain.model.Member;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class GetContactsForDatePersonal extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);

        Member m = (Member) request.getSession().getAttribute("user");
        try {
            String fromAsString = request.getParameter("from");
            String untilAsString = request.getParameter("until");
            Timestamp from = Timestamp.valueOf(fromAsString + " 00:00:00");
            Timestamp until = Timestamp.valueOf(untilAsString + " 00:00:00");
            String personid = m.getUserid();
            List<Contact> contacts = service.getAllContactsForDateFromMember(personid, from, until);
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("contactsPersonal.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
