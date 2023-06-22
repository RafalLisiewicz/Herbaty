package edp.herbaty.herbaty;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextFlow teaData;
    @FXML
    private Button addComent;
    @FXML
    private Button oldWindowButton;


    public void setData() {
        TeaHolder holder = TeaHolder.getInstance();
        JSONObject tea = holder.getTea();
        welcomeText.setText(tea.getString("Type"));
        Text text = new Text(tea.toString(4).replace("\"", "").replace("{", "").replace("}", "").replace(",",", "));
        text.setFont(Font.font("Times New Roman", 18));
        text.setLineSpacing(12);
        teaData.getChildren().add(text);
    }

    @FXML
    protected void onAddCommentClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("third-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Dodaj komentarz");
        stage.setScene(scene);
        stage.show();

        addComent.getScene().getWindow().hide();
    }

    @FXML
    protected void onOldWindowButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Herbaciarnia");
        stage.setScene(scene);
        stage.show();

        oldWindowButton.getScene().getWindow().hide();
    }

    @FXML
    protected void onShowCommentsClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fourth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Komentarze");
        stage.setScene(scene);
        stage.show();

        oldWindowButton.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
