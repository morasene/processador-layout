package br.com.processador.layout.processadorlayout.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.processador.layout.processadorlayout.bean.Item;

@Component
public class ItemParser extends DefaultParser<List<Item>> {

	private final static int QUANTITY = 1;
	private final static int PRICE = 2;

	//002çCNPJçNameçBusiness Area
	@Override
	public List<Item> parse(String[] dados) {
		List<Item> itens = new ArrayList<Item>();
		for (String string : dados) {
			String[] atributos = string.split("-");
			itens.add(new Item(atributos[ID], new Integer(atributos[QUANTITY]),new BigDecimal(atributos[PRICE])));
		}
		return itens;
	}

}
