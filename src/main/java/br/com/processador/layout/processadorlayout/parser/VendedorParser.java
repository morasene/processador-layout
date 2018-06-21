package br.com.processador.layout.processadorlayout.parser;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.processador.layout.processadorlayout.bean.Vendedor;

@Component
public class VendedorParser extends DefaultParser<Vendedor> {

    private final static int CPF = 1;
    private final static int NOME = 2;
    private final static int SALARIO = 3;

    @Override
    //001çCPFçNameçSalary
	public Vendedor parse(String[] dados) {
		return new Vendedor(dados[ID], dados[CPF], dados[NOME], new BigDecimal(dados[SALARIO].replace(",",".")));
	}

}
