package com.migestion.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Vendedor
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 16/10/2013
 *
 */

@Entity
@Table(name="vendedor")
public class Vendedor extends Persona{

	
	public Vendedor(){
	}


}