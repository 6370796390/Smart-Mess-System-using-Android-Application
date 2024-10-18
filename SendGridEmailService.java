package com.example.smartmess;

import android.os.AsyncTask;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

public class SendGridEmailService extends AsyncTask<Void, Void, Void> {

    private String email, subject, message;

    public SendGridEmailService(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SendGrid sendGrid = new SendGrid("SENDGRID_API_KEY");
        SendGrid.Email emailObj = new SendGrid.Email();
        emailObj.addTo(email);
        emailObj.setFrom("mess@example.com");
        emailObj.setSubject(subject);
        emailObj.setText(message);

        try {
            sendGrid.send(emailObj);
        } catch (SendGridException e) {
            e.printStackTrace();
        }
        return null;
    }
}
