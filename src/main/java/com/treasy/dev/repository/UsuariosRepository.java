package com.treasy.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treasy.dev.domain.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByParentId(Long parentId);

	//Usuario findByParentId(Long parentId);

	

	

}
