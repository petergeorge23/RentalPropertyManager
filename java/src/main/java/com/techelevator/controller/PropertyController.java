package com.techelevator.controller;

import com.techelevator.model.Image;
import com.techelevator.model.Property;
import com.techelevator.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/property")
@PreAuthorize("isAuthenticated()")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PreAuthorize("permitAll")
    @RequestMapping(method = RequestMethod.GET)
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Property getPropertyByPropertyId(@PathVariable int id) {
        return propertyService.getPropertyByPropertyId(id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping (path = "/landlord/{id}", method = RequestMethod.GET)
    public List<Property> getAllPropertiesByLandlordId(@PathVariable int id) {
        return propertyService.getAllPropertiesByLandlordId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_LANDLORD')")
    @RequestMapping (path = "/landlord/add", method = RequestMethod.POST)
    public void addProperty (Principal principal, @RequestBody Property property) {
        propertyService.addProperty(principal, property);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping (path = "/landlord/add/{id}", method = RequestMethod.POST)
    public void addProperty (@PathVariable int landlordId, @RequestBody Property property) {
        propertyService.addProperty(landlordId, property);
    }

    @PreAuthorize("hasAnyRole('ROLE_LANDLORD', 'ROLE_ADMIN')")
    @RequestMapping(path = "/landlord/update", method = RequestMethod.PUT)
    public void updateProperty (@RequestBody Property updatedProperty, Principal principal) {
        propertyService.updateProperty(principal, updatedProperty);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_LANDLORD','ROLE_ADMIN')")
    @RequestMapping (path = "/landlord/renter/assign", method = RequestMethod.POST)
    public void assignRenterToProperty(Principal principal, @RequestBody Map<String, Integer> ids ) {
        propertyService.assignRenterToProperty(principal, ids.get("propertyId"), ids.get("renterId"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_LANDLORD','ROLE_ADMIN')")
    @RequestMapping (path = "/landlord/renter/remove", method = RequestMethod.DELETE)
    public void removeRenterFromProperty(Principal principal, @RequestBody Map<String, Integer> ids) {
        propertyService.removeRenterFromProperty(principal, ids.get("propertyId"), ids.get("renterId"));
    }

    @PreAuthorize("hasAnyRole('ROLE_LANDLORD','ROLE_ADMIN')")
    @RequestMapping (path = "/landlord/amenity/add", method = RequestMethod.POST)
    public void addAmenityToProperty(Principal principal, @RequestBody Map<String, Integer> ids) {
        propertyService.addAmenityToProperty(principal, ids.get("amenityId"), ids.get("propertyId"));
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_LANDLORD','ROLE_ADMIN')")
    @RequestMapping (path = "/landlord/amenity/remove", method = RequestMethod.DELETE)
    public void removeAmenityFromProperty(Principal principal, @RequestBody Map<String, Integer> ids) {
        propertyService.removeAmenityFromProperty(principal, ids.get("amenityId"), ids.get("propertyId"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ROLE_LANDLORD', 'ROLE_ADMIN')")
    @RequestMapping (path = "landlord/remove/{id}", method = RequestMethod.DELETE)
    public void removeProperty (Principal principal, @PathVariable int id) {
        propertyService.removeProperty(principal, id);
    }
}