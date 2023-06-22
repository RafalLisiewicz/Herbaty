package edp.herbaty.herbaty;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class TextFormatter {

    public Text formatText(JSONObject rawText) {
        Text text = new Text(rawText.toString(4).replace("\"", "").replace("{", "").replace("}", "").replace(",", ", "));
        text.setFont(Font.font("Times New Roman", 18));
        text.setLineSpacing(12);
        return text;
    }
}
