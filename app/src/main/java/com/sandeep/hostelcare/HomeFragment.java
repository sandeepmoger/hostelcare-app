package com.sandeep.hostelcare;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {
    private WardenViewModel wardenViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        wardenViewModel = new ViewModelProvider(requireActivity()).get(WardenViewModel.class);

        TextView textView = view.findViewById(R.id.textViewWardenName);
        ImageView logoutImageView = view.findViewById(R.id.logoutImageView);
        Button updateRulesButton = view.findViewById(R.id.updateRulesButton);

        if (textView != null) {
            String wardenName = wardenViewModel.getWardenName();

            if (wardenName != null) {
                textView.setText("Hello " + wardenName);
            } else {
                textView.setText("Warden name is null");
            }
        }

        if (logoutImageView != null) {
            logoutImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLogoutDialog();
                }
            });
        }

        if (updateRulesButton != null) {
            updateRulesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the rulesActivity when the button is clicked
                    Intent intent1 = new Intent(requireContext(), rulesActivity.class);
                    startActivity(intent1);
                }
            });
        }

        return view;
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Logout");
        builder.setMessage("Do you really want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform logout action here
                // For example, navigate to the login activity
                Intent intent = new Intent(requireContext(), loginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
