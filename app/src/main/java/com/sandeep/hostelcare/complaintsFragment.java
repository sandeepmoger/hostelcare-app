package com.sandeep.hostelcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class complaintsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaints, container, false);

        // Assuming you have a Button with id "complaintsViewingButton" in your XML
        Button viewComplaintsButton = view.findViewById(R.id.complaintsViewingButton);

        viewComplaintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(getActivity(), userlist.class);

                // Add any extra data to the Intent if needed
                // intent.putExtra("key", "value");

                // Start the activity
                startActivity(intent);
            }
        });

        return view;
    }
}
