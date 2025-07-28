package com.skiller.hotel.service.implement;

import com.skiller.hotel.exception.EntityNotFoundException;
import com.skiller.hotel.repository.IGenericRepository;
import com.skiller.hotel.service.interfaces.ICRUDService;

import java.util.List;

public abstract class CRUDServiceImpl<T, ID> implements ICRUDService<T, ID> {
    protected abstract IGenericRepository<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        getRepo().findById(id).orElseThrow( () -> new EntityNotFoundException("ID NOT FOUND: " + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow( () -> new EntityNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow( () -> new EntityNotFoundException("ID NOT FOUND: " + id));
        getRepo().deleteById(id);
    }
}
