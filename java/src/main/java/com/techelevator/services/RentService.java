package com.techelevator.services;

import com.techelevator.model.Property;
import com.techelevator.model.Rent;

import java.security.Principal;
import java.util.List;

public interface RentService {
    public Rent getMyRent(Principal principal);
    public Rent getMyRent(int renterId);
    public List<Rent> getAllRents(Principal principal);
    public List<Rent> getAllRents(int landlordId);
    public void payRent(Principal principal, double amount);
    public void chargeRent(Principal principal, Property property);
    public void chargeRent(int landlordId, Property property);
}
