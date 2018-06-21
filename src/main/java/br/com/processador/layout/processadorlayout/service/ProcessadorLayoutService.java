package br.com.processador.layout.processadorlayout.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.processador.layout.processadorlayout.bean.Cliente;
import br.com.processador.layout.processadorlayout.bean.Venda;
import br.com.processador.layout.processadorlayout.bean.Vendedor;
import br.com.processador.layout.processadorlayout.business.ProcessadorLayoutBusiness;
import br.com.processador.layout.processadorlayout.business.RelatorioBusiness;

@Service
@PropertySource("classpath:processadorLayout.properties")
public class ProcessadorLayoutService {

	@Value("${processador.layout.sufixo.arquivo}")
	private String sufixo;

	@Value("${processador.layout.diretorio.relativo.entrada}")
	private String caminhoDiretorioEntrada;

	@Value("${processador.layout.diretorio.relativo.saida}")
	private String caminhoDiretorioSaida;

	@Value("${processador.layout.extensao.permitida}")
	private String extensaoPermitida;

	@Value("${processador.layout.diretorio.absoluto}")
	private String diretorioAbsoluto;

	@Autowired
	private ProcessadorLayoutBusiness processadorLayoutBusiness;

	@Autowired
	private RelatorioBusiness relatorioBusiness;

	Map<String, List<?>> mapa;

	/**
	 * @throws IOException
	 */
	@Scheduled(cron = "${processador.layout.tempo.processador}")
	public void carregar() throws IOException {
		inicializarMapa();
		List<File> arquivos = carregarArquivosParaProcessamento();
		if (arquivos.size() > 0) {			
			processadorLayoutBusiness.processar(arquivos, mapa);
			relatorioBusiness.gerarRelatorio(carregarCaminho(caminhoDiretorioSaida), mapa);
		}
	}

	private void inicializarMapa() {
		mapa = new HashMap<String, List<?>>();
		mapa.put("vendedor", new ArrayList<Vendedor>());
		mapa.put("cliente", new ArrayList<Cliente>());
		mapa.put("vendas", new ArrayList<Venda>());
	}

	private List<File> carregarArquivosParaProcessamento() throws IOException {
		File diretorioEntrada = new File(carregarCaminho(caminhoDiretorioEntrada));
		if (!diretorioEntrada.exists()) {
			diretorioEntrada.createNewFile();
		}
		List<File> arquivos = Files.walk(Paths.get(carregarCaminho(caminhoDiretorioEntrada)))
				.filter(Files::isRegularFile).filter(path -> path.toString().toLowerCase().endsWith(extensaoPermitida))
				.filter(path -> !path.toString().toLowerCase().endsWith(sufixo)).map(Path::toFile)
				.collect(Collectors.toList());
		return arquivos;
	}

	private String carregarCaminho(String relativeUrl) {
		String urlAbsolute = System.getProperty(diretorioAbsoluto);
		return MessageFormat.format(relativeUrl, urlAbsolute);
	}
}
