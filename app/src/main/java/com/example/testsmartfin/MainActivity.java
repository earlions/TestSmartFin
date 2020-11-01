package com.example.testsmartfin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.testsmartfin.fragment.CheckFragment;
import com.example.testsmartfin.fragment.RangeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements RangeFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private String sum="";
    private TextView tv;
    private double finalKol=0.00;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Боковое меню
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);
         //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return RangeFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position){
                switch (position){
                    case 0: return "Все";
                    case 1: return "Сочи";
                    case 2: return "Россия";
                    case 3: return "Булорусия";
                }

                 return "name"  ;
            }
        });

    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.click0 : sum += "0";
            break;
            case R.id.click1 : sum += "1";
            break;
            case R.id.click2 : sum += "2";
            break;
            case R.id.click3 : sum += "3";
            break;
            case R.id.click4 : sum += "4";
            break;
            case R.id.click5 : sum += "5";
            break;
            case  R.id.click6 : sum += "6";
            break;
            case R.id.click7 : sum += "7";
            break;
            case R.id.click8 : sum += "8";
            break;
            case R.id.click9 : sum += "9";
            break;
            case  R.id.clickZ : sum += ".";
            break;
            case  R.id.clickX : sum = "";
        }
        int kol=0;
        if (!sum.equals(""))
            kol =(int) (Double.parseDouble(sum)*100);
        finalKol=(double) kol/100;
        if (tv!=null) {
            tv.setText(sum);
        }

    }
    @Override
    public void onFragmentInteraction(String name, String price) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_input, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        mDialogBuilder.setView(promptsView);
        tv =(TextView) promptsView.findViewById(R.id.tv);
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Добавить",
                        (dialog, which) -> {
                            sum="";
                            CheckFragment fragment = (CheckFragment) getSupportFragmentManager()
                                    .findFragmentById(R.id.fragmentCheck);
                            if (fragment.isInLayout()) {
                                fragment.setText(name,Double.parseDouble(price),finalKol);
                            }
                        })
            .setNegativeButton("Отмена",
                    (dialog, which) -> sum="");
        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
}