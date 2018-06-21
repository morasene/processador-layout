package br.com.processador.layout.processadorlayout.parser;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.processador.layout.processadorlayout.bean.Cliente;
import br.com.processador.layout.processadorlayout.bean.Venda;
import br.com.processador.layout.processadorlayout.bean.Vendedor;

/**
 * Fábrica de Layouts
 * 
 * @author Marcelo Moraes
 *
 */
@Component
public class LayoutFactory {

	private static final String ID_VENDAS = "003";
	private static final String ID_CLIENTE = "002";
	private static final String ID_VENDEDOR = "001";
	private static final String SPLIT = "ç";

	@Autowired
	private VendedorParser vendedor;

	@Autowired
	private ClienteParser cliente;

	@Autowired
	private VendasParser vendas;

	/**
	 * Método responsável pro processar as linhas de um arquivo baseado no seu tipo (3 primeiros caracteres)
	 * 
	 * @param linha
	 * @param mapa
	 */
	public void processarLinha(String linha, Map<String, List<?>> mapa) {
		if (linha.length() > 3) {			
			String id = linha.substring(0, 3);
			String[] dados = linha.split(SPLIT);
			switch (id) {
			case ID_VENDEDOR:
				List<Vendedor> listaVendedor = (List<Vendedor>) mapa.get("vendedor");
				listaVendedor.add(vendedor.parse(dados));
				break;
			case ID_CLIENTE:
				List<Cliente> listaCliente = (List<Cliente>) mapa.get("cliente");
				listaCliente.add(cliente.parse(dados));
				break;
			case ID_VENDAS:
				List<Venda> listaVendas = (List<Venda>) mapa.get("vendas");
				listaVendas.add(vendas.parse(dados));
				break;
			default:
				break;
			}
		}
	}
}
