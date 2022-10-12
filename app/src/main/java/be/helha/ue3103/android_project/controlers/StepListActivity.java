package be.helha.ue3103.android_project.controlers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.helha.ue3103.android_project.R;

public class StepListActivity extends AppCompatActivity {

    private Button mSaveButton;
    private FloatingActionButton mAddStepButton;
    private EditText mProjectName;
    private EditText mProjectDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steplist);



        mSaveButton = findViewById(R.id.save_project);
        mAddStepButton = findViewById(R.id.floatingActionButton1);
        mProjectName = findViewById(R.id.project_title);
        mProjectDesc = findViewById(R.id.project_description);
        setButtonListener();
    }

    private void setButtonListener() {
        mAddStepButton.setOnClickListener(event -> {
            //Ouvrir un fragment en envoyant une étape en paramètre
            Toast.makeText(this, "Fragment Open from StepList", Toast.LENGTH_SHORT).show();
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