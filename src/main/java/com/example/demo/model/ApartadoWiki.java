package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase Apartado de la wikipedia
 * @author usuario
 *
 */
@Entity
@Table(name = "ApartadoWiki")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApartadoWiki {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idApartado;
	
	@Column(name="nombreApartado", nullable = false)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<InfoWiki> info = new ArrayList<>();


	public ApartadoWiki (String name) {
		this.name = name;
	}
	
}
