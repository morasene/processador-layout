package br.com.processador.layout.processadorlayout.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.processador.layout.processadorlayout.bean.Venda;

@Component
public class VendasParser extends DefaultParser<Venda> {

	@Autowired
	private ItemParser itemParser;

	private final static int SALES_ID = 1;
	private final static int ITEM = 2;
	private final static int SALESMAN_NAME = 3;
	private final static String LISTA_ITENS = ",";

	// 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
	@Override
	public Venda parse(String[] dados) {
		String itemNovo = dados[ITEM].replace("[", "").replace("]", "");
		return new Venda(dados[ID], dados[SALES_ID], itemParser.parse(itemNovo.split(LISTA_ITENS)), dados[SALESMAN_NAME]);
	}
}
