package com.arobld00.autentia.repositories.mongodb;

import com.arobld00.autentia.rest.dtos.FriendDto;
import com.arobld00.autentia.domain.exceptions.PhoneAlreadyExistException;
import com.arobld00.autentia.domain.exceptions.NotFoundException;
import com.arobld00.autentia.domain.Friend;
import com.arobld00.autentia.repositories.FriendPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("friendPersistence")
public class FriendPersistenceMongodb implements FriendPersistence {

    private FriendRepository friendRepository;

    @Autowired
    public FriendPersistenceMongodb(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public List<Friend> readAll() {
        return this.friendRepository
                .findAll().stream()
                .map(FriendEntity::toFriend)
                .collect(Collectors.toList());
    }

    @Override
    public Friend create(Friend friend) {
        this.assertPhoneExist(friend.getPhone());
        return this.friendRepository
                .save(new FriendEntity(friend))
                .toFriend();
    }

    @Override
    public void assertPhoneExist(String phone) {
        this.friendRepository
                .findByPhone(phone)
                .ifPresent(friend -> {
                    throw new PhoneAlreadyExistException("Phone exist: " + phone);
                });
    }

    @Override
    public Friend readByPhone(String phone) {
        return this.friendRepository
                .findByPhone(phone)
                .orElseThrow(() -> new NotFoundException("Friend phone: " + phone))
                .toFriend();
    }

}
