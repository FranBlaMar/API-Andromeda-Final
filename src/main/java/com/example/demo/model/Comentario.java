package com.example.demo.model;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase comentario
 * @author Usuario
 *
 */
@Entity
@Table(name = "Comentario")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comentario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComment;
	
	@Column (name="cuerpoComentario", nullable = false,length = 2500)
	private String comment;
	
	
	@ManyToOne
	private User author;
	
	public Comentario (String comment, User author) {
		this.comment = comment;
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComment);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return Objects.equals(idComment, other.idComment);
	}
	
	
}
