package com.migestion.services.criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * Representa un criterio de búsqueda.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 10/10/2013
 * 
 */
public class Criteria{

	public final static String ORDER_ASC = "ASC";
	public final static String ORDER_DESC = "DESC";
	
	/**
	 * offset para la paginación.
	 */
	private Integer offset;
	
	/**
	 * cantidad de elementos para la paginación.
	 */
	private Integer maxResult;
	
	/**
	 * hash para guardar los atributos por 
	 * los cuales ordenar.
	 */
	private HashMap<String,String> orders;
	
	public Criteria(){
		
		orders = new HashMap<String, String>();
		
	}
	
	/**
	 * agregar un order
	 * @param attribute atributo por el cual ordenar 
	 * @param type tipo de order ASC o DESC
	 */
	protected void addOrder(String attribute, String type){
	
		orders.put(attribute, type);
	}
	
	/**
	 * arma el order by para el query.
	 * @return
	 */
	public List<Order> buildOrderBy(Root root, CriteriaBuilder builder){
		List<Order> orders = new ArrayList<Order>();
		
		for (String attribute: this.orders.keySet()) {
			String type = this.orders.get( attribute );
			
			Order order;
			
			if( type == Criteria.ORDER_DESC )
				order = builder.desc( root.get(attribute) );
			else
				order = builder.asc( root.get(attribute) );
			
			orders.add(order);
		}
		
		return orders;
	}
	
	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	/**
	 * @return the maxResult
	 */
	public Integer getMaxResult() {
		return maxResult;
	}
	/**
	 * @param maxResult the maxResult to set
	 */
	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}
	
	
}