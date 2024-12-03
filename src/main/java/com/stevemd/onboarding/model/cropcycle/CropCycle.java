package com.stevemd.onboarding.model.cropcycle;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cropcycle")
@NoArgsConstructor
@AllArgsConstructor
public class CropCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String farmName;

    @Column(nullable = false, unique = true)
    private String cropName;

    @Column(nullable = false)
    private String startDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "farm_cycle_id") // Foreign key to link with LocalFarmCycle
    private List<CropCycleTasks> tasks;

    @Column(nullable = false)
    private String status = "Upcoming"; // Default value: Upcoming

    @Column(nullable = false)
    private String comments = "losses"; // Default value: Bad weather

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<CropCycleTasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<CropCycleTasks> tasks) {
        this.tasks = tasks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

