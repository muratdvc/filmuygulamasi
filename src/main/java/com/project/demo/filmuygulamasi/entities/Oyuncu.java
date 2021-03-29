package com.project.demo.filmuygulamasi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "oyuncu")
public class Oyuncu {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tam_isim")
	private String tamIsim;
	
	@Column(name = "rol")
	private String rol;
	
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	 ,fetch = FetchType.LAZY) //--> fetchtype i lazy yapinca oyuncu getirmede sikinti oluyo
	@JoinColumn(name = "film_id")
	private Film film;

	public Oyuncu() {}
	
	public Oyuncu(String tamIsim, String rol) {
		this.tamIsim = tamIsim;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTamIsim() {
		return tamIsim;
	}

	public void setTamIsim(String tamIsim) {
		this.tamIsim = tamIsim;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@Override
	public String toString() {
		return "Oyuncu [id=" + id + ", tamIsim=" + tamIsim + ", rol=" + rol + ", film=" + film + "]";
	}

}
