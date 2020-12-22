package ui.controller;

import domain.db.DbException;
import domain.model.CoronaTestResult;
import domain.model.Member;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterTest extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);

        List<String> errors = new ArrayList<>();

        CoronaTestResult test = new CoronaTestResult();
        setTestDate(test, request, errors);
        setMemberID(test, request, errors);
        if (errors.size() == 0) {
            try {
                service.addTestResult(test);
                String gelukt = "Test successfully registered.";
                request.setAttribute("gelukt", gelukt);
                request.getRequestDispatcher("Controller?command=ContactOverviewPersonal").forward(request, response);
                //response.sendRedirect("Controller?command=ContactOverviewPersonal");
            } catch (DbException | ServletException e) {
                errors.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("registerPositiveTest.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    private void setMemberID(CoronaTestResult test, HttpServletRequest request, List<String> errors) {
        Member m = (Member) request.getSession().getAttribute("user");
        try {
            String userid = m.getUserid();
            test.setPersonid(userid);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setTestDate(CoronaTestResult test, HttpServletRequest request, List<String> errors) {
        String dateAsString = request.getParameter("dateTest").trim();
        try {
            test.setDate(dateAsString);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
