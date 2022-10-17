package be.helha.ue3103.android_project.db;

import android.database.Cursor;
import android.database.CursorWrapper;

public class StudentCursorWrapper extends CursorWrapper {

    public StudentCursorWrapper(Cursor cursor) {
        super(cursor);
    }
}
