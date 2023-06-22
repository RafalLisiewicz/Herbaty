package edp.herbaty.herbaty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button newWindowButton;
    @FXML
    private ChoiceBox<String> choice;

    private JSONArray teas;

    public void setAvailableChoices() throws IOException {
        Runnable subTaskWithLambda = () -> {
            try {
                TeaApi teaTypes = new TeaApi();
                ArrayList<String> types = teaTypes.getTypes();
                teas = teaTypes.getTeas();
                ObservableList<String> availableChoices = FXCollections.observableArrayList(types);
                choice.setItems(availableChoices);
            } catch (Exception e){}
        };
        Thread subTask = new Thread(subTaskWithLambda);
        subTask.start();
    }

    public void setTitle() {
        choice.setOnAction((event) -> {
            welcomeText.setText(choice.getValue());
        });
    }

    @FXML
    protected void onWindowButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("second-view.fxml"));
        TeaHolder holder = TeaHolder.getInstance();
        for(Object tea: teas)
        {
            JSONObject temp = (JSONObject) tea;
            if ((temp.getString("Type").equals(choice.getValue()))){
                holder.setTea(temp);
                break;
            }
        }
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        Stage stage = new Stage();
        stage.setTitle(choice.getValue());
        stage.setScene(scene);
        stage.show();

        newWindowButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (choice != null) {
            try {
                setAvailableChoices();
                setTitle();
            } catch (IOException e) {

            }
        }
    }
}