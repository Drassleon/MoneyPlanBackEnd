package models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nombre;

	@NotNull
	private String telefono;

	@NotNull
	private String docId;
	
	@NotNull
	private String docIdType;
	
	@NotNull
	@Email
	private String email;

	@OneToMany(mappedBy="cliente"
			,fetch=FetchType.LAZY,
			cascade=CascadeType.ALL)
	private List<MetaAhorro> metas;
	public Cliente() {
		metas = new ArrayList<>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocIdType() {
		return docIdType;
	}
	public void setDocIdType(String docIdType) {
		this.docIdType = docIdType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<MetaAhorro> getMetas() {
		return metas;
	}
	public void setMetas(List<MetaAhorro> metas) {
		this.metas = metas;
	}
	
}
