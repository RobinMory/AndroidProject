package be.helha.ue3103.android_project.controlers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.Project;
import be.helha.ue3103.android_project.models.ProjectLab;
import be.helha.ue3103.android_project.models.Step;
import be.helha.ue3103.android_project.models.StepLab;
import be.helha.ue3103.android_project.models.StudentLab;

public class StepListActivity extends AppCompatActivity {

    public static final String PROJECT_EXTRA = "PROJECT_EXTRA";

    private FloatingActionButton mAddStepButton;
    private EditText mProjectName;
    private EditText mProjectDesc;
    private TextView mProjectAverage;

    protected UUID mProjectId;

    private ProjectLab projectLab;
    private StepLab stepLab;
    private Project mProject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steplist);

        mAddStepButton = findViewById(R.id.floatingActionButtonStepList);
        mProjectName = findViewById(R.id.project_title);
        mProjectDesc = findViewById(R.id.project_description);
        mProjectAverage = findViewById(R.id.project_average);

        mProjectId = (UUID) this.getIntent().getSerializableExtra(PROJECT_EXTRA);
        setButtonListener();
        updateUI();
    }

    private void updateUI() {
        projectLab = ProjectLab.get(this.getApplicationContext());
        mProject = projectLab.getProject(mProjectId);
        mProjectName.setText(mProject.getName());
        mProjectDesc.setText(mProject.getDescription());
        stepLab = StepLab.get(this.getApplicationContext());
        for (final Step step : stepLab.getSteps(mProjectId)) {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.step_list_container);
            fragment = new StepFragment();
            Bundle bundle = new Bundle();;
            bundle.putSerializable(StepFragment.PROJECT_ID,step);
            fragment.setArguments(bundle);
            fm.beginTransaction().add(R.id.step_list_container, fragment).commit();
        }
    }

    private void setButtonListener() {
        mAddStepButton.setOnClickListener(event-> {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.step_list_container);
            fragment = new StepFragment();
            Bundle bundle = new Bundle();
            Step step = new Step();
            step.setName("Nouvelle étape");
            step.setGrade(3);
            step.setProject_ID(mProjectId);
            stepLab = StepLab.get(this.getApplicationContext());
            stepLab.addStep(step);
            bundle.putSerializable(StepFragment.PROJECT_ID,step);
            fragment.setArguments(bundle);
            fm.beginTransaction().add(R.id.step_list_container, fragment).commit();
        });
    }
}