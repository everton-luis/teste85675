package com.treasy.dev.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.treasy.dev.domain.Children;
import com.treasy.dev.domain.Usuario;
import com.treasy.dev.repository.ChildrenRepository;
import com.treasy.dev.repository.UsuariosRepository;
import com.treasy.dev.services.UsuariosService;
import com.treasy.dev.services.exceptions.ParentIdNaoEncontradoException;


@RestController
@RequestMapping("/node")
public class UsuariosResources {
	
	
	@Autowired
	private UsuariosService usuariosService;
	
	
	@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<List<Usuario>> listar() {
			
			List<Usuario> usuario = usuariosService.listar();
		
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
			
			
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Usuario usuario) {
		usuario = usuariosService.salvar(usuario);
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	@RequestMapping(value = "/{parentId}" , method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscarParentId(@PathVariable("parentId") Long parentId) throws Exception{
		
		List<Usuario> usuario = usuariosService.buscarParentId(parentId);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	//@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	//public ResponseEntity<Usuario> buscarId(@PathVariable("id") Long id){
		//Usuario usuario = usuariosService.buscarId(id);
		//return ResponseEntity.status(HttpStatus.OK).body(usuario);
	//}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) throws Exception{
		usuariosService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Usuario usuario) {
		
		usuariosService.atualizar(usuario);
		
		return ResponseEntity.noContent().build();
	}
	
	//@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	//public ResponseEntity<Void> atualizarId(@RequestBody Usuario usuario,
			//@PathVariable("id") Long id){
		//usuariosService.atualizarId(id, usuario);
		//return ResponseEntity.noContent().build();
	//}
	
	
	@RequestMapping(value = "/{id}/children", method = RequestMethod.POST)
		public ResponseEntity<Void> adicionarChildren(@PathVariable("id") Long id,
				@RequestBody Children children) {
			
				usuariosService.salvarChildren(id, children);
				
				
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
				return ResponseEntity.created(uri).build();
				
		}
	
	
	@RequestMapping(value = "/children/{id}", method = RequestMethod.GET)
		public ResponseEntity<Children> buscarChildren(@PathVariable("id") Long id)  {
			Children children = usuariosService.buscarChildren(id);
			return ResponseEntity.status(HttpStatus.OK).body(children);
	}
	
	
	@RequestMapping(value = "/children/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> deletarChildren(@PathVariable("id") Long id) {
		
		usuariosService.deletarChildren(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	@RequestMapping(value = "/{id_usuario}/children/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Void> atualizarChildren(@RequestBody Children children, 
			@PathVariable("id_usuario") Long id_usuario, @PathVariable("id") Long id) {
				
				usuariosService.atualizarChildren(id_usuario, children, id);
					
				return ResponseEntity.noContent().build();
				
		}
	
	
}
