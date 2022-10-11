package be.helha.ue3103.android_project.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.Step;

public class ProjectActivity extends AppCompatActivity {

    private Button mSaveButton;
    private FloatingActionButton mAddStepButton;
    private EditText mProjectName;
    private EditText mProjectDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        mSaveButton = findViewById(R.id.save_project);
        mAddStepButton = findViewById(R.id.floatingActionButton1);
        mProjectName = findViewById(R.id.project_title);
        mProjectDesc = findViewById(R.id.project_description);
        setButtonListener();
    }

    private void setButtonListener() {
        mAddStepButton.setOnClickListener(event -> {
            //Ouvrir un fragment en envoyant une étape en paramètre
        });
    }
}