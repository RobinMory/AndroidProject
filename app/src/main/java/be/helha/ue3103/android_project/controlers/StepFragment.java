package be.helha.ue3103.android_project.controlers;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.MPMLab;
import be.helha.ue3103.android_project.models.Step;


public class StepFragment extends Fragment {

    public static final String PROJECT_ID = "PROJECT_ID";
    protected Step mStep;
    private MPMLab lab;
    private EditText mStepTitle;
    private Spinner mSpinnerPoints;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        mStep = (Step) bundle.getSerializable(PROJECT_ID);
        lab =  MPMLab.get(this.getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.step_fragment,container,false);
        mStepTitle = (EditText) v.findViewById(R.id.step_title);
        mSpinnerPoints = (Spinner) v.findViewById(R.id.points_spinner);
        mStepTitle.setText(mStep.getName());
        mSpinnerPoints.setSelection(mStep.getGrade());
        setSpinnerValue();
        setListener();
        return v;
    }

    private void setListener() {
        mStepTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mStep.setName(mStepTitle.getText().toString());
                lab.updateStep(mStep);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        mSpinnerPoints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                mStep.setGrade(position);
                lab.updateStep(mStep);

                if(fragmentInterfacer != null){
                    fragmentInterfacer.OnItemSelected(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    private void setSpinnerValue() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.points_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerPoints.setAdapter(adapter);
        Integer point = mStep.getGrade();
        int selectionPosition= adapter.getPosition(point.toString());
        mSpinnerPoints.setSelection(selectionPosition);
    }

    public interface MyFragmentInterfacer{
        void OnItemSelected(int grade);
    }
    MyFragmentInterfacer fragmentInterfacer;

    //Override this function as below to set fragmentInterfacer
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInterfacer = (MyFragmentInterfacer) context;
    }
}
