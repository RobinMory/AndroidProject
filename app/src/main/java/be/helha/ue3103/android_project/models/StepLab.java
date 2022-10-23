package be.helha.ue3103.android_project.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.helha.ue3103.android_project.db.MPMDataBaseHelper;
import be.helha.ue3103.android_project.db.MPMDbSchema;
import be.helha.ue3103.android_project.db.ProjectCursorWrapper;
import be.helha.ue3103.android_project.db.StepCursorWrapper;
import be.helha.ue3103.android_project.db.StudentCursorWrapper;

public class StepLab {

    //Peut-être inutile

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
        mDatabase = new MPMDataBaseHelper(mContext).getWritableDatabase();
    }

    public void addStep(Step step) {
        mDatabase.insert(MPMDbSchema.StepTable.NAME, null,
                getContentValues(step));
    }

    public void updateStep(Step step) {
        String uuidString = step.getId().toString();
        ContentValues values = getContentValues(step);
        mDatabase.update(MPMDbSchema.StepTable.NAME,
                values,
                MPMDbSchema.StepTable.cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public Step getStep(UUID id) {
        StepCursorWrapper cursor =
                querySteps(MPMDbSchema.ProjectTable.cols.UUID + " = ? ",
                        new String[]{id.toString()}
                );
        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getStep();
        } finally {
            cursor.close();
        }
    }

    public List<Step> getSteps(UUID id) {
        ArrayList<Step> steps = new ArrayList<>();
        ArrayList<Step> stepsWhitGoodID = new ArrayList<>();
        StepCursorWrapper cursor = querySteps(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                steps.add(cursor.getStep());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        for (Step step : steps) {
            if(step.getProjectId().toString().equals(id.toString()))
            {
                stepsWhitGoodID.add(step);
            }
        }
        return stepsWhitGoodID;
    }

    private ContentValues getContentValues(Step step) {
        ContentValues values = new ContentValues();
        values.put(MPMDbSchema.StepTable.cols.UUID, step.getId().toString());
        values.put(MPMDbSchema.StepTable.cols.NAME, step.getName());
        values.put(MPMDbSchema.StepTable.cols.PROJECT_ID, step.getProjectId().toString());
        values.put(MPMDbSchema.StepTable.cols.GRADE, step.getGrade());
        return values;
    }

    private StepCursorWrapper querySteps(String whereClause, String[] whereArgs) {
        return new StepCursorWrapper(mDatabase.query(
                MPMDbSchema.StepTable.NAME,
                null, //all columns
                whereClause,
                whereArgs,
                null, null, null));
    }
}
