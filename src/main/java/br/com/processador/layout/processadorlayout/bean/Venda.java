package br.com.processador.layout.processadorlayout.bean;

import java.math.BigDecimal;
import java.util.List;

public class Venda extends DefaultLayout {

	private String saleId;
	private List<Item> item;
	private String salesmanName;
	private BigDecimal valor;

	public Venda(String id, String saleId, List<Item> item, String salesmanName) {
		super();
		this.id = id;
		this.saleId = saleId;
		this.item = item;
		this.salesmanName = salesmanName;
		calcularValorVenda();
	}
	
	private void calcularValorVenda() {
		valor = BigDecimal.ZERO;
		for (Item item2 : item) {
			valor = valor.add(item2.getPrice());
		}
		
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

}
