package com.project.demo.filmuygulamasi.controller;

import com.project.demo.filmuygulamasi.entities.*;
import com.project.demo.filmuygulamasi.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/film")
public class FilmController {
	
	@Autowired
	FilmService filmService;

	@PostMapping("/save")
	public String saveFilm(Film film, Model model) {
		filmService.addFilm(film);
		model.addAttribute("film", film);
		OyuncuWrapper oyuncuWrapper = new OyuncuWrapper();
		int oyuncuSayisi= 5;
		for (int i = 0; i <oyuncuSayisi; i++) {
			oyuncuWrapper.addOyuncu(new Oyuncu());
		}
		model.addAttribute("oyuncuWrapper", oyuncuWrapper);
		return "film-ekle/oyuncu-ekle";
	}

	@PostMapping("/update")
	public String updateFilm(Film film) {
		filmService.addFilm(film);

		return "redirect:/film/incele?filmId="+ film.getId();
	}

	@PostMapping("/saveOyuncular")
	public String saveOyuncular(OyuncuWrapper oyuncuWrapper, Model model, @RequestParam int filmId) {
		filmService.oyuncuEkle(filmId, oyuncuWrapper.getOyuncular());
		DilWrapper dilWrapper = new DilWrapper();
		for (int i = 0; i < 3; i++) {
			dilWrapper.getDilSecenekleri().add(new DilSecenekleri());
		}
		model.addAttribute("film", filmService.fetchFilm(filmId));
		model.addAttribute("oyuncuWrapper", oyuncuWrapper);
		model.addAttribute("dilWrapper", dilWrapper);

		return "film-ekle/dil-ekle";
	}

	@PostMapping("/saveDiller")
	public String saveDiller(DilWrapper dilWrapper, @RequestParam int filmId) {
		filmService.dilEkle(filmId, dilWrapper.getDilSecenekleri());
		int page = Math.max(1, Math.round((filmService.fetchFilms().size()/12f))); // ******
		return "redirect:/film/list";
	}

	@GetMapping("/list")
	public String fetchFilms(Model theModel, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(12);

		Page<Film> filmPage = filmService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		theModel.addAttribute("filmPage", filmPage);

		int totalPages = filmPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
					.collect(Collectors.toList());
			theModel.addAttribute("pageNumbers", pageNumbers);
		}
		return "film-list";
	}

	@GetMapping("/incele")
	public String fetchFilm(@RequestParam("filmId") int filmId, Model filmModel) {
		filmModel.addAttribute("film", filmService.fetchFilm(filmId));

		// rastgele 4 film onerisi
		List<Film> filmler = filmService.fetchFilms();
		List<Film> rastgeleFilmler = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			rastgeleFilmler.add(filmler.get(new Random().nextInt(filmler.size())));
		}
		filmModel.addAttribute("filmOnerisi", rastgeleFilmler);
		return "film-incele";
	}

	@GetMapping("showFormUpdate")
	public String updateFilm(@RequestParam int filmId, Model filmModel) {
		Film film = filmService.fetchFilm(filmId);
		filmModel.addAttribute("film", film);
		return "film-form";
	}

	@GetMapping("showFormFilmEkle")
	public String saveFilm(Model model) {
		model.addAttribute("film", new Film());
		model.addAttribute("oyuncular", new ArrayList<Oyuncu>());
		model.addAttribute("dilSecenekleri", new ArrayList<DilSecenekleri>());
		return "film-ekle/film-ekle";
	}

	@GetMapping("showFormUpdateOyuncu")
	public String updateOYyuncu(@RequestParam int filmId, Model oyuncuModel) {
		Film film = filmService.fetchFilm(filmId);
		List<Oyuncu> oyuncular = film.getOyuncular();
		OyuncuWrapper oyuncuWrapper = new OyuncuWrapper();
		oyuncuModel.addAttribute("oyuncuWrapper", new OyuncuWrapper(oyuncular));
		return "oyuncu-form";
	}

	@PostMapping("/saveOyuncu")
	public String saveOyuncu(OyuncuWrapper oyuncuWrapper, @RequestParam String filmId) {
		int filmIdNum = Integer.parseInt(filmId);
		filmService.oyuncuUpdate(filmIdNum, oyuncuWrapper.getOyuncular());

		return "redirect:/film/incele?filmId=" + filmIdNum;
	}

	@GetMapping("/deleteFilm")
	public String deleteFilm(@RequestParam("filmId") int filmId, @RequestParam int page) {
		filmService.deleteFilm(filmId);

		return "redirect:/film/list?size=12&page="+page;
	}

	@GetMapping("/deleteFilmBy")
	public String deleteFilmBy(@RequestParam("filmId") int filmId) {
		filmService.deleteFilm(filmId);

		return "redirect:/film/list";
	}


	@GetMapping("/search")
	public String searchByFilm(String filmName, Model filmModel) {
		List<Film> films = filmService.searchByFilm(filmName);
		filmModel.addAttribute("films", films);

		return "search-film";
	}

}

























































