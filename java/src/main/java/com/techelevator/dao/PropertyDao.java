package com.techelevator.dao;

import com.techelevator.model.Amenity;
import com.techelevator.model.Image;
import com.techelevator.model.Property;

import java.util.List;

public interface PropertyDao {

    public Property getPropertyByPropertyId(int propertyId);

    public List<String> getAllAmenitiesByPropertyId(int propertyId);

    public void updateProperty(Property updatedProperty);

    public void addProperty(int landlordId, Property property);

    public List<Property> getAllPropertiesByLandlordId(int landlordId);

    public List<Integer> getAllPropertyIdsByLandlordId(int landlordId);

    public void assignRenterToProperty(int propertyId, int renterId);

    public void removeRenterFromProperty(int propertyId, int renterId);

    public void addAmenityToProperty(int amenityId, int propertyId);

    public void removeAmenityFromProperty (int amenityId, int propertyId);

    public List<Property> getAllProperties();

    public void removeProperty(int propertyId);

    public int getPropertyIdByRenterId(int renterId);

    public List<String> getAllPicturesByPropertyId(int propertyId);
}
