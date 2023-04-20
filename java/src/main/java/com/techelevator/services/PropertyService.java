package com.techelevator.services;

import com.techelevator.model.Image;
import com.techelevator.model.Property;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface PropertyService {

    public List<Property> getAllProperties();
    public Property getPropertyByPropertyId(int propertyId);
    public void updateProperty (Principal principal, Property updatedProperty);
    public void addProperty(Principal principal, Property property);
    public void addProperty(int landlordId, Property property);
    public void removeProperty(Principal principal, int propertyId);
    public List<Property> getAllPropertiesByLandlordId(int landlordId);
    public List<User> getAllRenters();
    public void assignRenterToProperty(Principal principal, int propertyId, int renterId);
    public void removeRenterFromProperty(Principal principal, int propertyId, int renterId);
    public void addAmenityToProperty(Principal principal, int amenityId, int propertyId);

    public void removeAmenityFromProperty (Principal principal, int amenityId, int propertyId);

    public boolean currentUserOwnsPropertyOrIsAdmin(Principal principal, int propertyId);
}
