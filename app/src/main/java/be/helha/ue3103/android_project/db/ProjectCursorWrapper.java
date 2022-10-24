package be.helha.ue3103.android_project.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import be.helha.ue3103.android_project.models.Project;
import be.helha.ue3103.android_project.models.Student;

public class ProjectCursorWrapper extends CursorWrapper {

    public ProjectCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Project getProject()
    {
        String uuidString = getString(getColumnIndex(MPMDbSchema.ProjectTable.cols.UUID));

        String name = getString(getColumnIndex(MPMDbSchema.ProjectTable.cols.NAME));

        String description = getString(getColumnIndex(MPMDbSchema.ProjectTable.cols.DESCRIPTION));

        double average = getDouble(getColumnIndex(MPMDbSchema.ProjectTable.cols.AVERAGE));

        String uuid_Student = getString(getColumnIndex(MPMDbSchema.ProjectTable.cols.STUDENT_ID));

        Project project = new Project(UUID.fromString(uuidString));
        project.setName(name);
        project.setDescription(description);
        project.setAverage(average);
        project.setStudentId(UUID.fromString(uuid_Student));
        return project;
    }
}