package com.sandeep.hostelcare;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studentComplaintsFragment extends Fragment {

    private EditText nameEditText;
    private EditText complaintEditText;
    private RadioGroup blockRadioGroup;
    private EditText roomNumberEditText;
    private Button submitCompButton;

    private DatabaseReference studentDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_complaints, container, false);

        nameEditText = view.findViewById(R.id.nameEditText1);
        complaintEditText = view.findViewById(R.id.complaintEditText1);
        blockRadioGroup = view.findViewById(R.id.blockRadioGroup);
        roomNumberEditText = view.findViewById(R.id.roomNumberEditText1);
        submitCompButton = view.findViewById(R.id.submitButton1);

        // Use a reference for student complaints data in the database
        studentDatabase = FirebaseDatabase.getInstance().getReference("complaintsDetails");


        submitCompButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitComplaint();
            }
        });

        return view;
    }

    private void submitComplaint() {
        String name = nameEditText.getText().toString().trim();
        String complaintText = complaintEditText.getText().toString().trim();
        String roomNumber = roomNumberEditText.getText().toString().trim();

        // Check if any of the fields is empty or if block is not selected
        if (name.isEmpty() || complaintText.isEmpty() || roomNumber.isEmpty() || getSelectedBlock().isEmpty()) {
            showToast("Please fill in all fields");
            return;
        }

        // Create a ComplaintDetails object
        ComplaintDetails complaintDetails = new ComplaintDetails(name, complaintText, getSelectedBlock(), roomNumber);

        // Add the complaint details to the database under "complaintsDetails"
        studentDatabase.push().setValue(complaintDetails)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        showToast("Complaint submitted successfully");
                    } else {
                        showToast("Failed to submit complaint. Please try again.");
                    }
                });

        // Clear the fields after submitting the complaint
        clearFields();
    }



    // Helper method to get the selected block from the radio group
    private String getSelectedBlock() {
        int selectedBlockId = blockRadioGroup.getCheckedRadioButtonId();

        if (selectedBlockId == -1) {
            // No block selected
            return "";
        }

        RadioButton selectedBlockRadioButton = blockRadioGroup.findViewById(selectedBlockId);
        return selectedBlockRadioButton.getText().toString().trim();
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        nameEditText.getText().clear();
        complaintEditText.getText().clear();
        blockRadioGroup.clearCheck();
        roomNumberEditText.getText().clear();
    }
}
