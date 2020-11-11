package com.arobld00.autentia.repositories.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FriendRepository extends MongoRepository<FriendEntity, String> {

    Optional<FriendEntity> findByPhone(String phone);

    Optional<FriendEntity> findByName(String name);

}
