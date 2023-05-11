package view;

import controller.INetflixController;
import controller.NetflixController;

public class main {
	public static void main(String[] args) {
		INetflixController n = new NetflixController();
		String path ="C:\\Temp";
		String name = "netflix_originals_series_2.csv";
		n.GeraFilaObjetos(path, name);

	}

}
