package com.project.demo.filmuygulamasi.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Data
@Table(name = "film")
public class Film {
	
	@Id
	@Column(name= "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name = "isim")
	private String isim;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "yayin_yili")
	private Date yayinYili;
	
	@Column(name = "tur")
	private String tur;
	
	@Column(name = "aciklama")
	private String aciklama;
	
	@Column(name = "medya")
	private String medya;

	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REFRESH},
				fetch = FetchType.LAZY,
				mappedBy = "film")
	private List<Oyuncu> oyuncular;


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "dil_id")
	private List<DilSecenekleri> dilSecenekleri;
	
	public Film() {}

	public Film(String isim, Date yayinYili, String tur, String aciklama, String medya) {
		this.isim = isim;
		this.yayinYili = yayinYili;
		this.tur = tur;
		this.aciklama = aciklama;
		this.medya = medya;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public Date getYayinYili() {
		return yayinYili;
	}

	public void setYayinYili(Date yayinYili) {
		this.yayinYili = yayinYili;
	}

	public String getTur() {
		return tur;
	}

	public void setTur(String tur) {
		this.tur = tur;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getMedya() {
		return medya;
	}

	public void setMedya(String medya) {
		this.medya = medya;
	}
	
	public List<Oyuncu> getOyuncular() {
		return oyuncular;
	}

	public void setOyuncular(List<Oyuncu> oyuncular) {
		this.oyuncular = oyuncular;
	}

	public List<DilSecenekleri> getDilSecenekleri() {
		return dilSecenekleri;
	}

	public void setDilSecenekleri(List<DilSecenekleri> dilSecenekleri) {
		this.dilSecenekleri = dilSecenekleri;
	}

	public void oyuncuEkle(Oyuncu yeniOyuncu) {
		
		if (oyuncular == null || oyuncular.isEmpty()) {
			
			oyuncular = new ArrayList<Oyuncu>();
		}
		
		oyuncular.add(yeniOyuncu);
		
		// iliski bi-directioanl oldugu icin filmi de oyuncuya set etmeliyiz bu sayede film_id foreign key'i de atanmis oluyor
		yeniOyuncu.setFilm(this);

	}

	public void dilEkle(DilSecenekleri yeniDil) {

		if(dilSecenekleri == null || dilSecenekleri.isEmpty()) {
			dilSecenekleri = new ArrayList<>();
		}

		dilSecenekleri.add(yeniDil);

	}
	
	
}
