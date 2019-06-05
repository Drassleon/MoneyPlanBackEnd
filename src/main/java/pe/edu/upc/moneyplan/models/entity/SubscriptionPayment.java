package pe.edu.upc.moneyplan.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SubscriptionPayment")
public class SubscriptionPayment {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@NotNull
	@Column(name = "BillingDate")
	private Date billingDate;

	@NotNull
	@Column(name = "Amount")
	private double amount;

	@NotNull
	@Column(name = "Description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "Client_Id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "CustomCategory_Id")
	private CustomCategory customCategory;

	@ManyToOne
	@JoinColumn(name = "DefaultCategory_Id")
	private DefaultCategory defaultCategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public void setClient(Client client) {
		this.client = client;
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
