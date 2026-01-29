package com.example.blackajck_api.domain.model;

public class Player {

    private String id;
    private String name;
    private Hand hand;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.hand = new Hand();
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public void changeName(String newName) {
        this.name = newName;

    }
}
