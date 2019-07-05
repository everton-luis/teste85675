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
	private UsuariosRepository usuariosRepository;
	
	
	@Autowired
	private UsuariosService usuariosService;
	
	@Autowired
	private ChildrenRepository childrenRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listar() {
		
		return ResponseEntity.status(HttpStatus.OK).body(usuariosService.listar());
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Usuario usuario) {
		usuario = usuariosService.salvar(usuario);
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	@RequestMapping(value = "/{parentId}", method = RequestMethod.GET)
	public ResponseEntity <List<Usuario>> listarParentId(@PathVariable("parentId") Long parentId){
		
		List<Usuario> usuario = null;
		
		
		try {
			usuario = usuariosService.listaParentId(parentId);
		}catch(EmptyResultDataAccessException e) {
			throw new ParentIdNaoEncontradoException("parentId não encontrado");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
		
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		try {
			
			usuariosService.deletar(id);
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Usuario usuario) {
		
		
		
		usuariosService.atualizar(usuario);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	@RequestMapping(value = "/{id}/children", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarChildren(@PathVariable("id") Long id,
			@RequestBody Children children){
		Usuario usuario = usuariosRepository.findOne(id);
		children.setUsuario(usuario);
		childrenRepository.save(children);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	
	@RequestMapping(value = "/children/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarChildren(@PathVariable("id") Long id){
		try {
			childrenRepository.delete(id);
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	
		@RequestMapping(value = "{id_usuario}/children/{id}", method = RequestMethod.PUT)
			public ResponseEntity<Void> atualizarChildren(@RequestBody Children children, 
				@PathVariable("id_usuario") Long id_usuario, @PathVariable("id") Long id) {
				
				try {
					Usuario usuario = usuariosRepository.findOne(id_usuario);
					children.setUsuario(usuario);
					Children children1 = childrenRepository.findOne(id);
					children.setId(id);
					if(children1 == null) {
						throw new Exception();
					}
					childrenRepository.save(children);
					
				}catch(Exception e) {
					return ResponseEntity.notFound().build();
				}
				
				
				return ResponseEntity.noContent().build();
			}
	
	
	
	
	
}
