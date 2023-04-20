package com.techelevator.dao;

import com.techelevator.model.Maintenance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMaintenanceDao implements MaintenanceDao {

    JdbcTemplate jdbcTemplate;

    public JdbcMaintenanceDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Maintenance getMaintenanceById(int maintenanceId) {
        Maintenance maintenance = new Maintenance();

        String sql = "SELECT maintenance_id, property_id, renter_id, employee_id, maintenance_status, request_details, notes " +
                "FROM maintenance " +
                "WHERE maintenance_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, maintenanceId);

        if (results.next()) {
            maintenance = mapRowToMaintenance(results);
        }

        return maintenance;
    }

    @Override
    public List<Maintenance> getAllMaintenancesByEmployeeId(int employeeId) {
        List<Maintenance> maintenanceList = new ArrayList<>();
        String sql = "SELECT maintenance_id, property_id, renter_id, employee_id, maintenance_status, request_details, notes " +
                "FROM maintenance " +
                "WHERE employee_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employeeId);

        while (results.next()) {
            maintenanceList.add(mapRowToMaintenance(results));
        }

        return maintenanceList;
    }

    @Override
    public List<Maintenance> getAllMaintenancesByLandlordId(int landlordId) {
        List<Maintenance> maintenanceList = new ArrayList<>();
        String sql = "SELECT maintenance_id, maintenance.property_id, renter_id, employee_id, maintenance_status, request_details, notes " +
                "FROM maintenance " +
                "JOIN property_landlord " +
                "ON property_landlord.property_id = maintenance.property_id " +
                "JOIN users " +
                "ON property_landlord.landlord_id = users.user_id " +
                "WHERE users.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, landlordId);

        while (results.next()) {
            maintenanceList.add(mapRowToMaintenance(results));
        }

        return maintenanceList;
    }

    @Override
    public List<Integer> getAllMaintenanceIdsByEmployeeId(int employeeId) {
        List<Integer> maintenanceIdList = new ArrayList<>();
        String sql = "SELECT maintenance_id " +
                "FROM maintenance " +
                "WHERE employee_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, employeeId);

        while (results.next()) {
            maintenanceIdList.add(results.getInt("maintenance_id"));
        }

        return maintenanceIdList;
    }

    @Override
    public List<Maintenance> getAllMaintenancesByRenterId(int renterId) {
        List<Maintenance> maintenanceList = new ArrayList<>();

        String sql = "SELECT maintenance_id, property_id, renter_id, employee_id, maintenance_status, request_details, notes " +
                "FROM maintenance " +
                "WHERE renter_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, renterId);

        while (results.next()) {
            maintenanceList.add(mapRowToMaintenance(results));
        }

        return maintenanceList;
    }

    @Override
    public List<Maintenance> getAllMaintenancesByPropertyId(int propertyId) {
        List<Maintenance> maintenanceList = new ArrayList<>();

        String sql = "SELECT maintenance_id, property_id, renter_id, employee_id, maintenance_status, request_details, notes " +
                "FROM maintenance " +
                "WHERE property_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);

        while (results.next()) {
            maintenanceList.add(mapRowToMaintenance(results));
        }

        return maintenanceList;
    }

    @Override
    public void sendRequest(Maintenance maintenance) {
        String sql = "INSERT INTO maintenance (property_id, renter_id, maintenance_status, request_details, notes) " +
                "VALUES (?, ?, 'REQUESTED', ?, ?);";
        int results = jdbcTemplate.update(sql, maintenance.getPropertyId(), maintenance.getRenterId(), maintenance.getRequestDetails(), maintenance.getNotes());
    }

    @Override
    public void assignRequestToEmployee(int maintenanceId, int employeeId) {
        String sql = "UPDATE maintenance " +
                "SET employee_id = ?, maintenance_status = 'IN PROGRESS' " +
                "WHERE maintenance_id = ?;";
        int results = jdbcTemplate.update(sql, employeeId, maintenanceId);
    }

    @Override
    public void completeRequest(int maintenanceId) {
        String sql = "UPDATE maintenance " +
                "SET maintenance_status = 'COMPLETE' " +
                "WHERE maintenance_id = ?;";
        int results = jdbcTemplate.update(sql, maintenanceId);
    }

    @Override
    public void addNotesToRequest(int maintenanceId, String notes) {
        String sql = "UPDATE maintenance " +
                "SET notes = ? " +
                "WHERE maintenance_id = ?;";
        int results = jdbcTemplate.update(sql, notes, maintenanceId);
    }

    private Maintenance mapRowToMaintenance(SqlRowSet rowSet) {
        Maintenance maintenance = new Maintenance();

        maintenance.setMaintenanceId(rowSet.getInt("maintenance_id"));
        maintenance.setPropertyId(rowSet.getInt("property_id"));
        maintenance.setRenterId(rowSet.getInt("renter_id"));
        maintenance.setEmployeeId(rowSet.getInt("employee_id"));
        maintenance.setMaintenanceStatus(rowSet.getString("maintenance_status"));
        maintenance.setRequestDetails(rowSet.getString("request_details"));
        maintenance.setNotes(rowSet.getString("notes"));

        return maintenance;
    }
}
