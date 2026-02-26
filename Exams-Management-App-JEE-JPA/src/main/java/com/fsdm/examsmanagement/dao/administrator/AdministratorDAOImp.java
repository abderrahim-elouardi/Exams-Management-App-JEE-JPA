package com.fsdm.examsmanagement.dao.administrator;

import com.fsdm.examsmanagement.model.Administrator;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class AdministratorDAOImp implements AdministratorDAO {

    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    @Override
    public Administrator findByEmailAndPassword(String email, String password) {
        String jpql = "SELECT a FROM Administrator a WHERE a.password = :password AND a.email = :email";

        try {
            return em.createQuery(jpql, Administrator.class)
                    .setParameter("password", password)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Administrator element) {
        if (element.getId() == null) {
            em.persist(element);
        } else {
            em.merge(element); // Utilise merge si l'utilisateur existe déjà
        }
    }

    @Override
    public void delete(Administrator element) {
        Administrator toDelete = em.contains(element) ? element : em.merge(element);
        em.remove(toDelete);
    }

    @Override
    public Administrator findById(Long id) {
        return em.find(Administrator.class, id);
    }

    @Override
    public List<Administrator> findAll() {
        String jpql = "SELECT u FROM User u";
        return em.createQuery(jpql, Administrator.class).getResultList();
    }

    @Override
    public List<Administrator> findPaginated(int page, int pageSize) {

        return em.createQuery(
                        "SELECT u FROM User u ORDER BY u.id",
                        Administrator.class
                )
                .setFirstResult((page - 1) * pageSize) // offset
                .setMaxResults(pageSize)               // limit
                .getResultList();
    }
}
