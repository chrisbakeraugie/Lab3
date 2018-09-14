package application;

import java.io.ByteArrayInputStream;
import java.util.Timer;
import java.util.TimerTask;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

	@FXML
	private Button startCameraButton;
	@FXML
	private ImageView currentFrame;
	private VideoCapture capture = new VideoCapture();
	private Timer timer;
	private boolean cameraActive = false;
	private Main mainApp;

	@FXML
	protected void startCamera(ActionEvent event) {

		final ImageView frameView = (ImageView) mainApp.getRootElement().lookup("#currentFrame");
		
		TimerTask frameGrabber = new TimerTask() {

			@Override
			public void run() {

			}
		};

		this.timer = new Timer();
		this.timer.schedule(frameGrabber, 0, 33);

		if (this.capture.isOpened()) {

		}

		Mat frame = new Mat();

		this.capture.read(frame);

		Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);

		
		MatOfByte buffer = new MatOfByte();

		new Image(new ByteArrayInputStream(buffer.toArray()));

	

		

	}
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	
}
