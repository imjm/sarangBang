package com.sarangBang.sarangBang.member.domain;

public enum Sex {
    MALE("남성"), FEMALE("여성");

    private final String description;

    Sex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
