package edp.herbaty.herbaty;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class TeaApi {

    public TeaApi() {
    }

    public JSONObject getTea(String type) throws IOException {
        String response = Request.get("https://my-json-server.typicode.com/sreeharshrajan/tea-collection/types_of_tea").execute().returnContent().asString();
        JSONArray teas = new JSONArray(response);

        for (Object tea : teas) {
            JSONObject temp = (JSONObject) tea;
            if (temp.getString("Type").equals(type)) {
                return temp;
            }
        }
        return null;
    }
    public JSONArray getTeas() throws IOException{
        String response = Request.get("https://my-json-server.typicode.com/sreeharshrajan/tea-collection/types_of_tea").execute().returnContent().asString();
        JSONArray teas = new JSONArray(response);
        return teas;
    }

    public ArrayList<String> getTypes() throws IOException {
        String response = Request.get("https://my-json-server.typicode.com/sreeharshrajan/tea-collection/types_of_tea").execute().returnContent().asString();
        JSONArray teas = new JSONArray(response);
        ArrayList<String> types = new ArrayList();
        for (Object tea : teas) {
            JSONObject temp = (JSONObject) tea;
            types.add(temp.getString("Type"));
        }
        return types;
    }

}
