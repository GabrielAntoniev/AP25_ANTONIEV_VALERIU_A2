package org.example.dao;

import org.example.Database;
import org.example.model.Continent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContinentDAO {
    public void create(String continentName) throws SQLException {
        final String QUERY = "INSERT INTO continents (name) values (?)";

        try(Connection con = Database.getConnection();
            PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setString(1, continentName);
            pstmt.executeUpdate();
        }
    }

    public Continent findById(int id) throws SQLException {
        final String QUERY = "SELECT * FROM continents WHERE id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            return rs.next() ? new Continent(
                    rs.getInt("id"),
                    rs.getString("name")
            ) : null;
        }
    }

    public Continent findByName(String name) throws SQLException {
        final String QUERY = "SELECT * FROM continents WHERE name=?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            return rs.next() ? new Continent(
                    rs.getInt("id"),
                    rs.getString("name")
            ) : null;
        }
    }
}
