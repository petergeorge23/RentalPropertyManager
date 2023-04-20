package com.techelevator.dao;

import com.techelevator.model.Amenity;
import com.techelevator.model.Image;
import com.techelevator.model.Property;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcPropertyDao implements PropertyDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcPropertyDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Property> getAllProperties() {
        List<Property> propertyList = new ArrayList<>();
        Property property;
        List<String> amenityList;
        List<String> imageList;

        String sql = "SELECT property_id, rent, property_type, bathrooms, bedrooms, square_footage, date_available, street_number, street_name, apartment_unit, city, state_abbreviation, zip, pets_allowed, property_description " +
                "FROM property;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            property = mapRowToProperty(results);
            amenityList = getAllAmenitiesByPropertyId(property.getPropertyId());
            property.setAmenities(amenityList);
            imageList = getAllPicturesByPropertyId(property.getPropertyId());
            property.setImages(imageList);
            propertyList.add(property);
        }

        return propertyList;
    }

    @Override
    public Property getPropertyByPropertyId(int propertyId) {
        Property property = new Property();
        List<String> amenityList;

        String sql = "SELECT property_id, rent, property_type, bathrooms, bedrooms, square_footage, date_available, street_number, street_name, apartment_unit, city, state_abbreviation, zip, pets_allowed, property_description " +
                "FROM property " +
                "WHERE property_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);
        if (results.next()) {
            property = mapRowToProperty(results);
            amenityList = getAllAmenitiesByPropertyId(property.getPropertyId());
            property.setAmenities(amenityList);
        }
        return property;
    }

    @Override
    public List<String> getAllAmenitiesByPropertyId(int propertyId) {
        List<String> amenitiesList = new ArrayList<>();

        String sql = "SELECT amenity.amenity_id, amenity_description " +
                "FROM amenity " +
                "JOIN amenity_property " +
                "ON amenity_property.amenity_id = amenity.amenity_id " +
                "JOIN property " +
                "ON property.property_id = amenity_property.property_id " +
                "WHERE property.property_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);
        while (results.next()) {
            amenitiesList.add(mapRowToAmenity(results).getAmenityDescription());
        }

        return amenitiesList;
    }

    @Override
    public List<String> getAllPicturesByPropertyId(int propertyId) {
        List<String> imageList = new ArrayList<>();

        String sql = "SELECT image_id, image_url " +
                "FROM image " +
                "WHERE property_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);

        while (results.next()) {
            imageList.add(mapRowToImage(results).getImageUrl());
        }

        return imageList;
    }

    @Override
    public List<Integer> getAllPropertyIdsByLandlordId(int landlordId) {
        List<Integer> propertyIdList = new ArrayList<>();

        String sql ="SELECT property_id " +
                "FROM property_landlord " +
                "WHERE landlord_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, landlordId);
        while(results.next()) {
            propertyIdList.add(results.getInt("property_id"));
        }

        return propertyIdList;
    }

    @Override
    public void updateProperty(Property updatedProperty) {
        String sql = "UPDATE property " +
                "SET rent = ?, property_type = ?, bathrooms = ?, bedrooms = ?, square_footage = ?, date_available = ?, street_number = ?, street_name = ?, apartment_unit = ?, city = ?, state_abbreviation = ?, zip = ?, pets_allowed = ?, property_description = ? " +
                "WHERE property_id = ?;";
        int results = jdbcTemplate.update(sql, updatedProperty.getRent(), updatedProperty.getPropertyType(), updatedProperty.getBathrooms(), updatedProperty.getBedrooms(), updatedProperty.getSquareFootage(), updatedProperty.getDateAvailable(), updatedProperty.getStreetNumber(), updatedProperty.getStreetName(), updatedProperty.getApartmentUnit(), updatedProperty.getCity(), updatedProperty.getStateAbbreviation(), updatedProperty.getZip(), updatedProperty.isPetsAllowed(), updatedProperty.getPropertyDescription(), updatedProperty.getPropertyId());
    }

    @Override
    public void addProperty(int landlordId, Property property) {
        String sql = "INSERT INTO property (rent, property_type, bathrooms, bedrooms, square_footage, date_available, street_number, street_name, apartment_unit, city, state_abbreviation, zip, pets_allowed, property_description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        int results = jdbcTemplate.update(sql, property.getRent(), property.getPropertyType(), property.getBathrooms(), property.getBedrooms(), property.getSquareFootage(), property.getDateAvailable(), property.getStreetNumber(), property.getStreetName(), property.getApartmentUnit(), property.getCity(), property.getStateAbbreviation(), property.getZip(), property.isPetsAllowed(), property.getPropertyDescription());

        sql = "SELECT property_id " +
                "FROM property " +
                "WHERE rent = ? AND property_type = ? AND bathrooms = ? AND bedrooms = ? AND square_footage = ? AND date_available = ? AND street_number = ? AND street_name = ? AND apartment_unit = ? AND city = ? AND state_abbreviation = ? AND zip = ? AND pets_allowed = ? AND property_description = ?;";


        int propertyId = Objects.requireNonNull(jdbcTemplate.queryForObject(sql, Integer.class, property.getRent(), property.getPropertyType(), property.getBathrooms(), property.getBedrooms(), property.getSquareFootage(), property.getDateAvailable(), property.getStreetNumber(), property.getStreetName(), property.getApartmentUnit(), property.getCity(), property.getStateAbbreviation(), property.getZip(), property.isPetsAllowed(), property.getPropertyDescription()));

        sql = "INSERT INTO property_landlord (landlord_id, property_id) " +
                    "VALUES (?, ?);";
        results = jdbcTemplate.update(sql, landlordId, propertyId);
    }

    @Override
    public void removeProperty(int propertyId) {
        String sql = "DELETE FROM amenity_property WHERE property_id = ?; " +
                "DELETE FROM property_landlord WHERE property_id = ?; " +
                "DELETE FROM property_renter WHERE property_id = ?; " +
                "DELETE FROM maintenance WHERE property_id = ?; " +
                "DELETE FROM image WHERE property_id = ?; " +
                "DELETE FROM rent WHERE property_id = ?; " +
                "DELETE FROM amenity_property WHERE property_id = ?; " +
                "DELETE FROM property WHERE property_id = ?; ";
        int results = jdbcTemplate.update(sql, propertyId, propertyId, propertyId, propertyId, propertyId, propertyId, propertyId, propertyId);
    }

    @Override
    public List<Property> getAllPropertiesByLandlordId(int landlordId) {
        List<Property> propertyList = new ArrayList<>();
        Property property;
        List<String> amenityList;

        String sql = "SELECT property.property_id, rent, property_type, bathrooms, bedrooms, square_footage, date_available, street_number, street_name, apartment_unit, city, state_abbreviation, zip, pets_allowed, property_description " +
            "FROM property " +
            "JOIN property_landlord " +
            "ON property_landlord.property_id = property.property_id " +
            "WHERE landlord_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, landlordId);

         while (results.next()) {
             property = mapRowToProperty(results);
             amenityList = getAllAmenitiesByPropertyId(property.getPropertyId());
             property.setAmenities(amenityList);
             propertyList.add(property);;
         }

        return propertyList;
    }

    @Override
    public void assignRenterToProperty(int propertyId, int renterId) {
        String sql ="INSERT INTO property_renter (property_id, renter_id) "+
                "VALUES(?,?);";
        int results = jdbcTemplate.update(sql, propertyId, renterId);
    }

    @Override
    public void removeRenterFromProperty(int propertyId, int renterId) {
        String sql ="DELETE FROM property_renter "+
                "WHERE property_id = ? AND renter_id = ?;";
        int results = jdbcTemplate.update(sql, propertyId, renterId);
    }

    @Override
    public void addAmenityToProperty(int amenityId, int propertyId) {
      String sql= "INSERT INTO amenity_property (amenity_id, property_id) "+
        "VALUES (? ,?);";
      int results = jdbcTemplate.update(sql, amenityId, propertyId);
    }

    @Override
    public void removeAmenityFromProperty (int amenityId, int propertyId) {
        String sql = "DELETE FROM amenity_property " +
                "WHERE amenity_id = ? AND property_id = ?;";
        int results = jdbcTemplate.update(sql, amenityId, propertyId);
    }

    @Override
    public int getPropertyIdByRenterId(int renterId) {
        String sql = "SELECT property_id FROM property_renter WHERE renter_id = ?;";

        return Objects.requireNonNull(jdbcTemplate.queryForObject(sql, Integer.class, renterId));
    }

    private Property mapRowToProperty(SqlRowSet rowSet) {
        Property property = new Property();

        property.setPropertyId(rowSet.getInt("property_id"));
        property.setRent(rowSet.getDouble("rent"));
        property.setPropertyType(rowSet.getString("property_type"));
        property.setBathrooms(rowSet.getInt("bathrooms"));
        property.setBedrooms(rowSet.getInt("bedrooms"));
        property.setSquareFootage(rowSet.getInt("square_footage"));
        property.setDateAvailable(rowSet.getDate("date_available"));
        property.setStreetNumber(rowSet.getString("street_number"));
        property.setStreetName(rowSet.getString("street_name"));
        property.setApartmentUnit(rowSet.getString("apartment_unit"));
        property.setCity(rowSet.getString("city"));
        property.setStateAbbreviation(rowSet.getString("state_abbreviation"));
        property.setZip(rowSet.getInt("zip"));
        property.setPetsAllowed(rowSet.getBoolean("pets_allowed"));
        property.setPropertyDescription(rowSet.getString("property_description"));

        return property;
    }

    private Amenity mapRowToAmenity(SqlRowSet rowSet) {
        Amenity amenity = new Amenity();

        amenity.setAmenityId(rowSet.getInt("amenity_id"));
        amenity.setAmenityDescription(rowSet.getString("amenity_description"));

        return amenity;
    }

    private Image mapRowToImage(SqlRowSet rowSet) {
        Image image = new Image();

        image.setImageId(rowSet.getInt("image_id"));
        image.setImageUrl(rowSet.getString("image_url"));

        return image;
    }
}
