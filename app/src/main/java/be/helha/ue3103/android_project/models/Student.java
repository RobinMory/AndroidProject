package be.helha.ue3103.android_project.models;

import java.util.List;

import be.helha.ue3103.android_project.models.Project;

public class Student {
    private String name;
    private List<Project> projectList;

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public String getName() {
        return name;
    }

    public List<Project> getProjectList() {
        return projectList;
    }
}
