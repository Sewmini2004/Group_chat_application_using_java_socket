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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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

    public TextArea txtArea;
    public VBox vBox;

    public boolean isImg;
    public ImageView selectedImage;
    public ScrollPane imgScroll;

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
    public File selectedFile;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";
    static String username;
    List<String> extensions;

    @FXML
    void initialize() {
        selectedImage.setFitWidth(200.00);
        selectedImage.setPreserveRatio(true);
        imgScroll.setVisible(false);
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
                    String message = dataInputStream.readUTF();


                    HBox hBox=new HBox();//parent
                    Text text=new Text(message);//child
                    hBox.getChildren().add(text);

                    vBox.getChildren().add(hBox);


                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    @FXML
    void btnSendOnAction(ActionEvent event) throws IOException {

        String msg = txtMessage.getText();
        isImg = false;
        dataOutputStream.writeBoolean(isImg);
        dataOutputStream.writeUTF((username + " : " + msg.trim()));
        dataOutputStream.flush();
        HBox container = new HBox();
        container.getChildren().add(new Text("You : ".concat(msg)));
        vBox.getChildren().add(container);
        txtMessage.clear();

    }

    @FXML
    void btnCloseSendImgOnAction(ActionEvent event) {
        selectedImage.setImage(null);
        imgScroll.setVisible(false);
    }

    @FXML
    void btnSendImgOnAction(ActionEvent event) {
        try {
            BufferedImage readImage = ImageIO.read(selectedFile);

            String imageFormat = "";
            for (String item : extensions) {
                boolean b = selectedFile.getName().endsWith(item.replace("*.", ""));
                if (b) {
                    imageFormat = item.replace("*.", "");
                    break;
                }

            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(readImage, imageFormat, byteArrayOutputStream);

            byte[] imgArray = byteArrayOutputStream.toByteArray();
            int imgArrayLength = imgArray.length;

            isImg = true;
            dataOutputStream.writeBoolean(isImg);
            dataOutputStream.writeInt(imgArrayLength);
            dataOutputStream.write(imgArray);
            dataOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        imgScroll.setVisible(false);
    }


    public void imagebtnClickOnMouse(MouseEvent mouseEvent) {
        extensions = new ArrayList<>();
        Collections.addAll(extensions, "*.jpeg", "*.jpg", "*.png");
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imgFilter = new FileChooser.ExtensionFilter("Images", extensions);
        fileChooser.getExtensionFilters().add(imgFilter);

        fileChooser.setTitle("Open Image");
        this.selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            selectedImage.setImage(image);
            imgScroll.setVisible(true);
        } else {
            imgScroll.setVisible(false);
        }


    }


    public void imghappyMoodOnMouseClick(MouseEvent mouseEvent) {


    }
}

