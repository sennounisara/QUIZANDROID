package com.quiz.app.sara;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quiz.app.sara.LoginActivity;
import com.quiz.app.sara.QuizActivity;
import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    TextView tvName, tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ParseUser currentUser = ParseUser.getCurrentUser();

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        if(currentUser!=null){
            tvName.setText(currentUser.getString("name"));
            tvEmail.setText(currentUser.getEmail());
        }
    }

    public void logout(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading ...");
        progress.show();
        ParseUser.logOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        progress.dismiss();
    }

    public void play(View view) {
        Intent i = new Intent(getApplicationContext(), QuizActivity.class);
        startActivity(i);

    }
}
