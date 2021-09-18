package com.example.zakat.models.types;

public class RentHouse extends Common {

    public RentHouse() {
    }

    public RentHouse(String uid, String houseHolder, String ic, String postCode, String number, String amount, String address, String city, String state) {
        super(uid, houseHolder, ic, postCode, number, amount, address, city, state);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}