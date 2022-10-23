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
import be.helha.ue3103.android_project.db.StepCursorWrapper;
import be.helha.ue3103.android_project.db.StudentCursorWrapper;

public class MPMLab {

    private static MPMLab sMPMLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MPMLab get(Context context) {
        if (sMPMLab == null) {
            sMPMLab = new MPMLab(context);
        }
        return sMPMLab;
    }

    private MPMLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new MPMDataBaseHelper(mContext).getWritableDatabase();
    }

    ////STUDENT

    public void addStudent(Student student) {
        mDatabase.insert(MPMDbSchema.StudentTable.NAME, null,
                getContentValues(student));
    }

    public void updateStudent(Student student){}

    public Student getStudent(UUID id) {
        StudentCursorWrapper cursor =
                queryStudents(MPMDbSchema.StudentTable.cols.UUID + " = ? ",
                        new String[]{id.toString()}
                );
        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getStudent();
        } finally {
            cursor.close();
        }
    }

    public List<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        StudentCursorWrapper cursor = queryStudents(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                students.add(cursor.getStudent());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return students;
    }

    private ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();
        values.put(MPMDbSchema.StudentTable.cols.UUID, student.getId().toString());
        values.put(MPMDbSchema.StudentTable.cols.NAME, student.getName());
        return values;
    }

    private StudentCursorWrapper queryStudents(String whereClause, String[] whereArgs) {
        return new StudentCursorWrapper(mDatabase.query(
                MPMDbSchema.StudentTable.NAME,
                null, //all columns
                whereClause,
                whereArgs,
                null, null, null));
    }

    /////PROJECT

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

    //STEP

    public void addStep(Step step) {
        mDatabase.insert(MPMDbSchema.StepTable.NAME, null,
                getContentValues(step));
    }

    public void updateStep(Step step) {
        String uuidString = step.getId().toString();
        ContentValues values = getContentValues(step);
        mDatabase.update(MPMDbSchema.StepTable.NAME,
                values,
                MPMDbSchema.StepTable.cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public Step getStep(UUID id) {
        StepCursorWrapper cursor =
                querySteps(MPMDbSchema.ProjectTable.cols.UUID + " = ? ",
                        new String[]{id.toString()}
                );
        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getStep();
        } finally {
            cursor.close();
        }
    }

    public List<Step> getSteps(UUID id) {
        ArrayList<Step> steps = new ArrayList<>();
        ArrayList<Step> stepsWhitGoodID = new ArrayList<>();
        StepCursorWrapper cursor = querySteps(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                steps.add(cursor.getStep());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        for (Step step : steps) {
            if(step.getProjectId().toString().equals(id.toString()))
            {
                stepsWhitGoodID.add(step);
            }
        }
        return stepsWhitGoodID;
    }

    private ContentValues getContentValues(Step step) {
        ContentValues values = new ContentValues();
        values.put(MPMDbSchema.StepTable.cols.UUID, step.getId().toString());
        values.put(MPMDbSchema.StepTable.cols.NAME, step.getName());
        values.put(MPMDbSchema.StepTable.cols.PROJECT_ID, step.getProjectId().toString());
        values.put(MPMDbSchema.StepTable.cols.GRADE, step.getGrade());
        return values;
    }

    private StepCursorWrapper querySteps(String whereClause, String[] whereArgs) {
        return new StepCursorWrapper(mDatabase.query(
                MPMDbSchema.StepTable.NAME,
                null, //all columns
                whereClause,
                whereArgs,
                null, null, null));
    }

}
