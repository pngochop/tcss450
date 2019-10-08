package edu.uw.tcss450;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import edu.uw.tcss450.model.Credentials;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements  LoginFragment.OnLoginFragmentInteractionListener,
            RegisterFragment.OnRegisterFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            if (findViewById(R.id.frame_main_container) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.frame_main_container, new LoginFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onLoginSuccess(Credentials cr, String st) {
        Log.d("ACTIVITY", "Login Success" + st);
        SuccessFragment successFragment;
        successFragment = new SuccessFragment();
        Bundle args = new Bundle();
        args.putSerializable(getString(R.string.email_login), cr.getEmail());
        successFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, successFragment)
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onRegisterClicked() {
        Log.d("ACTIVITY", "Register: ");
        RegisterFragment registerFragment;
        registerFragment = new RegisterFragment();
        Bundle args = new Bundle();
        registerFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, registerFragment)
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onRegisterSuccess(Credentials cr) {
        Log.d("ACTIVITY", "Login: " + cr.getEmail());
        LoginFragment loginFragment;
        loginFragment = new LoginFragment();
        Bundle args = new Bundle();
        loginFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_main_container, loginFragment)
                .addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }
}
