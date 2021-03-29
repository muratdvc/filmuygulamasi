package com.project.demo.filmuygulamasi.entities;

import javax.persistence.*;

@Entity
@Table(name = "dil_secenekleri")
public class DilSecenekleri {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //????
    private int id;

    @Column(name = "dil")
    private String dil;

    public DilSecenekleri() {}

    public DilSecenekleri(String dil) {
        this.dil = dil;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDil() {
        return dil;
    }

    public void setDil(String dil) {
        this.dil = dil;
    }
}