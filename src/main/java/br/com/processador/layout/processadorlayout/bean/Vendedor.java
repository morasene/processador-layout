package br.com.processador.layout.processadorlayout.bean;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class Vendedor extends DefaultLayout {

	private String id;
	private String cpf;
	private String name;
	private BigDecimal salary;

	public Vendedor() {
		super();
	}

	public Vendedor(String id, String cpf, String name, BigDecimal salary) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
