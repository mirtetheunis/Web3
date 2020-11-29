package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.CoronaTestResult;
import domain.model.Member;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Search extends RequestHandler  {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);

        List<String> errors = new ArrayList<>();
        List<Contact> contacts = service.getAllContacts();
        List<Contact> contactsSincePositive = new ArrayList<>();

        Member m = (Member) request.getSession().getAttribute("user");

            CoronaTestResult test = service.getTestFromMember(m.getUserid());
            if (test == null) {
                errors.add("You have not tested positive.");
            } else {

                Timestamp timeOfTest = Timestamp.valueOf(test.getDate().trim() + " 00:00:00");

                for (Contact c : contacts) {
                    if (c.getDate().after(timeOfTest) && !c.getPersonid().equals(m.getUserid())) {
                        contactsSincePositive.add(c);
                    }
                }

                if (contactsSincePositive == null) errors.add("You had no contacts after testing positive");
            }

        if (errors.size() == 0) {
            try {
                request.setAttribute("contacts", contactsSincePositive);
                request.getRequestDispatcher("search.jsp").forward(request, response);
            } catch (DbException | ServletException e) {
                errors.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("search.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }


    }
}
