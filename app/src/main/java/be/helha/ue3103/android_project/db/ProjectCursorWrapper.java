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
        String uuidString = getString(getColumnIndex(ProjectDbSchema.ProjectTable.cols.UUID));

        String name = getString(getColumnIndex(ProjectDbSchema.ProjectTable.cols.NAME));

        String description = getString(getColumnIndex(ProjectDbSchema.ProjectTable.cols.DESCRIPTION));

        int average = getInt(getColumnIndex(ProjectDbSchema.ProjectTable.cols.AVERAGE));

        Project project = new Project(UUID.fromString(uuidString));
        project.setName(name);
        project.setDescription(description);
        project.setAverage(average);
        return project;
    }
}