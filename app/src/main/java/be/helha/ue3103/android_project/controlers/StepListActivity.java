package be.helha.ue3103.android_project.controlers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.StudentLab;

public class StepListActivity extends AppCompatActivity {

    private FloatingActionButton mAddStepButton;
    private EditText mProjectName;
    private EditText mProjectDesc;
    private TextView mProjectAverage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steplist);

        mAddStepButton = findViewById(R.id.floatingActionButtonStepList);
        mProjectName = findViewById(R.id.project_title);
        mProjectDesc = findViewById(R.id.project_description);
        mProjectAverage = findViewById(R.id.project_average);
        setButtonListener();
        StudentLab s = StudentLab.get(this.getApplicationContext());
    }

    private void setButtonListener() {
        mAddStepButton.setOnClickListener(event -> {
            //Ouvrir un fragment en envoyant une étape en paramètre
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.step_list_container);
            //if (fragment == null) {
                fragment = new StepFragment();
                fm.beginTransaction()
                        .add(R.id.step_list_container, fragment)
                        .commit();
            //}
        });
    }
}