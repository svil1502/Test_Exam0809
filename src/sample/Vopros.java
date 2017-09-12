package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author admin
 */
public class Vopros {

    private final StringProperty textv;
    private final StringProperty text;
    //private final StringProperty grText;

    //Default constructor

    public Vopros(String textv, String text) {

        this.textv = new SimpleStringProperty(textv);
        this.text = new SimpleStringProperty(text);
        //this.grText = new SimpleStringProperty(grText);

    }

    //Getters

    public String gettextv() {
        return textv.get();
    }

    public String gettext() {
        return text.get();
    }



    //Setters

    public void settextv(String value) {
        textv.set(value);
    }
    public void settext(String value) {
        text.set(value);
    }




    //Property values
    public StringProperty textvProperty() {
        return textv;
    }
    public StringProperty textProperty() {
        return text;
    }




}
