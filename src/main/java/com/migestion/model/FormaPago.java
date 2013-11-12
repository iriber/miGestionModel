package com.migestion.model;


/**
 * Forma de pago
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 25/10/2013
 *
 */
public enum FormaPago{

	EFECTIVO("forma.pago.efectivo"),
	TRANSFERENCIA("forma.pago.transferencia"),
	DEPOSITO("forma.pago.deposito"),
	NOTA_CREDITO("forma.pago.notaCredito"),
	TARJETA("forma.pago.tarjeta"),
	CUENTA_BANCARIA("forma.pago.cuentaBancaria"),
	CHEQUE("forma.pago.cheque");
	
	
	//nombre.
	private String nombre;

	
	private FormaPago(String nombre){
		this.nombre = nombre;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		return  nombre;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	
}