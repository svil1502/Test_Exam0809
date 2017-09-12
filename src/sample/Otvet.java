package sample;


/**
 * Created by svetlanailina on 03.09.17.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by svetlanailina on 29.08.17.
 */
public class Otvet {



    private final SimpleStringProperty id_o;
    private final SimpleStringProperty text;
    private final SimpleStringProperty id_v;


    //Default constructor

    public Otvet( String id_v, String id_o, String text) {

        this.id_o = new SimpleStringProperty(id_o);
        this.text = new SimpleStringProperty(text);
        this.id_v = new SimpleStringProperty(id_v);


    }

    //Getters

    public String getid_o() {
        return id_o.get();
    }
    public String gettext() {
        return text.get();
    }
    public String getid_v() {
        return id_v.get();
    }


    //Setters

    public void setid_o(String value) {
        id_o.set(value);
    }
    public void settext(String value) {
        text.set(value);
    }
    public void setid_v(String value) {
        id_v.set(value);
    }


    //Property values

    public SimpleStringProperty id_oProperty() {return id_o;}
    public SimpleStringProperty textProperty() {
        return text;
    }
    public SimpleStringProperty id_vProperty() {return id_v;}

    @Override
    public String toString() {
        return "Otvet{" +
                "id_o=" + id_o +
                ", text=" + text +
                ", id_v=" + id_v +
                '}';
    }
}


