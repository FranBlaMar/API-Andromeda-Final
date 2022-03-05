package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ComentarioNotFoundException;
import com.example.demo.error.ErroresManager;
import com.example.demo.error.PostNotFoundException;
import com.example.demo.model.Comentario;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;

/**
 * Controller donde gestionar las peticiones a la clase post
 * @author usuario
 *
 */
@RestController
public class PostController {

	@Autowired
	private PostRepository repositorio;

	@Autowired
	private PostService servicio;
	
	/**
	 * Método para obtener todos los posts de la base de datos
	 * @return Lista con todos los posts
	 */
	@GetMapping("/post")
	public List<Post> getPosts() {
		return this.repositorio.findAll();
	}
	
	/**
	 * Método para crear un nuevo post en la base de datos 
	 * @param postNuevo
	 * @return Post guardado en la base de datos
	 */
	@PostMapping("/post")
	public ResponseEntity<Post> setPost(@RequestBody Post postNuevo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.crearPost(postNuevo));
	}
	
	/**
	 * Método para obtener un post por su id
	 * @param id
	 * @return Post obtenido mediante su id
	 * @throws PostNotFoundException
	 */
	@GetMapping("/post/{idPost}")
	public Post getPostById(@PathVariable Long idPost) throws PostNotFoundException{
		Post p = this.repositorio.findById(idPost).orElse(null);
		if (p == null) {
			throw new PostNotFoundException();
		}
		return p;
	}
	
	
	/**
	 * Método para modificar un post en la base de datos
	 * @param postModify Post modificado
	 * @param idPost Id del post que vamos a modificar
	 * @return Post modificado
	 */
	@PutMapping("/post/{idPost}")
	public ResponseEntity<Post> modifyPost(@RequestBody Post postModify, @PathVariable Long idPost) {
		return ResponseEntity.status(HttpStatus.CREATED).body( this.servicio.modifyPost(postModify, idPost));
	}

	

	/**
	 * Metodo para obtener todos los comentarios de un post
	 * @param idPost id del post 
	 * @return Todos los comentarios de un post
	 * @throws PostNotFoundException
	 */
	@GetMapping("/post/{idPost}/comentario")
	public List<Comentario> obtenerComentarios(@PathVariable Long idPost) throws PostNotFoundException{
		Post post = this.repositorio.findById(idPost).orElse(null);
		if(post == null) {
			throw new PostNotFoundException();
		}
		return post.getComments();
	}
	

	/**
	 * Metodo para obtener un comentario mediante su id
	 * @param idPost id del post
	 * @param idComent id del comentario
	 * @return comentario que se busca
	 * @throws PostNotFoundException
	 * @throws ComentarioNotFoundException
	 */
	@GetMapping("/post/{idPost}/comentario/{idComent}")
	public Comentario obtenerComentarioPorId(@PathVariable Long idPost, @PathVariable Long idComent) throws PostNotFoundException, ComentarioNotFoundException{
		Post post = this.repositorio.findById(idPost).orElse(null);
		if(post == null) {
			throw new PostNotFoundException();
		}
		Comentario comment = this.servicio.obtenerComentarioPorId(post, idComent);
		if(comment == null) {
			throw new ComentarioNotFoundException();
		}
		return comment;
	}
	
	/**
	 * Metodo para modificar un comentario
	 * @param idPost id del post al que pertenece el comentario
	 * @param idComent id del comentario que vamos a modificar
	 * @param comentarioModificado Comentario ya modificado
	 * @return El comentario modificado
	 * @throws PostNotFoundException
	 * @throws ComentarioNotFoundException
	 */
	@PutMapping("/post/{idPost}/comentario/{idComent}")
	public  ResponseEntity<Comentario> modificarComentarioPorId(@PathVariable Long idPost, @PathVariable Long idComent, @RequestBody Comentario comentarioModificado) throws PostNotFoundException, ComentarioNotFoundException{
		Post post = this.repositorio.findById(idPost).orElse(null);
		if(post == null) {
			throw new PostNotFoundException();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.modifyComentario(post, idComent, comentarioModificado));
	}
	
	/**
	 * Metodo para eliminar un comentario
	 * @param idPost id del post al que pertenece el comentario
	 * @param idComent id del comentaio que deseamos borrar
	 * @return El comentario borrado
	 * @throws PostNotFoundException
	 * @throws ComentarioNotFoundException
	 */
	@DeleteMapping("/post/{idPost}/comentario/{idComent}")
	public Comentario eliminarComentarioPorId(@PathVariable Long idPost, @PathVariable Long idComent) throws PostNotFoundException, ComentarioNotFoundException{
		Post post = this.repositorio.findById(idPost).orElse(null);
		if(post == null) {
			throw new PostNotFoundException();
		}
		Comentario comment = this.servicio.obtenerComentarioPorId(post, idComent);
		if(comment == null) {
			throw new ComentarioNotFoundException();
		}
		return this.servicio.eliminarComentario(post, comment);
	}
	
	
	
	/**
	 * Metodo para crear un comentario
	 * @param idPost Id del psot al que pertenece el comentario
	 * @param comentarioNuevo Comentario que vamos a añadir
	 * @return COmentario añadido
	 * @throws PostNotFoundException
	 */
	@PostMapping("/post/{idPost}/comentario")
	public ResponseEntity<Comentario> crearComentario(@PathVariable Long idPost, @RequestBody Comentario comentarioNuevo) throws PostNotFoundException{
		Post post = this.repositorio.findById(idPost).orElse(null);
		if(post == null) {
			throw new PostNotFoundException();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(this.servicio.crearComentario(post, comentarioNuevo));
	}
	
	
	/**
	 * Metodo handler de exception de comentarios no encontrados
	 * @param ex excepción lanzada
	 * @return la excepción modificada por nosotros
	 */
	@ExceptionHandler(ComentarioNotFoundException.class)
	public ResponseEntity<ErroresManager> handleComentarioNoEncontrado(ComentarioNotFoundException ex) {
		ErroresManager apiError = new ErroresManager();
		apiError.setEstadoPeticion(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensajeDeError(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	/**
	 * Metodo handler de exception de posts no encontrados
	 * @param ex excepción lanzada
	 * @return la excepción modificada por nosotros
	 */
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ErroresManager> handlePostNoEncontrado(PostNotFoundException ex) {
		ErroresManager apiError = new ErroresManager();
		apiError.setEstadoPeticion(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensajeDeError(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
}