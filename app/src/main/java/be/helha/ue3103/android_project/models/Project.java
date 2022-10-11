package be.helha.ue3103.android_project.models;

import java.util.List;

public class Project {
    private Student student;
    private String name;
    private String description;
    private float total;
    private List<Step> stepList;

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    public Student getStudent() {
        return student;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
