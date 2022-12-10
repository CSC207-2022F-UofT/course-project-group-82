package com.ouieat.interactor.handler;

import com.ouieat.models.handler.Model;
import com.ouieat.repository.handler.Repository;
import java.util.ArrayList;
import java.util.Optional;

@org.springframework.stereotype.Repository
public abstract class Interactor<J extends Model, T extends Repository<J>> {

    public T repository;

    public Interactor() {}

    public Interactor(T repository) {
        this.repository = repository;
    }

    public J save(J entity) {
        return repository.save(entity);
    }

    public void delete(J entity) {
        repository.delete(entity);
    }

    public J findById(String id) {
        if (id == null) {
            return null;
        }
        Optional<J> entity = repository.findById(id);
        return entity.orElse(null);
    }

    public ArrayList<J> findAllArray() {
        return (ArrayList<J>) repository.findAll();
    }
}
