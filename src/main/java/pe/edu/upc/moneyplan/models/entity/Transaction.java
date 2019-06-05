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
@Table(name = "Transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@NotNull
	@Column(name = "TransactionType")
	private int transactionType;

	@NotNull
	@Column(name = "Amount")
	private double amount;

	@NotNull
	@Column(name = "StartDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@NotNull
	@Column(name = "Description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "Client_Id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "CustomCategory_Id")
	@Nullable
	private CustomCategory customCategory;
	@ManyToOne
	@JoinColumn(name = "DefaultCategory_Id")
	@Nullable
	private DefaultCategory defaultCategory;

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client cliente) {
		this.client = cliente;
	}

	public CustomCategory getCustomCategory() {
		return customCategory;
	}

	public void setCustomCategory(CustomCategory customCategory) {
		this.customCategory = customCategory;
	}

	public DefaultCategory getDefaultCategory() {
		return defaultCategory;
	}

	public void setDefaultCategory(DefaultCategory defaultCategory) {
		this.defaultCategory = defaultCategory;
	}

}
