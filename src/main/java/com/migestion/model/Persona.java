package com.migestion.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

/**
 * Persona
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Persona extends GenericEntity{

	
	/**
	 * identificador de la entity
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)//, generator = "SEQ_DETALLEPAGO")
	//@SequenceGenerator(name = "SEQ_DETALLEPAGO", sequenceName = "SEQ_DETALLEPAGO", allocationSize = 1)
	private Long oid;
		

	/**
	 * nombre
	 */
	@Column
	@NotNull(message="{persona.nombre.required}")
	private String nombre;
	
	/**
	 * email
	 */
	@Column
	private String email;
	
	/**
	 * domicilio
	 */
	@Column
	private String domicilio;
	
	/**
	 * teléfono fijo
	 */
	@Column
	private String telefonoFijo;
	
	/**
	 * teléfono movil
	 */
	@Column
	private String telefonoMovil;
		
	/**
	 * nroDocumento
	 */
	@Column
	private Long nroDocumento;
	
	/**
	 * contacto
	 */
	@Column(columnDefinition="Text")
	private String observaciones;
	
	/**
	 * fecha de nacimiento
	 */
	@Column
	private Date fechaNacimiento;
	
	/**
	 * tipo de documento
	 */
	@Enumerated( value=EnumType.STRING )
	private TipoDocumento tipoDocumento;
	
	/**
	 * página web
	 */
	@Column
	private String paginaWeb;
	
	/**
	 * facebook
	 */
	@Column
	private String facebook;
	
	/**
	 * twitter
	 */
	@Column
	private String twitter;
	
	/**
	 * skype
	 */
	@Column
	private String skype;
	
	/**
	 * linkedin
	 */
	@Column
	private String linkedin;
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.codnet.cuentas.model.ICliente#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see com.codnet.cuentas.model.ICliente#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	@Override
	public String toString(){
		return getNombre();
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the telefonoFijo
	 */
	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	/**
	 * @param telefonoFijo the telefonoFijo to set
	 */
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	/**
	 * @return the telefonoMovil
	 */
	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	/**
	 * @param telefonoMovil the telefonoMovil to set
	 */
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	/**
	 * @return the nroDocumento
	 */
	public Long getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * @param nroDocumento the nroDocumento to set
	 */
	public void setNroDocumento(Long nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * @return the tipoDocumento
	 */
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the paginaWeb
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}

	/**
	 * @param paginaWeb the paginaWeb to set
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	/**
	 * @return the facebook
	 */
	public String getFacebook() {
		return facebook;
	}

	/**
	 * @param facebook the facebook to set
	 */
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	/**
	 * @return the twitter
	 */
	public String getTwitter() {
		return twitter;
	}

	/**
	 * @param twitter the twitter to set
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	/**
	 * @return the skype
	 */
	public String getSkype() {
		return skype;
	}

	/**
	 * @param skype the skype to set
	 */
	public void setSkype(String skype) {
		this.skype = skype;
	}

	/**
	 * @return the linkedin
	 */
	public String getLinkedin() {
		return linkedin;
	}

	/**
	 * @param linkedin the linkedin to set
	 */
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}
	

}
