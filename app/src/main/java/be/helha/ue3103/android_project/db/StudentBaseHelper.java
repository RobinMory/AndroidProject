package be.helha.ue3103.android_project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.helha.ue3103.android_project.db.StudentDbSchema.StudentTable;

public class StudentBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "studentBase.db";
    public StudentBaseHelper(Context context) {super(context, DATABASE_NAME, null, VERSION);
    }

    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ StudentTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + StudentTable.cols.UUID + ", " + StudentTable.cols.NAME + ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        System.out.println("StudentDB Upgrade");
    }

}
