package com.example.trabalho_final;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trabalho_final.Articles.ArticlesFragment;
import com.example.trabalho_final.databinding.ActivityMainBinding;

import com.example.trabalho_final.HomePage.HomeFragment;

import com.example.trabalho_final.Search.SearchFragment;

import com.example.trabalho_final.Settings.SettingsFragment;

import com.example.trabalho_final.Profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Button btnLogin;
    private Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        getSupportActionBar().hide();
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.articles:
                    replaceFragment(new ArticlesFragment());
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());

                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }

            return true;
        });




    }





    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


}