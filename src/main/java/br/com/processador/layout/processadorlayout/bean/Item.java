package br.com.processador.layout.processadorlayout.bean;

import java.math.BigDecimal;

public class Item extends DefaultLayout {

	private Integer quantity;
	private BigDecimal price;

	public Item(String id, Integer quantity, BigDecimal price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
