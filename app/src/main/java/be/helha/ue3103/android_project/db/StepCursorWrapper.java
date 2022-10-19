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
        String uuidString = getString(getColumnIndex(StepDbSchema.StepTable.cols.UUID));

        String name = getString(getColumnIndex(StepDbSchema.StepTable.cols.NAME));

        int grade = getInt(getColumnIndex(StepDbSchema.StepTable.cols.GRADE));

        Step step = new Step(UUID.fromString(uuidString));
        step.setName(name);
        step.setPoints(grade);
        return step;
    }
}
