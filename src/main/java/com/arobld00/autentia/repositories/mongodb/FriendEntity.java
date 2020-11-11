package com.arobld00.autentia.repositories.mongodb;

import com.arobld00.autentia.domain.Friend;
import com.arobld00.autentia.rest.dtos.FriendDto;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class FriendEntity {

    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String phone;

    public FriendEntity() {
        // Empty for framework
    }

    public FriendEntity(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public FriendEntity(Friend friend) {
        BeanUtils.copyProperties(friend, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Friend toFriend() {
        Friend friend = new Friend();
        BeanUtils.copyProperties(this, friend);
        return friend;
    }

    @Override
    public int hashCode() {
        return this.phone.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (phone.equals(((FriendEntity) obj).phone));
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
