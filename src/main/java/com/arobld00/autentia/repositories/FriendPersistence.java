package com.arobld00.autentia.repositories;

import com.arobld00.autentia.domain.Friend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendPersistence {

    List<Friend> readAll();

    Friend create(Friend friend);

    void assertPhoneExist(String phone);

    Friend readByPhone(String phone);

}
