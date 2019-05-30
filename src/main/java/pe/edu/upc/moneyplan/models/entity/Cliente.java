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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name="name")
	private String name;

	@NotNull
	@Column(name="phone_number")
	private String phoneNumber;

	@NotNull
	@Column(name="doc_id")
	private String docId;
	
	@NotNull
	@Column(name="doc_id_type")
	private String docIdType;
	
	@NotNull
	@Column(name="email")
	@Email
	private String email;

	/*@OneToMany(mappedBy="cliente"
			,fetch=FetchType.EAGER,
			cascade=CascadeType.ALL)
	private List<MetaAhorro> metasAhorro;
	
	@OneToMany(mappedBy="cliente",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<CategoriaPersonalizada> categoriasPersonalizadas;
	
	@OneToMany(mappedBy="cliente",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Recompensa> recompensas;
	
	@OneToMany(mappedBy="cliente",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Transaccion> transacciones;*/
	
	public Cliente() {
		/*metasAhorro = new ArrayList<>();
		categoriasPersonalizadas = new ArrayList<>();
		recompensas = new  ArrayList<>();*/

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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	/*public List<MetaAhorro> getMetasAhorro() {
		return metasAhorro;
	}
	public void setMetasAhorro(List<MetaAhorro> savingsGoal) {
		this.metasAhorro = savingsGoal;
	}
	public void addMetaAhorro(MetaAhorro metaAhorro)
	{
		this.metasAhorro.add(metaAhorro);
	}
	public List<CategoriaPersonalizada> getCategoriasPersonalizadas() {
		return categoriasPersonalizadas;
	}
	public void setCategoriasPersonalizadas(List<CategoriaPersonalizada> categoriasPersonalizadas) {
		this.categoriasPersonalizadas = categoriasPersonalizadas;
	}
	public void addCategoriaPersonalizada(CategoriaPersonalizada categoriaPersonalizada)
	{
		this.categoriasPersonalizadas.add(categoriaPersonalizada);
	}
	public List<Recompensa> getRecompensas() {
		return recompensas;
	}
	public void setRecompensas(List<Recompensa> recompensas) {
		this.recompensas = recompensas;
	}
	public void addRecompensa(Recompensa recompensa) {
		this.recompensas.add(recompensa);
	}
	public List<Transaccion> getTransacciones() {
		return transacciones;
	}
	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
	public void addTransaccion(Transaccion transaccion) {
		this.transacciones.add(transaccion);
	}*/
}
