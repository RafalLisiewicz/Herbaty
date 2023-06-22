package edp.herbaty.herbaty;

import java.util.ArrayList;

public class DBAccess {

    public void addComment(String comment) {
        TeaHolder holder = TeaHolder.getInstance();
        DBControl baza = new DBControl();

        baza.addComment(holder.getTea().getString("Type"), comment);
    }

    public ArrayList<String> getComments() {
        TeaHolder holder = TeaHolder.getInstance();
        DBControl baza = new DBControl();
        return baza.getComments(holder.getTea().getString("Type"));
    }

    public void deleteComment(String comment) {
        DBControl baza = new DBControl();
        baza.deleteComment(comment);
    }
}
