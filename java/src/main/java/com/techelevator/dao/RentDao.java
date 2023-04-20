package com.techelevator.dao;

import com.techelevator.model.Rent;
import java.util.List;

public interface RentDao {
    public Rent getRentByRenterId(int renterId);
    public List<Rent> getAllRentsByLandlordId(int landlordId);
    public void payRent(double amount, int rentId);
    public void chargeRent(double amount, int rentId);
    public Rent getRentByPropertyId(int propertyId);
}
