package com.techelevator.services;

import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Image;
import com.techelevator.model.Property;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Component
public class PropertyServiceImpl implements PropertyService {
    PropertyDao propertyDao;
    UserDao userDao;

    public PropertyServiceImpl(PropertyDao propertyDao, UserDao userDao) {
        this.propertyDao = propertyDao;
        this.userDao = userDao;
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyDao.getAllProperties();
    }

    @Override
    public Property getPropertyByPropertyId(int propertyId) {
        return propertyDao.getPropertyByPropertyId(propertyId);
    }

    @Override
    public void updateProperty (Principal principal, Property updatedProperty) {
        if (currentUserOwnsPropertyOrIsAdmin(principal, updatedProperty.getPropertyId())) {
            propertyDao.updateProperty(updatedProperty);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot edit a property you do not own.");
        }
    }

    @Override
    public void addProperty(Principal principal, Property property) {
        int landlordId = userDao.findIdByUsername(principal.getName());
        propertyDao.addProperty(landlordId, property);
    }

    @Override
    public void addProperty(int landlordId, Property property) {
        propertyDao.addProperty(landlordId, property);
    }

    @Override
    public void removeProperty(Principal principal, int propertyId) {
        if (currentUserOwnsPropertyOrIsAdmin(principal, propertyId)) {
            propertyDao.removeProperty(propertyId);;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot delete a property you do not own.");
        }
    }

    @Override
    public List<Property> getAllPropertiesByLandlordId(int landlordId) {
        return propertyDao.getAllPropertiesByLandlordId(landlordId);
    }

    @Override
    public List<User> getAllRenters() {
        return userDao.getAllRenters();
    }

    @Override
    public void assignRenterToProperty(Principal principal, int propertyId, int renterId) {
        User renter = userDao.getUserById(renterId);
            if (renter.getAuthorities().toString().contains("ROLE_RENTER")) {
                if (currentUserOwnsPropertyOrIsAdmin(principal, propertyId)) {
                    propertyDao.assignRenterToProperty(propertyId, renterId);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot assign a renter to a property you do not own.");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You may only assign a user to a property if they are registered as a renter.");
            }
    }

    @Override
    public void removeRenterFromProperty(Principal principal, int propertyId, int renterId) {
        if (currentUserOwnsPropertyOrIsAdmin(principal, propertyId)) {
            propertyDao.removeRenterFromProperty(propertyId, renterId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot remove a renter from a property you do not own.");
        }
    }

    @Override
    public void addAmenityToProperty(Principal principal, int amenityId, int propertyId) {
        if (currentUserOwnsPropertyOrIsAdmin(principal, propertyId)) {
            propertyDao.addAmenityToProperty(amenityId, propertyId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot edit a property you do not own.");
        }
    }

    @Override
    public void removeAmenityFromProperty (Principal principal, int amenityId, int propertyId) {
        if (currentUserOwnsPropertyOrIsAdmin(principal, propertyId)) {
            propertyDao.removeAmenityFromProperty(amenityId, propertyId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot edit a property you do not own.");
        }
    }

    @Override
    public boolean currentUserOwnsPropertyOrIsAdmin(Principal principal, int propertyId) {
        int landlordId = userDao.findIdByUsername(principal.getName());
        List<Integer> propertyIdList = propertyDao.getAllPropertyIdsByLandlordId(landlordId);
        return (propertyIdList.contains(propertyId) || userDao.userIsAdmin(principal));
    }
}
