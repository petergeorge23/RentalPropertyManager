package com.techelevator.controller;

import com.techelevator.model.Maintenance;
import com.techelevator.services.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/maintenance")
@PreAuthorize("isAuthenticated()")
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD', 'ROLE_EMPLOYEE')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Maintenance getMaintenanceById(Principal principal, @PathVariable int id) {
        return maintenanceService.getMaintenanceById(principal, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD', 'ROLE_EMPLOYEE')")
    @RequestMapping(path = "/property/{id}", method = RequestMethod.GET)
    public List<Maintenance> getAllMaintenancesByPropertyId(Principal principal, @PathVariable int id) {
        return maintenanceService.getAllMaintenancesByPropertyId(principal, id);
    }

    @PreAuthorize("hasRole('ROLE_RENTER')")
    @RequestMapping(path = "/renter", method = RequestMethod.GET)
    public List<Maintenance> getMyMaintenanceRenter(Principal principal) {
        return maintenanceService.getMyMaintenance(principal);
    }

    @PreAuthorize("hasRole('ROLE_LANDLORD')")
    @RequestMapping(path = "/landlord", method = RequestMethod.GET)
    public List<Maintenance> getMyMaintenanceLandlord(Principal principal) {
        return maintenanceService.getMyMaintenanceLandlord(principal);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/renter/{id}", method = RequestMethod.GET)
    public List<Maintenance> getMyMaintenance(@PathVariable int renterId) {
        return maintenanceService.getMyMaintenance(renterId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/renter/request", method = RequestMethod.POST)
    public void sendRequest(Principal principal, @RequestBody Maintenance maintenance) {
        maintenanceService.sendRequest(principal, maintenance);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD')")
    @RequestMapping(path = "/landlord/assign", method = RequestMethod.PUT)
    public void assignRequestToEmployee(Principal principal, @RequestBody Map<String, Integer> ids) {
        maintenanceService.assignRequestToEmployee(principal, ids.get("maintenanceId"), ids.get("employeeId"));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    @RequestMapping(path = "/employee", method = RequestMethod.GET)
    public List<Maintenance> getAllMaintenancesByEmployee(Principal principal) {
        return maintenanceService.getAllMaintenancesByEmployee(principal);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    @RequestMapping(path = "/employee/{id}/complete", method = RequestMethod.PUT)
    public void completeRequest(Principal principal, @PathVariable int id) {
        maintenanceService.completeRequest(principal, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
    @RequestMapping(path = "/employee/{id}/notes", method = RequestMethod.PUT)
    public void addNotesToRequest(Principal principal, @PathVariable int id, @RequestBody Map<String, String> map) {
        maintenanceService.addNotesToRequest(principal, id, map.get("notes"));
    }
}
