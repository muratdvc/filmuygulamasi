package com.project.demo.filmuygulamasi.service;

import com.project.demo.filmuygulamasi.dao.FilmDao;
import com.project.demo.filmuygulamasi.entities.DilSecenekleri;
import com.project.demo.filmuygulamasi.entities.Film;
import com.project.demo.filmuygulamasi.entities.Oyuncu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class FilmServiceImplementation implements FilmService {

	@Autowired
	FilmDao filmDao;


	@Override
	@Transactional
	public Film fetchFilm(int filmId) {
		return filmDao.fetchFilm(filmId);
	}

	@Override
	@Transactional
	public List<Film> fetchFilms() {
		return filmDao.fetchFilms();
	}

	@Override
	@Transactional
	public void addFilm(Film film) {
		filmDao.addFilm(film);
	}

	@Override
	@Transactional
	public void deleteFilm(int filmId) {
		filmDao.deleteFilm(filmId);
	}

	@Override
	@Transactional
	public List<DilSecenekleri> fetchLanguages() {
		return filmDao.fetchLanguages();
	}

	@Override
	@Transactional
	public List<Film> searchByFilm(String filmName) {
		return filmDao.searchBy(filmName);
	}

	@Override
	@Transactional
	public Page<Film> findPaginated(PageRequest pageable) {
		List<Film> films = filmDao.fetchFilms();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Film> list;

		if (films.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, films.size());
			list = films.subList(startItem, toIndex);
		}

		Page<Film> bookPage = new PageImpl<Film>(list, PageRequest.of(currentPage, pageSize), films.size());
		return bookPage;
	}

	@Override
	@Transactional
	public void dilEkle(int filmId, List<DilSecenekleri> dilSecenekleri) {
		filmDao.dilEkle(filmId, dilSecenekleri);
	}

	@Override
	@Transactional
	public void oyuncuUpdate(int filmIdNum, List<Oyuncu> oyuncular) {
		filmDao.oyuncuUpdate(filmIdNum, oyuncular);
	}

	@Override
	@Transactional
	public void oyuncuEkle(int filmId, List<Oyuncu> oyuncular) {
		filmDao.oyuncuEkle(filmId, oyuncular);
	}

}
