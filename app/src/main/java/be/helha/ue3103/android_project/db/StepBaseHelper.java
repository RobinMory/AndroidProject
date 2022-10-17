package be.helha.ue3103.android_project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.helha.ue3103.android_project.db.StepDbSchema.StepTable;

public class StepBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "stepBase.db";
    public StepBaseHelper(Context context) {super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ StepTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + StepTable.cols.UUID + ", " + StepTable.cols.PROJECT_ID  + " , "
                + StepTable.cols.NAME + " , " + StepTable.cols.GRADE + ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }

}
