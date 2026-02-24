package com.fsdm.examsmanagement.dao.User;

import com.fsdm.examsmanagement.model.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class UserDAOImp implements UserDAO{

    @PersistenceContext(unitName = "myPU")
    EntityManager em= Persistence.createEntityManagerFactory("myPU").createEntityManager();

    @Override
    public User findByEmailAndPassword(String email, String password) {
        String jpql = "SELECT a FROM User a WHERE a.password = :password AND a.email = :email";

        try {
            return em.createQuery(jpql, User.class)
                    .setParameter("password", password)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(User element) {
        if (element.getId() == null) {
            em.persist(element);
        } else {
            em.merge(element); // Utilise merge si l'utilisateur existe déjà
        }
    }

    @Override
    public void delete(User element) {
        // En EJB, on doit s'assurer que l'objet est géré avant de le supprimer
        User toDelete = em.contains(element) ? element : em.merge(element);
        em.remove(toDelete);
        // SUPPRESSION de em.getTransaction().commit(); -> C'est automatique !
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        String jpql = "SELECT u FROM User u";
        return em.createQuery(jpql, User.class).getResultList();
    }

    @Override
    public List<User> findPaginated(int page, int pageSize) {

        return em.createQuery(
                        "SELECT u FROM User u ORDER BY u.id",
                        User.class
                )
                .setFirstResult((page - 1) * pageSize) // offset
                .setMaxResults(pageSize)               // limit
                .getResultList();
    }
}
