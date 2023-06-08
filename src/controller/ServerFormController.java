/*
package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ServerFormController {


    public VBox VBox;
    ServerSocket serverSocket;
    Socket socket;


    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";

    public void initialize() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5002);
                */
/* Text text = new Text("Server Started!");*//*

                Text text = new Text("Server Started!");
                VBox messageContainer = new VBox();
                messageContainer.getChildren().add(text);
                VBox.getChildren().add(messageContainer);

                while (true) {

                    socket = serverSocket.accept();
                    VBox messageContainer1 = new VBox();
                    Text text1 = new Text("\nClient Accepted!");
                    messageContainer.getChildren().add(text1);
                    VBox.getChildren().add(messageContainer1);


                 */
/*   Text text1 = new Text("\nClient Accepted!");

                 txtArea.getChildren().add(text1);
                    Date d1 = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm a");
                    String formattedDate = df.format(d1);
                    Text text2 = new Text("\nNew client connected. " + formattedDate);
                    txtArea.getChildren().add(text2);*//*


                    Platform.runLater(() -> {

                    });

*/
/*

                    String messageUsername = dataInputStream.readUTF();
                    Text text3=new Text("\nServer: " + messageUsername+" joined the chat room. ");
                    txtArea.getChildren().add(text3);

                    while (true) {
                        message = dataInputStream.readUTF();
                        Text text4=new Text("\n"+ messageUsername+": "+ message);
                        txtArea.getChildren().add(text4);

                    }
*//*



                    new AcceptSocketServer(socket).start();

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
*/




/* =====================================================================================================================*/


package controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ServerFormController {

    public TextArea txtArea;
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";


    public void initialize() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5002);
                txtArea.appendText("Server Started!");

                while (true) {

                    socket = serverSocket.accept();
                    txtArea.appendText("\nClient Accepted!");
                    Date d1 = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm a");
                    String formattedDate = df.format(d1);
                    txtArea.appendText("\nNew client connected. " + formattedDate);

                    dataInputStream = new DataInputStream(socket.getInputStream());
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    String messageUsername = dataInputStream.readUTF();
                    txtArea.appendText("\nServer: " + messageUsername + " joined the chat room. ");

                    new AcceptSocketServer(socket,messageUsername).start();


                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
