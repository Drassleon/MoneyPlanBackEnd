package models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="categoriaPredefinida")
public class CategoriaPredefinida implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;
	@NotNull
	private String description;
	
	@OneToMany(mappedBy="categoriaPredefinida"
			,fetch=FetchType.LAZY,
			cascade=CascadeType.ALL)
	private List<Transaccion> transaccion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Transaccion> getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(List<Transaccion> transaccion) {
		this.transaccion = transaccion;
	}
	
	public void addTransaccion(Transaccion transaccion)
	{
		this.transaccion.add(transaccion);
	}
}
