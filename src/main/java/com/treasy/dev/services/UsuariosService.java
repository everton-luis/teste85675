package com.treasy.dev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.treasy.dev.domain.Children;
import com.treasy.dev.domain.Usuario;
import com.treasy.dev.repository.ChildrenRepository;
import com.treasy.dev.repository.UsuariosRepository;
import com.treasy.dev.services.exceptions.ChildrenIdNaoEncontradoException;
import com.treasy.dev.services.exceptions.IdNaoEncontradoException;
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
	
	
			
	public List<Usuario> buscarParentId(Long parentId) {
			
			List<Usuario> usuarios = usuariosRepository.findByParentId(parentId);
			if(usuarios.size() == 0) {
				throw new ParentIdNaoEncontradoException("O parentId não pode ser encontrado"); 
			}
			return usuarios;
	}
	
	//public Usuario buscarId(Long id) {
		//Usuario usuario = usuariosRepository.findOne(id);
		
		//if(usuario == null) {
			//throw new IdNaoEncontradoException("id não encontrado");
		//}
		
		//return usuario;
	//}
	
	public Usuario salvar(Usuario usuario) {
		return usuariosRepository.save(usuario);
	}
	
	
	
	public void deletar(Long id)  {
		try {
			usuariosRepository.delete(id);
		}catch(EmptyResultDataAccessException e) {
			throw new IdNaoEncontradoException("O id não pode ser encontrado");
		}
	}
	
	public void atualizar(Usuario usuario) {
		
		usuariosRepository.save(usuario);
	}
	
	//public Usuario atualizarId(Long id, Usuario usuario) {
		//Usuario usuario1 = usuariosRepository.findOne(id);
		
		//if(usuario1 == null) {
			//throw new IdNaoEncontradoException("id não encontrado");	
		//}
		
		//usuario.setId(id);
		
		//return usuariosRepository.save(usuario);
		
	//}
	
	
	
	public Children salvarChildren(Long usuarioId, Children children)  {
		Usuario usuario = usuariosRepository.findOne(usuarioId);
		
		if(usuario == null) {
			throw new IdNaoEncontradoException("id não encontrado");
		}
		
		children.setUsuario(usuario);
		
		return childrenRepository.save(children);
		
	}
	
	
	
	public Children buscarChildren(Long id)  {
		Children children = childrenRepository.findOne(id);
		
		if(children == null) {
			throw new ChildrenIdNaoEncontradoException("children id não encontrado");
		}
		
		return children;
		
	}
	
	
	public void deletarChildren(Long id)  {
			
		try {
			childrenRepository.delete(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ChildrenIdNaoEncontradoException("o id children não pode ser encontrado");
		}
	}
	
	
	
	public Children atualizarChildren(Long id_usuario,Children children, Long id) {
	
			
				Usuario usuario = usuariosRepository.findOne(id_usuario);
				children.setUsuario(usuario);
				Children children1 = childrenRepository.findOne(id);
				children.setId(id);
			
				if(usuario == null) {
					throw new IdNaoEncontradoException("usuario id não encontrado");
				}
			
				if(children1 == null) {
					throw new ChildrenIdNaoEncontradoException("children id não encontrado");
				}
			
			
				return childrenRepository.save(children);
			
		}
	
	
	
	
	
}
