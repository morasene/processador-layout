package br.com.processador.layout.processadorlayout.bean;

public class Cliente extends DefaultLayout {

	private String cnpj;
	private String name;
	private String businessArea;

	public Cliente(String id, String cnpj, String name, String businessArea) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.name = name;
		this.businessArea = businessArea;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

}
