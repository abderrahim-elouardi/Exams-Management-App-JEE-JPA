package com.fsdm.examsmanagement.service;

import com.fsdm.examsmanagement.dao.administrator.AdministratorDAO;
import com.fsdm.examsmanagement.dao.student.StudentDAO;
import com.fsdm.examsmanagement.model.User;
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
     * @param password mot de passe de l'administrateur
     * @return administrateur trouvé, sinon null
     */
    private User authenticateAdministrator(String email, String password) {
        return administratorDAO.findByEmailAndPassword(email, password);
    }

    /**
     * Authentifie un étudiant.
     *
     * @param email email de l'étudiant
     * @param password mot de passe de l'étudiant
     * @return étudiant trouvé, sinon null
     */
    private User authenticateStudent(String email, String password) {
        return studentDAO.findByEmailAndPassword(email, password);
    }
}
