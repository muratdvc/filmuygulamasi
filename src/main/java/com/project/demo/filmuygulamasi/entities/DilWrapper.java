package com.project.demo.filmuygulamasi.entities;

import java.util.ArrayList;
import java.util.List;

public class DilWrapper {


    private List<DilSecenekleri> dilSecenekleri;

    public DilWrapper() {
        dilSecenekleri = new ArrayList<>();
    }

    public DilWrapper(List<DilSecenekleri> dilSecenekleri) {
        this.dilSecenekleri = dilSecenekleri;
    }


    public List<DilSecenekleri> getDilSecenekleri() {
        return dilSecenekleri;
    }

    public void setDilSecenekleri(List<DilSecenekleri> dilSecenekleri) {
        this.dilSecenekleri = dilSecenekleri;
    }

    public void addDil(DilSecenekleri dilSecenekleri) {
        this.dilSecenekleri.add(dilSecenekleri);
    }
}
