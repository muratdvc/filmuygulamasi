package com.project.demo.filmuygulamasi.entities;

import java.util.ArrayList;
import java.util.List;

public class OyuncuWrapper {

    private List<Oyuncu> oyuncular;

    public OyuncuWrapper() {
        oyuncular = new ArrayList<>();
    }

    public OyuncuWrapper(List<Oyuncu> oyuncular) {
        this.oyuncular = oyuncular;
    }

    public List<Oyuncu> getOyuncular() {
        return oyuncular;
    }

    public void setOyuncular(List<Oyuncu> oyuncular) {
        this.oyuncular = oyuncular;
    }

    public void addOyuncu(Oyuncu oyuncu) {
        oyuncular.add(oyuncu);
    }
}
