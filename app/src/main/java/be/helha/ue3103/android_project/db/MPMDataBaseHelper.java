package be.helha.ue3103.android_project.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.helha.ue3103.android_project.db.MPMDbSchema.ProjectTable;
import be.helha.ue3103.android_project.db.MPMDbSchema.StepTable;
import be.helha.ue3103.android_project.db.MPMDbSchema.StudentTable;


public class MPMDataBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "MPMDataBase.db";
    public MPMDataBaseHelper(Context context) {super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ StudentTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + StudentTable.cols.UUID + ", " + StudentTable.cols.NAME + ")"
        );

        db.execSQL("CREATE TABLE "+ ProjectTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + ProjectTable.cols.UUID + ", " + ProjectTable.cols.STUDENT_ID  + " , " +
                ProjectTable.cols.NAME + " , " + ProjectTable.cols.AVERAGE + " , " + ProjectTable.cols.DESCRIPTION +")"
        );

        db.execSQL("CREATE TABLE "+ StepTable.NAME + "("
                + "_id integer PRIMARY KEY AUTOINCREMENT, "
                + StepTable.cols.UUID + ", " + StepTable.cols.PROJECT_ID  + " , "
                + StepTable.cols.NAME + " , " + StepTable.cols.GRADE + ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        System.out.println("MPMDb Upgrade");
    }
}
