package be.helha.ue3103.android_project.controlers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.MPMLab;
import be.helha.ue3103.android_project.models.Project;
import be.helha.ue3103.android_project.models.Step;

public class StepListActivity extends AppCompatActivity implements StepFragment.MyFragmentInterfacer {

    public static final String PROJECT_EXTRA = "PROJECT_EXTRA";

    private FloatingActionButton mAddStepButton;
    private EditText mProjectName;
    private EditText mProjectDesc;
    private TextView mProjectAverage;

    protected UUID mProjectId;

    private MPMLab lab;
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
        lab =  MPMLab.get(this.getApplicationContext());
        mProject = lab.getProject(mProjectId);
        mProjectName.setText(mProject.getName());
        mProjectDesc.setText(mProject.getDescription());
        for (final Step step : lab.getSteps(mProjectId)) {
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
            lab =  MPMLab.get(this.getApplicationContext());
            lab.addStep(step);
            bundle.putSerializable(StepFragment.PROJECT_ID,step);
            fragment.setArguments(bundle);
            fm.beginTransaction().add(R.id.step_list_container, fragment).commit();
        });
        mProjectName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //NOTHING
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //UPDATE
                mProject.setName(mProjectName.getText().toString());
                lab.updateProject(mProject);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //NOTHING
            }
        });
        mProjectDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //NOTHING
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //UPDATE
                mProject.setDescription(mProjectDesc.getText().toString());
                lab.updateProject(mProject);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //NOTHING
            }
        });
    }

    @Override
    public void OnItemSelected(int grade) {
        //Il faut recalculer le total des points
    }
}