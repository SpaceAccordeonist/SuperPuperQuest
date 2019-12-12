package com.company;

public class Player {
    private String name;
    private int health = 100;

    public Player(String name, int health){
        this.name = name;
        this.health = health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth(){
        return health;
    }
    public void deliverDamage(int count){
        health -= count;
    }
}
