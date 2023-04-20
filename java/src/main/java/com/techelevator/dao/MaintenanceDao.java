package com.techelevator.dao;

import com.techelevator.model.Maintenance;

import java.util.List;

public interface MaintenanceDao {
    public Maintenance getMaintenanceById(int maintenanceId);
    public List<Maintenance> getAllMaintenancesByEmployeeId(int employeeId);
    public List<Maintenance> getAllMaintenancesByRenterId(int renterId);
    public List<Maintenance> getAllMaintenancesByPropertyId(int propertyId);

    public void sendRequest(Maintenance maintenance);

    public void assignRequestToEmployee(int maintenanceId, int employeeId);

    public void completeRequest(int maintenanceId);

    public void addNotesToRequest(int maintenanceId, String notes);

    public List<Integer> getAllMaintenanceIdsByEmployeeId(int employeeId);
    public List<Maintenance> getAllMaintenancesByLandlordId(int landlordId);
}
