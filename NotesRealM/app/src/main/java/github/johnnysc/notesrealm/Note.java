package github.johnnysc.notesrealm;

import io.realm.RealmObject;

/**
 * Created by Hovhannes Asatryan on 23.07.16.
 */
public class Note extends RealmObject{

    private String date;
    private String Head;
    private String body;

    public Note() {}

    public void setDate(String date) {
        this.date = date;
    }

    public void setHead(String head) {
        Head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public String getHead() {
        return Head;
    }

    public String getBody() {
        return body;
    }
}
