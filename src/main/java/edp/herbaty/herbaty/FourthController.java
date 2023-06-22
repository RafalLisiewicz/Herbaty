package edp.herbaty.herbaty;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FourthController implements Initializable {

    @FXML
    private Label welcomeText;
    @FXML
    private Button backBtn;
    @FXML
    private ListView<String> comments;

    private String comment;

    public void setData() {
        TeaHolder holder = TeaHolder.getInstance();
        welcomeText.setText(holder.getTea().getString("Type"));

        Runnable subTaskWithLambda = () -> {
            DBAccess baza = new DBAccess();
            comments.getItems().addAll(baza.getComments());
        };
        Thread subTask = new Thread(subTaskWithLambda);
        subTask.start();
    }

    protected void selectedComment() {
        comments.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                comment = comments.getSelectionModel().getSelectedItem();
            }
        });
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

    @FXML
    protected void onDeleteBtnClick() {
        Runnable subTaskWithLambda = () -> {
            DBAccess baza = new DBAccess();
            baza.deleteComment(comment);
            comments.getItems().clear();
            comments.getItems().addAll(baza.getComments());
        };
        Thread subTask = new Thread(subTaskWithLambda);
        subTask.start();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        selectedComment();
    }
}
