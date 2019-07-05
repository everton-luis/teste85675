package com.treasy.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.treasy.dev.domain.Children;
import com.treasy.dev.domain.Usuario;
import com.treasy.dev.repository.ChildrenRepository;
import com.treasy.dev.repository.UsuariosRepository;
import com.treasy.dev.services.exceptions.ParentIdNaoEncontradoException;

@Service
public class UsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private ChildrenRepository childrenRepository;
	
	public List<Usuario> listar(){
		return usuariosRepository.findAll();
	}
	
	public List<Usuario> listaParentId(Long parentId){
		List<Usuario> usuarios = usuariosRepository.findByParentId(parentId);
		
		if(usuarios.size() == 0) {
			throw new ParentIdNaoEncontradoException("parentId não encontrado");
		}
		
		return usuarios;
		
	}
	
	public Usuario salvar(Usuario usuario) {
		return usuariosRepository.save(usuario);
	}
	
	public void deletar(Long id) {
		
			usuariosRepository.delete(id);
		
	}
	
	public void atualizar(Usuario usuario) {
		usuariosRepository.save(usuario);
	}
	
	
	
	
	
}
