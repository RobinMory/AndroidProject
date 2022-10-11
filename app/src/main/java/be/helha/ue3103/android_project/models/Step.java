package be.helha.ue3103.android_project.models;

public class Step {
    private String name;
    private float points;

    public Step() {
        this.name = "Etape test";
        this.points = 5;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public float getPoints() {
        return points;
    }
}
