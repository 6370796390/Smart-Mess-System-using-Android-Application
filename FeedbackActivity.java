package com.example.smartmess;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackInput;
    private Button submitFeedbackButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackInput = findViewById(R.id.feedbackInput);
        submitFeedbackButton = findViewById(R.id.submitFeedbackButton);
        databaseReference = FirebaseDatabase.getInstance().getReference("feedback");

        submitFeedbackButton.setOnClickListener(v -> submitFeedback());
    }

    private void submitFeedback() {
        String feedback = feedbackInput.getText().toString();
        if (!feedback.isEmpty()) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            databaseReference.child(userId).setValue(feedback).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(FeedbackActivity.this, "Feedback submitted", Toast.LENGTH_SHORT).show();
                    feedbackInput.setText("");
                } else {
                    Toast.makeText(FeedbackActivity.this, "Error submitting feedback", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter feedback", Toast.LENGTH_SHORT).show();
        }
    }
}
