package be.helha.ue3103.android_project.models;

import java.util.List;
import java.util.UUID;

public class Project {

    protected java.util.UUID mId;

    public UUID getmId() {
        return mId;
    }

    private Student mStudent;
    private String mName;
    private String mDescription;
    private int mTotal;
    private List<Step> mStepList;

    public void setStudent(Student student) {
        this.mStudent = student;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setStepList(List<Step> stepList) {
        this.mStepList = stepList;
    }

    public Student getStudent() {
        return mStudent;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public List<Step> getStepList() {
        return mStepList;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        this.mTotal = total;
    }
}
