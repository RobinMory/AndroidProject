package be.helha.ue3103.android_project.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import be.helha.ue3103.android_project.models.Project;
import be.helha.ue3103.android_project.models.Step;

public class StepCursorWrapper extends CursorWrapper {

    public StepCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Step getStep()
    {
        String uuidString = getString(getColumnIndex(MPMDbSchema.StepTable.cols.UUID));

        String uuidProject_id = getString(getColumnIndex(MPMDbSchema.StepTable.cols.PROJECT_ID));

        String name = getString(getColumnIndex(MPMDbSchema.StepTable.cols.NAME));

        int grade = getInt(getColumnIndex(MPMDbSchema.StepTable.cols.GRADE));

        Step step = new Step(UUID.fromString(uuidString));
        step.setProject_ID(UUID.fromString(uuidProject_id));
        step.setName(name);
        step.setGrade(grade);
        return step;
    }
}
