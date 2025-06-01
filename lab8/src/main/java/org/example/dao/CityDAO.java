package org.example.dao;

import org.example.Database;
import org.example.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDAO  {
    public void create(City city) throws SQLException {
        final String QUERY = "INSERT INTO cities (country, name, capital, latitude, longitude) values (?, ?, ?, ?, ?)";

        try(Connection con = Database.getConnection();
            PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setInt(1, city.getCountry());
            pstmt.setString(2, city.getName());
            pstmt.setBoolean(3, city.isCapital());
            pstmt.setDouble(4, city.getLatitude());
            pstmt.setDouble(5, city.getLongitude());
            pstmt.executeUpdate();
        }
    }

    public City findById(int id) throws SQLException {
        final String QUERY = "SELECT * FROM cities WHERE id=?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            return rs.next() ? new City(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("capital"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"),
                    rs.getInt("country")
            ) : null;
        }
    }

    public City findByName(String name) throws SQLException {
        final String QUERY = "SELECT * FROM cities WHERE name=?";

        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(QUERY)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            return rs.next() ? new City(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("capital"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"),
                    rs.getInt("country")
            ) : null;
        }
    }

    public void deleteAll(){
        final String QUERY = "DELETE FROM cities WHERE id > 0";
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(QUERY)){

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
