package com.techelevator.services;

import com.techelevator.dao.MaintenanceDao;
import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Maintenance;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
@Component
public class MaintenanceServiceImpl implements MaintenanceService {

    MaintenanceDao maintenanceDao;
    PropertyDao propertyDao;
    UserDao userDao;
    PropertyService propertyService;

    public MaintenanceServiceImpl(MaintenanceDao maintenanceDao, UserDao userDao, PropertyDao propertyDao, PropertyService propertyService) {
        this.maintenanceDao = maintenanceDao;
        this.userDao = userDao;
        this.propertyDao = propertyDao;
        this.propertyService = propertyService;
    }

    @Override
    public Maintenance getMaintenanceById(Principal principal, int maintenanceId) {
        Maintenance maintenance = maintenanceDao.getMaintenanceById(maintenanceId);
        User user = userDao.getUserById(userDao.findIdByUsername(principal.getName()));

        if (user.getAuthorities().toString().contains("ROLE_LANDLORD") || user.getAuthorities().toString().contains("ROLE_ADMIN")) {
            if (propertyService.currentUserOwnsPropertyOrIsAdmin(principal, maintenance.getPropertyId())) {
                return maintenance;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot view maintenance requests on properties you do not own.");
            }
        } else {
            return maintenance;
        }
    }

    @Override
    public List<Maintenance> getAllMaintenancesByEmployee(Principal principal) {
        int employeeId = userDao.findIdByUsername(principal.getName());
        return maintenanceDao.getAllMaintenancesByEmployeeId(employeeId);
    }

    @Override
    public List<Maintenance> getMyMaintenance(Principal principal) {
        int renterId = userDao.findIdByUsername(principal.getName());
        return maintenanceDao.getAllMaintenancesByRenterId(renterId);
    }

    @Override
    public List<Maintenance> getMyMaintenanceLandlord(Principal principal) {
        int landlordId = userDao.findIdByUsername(principal.getName());
        return maintenanceDao.getAllMaintenancesByLandlordId(landlordId);
    }

    @Override
    public List<Maintenance> getMyMaintenance(int renterId) {
        return maintenanceDao.getAllMaintenancesByRenterId(renterId);
    }

    @Override
    public List<Maintenance> getAllMaintenancesByPropertyId(Principal principal, int propertyId) {
        List<Maintenance> maintenanceList = maintenanceDao.getAllMaintenancesByPropertyId(propertyId);

        User user = userDao.getUserById(userDao.findIdByUsername(principal.getName()));

        if (user.getAuthorities().toString().contains("ROLE_LANDLORD") || user.getAuthorities().toString().contains("ROLE_ADMIN")) {
            if (propertyService.currentUserOwnsPropertyOrIsAdmin(principal, propertyId)) {
                return maintenanceList;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot view maintenance requests on properties you do not own.");
            }
        } else {
            return maintenanceList;
        }
    }

    @Override
    public void sendRequest(Principal principal, Maintenance maintenance) {
        int renterId = userDao.findIdByUsername(principal.getName());
        int renterPropertyId = propertyDao.getPropertyIdByRenterId(renterId);

        if ((renterPropertyId == maintenance.getPropertyId()) || userDao.userIsAdmin(principal)) {
            maintenance.setRenterId(renterId);
            maintenanceDao.sendRequest(maintenance);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot request maintenance on a property you do not rent.");
        }

    }
    @Override
    public void assignRequestToEmployee(Principal principal, int maintenanceId, int employeeId) {
        Maintenance maintenance = maintenanceDao.getMaintenanceById(maintenanceId);

        if (propertyService.currentUserOwnsPropertyOrIsAdmin(principal, maintenance.getPropertyId())) {
            maintenanceDao.assignRequestToEmployee(maintenanceId, employeeId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot assign an employee to a maintenance request on a property you do not own.");
        }
    }

    @Override
    public void completeRequest(Principal principal, int maintenanceId) {
        if (currentUserIsAssignedToMaintenanceOrIsAdmin(principal, maintenanceId)) {
            maintenanceDao.completeRequest(maintenanceId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You may only mark a request as complete if you have been assigned to it.");
        }
    }

    @Override
    public void addNotesToRequest(Principal principal, int maintenanceId, String notes) {
        if (currentUserIsAssignedToMaintenanceOrIsAdmin(principal, maintenanceId)) {
            maintenanceDao.addNotesToRequest(maintenanceId, notes);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You may add notes to a request as complete if you have been assigned to it.");
        }
    }

    @Override
    public boolean currentUserIsAssignedToMaintenanceOrIsAdmin(Principal principal, int maintenanceId) {
        int employeeId = userDao.findIdByUsername(principal.getName());
        List<Integer> employeeMaintenanceIds = maintenanceDao.getAllMaintenanceIdsByEmployeeId(employeeId);
        return (employeeMaintenanceIds.contains(maintenanceId) || userDao.userIsAdmin(principal));
    }
}
