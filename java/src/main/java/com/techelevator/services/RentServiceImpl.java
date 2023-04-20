package com.techelevator.services;

import com.techelevator.dao.RentDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Property;
import com.techelevator.model.Rent;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Component
public class RentServiceImpl implements RentService {
    RentDao rentDao;
    UserDao userDao;
    PropertyService propertyService;

    public RentServiceImpl (RentDao rentDao, UserDao userDao, PropertyService propertyService) {
        this.rentDao = rentDao;
        this.userDao = userDao;
        this.propertyService = propertyService;
    }

    @Override
    public Rent getMyRent(Principal principal) {
        int renterId = userDao.findIdByUsername(principal.getName());
        return rentDao.getRentByRenterId(renterId);
    }

    @Override
    public Rent getMyRent(int renterId) {
        return rentDao.getRentByRenterId(renterId);
    }

    @Override
    public void payRent(Principal principal, double amount) {
        int rentId = getMyRent(principal).getRentId();
        rentDao.payRent(amount, rentId);
    }

    @Override
    public List<Rent> getAllRents(Principal principal) {
        int landlordId = userDao.findIdByUsername(principal.getName());
        return rentDao.getAllRentsByLandlordId(landlordId);
    }
    @Override
    public List<Rent> getAllRents(int landlordId) {
        return rentDao.getAllRentsByLandlordId(landlordId);
    }

    @Override
    public void chargeRent(Principal principal, Property property) {
        if (propertyService.currentUserOwnsPropertyOrIsAdmin(principal, property.getPropertyId())) {
            int rentId = rentDao.getRentByPropertyId(property.getPropertyId()).getRentId();
            double amount = property.getRent();
            rentDao.chargeRent(amount, rentId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot charge rent on a property you do not own.");
        }
    }

    @Override
    public void chargeRent(int landlordId, Property property) {
        int rentId = rentDao.getRentByPropertyId(property.getPropertyId()).getRentId();
        double amount = property.getRent();
        rentDao.chargeRent(amount, rentId);
    }
}
