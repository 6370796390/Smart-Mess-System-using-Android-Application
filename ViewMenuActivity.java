package com.example.smartmess;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewMenuActivity extends AppCompatActivity {

    private TextView menuTextView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        menuTextView = findViewById(R.id.menuTextView);
        databaseReference = FirebaseDatabase.getInstance().getReference("menu");

        // Fetch daily menu and display it
        databaseReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String menu = task.getResult().getValue(String.class);
                menuTextView.setText(menu);
            }
        });
    }
}
