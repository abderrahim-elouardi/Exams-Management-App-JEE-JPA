package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.administrator.AdministratorDAOImp;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.dao.student.StudentDAOImp;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.security.PasswordSecurity;
import jakarta.ejb.EJB;


public class AuthentificationService {
    private String email;
    private String password;
    private String role;

    @EJB
    private AdministratorDAO administratorDAO;
    private StudentDAO studentDAO;

    public AuthentificationService(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public User authenticate(){
        if(email.trim().isEmpty() || password.trim().isEmpty() || role.trim().isEmpty() ||
            email==null || password==null || role==null
        ){
            return null;
        }
        switch (role){
            case "administrateur":
                return authenticateAdministrateur();
            case "etudiant":
                return authenticateEtudiant();
        }
        System.out.println("role indefinit");
        return null;
    }
    private User authenticateAdministrateur(){
        String hashPassword = PasswordSecurity.hash(password);
        return administratorDAO.findByEmailAndPassword(email, hashPassword);
    }
    private User authenticateEtudiant(){
        studentDAO = new StudentDAOImp();
        if (studentDAO == null) {
            return null;
        }
        String hashPassword = PasswordSecurity.hash(password);
        User user = studentDAO.findByEmailAndPassword(email, hashPassword);
        if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
