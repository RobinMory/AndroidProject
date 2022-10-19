package be.helha.ue3103.android_project.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import be.helha.ue3103.android_project.models.Student;

public class StudentCursorWrapper extends CursorWrapper {

    public StudentCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Student getStudent()
    {
        String uuidString = getString(getColumnIndex(StudentDbSchema.StudentTable.cols.UUID));

        String name = getString(getColumnIndex(StudentDbSchema.StudentTable.cols.NAME));

        Student student = new Student(UUID.fromString(uuidString));
        student.setName(name);

        return student;
    }
}
