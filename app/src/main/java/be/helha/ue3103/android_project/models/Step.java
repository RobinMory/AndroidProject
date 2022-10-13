package be.helha.ue3103.android_project.models;

import java.util.UUID;

public class Step {

    protected java.util.UUID mId;
    private String mName;
    private int mPoints;

    public Step() {
        mId = UUID.randomUUID();
        this.mName = "Etape test";
        this.mPoints = 5;
    }

    public UUID getId() {
        return mId;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setPoints(int points) {
        this.mPoints = points;
    }

    public String getName() {
        return mName;
    }

    public int getPoints() {
        return mPoints;
    }
}
