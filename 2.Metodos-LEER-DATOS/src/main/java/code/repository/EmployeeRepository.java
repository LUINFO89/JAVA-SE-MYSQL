package code.repository;

import code.model.Employee;
import code.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee>{

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance(); // Agregué ".getConnection()"
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>(); // Debes crear una lista para almacenar los empleados.

        try (Connection connection = getConnection(); // Obtener la conexión correctamente
             Statement myStamt = connection.createStatement();
             ResultSet myRes = myStamt.executeQuery("SELECT * FROM empleados")) {

            while (myRes.next()) {
                Employee e = new Employee();
                e.setId(myRes.getInt("id"));
                e.setNombre(myRes.getString("nombre")); // Cambiado a getString para obtener una cadena.
                e.setApellidos(myRes.getString("apellidos")); // Cambiado a getString.
                e.setCorreo(myRes.getString("correo")); // Cambiado a getString.
                e.setSalario(myRes.getInt("salario")); // Cambiado a getDouble para obtener un número decimal.
                employees.add(e);
            }
        }

        return employees; // Debes devolver la lista de empleados.
    }







    @Override
    public Employee getById(Integer id) {
        return null;
    }

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void delete(Integer id) {

    }
}
