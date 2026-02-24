package com.fsdm.examsmanagement.dao.core;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public abstract class AbstractGeneriqueDAO<T,V> implements GeneriqueDAO<T,V> {
    @PersistenceContext(unitName = "myPU")
    protected EntityManager entityManager;
    private final Class<T> entityClass;
    private final String idField;
    public AbstractGeneriqueDAO(Class<T> entityClass, String idField) {
        this.entityClass = entityClass;
        this.idField = idField;
    }
    @Override
    public void save(T element) {
        Object id = entityManager.getEntityManagerFactory()
                .getPersistenceUnitUtil()
                .getIdentifier(element);

        if (id == null) entityManager.persist(element);
        else entityManager.merge(element);
    }

    @Override
    public void delete(T element) {
        T toDelete = entityManager.contains(element) ? element : entityManager.merge(element);
        entityManager.remove(toDelete);
    }

    @Override
    public T findById(V id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(jpql, entityClass).getResultList();
    }

    @Override
    public List<T> findPaginated(int page, int pageSize) {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e ORDER BY e." + idField;
        return entityManager.createQuery(jpql, entityClass)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
