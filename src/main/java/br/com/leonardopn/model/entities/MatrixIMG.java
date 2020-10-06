package br.com.leonardopn.model.entities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MatrixIMG {
	BufferedImage img;
	int[][] imgMatrix;

	public MatrixIMG(BufferedImage img) {
		this.img = img;
	}

	public int[][] getMatrix() {
		this.imgMatrix = new int[this.img.getWidth()][this.img.getHeight()];
		for (int i = 0; i < this.img.getWidth(); ++i) {
			for (int j = 0; j < this.img.getHeight(); ++j) {
				int rgb = this.img.getRGB(i, j);
				Color mycolor = new Color(rgb);

				int red = mycolor.getRed();
				int green = mycolor.getGreen();
				int blue = mycolor.getBlue();
				int pixelCombinado = (red + green + blue) / 3;
				this.imgMatrix[i][j] = pixelCombinado;
			}
		}
		return this.imgMatrix;
	}

	public ArrayList<Histograma> getHistograma() {
		Map<Integer, Histograma> histogramas = new HashMap<Integer, Histograma>();

		for (int i = 0; i < this.img.getWidth(); ++i) {
			for (int j = 0; j < this.img.getHeight(); ++j) {
				int valor = this.imgMatrix[j][j];
				if (histogramas.containsKey(valor)) {
					histogramas.get(valor).quant++;
				} else {
					histogramas.put(valor, new Histograma(valor));
				}
			}
		}
		ArrayList<Histograma> histogramaArrayList = new ArrayList<Histograma>(histogramas.values());
		Collections.sort(histogramaArrayList);
		int somador = 0;
		for (Histograma his : histogramaArrayList) {
			somador += his.quant;
		}

		return histogramaArrayList;
	}

	public int getLimiar(ArrayList<Histograma> histogramas) {
		int limiar = 5000000;
		int histEscolhido = histogramas.indexOf(histogramas.get(0));

		for (Histograma his : histogramas) {
			int somador = 0;
			int somador2 = 0;

			for (int i = histogramas.indexOf(his) + 1; i < histogramas.size(); ++i) {
				somador += histogramas.get(i).quant;
			}
			for (int i = histogramas.indexOf(his) - 1; i >= 0; --i) {
				somador2 += histogramas.get(i).quant;

			}
			System.out.println(histogramas.indexOf(his) + "" + his + "-" + somador + "=" + somador2);
			if (Math.abs((somador - somador2)) <= limiar) {
				limiar = Math.abs((somador - somador2));
				histEscolhido = his.valor;
			}
		}
		return histEscolhido;
	}

	public BufferedImage binarizador(int limiar) {
		BufferedImage imagem = new BufferedImage(this.img.getWidth(), this.img.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < this.img.getWidth(); ++i) {
			for (int j = 0; j < this.img.getHeight(); ++j) {
				int valor = imgMatrix[i][j];
				if (valor >= limiar) {
					Color cor = new Color(255, 255, 255);
					imagem.setRGB(i, j, cor.getRGB());
					imgMatrix[i][j] = 255;
				} else {
					Color cor = new Color(0, 0, 0);
					imagem.setRGB(i, j, cor.getRGB());
					imgMatrix[i][j] = 0;
				}
			}
		}

		return imagem;
	}
}
