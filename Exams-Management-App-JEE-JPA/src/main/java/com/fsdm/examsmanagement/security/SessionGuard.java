package com.fsdm.examsmanagement.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public final class SessionGuard {

    private SessionGuard() {
    }

    public static boolean requireRole(HttpServletRequest request, HttpServletResponse response, String role) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(role) == null) {
            response.sendRedirect(request.getContextPath() + "/authentification?role=" + role);
            return false;
        }
        return true;
    }
}
