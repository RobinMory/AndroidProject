package be.helha.ue3103.android_project.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.UUID;

import be.helha.ue3103.android_project.db.StepBaseHelper;
import be.helha.ue3103.android_project.db.StudentBaseHelper;
import be.helha.ue3103.android_project.db.StudentCursorWrapper;

public class StepLab {

    private static StepLab sStepLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static StepLab get(Context context) {
        if (sStepLab == null) {
            sStepLab = new StepLab(context);
        }
        return sStepLab;
    }

    private StepLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new StepBaseHelper(mContext).getWritableDatabase();
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
