package com.startererp.dao;

import com.startererp.dto.UserDTO;
import com.startererp.util.DBConnection;
import com.startererp.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public UserDTO authenticate(String username, String plainPassword) {

        UserDTO user = null;

        String sql = "SELECT username, password, role FROM users WHERE username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");

                if (PasswordUtil.checkPassword(plainPassword, hashedPassword)) {
                    user = new UserDTO();
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
