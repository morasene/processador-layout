package br.com.processador.layout.processadorlayout.parser;

import org.springframework.stereotype.Component;

import br.com.processador.layout.processadorlayout.bean.Cliente;

@Component
public class ClienteParser extends DefaultParser<Cliente> {

	private final static int CNPJ = 1;
	private final static int NAME = 2;
	private final static int BUSINESS_AREA = 3;

	//002çCNPJçNameçBusiness Area
	@Override
	public Cliente parse(String[] dados) {
		return new Cliente(dados[ID], dados[CNPJ], dados[NAME], dados[BUSINESS_AREA]);
	}

}
