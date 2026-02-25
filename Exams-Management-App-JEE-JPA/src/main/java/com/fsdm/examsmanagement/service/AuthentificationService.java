package com.fsdm.examsmanagement.service;

<<<<<<< HEAD
import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.administrator.AdministratorDAOImp;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.dao.student.StudentDAOImp;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.security.PasswordSecurity;
import jakarta.ejb.EJB;

=======
import com.fsdm.examsmanagement.dao.Administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.Student.StudentDAO;
import com.fsdm.examsmanagement.model.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
>>>>>>> 10692ea (corriger l'authentification (EJB + service))

@Stateless
public class AuthentificationService {
<<<<<<< HEAD
    private String email;
    private String password;
    private String role;
=======
>>>>>>> 10692ea (corriger l'authentification (EJB + service))

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
<<<<<<< HEAD
    private User authenticateAdministrateur(){
        String hashPassword = PasswordSecurity.hash(password);
        return administratorDAO.findByEmailAndPassword(email, hashPassword);
=======

    private User authenticateAdministrateur(String email, String password) {
        return administratorDAO.findByEmailAndPassword(email, password);
>>>>>>> 10692ea (corriger l'authentification (EJB + service))
    }

    private User authenticateEtudiant(String email, String password) {
        return studentDAO.findByEmailAndPassword(email, password);
    }
}
