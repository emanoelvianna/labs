package br.com.exemplo.commonsCsv.principal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import br.com.exemplo.jsefa.modelo.Pessoa;

public class Principal {

	static final Object[] FILE_HEADER = { "nome", "idade" };

	/*
	 * Exemplo de utlização da lib commons-csv
	 */
	public static void main(String[] args) throws IOException {
		Pessoa pessoa1 = new Pessoa("Maria", 22);
		Pessoa pessoa2 = new Pessoa("Pedro", 32);

		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.add(pessoa1);
		pessoas.add(pessoa2);

		File file = new File("commons.csv");
		FileWriter fileWriter = new FileWriter(file);

		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
		CSVPrinter csvFilePrinter;

		csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
		csvFilePrinter.printRecord(FILE_HEADER);

		for (Pessoa pessoa : pessoas) {
			List pessoaDataRecord = new ArrayList();
			pessoaDataRecord.add(pessoa.getNome());
			pessoaDataRecord.add(pessoa.getIdade());
			csvFilePrinter.printRecord(pessoaDataRecord);
		}

		fileWriter.flush();
		fileWriter.close();
		csvFilePrinter.close();

	}

}
