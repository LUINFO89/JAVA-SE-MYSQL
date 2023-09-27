package code.main;

import code.model.Employee;
import code.repository.EmployeeRepository;
import code.repository.Repository;
import code.util.DatabaseConnection;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {

                try (Connection myConn = DatabaseConnection.getInstance()){

                Repository<Employee> reopository = new EmployeeRepository();

              //  System.out.println("------Listado----------");
              //  reopository.findAll().forEach(System.out::println);

                System.out.println("------Actualizando un empleado----------");
                Employee employee = new Employee();
                employee.setId(1);
                employee.setNombre("Diego");
                employee.setApellidos("Soto");
                employee.setCorreo("DiegoSoto@ejemplo.com");
                employee.setSalario(20000);
                reopository.save(employee);
                System.out.println("------Empleado Actualizado----------");
                reopository.findAll().forEach(System.out::println);


            }catch (SQLException e) {
                    e.printStackTrace();
                }



    }
}
