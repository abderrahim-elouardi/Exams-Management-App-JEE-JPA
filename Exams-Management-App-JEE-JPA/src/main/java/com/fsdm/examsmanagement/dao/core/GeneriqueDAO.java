package com.fsdm.examsmanagement.dao.core;

import java.util.List;

public interface GeneriqueDAO <T,V>{
//    T: Entity
//    V: Type Of Id
    public void save(T element);
    public void delete(T element);
    public T findById(V id);
    public List<T> findAll();
    public List<T> findPaginated(int page, int pageSize);
}
