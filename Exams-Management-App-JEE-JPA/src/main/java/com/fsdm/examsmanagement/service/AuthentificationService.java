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
            case "administrateur":
                return authenticateAdministrateur(email, password);
            case "etudiant":
                return authenticateEtudiant(email, password);
        }
        System.out.println("role indefinit");
        return null;
    }
    private User authenticateAdministrateur(String email, String password) {
        return administratorDAO.findByEmailAndPassword(email, password);
    }
    private User authenticateEtudiant(String email, String password) {
        return studentDAO.findByEmailAndPassword(email, password);
    }
}
