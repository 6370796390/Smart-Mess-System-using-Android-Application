package com.example.smartmess;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MarkAttendanceActivity extends AppCompatActivity {

    private Button markAttendanceButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        markAttendanceButton = findViewById(R.id.markAttendanceButton);
        databaseReference = FirebaseDatabase.getInstance().getReference("attendance");

        markAttendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markAttendance();
            }
        });
    }

    private void markAttendance() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child(userId).setValue(true).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(MarkAttendanceActivity.this, "Attendance marked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MarkAttendanceActivity.this, "Error marking attendance", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
