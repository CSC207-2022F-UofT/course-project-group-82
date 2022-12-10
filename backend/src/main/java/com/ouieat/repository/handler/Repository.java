package com.ouieat.repository.handler;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository<T> extends MongoRepository<T, String> {}
