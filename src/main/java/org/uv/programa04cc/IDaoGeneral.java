package org.uv.programa04cc;

import java.util.List;

public interface IDaoGeneral <T, ID> {
    public T create(T p);
    public boolean delete(ID id);
    public T update(ID id, T p);
    
    public List<T> findAll(); //retorna una lista de pojos
    public T findByIdD(ID id);
}
