package be.helha.ue3103.android_project.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.helha.ue3103.android_project.db.MPMDataBaseHelper;
import be.helha.ue3103.android_project.db.MPMDbSchema;
import be.helha.ue3103.android_project.db.ProjectCursorWrapper;
import be.helha.ue3103.android_project.db.StudentCursorWrapper;

public class ProjectLab {

    //Peut-être inutile

    private static ProjectLab sProjectLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ProjectLab get(Context context) {
        if (sProjectLab == null) {
            sProjectLab = new ProjectLab(context);
        }
        return sProjectLab;
    }

    private ProjectLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new MPMDataBaseHelper(mContext).getWritableDatabase();
    }

    public void addProject(Project project) {
        mDatabase.insert(MPMDbSchema.ProjectTable.NAME, null,
                getContentValues(project));
    }

    public void updateProject(Project project) {
        String uuidString = project.getId().toString();
        ContentValues values = getContentValues(project);
        mDatabase.update(MPMDbSchema.ProjectTable.NAME,
                values,
                MPMDbSchema.ProjectTable.cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public Project getProject(UUID id) {
        ProjectCursorWrapper cursor =
                queryProjects(MPMDbSchema.ProjectTable.cols.UUID + " = ? ",
                        new String[]{id.toString()}
                );
        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getProject();
        } finally {
            cursor.close();
        }
    }

    public Project getProjectByStudentId (UUID student_id) {
        ProjectCursorWrapper cursor =
                queryProjects(MPMDbSchema.ProjectTable.cols.STUDENT_ID + " = ? ",
                        new String[]{student_id.toString()}
                );
        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getProject();
        } finally {
            cursor.close();
        }
    }

    public List<Project> getProjects(UUID id) {
        ArrayList<Project> projects = new ArrayList<>();
        ArrayList<Project> projectsWhitGoodID = new ArrayList<>();
        ProjectCursorWrapper cursor = queryProjects(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                projects.add(cursor.getProject());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        for (Project project : projects) {
            if(project.getStudentId().toString().equals(id.toString()))
            {
                projectsWhitGoodID.add(project);
            }
        }
        return projectsWhitGoodID;
    }

    private ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();
        values.put(MPMDbSchema.ProjectTable.cols.UUID, project.getId().toString());
        values.put(MPMDbSchema.ProjectTable.cols.NAME, project.getName());
        values.put(MPMDbSchema.ProjectTable.cols.DESCRIPTION, project.getDescription());
        values.put(MPMDbSchema.ProjectTable.cols.AVERAGE, project.getAverage());
        values.put(MPMDbSchema.ProjectTable.cols.STUDENT_ID, project.getStudentId().toString());
        return values;
    }

    private ProjectCursorWrapper queryProjects(String whereClause, String[] whereArgs) {
        return new ProjectCursorWrapper(mDatabase.query(
                MPMDbSchema.ProjectTable.NAME,
                null, //all columns
                whereClause,
                whereArgs,
                null, null, null));
    }
}
