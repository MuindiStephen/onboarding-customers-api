package com.stevemd.onboarding.model.farmfield;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "farmfields")
public class FarmField {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @Column(name = "farmName")
    private String farmName;

    @Column(name = "cropName")
    private String cropName;

    @Column(name = "farmLocationDescription")
    private String farmLocationDescription;

    @Column(name = "farmLocationCoordinates")
    private String farmLocationCoordinates;

    @Column(name = "farmSizeInHectares")
    private String farmSizeInHa;

    @Column(name = "yearOfFarming")
    private String farmYearOfFarming;

    @Column(name = "farmingSeason")
    private String farmingSeason;

    @Column(name = "farmOwnershipType")
    private String farmOwnershipType;

    @Column(name = "countyLocationOfFarm")
    private String farmCountyLocation;

    @Column(name = "labourNumberOfFarmerPerDay")
    private String labourNumberOfFarmerPerDay;

    public FarmField(
            Long id, String farmName, String cropName, String farmLocationDescription, String farmLocationCoordinates,
            String farmSizeInHa, String farmYearOfFarming, String farmingSeason, String farmOwnershipType,
            String farmCountyLocation, String labourNumberOfFarmerPerDay
    ) {
        this.id = id;
        this.farmName = farmName;
        this.cropName = cropName;
        this.farmLocationDescription = farmLocationDescription;
        this.farmLocationCoordinates = farmLocationCoordinates;
        this.farmSizeInHa = farmSizeInHa;
        this.farmYearOfFarming = farmYearOfFarming;
        this.farmingSeason = farmingSeason;
        this.farmOwnershipType = farmOwnershipType;
        this.farmCountyLocation = farmCountyLocation;
        this.labourNumberOfFarmerPerDay = labourNumberOfFarmerPerDay;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getCropName() {
        return cropName;
    }

    public String getFarmLocationDescription() {
        return farmLocationDescription;
    }

    public String getFarmLocationCoordinates() {
        return farmLocationCoordinates;
    }

    public String getFarmSizeInHa() {
        return farmSizeInHa;
    }

    public String getFarmYearOfFarming() {
        return farmYearOfFarming;
    }

    public String getFarmingSeason() {
        return farmingSeason;
    }

    public String getFarmOwnershipType() {
        return farmOwnershipType;
    }

    public String getFarmCountyLocation() {
        return farmCountyLocation;
    }

    public String getLabourNumberOfFarmerPerDay() {
        return labourNumberOfFarmerPerDay;
    }


    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setFarmLocationDescription(String farmLocationDescription) {
        this.farmLocationDescription = farmLocationDescription;
    }

    public void setFarmLocationCoordinates(String farmLocationCoordinates) {
        this.farmLocationCoordinates = farmLocationCoordinates;
    }

    public void setFarmSizeInHa(String farmSizeInHa) {
        this.farmSizeInHa = farmSizeInHa;
    }

    public void setFarmYearOfFarming(String farmYearOfFarming) {
        this.farmYearOfFarming = farmYearOfFarming;
    }

    public void setFarmingSeason(String farmingSeason) {
        this.farmingSeason = farmingSeason;
    }

    public void setFarmOwnershipType(String farmOwnershipType) {
        this.farmOwnershipType = farmOwnershipType;
    }

    public void setFarmCountyLocation(String farmCountyLocation) {
        this.farmCountyLocation = farmCountyLocation;
    }

    public void setLabourNumberOfFarmerPerDay(String labourNumberOfFarmerPerDay) {
        this.labourNumberOfFarmerPerDay = labourNumberOfFarmerPerDay;
    }
}

