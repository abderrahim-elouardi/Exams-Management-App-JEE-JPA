package com.fsdm.examsmanagement.dao.superadmin;

import com.fsdm.examsmanagement.model.Administrator;
import com.fsdm.examsmanagement.model.SuperAdmin;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class SuperAdministratorDAOImp implements SuperAdministratorDAO {

    @PersistenceContext(unitName = "myPU")
    EntityManager em;

    @Override
    public SuperAdmin findByEmailAndPassword(String email, String password) {
        String jpql = "SELECT a FROM Administrator a WHERE a.password = :password AND a.email = :email";

        try {
            return em.createQuery(jpql, SuperAdmin.class)
                    .setParameter("password", password)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public SuperAdmin findByEmail(String email) {
        String jpql = "SELECT a FROM Administrator a WHERE a.email = :email";
        try {
            return em.createQuery(jpql, SuperAdmin.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(SuperAdmin element) {
        if (element.getId() == null) {
            em.persist(element);
        } else {
            em.merge(element); // Utilise merge si l'utilisateur existe déjà
        }
    }

    @Override
    public void delete(SuperAdmin element) {
        SuperAdmin toDelete = em.contains(element) ? element : em.merge(element);
        em.remove(toDelete);
    }

    @Override
    public SuperAdmin findById(Long id) {
        return em.find(SuperAdmin.class, id);
    }

    @Override
    public List<SuperAdmin> findAll() {
        String jpql = "SELECT u FROM User u";
        return em.createQuery(jpql, SuperAdmin.class).getResultList();
    }

    @Override
    public List<SuperAdmin> findPaginated(int page, int pageSize) {

        return em.createQuery(
                        "SELECT u FROM User u ORDER BY u.id",
                        SuperAdmin.class
                )
                .setFirstResult((page - 1) * pageSize) // offset
                .setMaxResults(pageSize)               // limit
                .getResultList();
    }
}
