package com.ouieat.interactor;

import com.ouieat.models.Model;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public abstract class Interactor<
    J extends Model, T extends MongoRepository<J, String>
> {

    public T repository;

    public Interactor() {}

    public Interactor(T repository) {
        this.repository = repository;
    }

    public void init(T repository) {
        this.repository = repository;
    }

    public J save(J entity) {
        return repository.save(entity);
    }

    public void delete(J entity) {
        repository.delete(entity);
    }

    public void update(J entity) {
        if (repository.existsById(entity.getId())) {
            repository.save(entity);
        } else {
            throw new RuntimeException(
                "Entity does not exist while saving: " + entity.getId()
            );
        }
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public J findById(String id) {
        if (id == null) {
            return null;
        }
        Optional<J> entity = repository.findById(id);
        return entity.orElse(null);
    }

    public Iterable<J> findAll() {
        return repository.findAll();
    }

    public ArrayList<J> findAllArray() {
        return (ArrayList<J>) repository.findAll();
    }
}
