package com.sandeep.hostelcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentRulesFragment extends Fragment {

    private TextView rulesTextView;
    private DatabaseReference rulesDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_rules, container, false);

        rulesTextView = view.findViewById(R.id.rulesTextView);
        rulesDatabase = FirebaseDatabase.getInstance().getReference("warden").child("rules");

        retrieveLatestRules();

        return view;
    }

    private void retrieveLatestRules() {
        rulesDatabase.orderByChild("timestamp").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot rulesSnapshot : dataSnapshot.getChildren()) {
                    Ruless latestRules = rulesSnapshot.getValue(Ruless.class);

                    if (latestRules != null) {
                        rulesTextView.setText(latestRules.getRulesText());
                        showToast("Latest rules loaded!");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showToast("Failed to load rules. Please try again.");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
