package com.techelevator.dao;

import com.techelevator.model.Rent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcRentDao implements RentDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcRentDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Rent getRentByRenterId(int renterId) {
        Rent rent = new Rent();

        String sql = "SELECT rent_id, rent_status, rent_due_date, rent.property_id, property_renter.renter_id, property_landlord.landlord_id, property.rent AS rent_amount, balance " +
                "FROM rent " +
                "JOIN property_landlord " +
                "ON property_landlord.property_id = rent.property_id " +
                "JOIN property_renter " +
                "ON property_renter.property_id = rent.property_id " +
                "JOIN property ON property.property_id = rent.property_id " +
                "WHERE property_renter.renter_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, renterId);

        if (results.next()) {
            rent = mapRowToRent(results);
        }

        return rent;
    }

    @Override
    public List<Rent> getAllRentsByLandlordId(int landlordId) {
        List<Rent> rentList = new ArrayList<>();

        String sql = "SELECT rent_id, rent_status, rent_due_date, rent.property_id, property_renter.renter_id, property_landlord.landlord_id, property.rent AS rent_amount, balance " +
                "FROM rent " +
                "JOIN property_landlord " +
                "ON property_landlord.property_id = rent.property_id " +
                "JOIN property_renter " +
                "ON property_renter.property_id = rent.property_id " +
                "JOIN property ON property.property_id = rent.property_id " +
                "WHERE property_landlord.landlord_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, landlordId);

        while (results.next()) {
            rentList.add(mapRowToRent(results));
        }

        return rentList;
    }

    @Override
    public void payRent(double amount, int rentId) {
        String sql = "INSERT INTO rent_log (transaction_type, transaction_date, amount, rent_id) " +
                "VALUES ('payment', ?, ?, ?);";
        int results = jdbcTemplate.update(sql, LocalDate.now(), amount, rentId);

        sql = "UPDATE rent " +
                "SET balance = (balance - ?), rent_status = 'Paid'" +
                "WHERE rent_id = ?;";
        results = jdbcTemplate.update(sql, amount, rentId);
    }

    @Override
    public void chargeRent(double amount, int rentId) {
        String sql = "INSERT INTO rent_log (transaction_type, transaction_date, amount, rent_id) " +
                "VALUES ('charge', ?, ?, ?);";
        int results = jdbcTemplate.update(sql, LocalDate.now(), amount, rentId);

        sql = "UPDATE rent " +
                "SET balance = (balance + ?), rent_status = 'Due'" +
                "WHERE rent_id = ?;";
        results = jdbcTemplate.update(sql, amount, rentId);
    }

    @Override
    public Rent getRentByPropertyId(int propertyId) {
        Rent rent = new Rent();

        String sql = "SELECT rent_id, rent_status, rent_due_date, rent.property_id, property_renter.renter_id, property_landlord.landlord_id, property.rent AS rent_amount, balance " +
                "FROM rent " +
                "JOIN property_landlord " +
                "ON property_landlord.property_id = rent.property_id " +
                "JOIN property_renter " +
                "ON property_renter.property_id = rent.property_id " +
                "JOIN property ON property.property_id = rent.property_id " +
                "WHERE property_landlord.property_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);

        if (results.next()) {
            rent = mapRowToRent(results);
        }

        return rent;
    }

    public Rent mapRowToRent(SqlRowSet rowSet) {
        Rent rent = new Rent();

        rent.setRentId(rowSet.getInt("rent_id"));
        rent.setRentStatus(rowSet.getString("rent_status"));
        rent.setPropertyId(rowSet.getInt("property_id"));
        rent.setRenterId(rowSet.getInt("renter_id"));
        rent.setLandlordId(rowSet.getInt("landlord_id"));
        rent.setRentAmount(rowSet.getDouble("rent_amount"));
        rent.setBalance(rowSet.getDouble("balance"));
        rent.setRentDueDate(rowSet.getDate("rent_due_date"));

        return rent;
    }
}
