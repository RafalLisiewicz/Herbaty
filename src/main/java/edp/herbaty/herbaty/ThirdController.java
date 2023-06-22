package edp.herbaty.herbaty;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThirdController implements Initializable {

    @FXML
    private Label welcomeText;
    @FXML
    private TextArea comment;
    @FXML
    private Button backBtn;

    public void setTitle() {
        TeaHolder holder = TeaHolder.getInstance();
        welcomeText.setText(holder.getTea().getString("Type"));
    }

    @FXML
    protected void onCommentAddClick() {
        Runnable subTaskWithLambda = () -> {
            DBAccess baza = new DBAccess();
            baza.addComment(comment.getText());
            comment.clear();
        };
        Thread subTask = new Thread(subTaskWithLambda);
        subTask.start();
    }

    @FXML
    protected void onOldWindowButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("second-view.fxml"));
        TeaHolder holder = TeaHolder.getInstance();
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        Stage stage = new Stage();
        stage.setTitle(holder.getTea().getString("Type"));
        stage.setScene(scene);
        stage.show();

        backBtn.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTitle();
    }
}
