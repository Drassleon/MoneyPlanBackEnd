package pe.edu.upc.moneyplan.models.entity;

import java.io.Serializable;
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
import org.springframework.lang.Nullable;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "transaction_type")
	private int transactionType;

	@NotNull
	@Column(name = "amount")
	private double amount;

	@NotNull
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@NotNull
	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "custom_category_id")
	@Nullable
	private CustomCategory customCategory;
	@ManyToOne
	@JoinColumn(name = "default_category_id")
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
