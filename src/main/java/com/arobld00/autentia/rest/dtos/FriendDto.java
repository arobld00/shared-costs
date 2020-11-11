package com.arobld00.autentia.rest.dtos;

import com.arobld00.autentia.domain.Friend;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FriendDto {

    private String name;

    @NotNull
    @Pattern(regexp = "\\d{9}")
    private String phone;

    public FriendDto() {
        // Empty for framework
    }

    public FriendDto(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public FriendDto(Friend friend) {
        this.name = friend.getName();
        this.phone = friend.getPhone();
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
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (phone.equals(((FriendDto) obj).phone));
    }

    @Override
    public int hashCode() {
        return this.phone.hashCode();
    }

    @Override
    public String toString() {
        return "FriendDto{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
