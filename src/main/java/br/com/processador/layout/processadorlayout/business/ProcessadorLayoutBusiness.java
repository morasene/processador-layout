package br.com.processador.layout.processadorlayout.business;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.processador.layout.processadorlayout.parser.LayoutFactory;

/**
 * Classe responsável por processar os arquivos
 * 
 * @author Marcelo Moraes
 *
 */
@Service
public class ProcessadorLayoutBusiness {

	@Value("${processador.layout.sufixo.arquivo}")
	private String sufixo;

	@Autowired
	private LayoutFactory factory;

	/**
	 * Método responsável por processar os arquivos recebidos
	 * 
	 * @param arquivos
	 * @param mapa
	 * @throws IOException
	 */
	public void processar(List<File> arquivos, Map<String, List<?>> mapa) throws IOException {
		for (File arquivo : arquivos) {
			Files.readAllLines(arquivo.toPath()).forEach(linha -> {
				factory.processarLinha(linha, mapa);
			});
			arquivo.renameTo(new File(arquivo.getAbsoluteFile().toString() + sufixo));
		}
	}
}
