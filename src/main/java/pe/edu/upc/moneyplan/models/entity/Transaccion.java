package pe.edu.upc.moneyplan.models.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
@Table(name="transaccion")
public class Transaccion implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name="transaction_type")
	private int transactionType;

	@NotNull
	@Column(name="amount")
	private double amount;

	@NotNull
	@Column(name="start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;	
	
	@ManyToOne
	@JoinColumn(name="categoria_personalizada")
	@Nullable
	private CategoriaPersonalizada categoriaPersonalizada;	
	@ManyToOne
	@JoinColumn(name="categoria_predefinida")
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
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
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
