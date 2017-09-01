package com.example.santhosh.earthquake;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.addToBackStack(EarthQuakeFragment.TAG)
                .replace(R.id.container, EarthQuakeFragment.newInstance(), EarthQuakeFragment.TAG)
                .commit();
    }
}
