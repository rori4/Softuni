package com.springbootlecture.entities;

public class Dog implements Animal {

    private Long id;
    private String name;

    public Dog(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
