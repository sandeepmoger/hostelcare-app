package com.sandeep.hostelcare;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class somethingFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_something, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = view.findViewById(R.id.alertButton);
        EditText editText = view.findViewById(R.id.alertMessage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (text.isEmpty()) {
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(requireContext(), "Alert Message can't be empty.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(requireContext(), "Sending...", Toast.LENGTH_SHORT).show();
                                }
                            });
                            send(text);
                        }
                    }
                }).start();
            }
        });
    }

    public void send(String message) {
        try {
            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Authorization", "Basic OGIwYjRlNjItMzJhOS00NWNjLWFkYTctZTFjMzJhMzRhODEx");
            httpConn.setRequestProperty("accept", "application/json");
            httpConn.setRequestProperty("content-type", "application/json");
            httpConn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
            writer.write("\n{\n  \"included_segments\": [\n    \"All\"\n  ],\n  \"contents\": {\n    \"en\": \"" + message + "\"},\n  \"app_id\": \"bd062913-293e-47bf-8038-084b97ccc169\"\n}\n");
            writer.flush();
            writer.close();
            httpConn.getOutputStream().close();
            Log.d("HEI", new Scanner(httpConn.getInputStream()).next());
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(requireContext(), "Alert sent successfully.", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(requireContext(), "Error sending alert.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}