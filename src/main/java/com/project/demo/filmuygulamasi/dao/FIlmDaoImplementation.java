package com.project.demo.filmuygulamasi.dao;

import com.project.demo.filmuygulamasi.entities.DilSecenekleri;
import com.project.demo.filmuygulamasi.entities.Film;
import com.project.demo.filmuygulamasi.entities.Oyuncu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FIlmDaoImplementation implements FilmDao {


	//@Autowired
	EntityManager entityManager;

	@Autowired
	public FIlmDaoImplementation(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public Film fetchFilm(int filmId) {
		return entityManager.find(Film.class, filmId);
	}

	@Override
	public List<Film> fetchFilms() {
		return entityManager.createQuery("from Film", Film.class).getResultList();
	}

	@Override
	public void addFilm(Film film) {

		Film dbFilm = entityManager.merge(film);

		// update with id from db ... so we can get generated id for save/insert
		film.setId(dbFilm.getId());
	}

	@Override
	public void deleteFilm(int filmId) {
		Film film = entityManager.find(Film.class, filmId);
		for (Oyuncu oyuncu : film.getOyuncular()) {
			entityManager.remove(oyuncu);
		}
		for (DilSecenekleri dilSecenekleri : film.getDilSecenekleri()) {
			entityManager.remove(dilSecenekleri);
		}
		entityManager.remove(film);
	}


	@Override
	public void dilEkle(int filmId, List<DilSecenekleri> dilSecenekleri) {
		Film film = entityManager.find(Film.class, filmId);
		for (DilSecenekleri dil : dilSecenekleri) {
            if (!(dil.getDil() == null || dil.getDil().isEmpty())) {
			    film.dilEkle(dil);
            }
			entityManager.merge(dil);
		}
	}

	@Override
	public List<DilSecenekleri> fetchLanguages() {
		return entityManager.createQuery("from DilSecenekleri", DilSecenekleri.class).getResultList();
	}

	@Override
	public void oyuncuEkle(int filmId, List<Oyuncu> oyuncular) {
		Film film = entityManager.find(Film.class, filmId);
		for (Oyuncu oyuncu : oyuncular) {
		    if (!(oyuncu.getTamIsim() == null || oyuncu.getTamIsim().isEmpty())) {
			    film.oyuncuEkle(oyuncu);
            }
			entityManager.merge(oyuncu);
		}
	}

	@Override
	public void oyuncuUpdate(int filmIdNum, List<Oyuncu> oyuncular) {
		Film film = entityManager.find(Film.class, filmIdNum);
		for (Oyuncu oyuncu : oyuncular) {
			oyuncu.setFilm(film);
			entityManager.merge(oyuncu);
		}
	}
	@Override
	public List<Oyuncu> fetchOyuncular(int filmId) {
		return entityManager.find(Film.class, filmId).getOyuncular();
	}

	@Override
	public List<Film> searchBy(String filmName) {
		List films;
		if(filmName != null || !filmName.trim().isEmpty()) {
			 Query query = entityManager.createQuery("FROM Film f WHERE f.isim LIKE :search", Film.class);
			 query.setParameter("search", "%"+filmName+"%");
			 films = query.getResultList();
			return films;
		}
		return null;
	}



}

















