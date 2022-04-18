package database;

import java.io.Serializable;
import java.util.Date;

public class PackageData implements Serializable {
    private String sender;
    private String content;
    private Object object;


    public PackageData() {
        this.sender = "none";
        this.content = "none";
        this.object = null;
    }

    public PackageData(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.object = null;
    }

    public PackageData(String sender, Object object) {
        this.sender = sender;
        this.content = "none";
        this.object = object;
    }

    public PackageData(String sender, String content, Object object) {
        this.sender = sender;
        this.content = content;
        this.object = object;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


    @Override
    public String toString() {
        return (sender + ": " + "\"" + content + "\"" + " Object:" + ((object == null) ? "none" : object.toString())
                + " (at " + new Date() + ")");
    }
}
