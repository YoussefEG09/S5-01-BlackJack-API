package com.example.blackajck_api.domain.model;

public class Player {

    private String name;


    public Player(String name) {
        this.name = name;

    }

    public String getName(){
        return name;
    }


    public void changeName(String newName) {
        this.name = newName;

    }
}
