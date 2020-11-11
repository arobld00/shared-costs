package com.arobld00.autentia.domain;

public class Friend {

    private String id;

    private String name;

    private String phone;

    public Friend() {
        // Empty for framework
    }

    public Friend(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        return this.phone.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (phone.equals(((Friend) obj).phone));
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
