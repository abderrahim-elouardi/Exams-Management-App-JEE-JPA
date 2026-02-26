package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class AuthentificationService {
    @EJB
    private AdministratorDAO administratorDAO;

    @EJB
    private StudentDAO studentDAO;

    public User authenticate(String email, String password, String role) {
        if (email == null || password == null || role == null ||
            email.trim().isEmpty() || password.trim().isEmpty() || role.trim().isEmpty()) {
            return null;
        }
        switch (role){
            case "admin":
                return authenticateAdministrator(email, password);
            case "student":
                return authenticateStudent(email, password);
        }
        System.out.println("role indefinit");
        return null;
    }
    private User authenticateAdministrator(String email, String password) {
        return administratorDAO.findByEmailAndPassword(email, password);
    }
    private User authenticateStudent(String email, String password) {
        return studentDAO.findByEmailAndPassword(email, password);
    }
}
