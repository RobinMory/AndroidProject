package be.helha.ue3103.android_project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.helha.ue3103.android_project.db.ProjectDbSchema.ProjectTable;

public class ProjectBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "projectBase.db";
    public ProjectBaseHelper(Context context) {super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ ProjectTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + ProjectTable.cols.UUID + ", " + ProjectTable.cols.STUDENT_ID  + " , " +
                ProjectTable.cols.NAME + " , " + ProjectTable.cols.AVERAGE + ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }

}
