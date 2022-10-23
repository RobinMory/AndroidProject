package be.helha.ue3103.android_project.models;

import java.io.Serializable;
import java.util.UUID;

public class Step implements Serializable {

    protected java.util.UUID mId;
    protected java.util.UUID mProject_ID;
    private String mName;
    private int mGrade;

    public Step() {
        this(UUID.randomUUID());
    }

    public void setProject_ID(UUID project_ID) {
        this.mProject_ID = project_ID;
    }

    public Step(UUID id){
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public UUID getProjectId(){return mProject_ID;}

    public void setName(String name) {
        this.mName = name;
    }

    public void setGrade(int grade) {
        this.mGrade = grade;
    }

    public String getName() {
        return mName;
    }

    public int getGrade() {
        return mGrade;
    }
}
