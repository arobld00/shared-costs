package com.arobld00.autentia;

import com.arobld00.autentia.domain.Friend;
import com.arobld00.autentia.rest.dtos.FriendDto;

public class MockObjectFactory {

    public static Friend getFriendValid() {
        return new Friend("Alberto", "666666666");
    }

    public static Friend getExistFriend() {
        return new Friend("Francisco Buyo", "666666601");
    }

    public static Friend getFriendWithInvalidPhone() {
        return new Friend("Alberto", "6");
    }

    public static Friend getFriendWithRepeatPhone() {
        return new Friend("Alberto", "666666601");
    }

    public static FriendDto getFriendDtoFriendValid() {
        FriendDto out = new FriendDto();
        out.setName(getFriendValid().getName());
        out.setPhone(getFriendValid().getPhone());
        return out;
    }

}
