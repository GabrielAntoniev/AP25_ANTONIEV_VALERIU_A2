package org.example.dao;

import org.example.Database;
import org.example.model.Country;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CountryDAO {
    public void create(String name, String code, int continent) throws SQLException {
        final String QUERY = "INSERT INTO countries (name, code, continent) values (?, ?, ?);";

        try(Connection con = Database.getConnection();
            PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setInt(3, continent);
            pstmt.executeUpdate();
        }
    }

    public Country findById(int id) throws SQLException {
        final String QUERY = "SELECT * FROM countries WHERE id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            return rs.next() ? new Country(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("code"),
                    rs.getInt("continent")
            ) : null;
        }
    }

    public Country findByName(String name) throws SQLException {
        final String QUERY = "SELECT * FROM countries WHERE name=?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            return rs.next() ? new Country(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("code"),
                    rs.getInt("continent")
            ) : null;
        }
    }
}