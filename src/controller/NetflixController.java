package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.kauanPaulino.filaObject.FilaObject;
import model.Serie;

public class NetflixController implements INetflixController {

	@Override
	public void GeraFilaObjetos(String path, String name) {
		try {
			GeraFila(path, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void GeraFila(String path, String name) throws IOException {
		File arquivo = new File(path, name);

		if (arquivo.exists() && arquivo.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitorfluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitorfluxo);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while (linha != null) {
				String vtlinha[] = linha.split(";");
				String genero = vtlinha[0];
				FilaObject filaaux = new FilaObject();
				while (genero.contains(vtlinha[0]) && linha != null) {
					Serie s = new Serie();
					s.major_genre = vtlinha[0];
					s.title = vtlinha[1];
					s.subgenre = vtlinha[2];
					s.premiere_year = vtlinha[4];
					s.status = vtlinha[6];
					s.imdb_rating = Integer.parseInt(vtlinha[11]);
					s.episodes = (vtlinha[10]);
					filaaux.insert(s);
					linha = buffer.readLine();
					if (linha != null) {
						vtlinha = linha.split(";");
					}
				}
				GeraArquivos(filaaux, genero);
			}
		} else {
			throw new IOException("Arquivo invalido");
		}

	}

	private void GeraArquivos(FilaObject filas, String genero) throws IOException {
		File file = new File("C:\\Temp", genero + ".csv");
		StringBuffer buffer = new StringBuffer();
		//Linha De titulo
		buffer.append("Genero;Titulo;Subgenero;Ano;Episodios;Status;Nota\n");
		FileWriter filewrite = new FileWriter(file);
		PrintWriter print = new PrintWriter(filewrite);
		print.write(buffer.toString());
		print.flush();
		print.close();
		filewrite.close();
		
		int tamanho = filas.size();
		for (int i = 0; i < tamanho; i++) {
			Serie s = new Serie();
			try {
				s = (Serie) filas.remove();
				buffer.append(s);
				FileWriter filewrite1 = new FileWriter(file);
				PrintWriter print1 = new PrintWriter(filewrite1);
				print1.write(buffer.toString());
				print1.flush();
				print1.close();
				filewrite1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
