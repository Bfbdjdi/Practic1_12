package org.oaosalty;

import java.io.Serializable;

public class Car implements Serializable {
    private String Name;
    private String licenceNumber;
    private String color;
    private int productionYear;
    private int ownersNumber;

    public void setName(String name) {
        Name = name;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public void setOwnersNumber(int ownersNumber) {
        this.ownersNumber = ownersNumber;
    }
}
