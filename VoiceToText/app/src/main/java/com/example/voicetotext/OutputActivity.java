package com.example.voicetotext;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    private Button commandBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Output Text");
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView textOutput = (TextView) findViewById(R.id.textOutput);
        String result = getIntent().getStringExtra("result");
        textOutput.setText(result);
        assert result != null;
        if(result.equals("display my photos") || result.equals("display my contacts")) {
            commandBtn = findViewById(R.id.commandButton);
            commandBtn.setVisibility(View.VISIBLE);
            commandBtn.setText(result.equals("display my photos") ? "Open Gallery" : "Open Contacts");
        }
    }

    public void onClick(View v)
    {
        if(commandBtn.getText().toString().equals("Open Gallery")){
            Intent galleryIntent = new Intent(Intent.ACTION_VIEW, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivity(galleryIntent);
        } else {
            Intent contactsIntent = new Intent(Intent.ACTION_VIEW, android.provider.ContactsContract.Contacts.CONTENT_URI);
            startActivity(contactsIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Respond to the action bar's Up/Home button
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}