/*
package controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class ClientFormController01 {

    public static String name = "default name";

    public ImageView imghappyMood;
    public TextFlow txtTextArea;
    public VBox vBox;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane clientContext;

    @FXML
    private Label lblChatingName;

    @FXML
    private TextField txtMessage;

    @FXML
    private Button btnSend;


    @FXML
    private ImageView imgCamera;
    public FileChooser fileChooser;
    public File path;
    Socket socket;
    PrintStream printWriter;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";
    static String username;

    @FXML
    void initialize() {
        new Thread(() -> {
            try {
                name = LoginFormController.username;
                lblChatingName.setText(name);
                socket = new Socket("localhost", 5002);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(name);
                dataOutputStream.writeUTF(username); // send 'name' to Server
                dataOutputStream.flush();
                while (true) {
                    message = dataInputStream.readUTF();
//  me e msg ek danna api container ekk hduw ethkot img ekk em awth apita ekt dann pluwm gnta
                    VBox messageContainer = new VBox();
//   me thmai msg ek manika
                    Text text = new Text("\nServer: " + message);

//  ar msg ek container ekta damma
                    messageContainer.getChildren().add(text);

//  ape chat group eke ui ekta add kra
                    vBox.getChildren().add(messageContainer);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            txtMessage.setText("");
        }).start();

    }


    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(txtMessage.getText().trim());
        dataOutputStream.flush();
        String msg = txtMessage.getText();
//        1. Container ek hdno
        VBox container = new VBox();

//        2.Ona msg ek text ekkt hdgnno
        Text text = new Text("Me: " + msg + "\n");

//        3.eka message ek danna hdpu container ekta dano
        container.getChildren().add(text);


//        4.e container ek UI ekta add krno
        vBox.getChildren().add(container);

        txtMessage.clear();

    }


    public void imagebtnClickOnMouse(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.path = fileChooser.showOpenDialog(stage);

    }


    public void imghappyMoodOnMouseClick(MouseEvent mouseEvent) {


    }
}
*/

/*  ==================================================================================================== */


package controller;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class ClientFormController01 {

    public static String name = "default name";

    public ImageView imghappyMood;
    public TextFlow txtTextArea;
    public VBox vBox;
    public TextArea txtArea;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane clientContext;

    @FXML
    private Label lblChatingName;

    @FXML
    private TextField txtMessage;

    @FXML
    private Button btnSend;


    @FXML
    private ImageView imgCamera;
    public FileChooser fileChooser;
    public File path;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";
    static String username;

    @FXML
    void initialize() {
        new Thread(() -> {
            try {
                name = LoginFormController.username;
                lblChatingName.setText(name);
                socket = new Socket("localhost", 5002);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(username);
                dataOutputStream.flush();

                while (true) {
                    message = dataInputStream.readUTF();
                    txtArea.appendText("\nServer: " + message);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF((username+" : "+txtMessage.getText()).trim());
        dataOutputStream.flush();
        String msg = txtMessage.getText();
        txtArea.appendText("Me: " + msg + "\n");
        txtMessage.clear();

    }


    public void imagebtnClickOnMouse(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.path = fileChooser.showOpenDialog(stage);

    }


    public void imghappyMoodOnMouseClick(MouseEvent mouseEvent) {


    }
}

