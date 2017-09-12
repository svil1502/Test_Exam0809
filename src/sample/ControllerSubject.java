package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import  sample.Otvet;
import  sample.Vopros;
import  sample.LoginModel;
import sample.FirebirdConnection;

/**
 * Created by svetlanailina on 11.09.17.
 */
public class ControllerSubject  implements Initializable {
    @FXML
    private TableView<Subj> tableSubj;

    @FXML
    private TableColumn<Subj, String> column_id;

    @FXML
    private TableColumn<Subj, String> column_name;


    private ObservableList<Subj> id = FXCollections.observableArrayList();
    private ObservableList<Subj> name = FXCollections.observableArrayList();

    private ObservableList<Subj> data;
    private FirebirdConnection dc;


    @Override

    public void initialize(URL location, ResourceBundle resources) {

        column_id.setCellFactory(TextFieldTableCell.forTableColumn());// id_v
        column_id.setCellValueFactory(cellData->cellData.getValue().idProperty());

        column_name.setCellFactory(TextFieldTableCell.forTableColumn());// id_v
        column_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }




    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
        try {
            Connection conn = dc.Connector();
            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT *  FROM subject");

            while (rs.next())
            {
                //get string from db,whichever way
                data.add(new Subj(rs.getString(1), rs.getString(2)));

            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }


        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableSubj.setItems(null);
        tableSubj.setItems(data);

    }



}
