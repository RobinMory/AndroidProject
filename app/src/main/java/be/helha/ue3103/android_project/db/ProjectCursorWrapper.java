package be.helha.ue3103.android_project.db;

import android.database.Cursor;
import android.database.CursorWrapper;

public class ProjectCursorWrapper extends CursorWrapper {

    public ProjectCursorWrapper(Cursor cursor) {
        super(cursor);
    }
}