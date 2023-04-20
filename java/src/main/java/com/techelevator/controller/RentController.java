package com.techelevator.controller;

import com.techelevator.model.Property;
import com.techelevator.model.Rent;
import com.techelevator.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/rent")
@PreAuthorize("isAuthenticated()")
public class RentController {
    @Autowired
    private RentService rentService;

    @PreAuthorize("hasRole('ROLE_RENTER')")
    @RequestMapping(path = "/renter", method = RequestMethod.GET)
    public Rent getMyRent(Principal principal) {
        return rentService.getMyRent(principal);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RENTER')")
    @RequestMapping(path = "/renter/{id}", method = RequestMethod.GET)
    public Rent getMyRent(@PathVariable int renterId) {
        return rentService.getMyRent(renterId);
    }

    @PreAuthorize("hasRole('ROLE_RENTER')")
    @RequestMapping(path = "/renter/pay", method = RequestMethod.POST)
    public void payRent(Principal principal, @RequestBody Map<String, Double> map) {
        rentService.payRent(principal, map.get("amount"));
    }

    @PreAuthorize("hasRole('ROLE_LANDLORD')")
    @RequestMapping(path = "/landlord", method = RequestMethod.GET)
    public List<Rent> getAllRents(Principal principal) {
        return rentService.getAllRents(principal);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/landlord/{landlordId}", method = RequestMethod.GET)
    public List<Rent> getAllRentsAdmin(@PathVariable int landlordId) {
        return rentService.getAllRents(landlordId);
    }

    @PreAuthorize("hasRole('ROLE_LANDLORD')")
    @RequestMapping(path = "landlord/charge", method = RequestMethod.POST)
    public void chargeRent(Principal principal, @RequestBody Property property) {
        rentService.chargeRent(principal, property);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "landlord/charge/{landlordId}", method = RequestMethod.POST)
    public void chargeRentAdmin(@PathVariable int landlordId, @RequestBody Property property) {
        rentService.chargeRent(landlordId, property);
    }
}
