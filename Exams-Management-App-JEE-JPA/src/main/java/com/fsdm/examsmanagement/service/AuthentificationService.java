package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.dao.Administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.Administrator.AdministratorDAOImp;
import com.fsdm.examsmanagement.dao.Student.StudentDAO;
import com.fsdm.examsmanagement.dao.Student.StudentDAOImp;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.security.PasswordSecurity;

public class AuthentificationService {
    private String email;
    private String password;
    private String role;
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
        return null;
    }
    private User authenticateAdministrateur(){
        administratorDAO = new AdministratorDAOImp();
        if(administratorDAO==null){
            return null;
        }
        String hashPassword = PasswordSecurity.hash(password);
        User user = administratorDAO.findByEmailAndPassword(email, hashPassword);
        if(user.getPassword().equals(password)){
            return user;
        }
        return null;
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
