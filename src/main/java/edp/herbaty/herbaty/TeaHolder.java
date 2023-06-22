package edp.herbaty.herbaty;

import org.json.JSONObject;

public final class TeaHolder {

    private JSONObject tea;
    private final static TeaHolder INSTANCE = new TeaHolder();

    private TeaHolder() {
    }

    public static TeaHolder getInstance() {
        return INSTANCE;
    }

    public void setTea(JSONObject u) {
        this.tea = u;
    }

    public JSONObject getTea() {
        return this.tea;
    }
}
