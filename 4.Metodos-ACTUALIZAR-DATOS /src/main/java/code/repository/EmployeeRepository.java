package code.repository;

import code.model.Employee;
import code.util.DatabaseConnection;

import java.sql.*;
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
                Employee e = createEmployee(myRes);
                employees.add(e);
            }
        }

        return employees; // Debes devolver la lista de empleados.
    }




    @Override
    public Employee getById(Integer id) {
        Employee employee = null;

        try (Connection connection = getConnection();
             PreparedStatement myStamt = connection.prepareStatement("SELECT * FROM empleados WHERE id = ?")) {

            myStamt.setInt(1, id);

            try (ResultSet myRes = myStamt.executeQuery()) {
                if (myRes.next()) {
                    employee = createEmployee(myRes);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción de alguna manera, por ejemplo, lanzar una excepción personalizada o devolver null.
        }

        return employee;
    }


    @Override
    public void save(Employee employee) throws SQLException {
        String sql;
        // si contiene un valor numerico mayor que cero , entonces existe un id y es una actualizacion
        if(employee.getId()!= null && employee.getId()>0){
            sql = "UPDATE empleados SET nombre = ?, apellidos = ?, correo = ?, salario = ? WHERE id = ?";
        }else{
            sql = "INSERT INTO empleados(nombre,apellidos,correo,salario) VALUES(?,?,?,?)";
        }
        try(PreparedStatement myStamt = getConnection().prepareStatement(sql)){
            myStamt.setString(1,employee.getNombre());
            myStamt.setString(2,employee.getApellidos());
            myStamt.setString(3,employee.getCorreo());
            myStamt.setInt(4,employee.getSalario());
            if (employee.getId() != null && employee.getId()>0) {
                myStamt.setInt(5,employee.getId());
            }
            myStamt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement myStamt = getConnection().prepareStatement("DELETE FROM empleados WHERE id=?")){
            myStamt.setInt(1,id);
            myStamt.executeUpdate();
        }

    }

    //Metodo para crear empleados
    private Employee createEmployee(ResultSet myRes) throws SQLException {
        Employee e = new Employee();
        e.setId(myRes.getInt("id"));
        e.setNombre(myRes.getString("nombre")); // Cambiado a getString para obtener una cadena.
        e.setApellidos(myRes.getString("apellidos")); // Cambiado a getString.
        e.setCorreo(myRes.getString("correo")); // Cambiado a getString.
        e.setSalario(myRes.getInt("salario")); // Cambiado a getDouble para obtener un número decimal.
        //employees.add(e);
        return e;
    }
}
