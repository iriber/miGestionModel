package com.migestion.model;

import java.io.Serializable;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Entity genérica para MiGestión
 * 
 * @author Bernardo Iribarne
 * @since 09/10/2013
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class GenericEntity implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 5011096653614088679L;
	
	@Version
	private Integer version;
	


	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!obj.getClass().equals(this.getClass()))
			return false;

		GenericEntity other = (GenericEntity) obj;
		Boolean ok = other.getOid()!=null && this.getOid()!=null;
		ok = ok &&  new EqualsBuilder().append(getOid().longValue(), other.getOid().longValue()).isEquals();
		return ok;
		
	}

	

	@Override
	public int hashCode() {
		return new HashCodeBuilder(3, 7).append(getOid()).toHashCode();
	}

	/**
	 * @return the oid
	 */
	public abstract Long getOid();

	/**
	 * @param oid the oid to set
	 */
	public abstract void setOid(Long oid);

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}	
}
