package com.example.trabalho_final.Profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.trabalho_final.Login;
import com.example.trabalho_final.MainActivity;
import com.example.trabalho_final.R;
import com.example.trabalho_final.Register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

    }

    public void onStart() {
        super.onStart();
       /* FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // No user is signed in
        } else {
            // User logged in
        }
        */
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = null;
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_profile, container, false);
            Button btnlogin = (Button) view.findViewById(R.id.login);
            Button btnregister = (Button) view.findViewById(R.id.register);
            btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent login = new Intent(getActivity(),Login.class);
                    startActivity(login);
                }
            });
            btnregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent register = new Intent(getActivity(), Register.class);
                    startActivity(register);
                }
            });
        } else {
            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.logout, container, false);
            Button btnLogout = (Button) view.findViewById(R.id.logout);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAuth.signOut();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new ProfileFragment());
                    fragmentTransaction.commit();
                }
            });
        }

        return view;
    }


}