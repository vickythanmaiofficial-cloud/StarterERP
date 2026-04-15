package com.startererp.dao;

import com.startererp.dto.EmployeeDTO;
import com.startererp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//EmployeeDAO Class
public class EmployeeDAO {

    public EmployeeDTO getEmployeeByUsername(String username) {

        EmployeeDTO employee = null;

        String sql = "SELECT id, username, name, email, department " +
                     "FROM employees WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                employee = new EmployeeDTO();
                employee.setId(rs.getInt("id"));
                employee.setUsername(rs.getString("username"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setDepartment(rs.getString("department"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;
    }
    public List<EmployeeDTO> getAllEmployees() {

        List<EmployeeDTO> employees = new ArrayList<>();

        String sql = "SELECT id, username, name, email, department FROM employees";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmployeeDTO e = new EmployeeDTO();
                e.setId(rs.getInt("id"));
                e.setUsername(rs.getString("username"));
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setDepartment(rs.getString("department"));

                employees.add(e);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return employees;
    }
    public boolean addEmployee(EmployeeDTO employee) {

        String sql = "INSERT INTO employees (username, name, email, department) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getUsername());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getDepartment());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean usernameExists(String username) {

        String sql = "SELECT id FROM employees WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // true if found

        } catch (Exception e) {
            e.printStackTrace();
            return true; // fail-safe: assume exists
        }
    }
    public boolean deleteEmployee(int employeeId) {

        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public EmployeeDTO getEmployeeById(int id) {

        String sql = "SELECT id, username, name, email, department FROM employees WHERE id = ?";
        EmployeeDTO employee = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                employee = new EmployeeDTO();
                employee.setId(rs.getInt("id"));
                employee.setUsername(rs.getString("username"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setDepartment(rs.getString("department"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;
    }
    public boolean updateEmployee(EmployeeDTO employee) {

        String sql = "UPDATE employees SET name = ?, email = ?, department = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getDepartment());
            ps.setInt(4, employee.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }






}
