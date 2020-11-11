package com.arobld00.autentia.data_services.impl;

import com.arobld00.autentia.data_services.FriendService;
import com.arobld00.autentia.domain.Friend;
import com.arobld00.autentia.repositories.FriendPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("friendService")
public class FriendServiceImpl implements FriendService {

    private FriendPersistence friendPersistence;

    @Autowired
    public FriendServiceImpl(FriendPersistence friendPersistence) {
        this.friendPersistence = friendPersistence;
    }

    @Override
    public Friend create(Friend friend) {
        return this.friendPersistence.create(friend);
    }

    @Override
    public List<Friend> readAll() {
        return this.friendPersistence.readAll();
    }

}
