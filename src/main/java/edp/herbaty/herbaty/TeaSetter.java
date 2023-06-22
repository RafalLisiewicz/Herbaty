package edp.herbaty.herbaty;

import org.json.JSONArray;
import org.json.JSONObject;

public class TeaSetter {

    public void setTea(JSONArray teas, String choice){
        TeaHolder holder = TeaHolder.getInstance();
        for (Object tea : teas) {
            JSONObject temp = (JSONObject) tea;
            if ((temp.getString("Type").equals(choice))) {
                holder.setTea(temp);
                break;
            }
        }
    }
}
