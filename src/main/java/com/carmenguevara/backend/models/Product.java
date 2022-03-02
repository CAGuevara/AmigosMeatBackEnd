package com.carmenguevara.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@Column
	private String code;
	@Column
	private String description;
	@Column
	private double cost;
	@Column
	private int qty;
	
	
	public String getCode() {
		return code;
	}
	//We need this setter because we need to set our code for each product.
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int d) {
		this.qty = d;
	}

}
