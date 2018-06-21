package br.com.processador.layout.processadorlayout.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.processador.layout.processadorlayout.bean.Cliente;
import br.com.processador.layout.processadorlayout.bean.Venda;
import br.com.processador.layout.processadorlayout.bean.Vendedor;

/**
 * 
 * 
 * @author Marcelo Moraes
 *
 */
@Service
public class RelatorioBusiness {

	private static final String PATTERN_DDMMYYYHHMMSS = "ddmmyyyhhmmss";
	private static final String BARRA = "/";
	private String idVendaMaisCara;
	private String piorVendedor;

	@Value("${processador.layout.prefixo.relatorio}")
	private String prefixoRelatorio;

	@Value("${processador.layout.sufixo.relatorio}")
	private String sufixoRelatorio;

	private BigDecimal calcularVendas(List<Venda> vendas) {
		idVendaMaisCara = "";
		piorVendedor = "";
		BigDecimal valorMaior = BigDecimal.ZERO;
		BigDecimal valorMenor = vendas.get(0).getValor();

		for (Venda venda : vendas) {
			BigDecimal valorAtual = venda.getValor();
			if (valorAtual.compareTo(valorMaior) > 0) {
				valorMaior = valorAtual;
				idVendaMaisCara = venda.getSaleId();
			}
			if (valorAtual.compareTo(valorMenor) < 0) {
				valorMenor = valorAtual;
				piorVendedor = venda.getSalesmanName();
			}
		}
		return valorMaior;
	}

	/**
	 * Método responsável por gerar o relatório final do processamento dos arquivos
	 * 
	 * O nome do arquivo deve seguir o padrão, {flat_file_name} .done.dat.
	 * localizado em %HOMEPATH% /data/out.
	 * 
	 * O conteúdo do arquivo de saída deve resumir os seguintes dados: Quantidade de
	 * clientes no arquivo de entrada Quantidade de vendedor no arquivo de entrada
	 * ID da venda mais cara O pior vendedor
	 * 
	 * @param caminhoArquivoSaida
	 * @param mapa
	 * @throws IOException
	 */
	public void gerarRelatorio(String caminhoArquivoSaida, Map<String, List<?>> mapa) throws IOException {
		List<Vendedor> vendedores = (List<Vendedor>) mapa.get("vendedor");
		List<Cliente> clientes = (List<Cliente>) mapa.get("vendedor");
		List<Venda> vendas = (List<Venda>) mapa.get("vendas");

		Integer quantidadeClientesArquivoEntrada = clientes.size();
		Integer quantidadeVendedorArquivoEntrada = vendedores.size();
		calcularVendas(vendas);
		BufferedWriter writer = null;
		String nomeArquivo = prefixoRelatorio
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_DDMMYYYHHMMSS));
		File arquivoSaida = new File(caminhoArquivoSaida + BARRA + nomeArquivo + sufixoRelatorio);
		FileWriter fw = new FileWriter(arquivoSaida);
		writer = new BufferedWriter(fw);

		writer.write("Quantidade de clientes no arquivo de entrada= " + quantidadeClientesArquivoEntrada);
		writer.newLine();
		writer.write("Quantidade de vendedores no arquivo de entrada= " + quantidadeVendedorArquivoEntrada);
		writer.newLine();
		writer.write("ID da venda mais cara= " + idVendaMaisCara);
		writer.newLine();
		writer.write("O pior vendedor= " + piorVendedor);
		writer.newLine();
		writer.flush();
		writer.close();
	}
}
