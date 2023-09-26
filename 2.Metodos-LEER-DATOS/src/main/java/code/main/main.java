package code.main;

import code.util.DatabaseConnection;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {



        try (   Connection myConn = DatabaseConnection.getInstance();
                Statement myStamt = myConn.createStatement();
                ResultSet myRes = myStamt.executeQuery("SELECT * FROM usuarios");){
                System.out.println("Conexion de forma correcta ");

                while (myRes.next()){
                    System.out.println(myRes.getString("nombre"));
                }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Algo salio mal ");
        }
    }
}
