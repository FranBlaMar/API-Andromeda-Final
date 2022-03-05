package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comentario;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;

/**
 * Clase service para la lógica de negocio de los posts
 * @author usuario
 *
 */
@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	@Autowired
	private UsuarioService servicioUsuario;
	
	/**
	 * Método para crear post
	 * @param post nuevo post
	 * @return Post creado
	 */
	public Post crearPost(Post post) {
		User user = this.servicioUsuario.getUserbyUserName(post.getAuthor().getUserName());
		user.setNumberOfPosts(user.getNumberOfPosts()+1);
		this.servicioUsuario.editUsuario(user);
		return this.repository.save(post);
	}
	
	
	/**
	 * Método para modificar un post de la base de datos
	 * @param post post modificado
	 * @param id id del post que vamos a modificar
	 * @return Post modificado
	 */
	public Post modifyPost(Post post, Long id) {
		post.setId(id);
		post.setAuthor(this.repository.findById(id).get().getAuthor());
		post.setComments(this.repository.findById(id).get().getComments());
		return this.repository.save(post);
	}
	
	/**
	 * Metodo para obtener un comentario mediante su id
	 * @param post Post al que pertenece el comentario
	 * @param idComent id del post
	 * @return El post buscado
	 */
	public Comentario obtenerComentarioPorId(Post post, Long idComent) {
		List<Comentario> comentarios = post.getComments();
		Comentario resultado = null;
		for(Comentario c : comentarios) {
			if (c.getIdComment().equals(idComent)) {
				resultado = c;
			}
		}
		return resultado;
	}
	
	
	/**
	 * Método para modificar un comentario de la base de datos
	 * @param post post al que pertenece el comentario
	 * @param idComentario id del Comentario que estamos modificando
	 * @param comentarioModificado Comentario ya modificado
	 * @return Comentario modificado
	 */
	public Comentario modifyComentario(Post post,Long idComentario,  Comentario comentarioModificado) {
		comentarioModificado.setIdComment(idComentario);
		post.getComments().remove(comentarioModificado);
		post.addComentario(comentarioModificado);
		this.repository.save(post);
		return comentarioModificado;
	}
	
	/**
	 * Metodo para eliminar un comentario
	 * @param comentarioAEliminar Comentario que deseamos eliminar
	 * @return Comentario Eliminado
	 */
	public Comentario eliminarComentario(Post post, Comentario comentarioAEliminar) {
		post.getComments().remove(comentarioAEliminar);
		User user = this.servicioUsuario.getUserbyUserName(post.getAuthor().getUserName());
		user.setNumberOfComents(user.getNumberOfComents()-1);
		this.servicioUsuario.editUsuario(user);
		this.repository.save(post);
		return comentarioAEliminar;
	}
	
	
	/**
	 * Metodo para crear un comentario
	 * @param post Post al que añadimos el comentario
	 * @param comentarioAAñadir comentario que queremos añadir
	 * @return El comentario añadido
	 */
	public Comentario crearComentario(Post post, Comentario comentarioNuevo) {
		post.addComentario(comentarioNuevo);
		User user = this.servicioUsuario.getUserbyUserName(comentarioNuevo.getAuthor().getUserName());
		user.setNumberOfComents(user.getNumberOfComents()+1);
		this.servicioUsuario.editUsuario(user);
		this.repository.save(post);
		return comentarioNuevo;
	}
}

