package be.helha.ue3103.android_project.controlers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.Step;


public class StepFragment extends Fragment {

    protected Step mStep;
    private Spinner mPointsSpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStep = new Step();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.step_fragment,container,false);
        return v;
    }
}
