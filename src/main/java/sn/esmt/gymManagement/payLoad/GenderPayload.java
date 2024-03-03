package sn.esmt.gymManagement.payLoad;

import sn.esmt.gymManagement.models.beans.enums.GenderType;

public class GenderPayload {
    private GenderType genderType;
    private String name;

    public GenderPayload() {
    }

    public GenderPayload(GenderType genderType, String name) {
        this.genderType = genderType;
        this.name = name;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
