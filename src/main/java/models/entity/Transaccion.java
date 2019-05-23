package models.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name="transaccion")
public class Transaccion implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private int transactionType;

	@NotNull
	private double amount;

	@NotNull
	private Timestamp timestamp;
	
	@NotNull
	private String description;
	
	@ManyToOne
	@JoinColumn
	private Cliente cliente;	
	
	@ManyToOne
	@JoinColumn
	@Nullable
	private CategoriaPersonalizada categoriaPersonalizada;	
	@ManyToOne
	@JoinColumn
	@Nullable
	private CategoriaPredefinida categoriaPredefinida;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public CategoriaPersonalizada getCategoriaPersonalizada() {
		return categoriaPersonalizada;
	}
	public void setCategoriaPersonalizada(CategoriaPersonalizada categoriaPersonalizada) {
		this.categoriaPersonalizada = categoriaPersonalizada;
	}
	public CategoriaPredefinida getCategoriaPredefinida() {
		return categoriaPredefinida;
	}
	public void setCategoriaPredefinida(CategoriaPredefinida categoriaPredefinida) {
		this.categoriaPredefinida = categoriaPredefinida;
	}
	
}
