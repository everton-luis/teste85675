package com.treasy.dev.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Usuario {
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonInclude(Include.NON_NULL)
	//@Id
	//@GeneratedValue(generator = "increment")
	//@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	private String code;
	
	@JsonInclude(Include.NON_NULL)
	private String description;
	
	@JsonInclude(Include.NON_NULL)
	private Long parentId;
	
	@JsonInclude(Include.NON_NULL)
	private String detail;
	
	@JsonInclude(Include.NON_NULL)
	@OneToMany(mappedBy = "usuario")
	private List<Children> childrens;
	

	public Usuario() {
		
	}
	
	public Usuario(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<Children> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Children> childrens) {
		this.childrens = childrens;
	}
	
	
}
