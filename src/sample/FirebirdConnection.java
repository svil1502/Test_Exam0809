package sample;
import java.sql.*;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by svetlanailina on 25.08.17.
 */
public class FirebirdConnection {



    public static Connection Connector() {
        String strDatabasePath = "/Users/svetlanailina/Desktop/Test_Exam2508/EXAM.FDB";
        //jdbc:firebirdsql:localhost/3050:/Users/svetlanailina/Desktop/test_fire-master/EXAM.FDB?lc_ctype=WIN1251;sql_dialect=3
        String strURL = "jdbc:firebirdsql:localhost/3050:" + strDatabasePath + "?lc_ctype=WIN1251;sql_dialect=3";
        String strUser = "SYSDBA";
        String strPassword = "sysdba";

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection conn = DriverManager.getConnection(strURL, strUser, strPassword);
            return conn;
        } catch (Exception e) {
            return null;
        }
    }

}

