package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.User;
import com.fsdm.examsmanagement.security.PasswordSecurity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * Service qui gère l'authentification des utilisateurs.
 * Il choisit la bonne vérification selon le rôle.
 */
@Stateless
public class AuthentificationService {
    /**
     * DAO pour accéder aux administrateurs.
     */
    @EJB
    private AdministratorDAO administratorDAO;

    /**
     * DAO pour accéder aux étudiants.
     */
    @EJB
    private StudentDAO studentDAO;

    /**
     * Vérifie les informations de connexion selon le rôle.
     *
     * @param email email saisi par l'utilisateur
     * @param password mot de passe saisi
     * @param role rôle de l'utilisateur (admin ou student)
     * @return l'utilisateur trouvé si les données sont correctes, sinon null
     */
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

    /**
     * Authentifie un administrateur.
     *
     * @param email email de l'administrateur
     * @param plainPassword mot de passe de l'administrateur
     * @return administrateur trouvé, sinon null
     */
    private User authenticateAdministrator(String email, String plainPassword) {
        User admin = administratorDAO.findByEmail(email);
        if (admin == null || admin.getPassword() == null) {
            return null;
        }
        return PasswordSecurity.verify(plainPassword, admin.getPassword()) ? admin : null;
    }

    /**
     * Authentifie un étudiant.
     *
     * @param email email de l'étudiant
     * @param plainPassword mot de passe de l'étudiant
     * @return étudiant trouvé, sinon null
     */
    private User authenticateStudent(String email, String plainPassword) {
        User student = studentDAO.findByEmail(email);
        if (student == null || student.getPassword() == null) {
            return null;
        }
        return PasswordSecurity.verify(plainPassword, student.getPassword()) ? student : null;
    }
}
