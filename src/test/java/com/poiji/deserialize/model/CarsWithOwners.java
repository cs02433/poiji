package com.poiji.deserialize.model;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellDeserialize;
import com.poiji.deserialize.model.byid.Car;

import java.util.List;

public class CarsWithOwners extends Car {


    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    @ExcelCell(4)
    @ExcelCellDeserialize(CommaSeparatedOwnersName.class)
    private List<String> owners;

    public void setElectrical(String type) {
        isAdult = type;
    }

    @ExcelCell(5)
    @ExcelCellDeserialize(CarType.class)
    private String isAdult;

    public String  getIsAdult() {
        return isAdult;
    }
}
