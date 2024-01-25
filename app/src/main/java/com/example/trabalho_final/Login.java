package com.example.trabalho_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

    Button login = findViewById(R.id.loginbutton);
     login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            authenticateUser();
        }
    });
}

    public void authenticateUser() {
        EditText etLoginEmail = findViewById(R.id.emailLogin);
        EditText etLoginPassword = findViewById(R.id.passwordLogin);

        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            showHomeActivity();
                            sendNotification();
                        } else {
                            Toast.makeText(Login.this, "Erro na autenticação.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void showHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void sendNotification() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this, "My Notification");
        builder.setContentTitle("Login de conta");
        builder.setContentText("Login com sucesso");
        builder.setSmallIcon(R.drawable.ic_baseline_home_24);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Login.this);
        managerCompat.notify(1,builder.build());
    }
}