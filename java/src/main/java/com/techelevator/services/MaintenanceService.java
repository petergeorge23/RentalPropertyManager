package com.techelevator.services;

import com.techelevator.model.Maintenance;

import java.security.Principal;
import java.util.List;

public interface MaintenanceService {
    public Maintenance getMaintenanceById(Principal principal, int maintenanceId);
    public List<Maintenance> getAllMaintenancesByEmployee(Principal principal);
    public List<Maintenance> getMyMaintenance(Principal principal);
    public List<Maintenance> getMyMaintenanceLandlord(Principal principal);
    public List<Maintenance> getMyMaintenance(int renterId);
    public List<Maintenance> getAllMaintenancesByPropertyId(Principal principal, int propertyId);

    public void sendRequest(Principal principal, Maintenance maintenance);

    public void assignRequestToEmployee(Principal principal, int maintenanceId, int employeeId);

    public void completeRequest(Principal principal, int maintenanceId);

    public void addNotesToRequest(Principal principal, int maintenanceId, String notes);

    public boolean currentUserIsAssignedToMaintenanceOrIsAdmin(Principal principal, int maintenanceId);
}
