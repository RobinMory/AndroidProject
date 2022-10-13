package be.helha.ue3103.android_project.controlers;

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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.Step;


public class StepFragment extends Fragment {

    protected Step mStep;
    private EditText mStepTitle;
    private Spinner mSpinnerPoints;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStep = new Step();
        /* Lors de l'utilisation de la DB, l'étape sera reconnu via son UUID dans la table
        UUID crime_id = (UUID)
                getActivity().getIntent().getSerializableExtra(CRIME_ID);
        mCrime = CrimeLab.get(getContext()).getCrime(crime_id);
         */
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.step_fragment,container,false);
        mStepTitle = (EditText) v.findViewById(R.id.step_title);
        mSpinnerPoints = (Spinner) v.findViewById(R.id.points_spinner);
        mStepTitle.setText(mStep.getName());
        //Il faut setLaValue des points dans le spinner
        setSpinner();
        setListener();

        return v;
    }

    private void setListener() {
        mStepTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // do nothing
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mStep.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // do nothing, everything’s done in onTextChanged
            }
        });
        mSpinnerPoints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                /*Toast toast = Toast.makeText(getActivity(), mSpinnerPoints.getSelectedItem().getClass().toString()
                        , Toast.LENGTH_SHORT);
                toast.show();
                 */
                //Parse de string à int
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }

    private void setSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.points_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerPoints.setAdapter(adapter);
        Integer point = mStep.getPoints();
        int selectionPosition= adapter.getPosition(point.toString());
        mSpinnerPoints.setSelection(selectionPosition);
    }
}
