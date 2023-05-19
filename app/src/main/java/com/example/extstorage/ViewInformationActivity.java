package com.example.extstorage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ViewInformationActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_information);

        textView = findViewById(R.id.textView_get_saved_data);
    }

    @SuppressLint("SetTextI18n")
    public void showPublicData(View view) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "pqr.txt");
        String data = getData(file);
        if (data != null) {
            textView.setText(data);
        } else {
            textView.setText("No Data Found");
        }
    }

    @SuppressLint("SetTextI18n")
    public void showPrivateData(View view) {
        File folder = getExternalFilesDir("ABC");
        File file = new File(folder, "gfg.txt");
        String data = getData(file);
        if (data != null) {
            textView.setText(data);
        } else {
            textView.setText("No Data Found");
        }
    }

    public void back(View view) {
        Intent intent = new Intent(ViewInformationActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private String getData(File file) {
        StringBuilder data = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
                data.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
}
