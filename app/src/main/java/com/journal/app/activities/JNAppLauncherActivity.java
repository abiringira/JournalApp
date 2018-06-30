package com.journal.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.SignInButton;

public class JNAppLauncherActivity extends JNBaseActivity {
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jn_activity_launcher);
        signInButton = findViewById(R.id.sign_in);
        signInButton .setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });


    }

    public void openLogin() {
        Intent intent = new Intent(this, JNLoginActivity.class);
        startActivity(intent);
        finish();
    }
}
