package be.helha.ue3103.android_project.models;

import java.util.List;
import java.util.UUID;

import be.helha.ue3103.android_project.models.Project;

public class Student {

    protected java.util.UUID mId;

    public UUID getmId() {
        return mId;
    }

    private String mName;
    private List<Project> mProjectList;

    public void setName(String name) {
        this.mName = name;
    }

    public void setProjectList(List<Project> projectList) {
        this.mProjectList = projectList;
    }

    public String getName() {
        return mName;
    }

    public List<Project> getProjectList() {
        return mProjectList;
    }
}
