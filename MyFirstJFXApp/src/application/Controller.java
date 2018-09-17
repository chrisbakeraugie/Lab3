package application;

import java.io.ByteArrayInputStream;

import java.util.Timer;
import java.util.TimerTask;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
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

		final ObjectProperty<Image> imageProp = new SimpleObjectProperty<>();

		frameView.imageProperty().bind(imageProp);

		if (!this.cameraActive) {

			this.capture.open("S:\\CLASS\\CS\\285\\sample_videos\\sample1.mp4");

			if (this.capture.isOpened()) {

				this.cameraActive = true;

				TimerTask frameGrabber = new TimerTask() {

					@Override
					public void run() {
						imageProp.set(grabFrame());
					}
				};

				this.timer = new Timer();
				this.timer.schedule(frameGrabber, 0, 33);

				this.startCameraButton.setText("Stop Camera");

			} else {
				// log the error
				System.err.println("Impossible to open the camera connection...");
			}

		} else {
			// the camera is not active at this point
			this.cameraActive = false;
			// update again the button content
			this.startCameraButton.setText("Start Camera");
			// stop the timer
			if (this.timer != null) {
				this.timer.cancel();
				this.timer = null;
			}
			// release the camera
			this.capture.release();
		}
	}

	private Image grabFrame() {

		Image imageToShow = null;
		Mat frame = new Mat();

		if (this.capture.isOpened()) {

			try {

				this.capture.read(frame);

				if (!frame.empty()) {
					Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);

					imageToShow = mat2Image(frame);
				}
			} catch (Exception e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}
		return imageToShow;
	}

	private Image mat2Image(Mat frame) {

		MatOfByte buffer = new MatOfByte();
		Imgcodecs.imencode(".png", frame, buffer);
		return new Image(new ByteArrayInputStream(buffer.toArray()));
	}

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

}
