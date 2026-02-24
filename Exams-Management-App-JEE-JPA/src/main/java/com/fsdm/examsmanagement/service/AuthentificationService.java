package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.repositories.StudentRepository;

public class AuthentificationService {
    private String email;
    private String password;
    private String role;
    private StudentRepository studentRepository;
    public AuthentificationService(String email, String password, String role) {
        this(email, password, role, null);
    }

    public AuthentificationService(String email, String password, String role, StudentRepository studentRepository) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.studentRepository = studentRepository;
    }
    public User authenticate(){
        if(email.trim().isEmpty() || password.trim().isEmpty() || role.trim().isEmpty()){
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
        return null;
    }
    private User authenticateEtudiant(){
        if (studentRepository == null) {
            return null;
        }
        User user = studentRepository.findStudentByEmail(email);
        if(user==null){
            return null;
        }
        if(user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
