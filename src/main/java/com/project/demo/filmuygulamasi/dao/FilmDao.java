package com.project.demo.filmuygulamasi.dao;


import com.project.demo.filmuygulamasi.entities.DilSecenekleri;
import com.project.demo.filmuygulamasi.entities.Film;
import com.project.demo.filmuygulamasi.entities.Oyuncu;

import java.util.List;


public interface FilmDao {

    Film fetchFilm(int filmId);
	List<Film> fetchFilms();
	void addFilm(Film film);
	void deleteFilm(int filmId);

	List<DilSecenekleri> fetchLanguages();
	void dilEkle(int filmId, List<DilSecenekleri> dilSecenekleri);

    List<Oyuncu> fetchOyuncular(int filmId);
	void oyuncuEkle(int filmId, List<Oyuncu> oyuncular);
    void oyuncuUpdate(int filmIdNum, List<Oyuncu> oyuncular);

    List<Film> searchBy(String filmName);



}
