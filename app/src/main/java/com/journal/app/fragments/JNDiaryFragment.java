package com.journal.app.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.journal.app.activities.JNLoginActivity;
import com.journal.app.activities.JNMainActivity;
import com.journal.app.activities.R;

import java.util.Arrays;

public class JNDiaryFragment extends Fragment {
    private static final String clazzz = JNDiaryFragment.class.getName();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getInstance().getReference();
    private EditText diaryText;
    private Button saveDiaryButton;
    private JNMainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jn_fragment_diary, container, false);
        diaryText = view.findViewById(R.id.edit_input);
        saveDiaryButton = view.findViewById(R.id.continue_button);
        saveDiaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiary();
            }
        });
        return view;
    }

    private void saveDiary() {
        String message = diaryText.getText().toString();
        DatabaseReference journalRef = databaseReference.child("Diary");
        DatabaseReference diaryRef = journalRef.child("Users");
        diaryRef.keepSynced(true);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        String email = account.getDisplayName();
        if (email != null) {
            diaryRef.child(email).setValue(String.valueOf(Arrays.asList(message)));
            String response = "Diary Saved Successfully";
            showMessage(response);
        }

    }


    private void showMessage(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(msg)
                .setTitle(getResources().getString(R.string.app_name));

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.d(clazzz, "Clicked on OK on Alert box");


            }
        });
    }
}
