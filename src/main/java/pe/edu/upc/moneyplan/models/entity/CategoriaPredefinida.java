package pe.edu.upc.moneyplan.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	/*@OneToMany(mappedBy="categoriaPredefinida"
			,fetch=FetchType.EAGER,
			cascade=CascadeType.ALL)
	private List<Transaccion> transacciones;*/

	public CategoriaPredefinida() {
		//transacciones = new ArrayList<>();
	}
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

	/*public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
	
	public void addTransaccion(Transaccion transaccion)
	{
		this.transacciones.add(transaccion);
	}*/
}
