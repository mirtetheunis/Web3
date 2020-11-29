package ui.controller;

import domain.model.Member;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Utility{

    public static void checkRole(HttpServletRequest request, Role[] roles) {
        boolean found = false;
        Member member = (Member) request.getSession().getAttribute("user");
        if(member != null) {
            for(Role role : roles) {
                if (member.getRole().equals(role))
                    found = true;
            }
        }
        if(!found)
            throw new NotAuthorizedException();
    }
}
