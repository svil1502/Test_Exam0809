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



public class Controller implements Initializable {
    public LoginModel LoginModel = new LoginModel();


    @FXML
    private Label isConnected;

    @FXML
    private TableView<Vopros> tableUser;

    @FXML
    private TableColumn<Vopros, String> column_id;

    @FXML
    private TableColumn<Vopros, String> column_name;


    private ObservableList<Otvet> id_v = FXCollections.observableArrayList();//внешний ключ
    private ObservableList<Otvet> id_o = FXCollections.observableArrayList();
    private ObservableList<Otvet> text = FXCollections.observableArrayList();


    @FXML
    private Button btnLoad;

    private ObservableList<Vopros> data;
    private FirebirdConnection dc;

    // 2 таблица

    @FXML
    private TableView<Otvet> tableOUser;

    @FXML
    private TableColumn<Otvet, String> column_oid;


    @FXML
    private TableColumn<Otvet, String> column_oidname;

    @FXML
    private TableColumn<Otvet, String> column_oname;

    private final ObservableList<Otvet> datao = FXCollections.observableArrayList();

//поля ввода информации

    @FXML
    private TextField subj;

    @FXML
    private TextField all;

    @FXML
    private TextField hard;

    @FXML
    private TextField arrange;

    @FXML
    private TextField easy;


    @Override

    public void initialize(URL location, ResourceBundle resources) {

        if (LoginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Not Connected");
        }

        // column_oid.setCellValueFactory(new PropertyValueFactory<>("id_v"));
        //   column_oidname.setCellValueFactory(new PropertyValueFactory<>("text"));
        // column_oname.setCellValueFactory(new PropertyValueFactory<>("id_o"));

        column_oid.setCellFactory(TextFieldTableCell.forTableColumn());// id_v
        column_oid.setCellValueFactory(cellData->cellData.getValue().id_oProperty());

        column_oidname.setCellFactory(TextFieldTableCell.forTableColumn());// id_v
        column_oidname.setCellValueFactory(cellData->cellData.getValue().id_vProperty());

        column_oname.setCellFactory(TextFieldTableCell.forTableColumn());// id_v
        column_oname.setCellValueFactory(cellData -> cellData.getValue().textProperty());
    }
/*
    select first  :kol  idv, gr, textv, id_subj from vopros
where idv in(
select first :kol0  idv
from vopros  where gr='0'
union
select first :kol1  idv
from vopros  where gr='1'
union
select first :kol2  idv
from vopros  where gr='2' )
and id_subj=:sub
order by rand()

 select first  " +kol+ "  idv, gr, textv, id_subj from vopros
where idv in(
select first " +kol0+ "  idv
from vopros  where gr='0'
union
select first " +kol1+ " idv
from vopros  where gr='1'
union
select first " +kol2+ "  idv
from vopros  where gr='2' )
and id_subj=:sub
order by rand()

select first  " +kol+ "  idv, gr, textv, id_subj from vopros
where idv in(
select first " +kol0+ "  idv
from vopros  where gr='0'
union
select first " +kol1+ " idv
from vopros  where gr='1'
union
select first " +kol2+ "  idv
from vopros  where gr='2' )
order by rand()

statement.execute("INSERT INTO deftab (name ) VALUE (txtName.getText().toString)");
txtName это textfield

 */



    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
        try {
            Connection conn = dc.Connector();
            data = FXCollections.observableArrayList();
            String sub = subj.getText().toString();
            String kol = all.getText().toString();
            String kol0 = easy.getText().toString();
            String kol1 = arrange.getText().toString();
            String kol2 = hard.getText().toString();

           // ResultSet rs = conn.createStatement().executeQuery("SELECT idv, textv  FROM vopros");
  //ResultSet rs = conn.createStatement().executeQuery("SELECT first " +kol+ " idv, textv  FROM vopros");
            ResultSet rs = conn.createStatement().executeQuery("select first  " +kol+ "  idv, gr, textv, id_subj from vopros where idv in (select first " +kol0+ "  idv from vopros  where gr='0'  union select first " +kol1+ " idv from vopros  where gr='1' union select first " +kol2+ "  idv from vopros  where gr='2' ) and id_subj=" +sub+ " order by rand()");
            //ResultSet rs2 = conn.createStatement().executeQuery("SELECT ID_V as a1, ID_O as a2, TEXT as a3 FROM otvet where otvet.ID_V='" + Integer.valueOf(thCatalog) + "'");

            while (rs.next())
            {
                //get string from db,whichever way
                data.add(new Vopros(rs.getString(1), rs.getString(2)));

            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }


        column_id.setCellValueFactory(new PropertyValueFactory<>("textv"));
        column_name.setCellValueFactory(new PropertyValueFactory<>("text"));

        tableUser.setItems(null);
        tableUser.setItems(data);

    }

    @FXML
    private  void clickFieldSubj(MouseEvent evt) {
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            //System.out.println("Double Click");
            //Subj tempPerson = new Subj();
          Main.showPersonEditDialog();

        }
        /*try {
           System.out.println("Есть!!");
       }
       catch (Exception e) {

       }*/
    }

    // начало


    @FXML
    private void clickCatalogList(MouseEvent event) {

        try {
            System.out.println("id_v"+id_v); // проверка
            Vopros selGroups = (Vopros)  tableUser.getSelectionModel().getSelectedItem();
            showListChildrenCatalog(selGroups.gettextv()); //это вызов нужной функции по заполнению и передача параметров в нее
/*
            Connection conn = FirebirdConnection.Connector();
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT ID_V as a1, ID_O as a2, TEXT as a3 FROM otvet");

            while (rs2.next()) {
                System.out.println("текст");
                datao.addAll(new Otvet(rs2.getString(2), rs2.getString(1), rs2.getString(3)));
            }
            conn.close();
            */
        } catch (Exception e) {

        }

        tableOUser.setItems(id_v); // дб внешний ключ
        tableOUser.setItems(datao);
        System.out.println("id_v"+id_v);//проверка
    }



    private void showListChildrenCatalog(String thCatalog) {
        datao.clear();
        try {
            System.out.println("каталог"+thCatalog); // проверка
            Connection conn = FirebirdConnection.Connector();
            //datao = FXCollections.observableArrayList();

            ResultSet rs2 = conn.createStatement().executeQuery("SELECT ID_V as a1, ID_O as a2, TEXT as a3 FROM otvet where otvet.ID_V='" + Integer.valueOf(thCatalog) + "'");

            //  ResultSet rs2 = conn.createStatement().executeQuery("SELECT ID_V as a1, ID_O as a2, TEXT as a3 FROM otvet");


            //ResultSet rs = conn.createStatement().executeQuery("SELECT ID_V, ID_O, TEXT FROM otvet");

            while (rs2.next()) {
                System.out.println("текст");
                datao.addAll(new Otvet(rs2.getString(2), rs2.getString(1), rs2.getString(3)));
            }
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }




        //column_oidname.setCellValueFactory(new PropertyValueFactory<>("id_o")); //id_o

        //column_oname.setCellValueFactory(new PropertyValueFactory<>("text"));  //text

        // tableOUser.setItems(null);
        tableOUser.setItems(datao);

    }


//


}
