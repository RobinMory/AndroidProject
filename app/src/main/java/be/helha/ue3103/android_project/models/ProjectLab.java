package be.helha.ue3103.android_project.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.UUID;

import be.helha.ue3103.android_project.db.ProjectBaseHelper;
import be.helha.ue3103.android_project.db.StepBaseHelper;
import be.helha.ue3103.android_project.db.StudentCursorWrapper;

public class ProjectLab {

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
        mDatabase = new ProjectBaseHelper(mContext).getWritableDatabase();
    }

    public void addStudent(Student student) {

    }

    public void updateStudent(Student student) {

    }

    public Student getStudent(UUID id) {
        return null;
    }

    public List<Student> getStudents() {
        return null;
    }

    private ContentValues getContentValues(Student student) {
        return null;
    }

    private StudentCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        return null;
    }
}
