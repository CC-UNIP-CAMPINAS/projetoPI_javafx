package br.com.leonardopn.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import br.com.leonardopn.App;
import br.com.leonardopn.model.entities.EscalaDeCinza;
import br.com.leonardopn.model.entities.Histograma;
import br.com.leonardopn.model.entities.MatrixIMG;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class MainController implements Initializable {

	@FXML
	private SplitPane split;

	@FXML
	private ImageView ImgBinarizada;

	@FXML
	private ImageView imgCinza;

	@FXML
	private Button btBuscarArquivo;

	@FXML
	private Button btAbrirHistograma;

	@FXML
	private BarChart<Integer, Integer> barChartHistograma;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	public void abrirHistograma() {
		if (split.getDividerPositions()[0] > 0.2) {
			split.setDividerPositions(0.1364);
		} else {
			split.setDividerPositions(1.0);
		}
	}

	public void buscaArquivo() {
		try {
			barChartHistograma.getData().clear();
			FileChooser fileChooser = new FileChooser();
			File selectedFile = fileChooser.showOpenDialog(App.stageMain);
			if (selectedFile.exists() && selectedFile != null) {
				BufferedImage imgNormal = ImageIO.read(selectedFile);
				EscalaDeCinza filterCinza = new EscalaDeCinza();
				BufferedImage imgCinzaBuff = filterCinza.getImgGray(imgNormal);
				Image imageCinzaFX = SwingFXUtils.toFXImage(imgCinzaBuff, null);

				imgCinza.setImage(imageCinzaFX);

				Task<Void> tarefa = new Task<Void>() {
					@Override
					protected Void call() throws Exception {

						MatrixIMG imgMatrixCinza = new MatrixIMG(imgCinzaBuff);
						imgMatrixCinza.getMatrix();

						ArrayList<Histograma> histogramas = imgMatrixCinza.getHistograma();
						int limiar = imgMatrixCinza.getLimiar(histogramas);
						System.out.println(limiar);

						BufferedImage imgBinarizada = imgMatrixCinza.binarizador(limiar);
						Image imgBinarizadaFX = SwingFXUtils.toFXImage(imgBinarizada, null);


						Platform.runLater(() -> {
							ImgBinarizada.setImage(imgBinarizadaFX);
							preencheHistograma(histogramas);
						});
						return null;
					}
				};
				Platform.runLater(() -> {
					Thread t = new Thread(tarefa);
					t.start();
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void preencheHistograma(ArrayList<Histograma> histogramas) {

		XYChart.Series<Integer, Integer> series = new XYChart.Series<>();

		for (Histograma his : histogramas) {
			series.getData().add(new XYChart.Data(String.valueOf(his.valor), his.quant));
		}

		barChartHistograma.getData().addAll(series);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
