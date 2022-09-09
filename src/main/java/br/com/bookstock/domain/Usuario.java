package br.com.bookstock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USER_ENTITY", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Usuario extends PanacheEntityBase {
	
	@Id
	private String id;
	
	private String email;
	
	@Column(name = "FIRST_NAME")
	private String nome;
	
	@Column(name = "LAST_NAME")
	private String sobrenome;
	
	private String username;
	
	private Boolean enabled;
	
}
