package be.helha.ue3103.android_project.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.helha.ue3103.android_project.db.ProjectBaseHelper;
import be.helha.ue3103.android_project.db.StepBaseHelper;
import be.helha.ue3103.android_project.db.StudentBaseHelper;
import be.helha.ue3103.android_project.db.StudentCursorWrapper;
import be.helha.ue3103.android_project.db.StudentDbSchema;

public class StudentLab {
    private static StudentLab sStudentLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static StudentLab get(Context context) {
        if (sStudentLab == null) {
            sStudentLab = new StudentLab(context);
        }
        return sStudentLab;
    }

    private StudentLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new StudentBaseHelper(mContext).getWritableDatabase();
        mDatabase = new ProjectBaseHelper(mContext).getWritableDatabase();
        mDatabase = new StepBaseHelper(mContext).getWritableDatabase();
    }

    public void addStudent(Student student) {
            mDatabase.insert(StudentDbSchema.StudentTable.NAME, null,
                    getContentValues(student));
    }

    public void updateStudent(Student student){}

    public Student getStudent(UUID id) {
        StudentCursorWrapper cursor =
                queryCrimes(StudentDbSchema.StudentTable.cols.UUID + " = ? ",
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
        StudentCursorWrapper cursor = queryCrimes(null, null);
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
        values.put(StudentDbSchema.StudentTable.cols.UUID, student.getId().toString());
        values.put(StudentDbSchema.StudentTable.cols.NAME, student.getName());
        return values;
    }

    private StudentCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        return new StudentCursorWrapper(mDatabase.query(
                StudentDbSchema.StudentTable.NAME,
                null, //all columns
                whereClause,
                whereArgs,
                null, null, null));
    }

}
