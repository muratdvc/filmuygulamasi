package com.project.demo.filmuygulamasi.service;

import com.project.demo.filmuygulamasi.entities.DilSecenekleri;
import com.project.demo.filmuygulamasi.entities.Film;
import com.project.demo.filmuygulamasi.entities.Oyuncu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface FilmService {

	Film fetchFilm(int filmId);
	List<Film> fetchFilms();
	void addFilm(Film film);
	void deleteFilm(int filmId);
	Page<Film> findPaginated(PageRequest of);


	void oyuncuEkle(int filmId, List<Oyuncu> oyuncular);
	void oyuncuUpdate(int filmIdNum, List<Oyuncu> oyuncular);

	void dilEkle(int filmId, List<DilSecenekleri> dilSecenekleri);
	List<DilSecenekleri> fetchLanguages();

	List<Film> searchByFilm(String filmName);

}
