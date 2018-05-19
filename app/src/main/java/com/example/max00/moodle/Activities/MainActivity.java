package com.example.max00.moodle.Activities;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.max00.moodle.Fragments.InsertFragment;
import com.example.max00.moodle.Fragments.SearchFragment;
import com.example.max00.moodle.Fragments.ShowFragment;
import com.example.max00.moodle.Fragments.UpdateFragment;
import com.example.max00.moodle.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mactionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        mactionBarDrawerToggle = new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(mactionBarDrawerToggle);
        //Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout.
        mactionBarDrawerToggle.syncState();
        //Set whether home should be displayed as an "up" affordance.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragtransac = false;
                android.support.v4.app.Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.Insertar:
                        fragment = new InsertFragment();
                        fragtransac = true;
                        break;
                    case R.id.Mostrar:
                        fragment = new SearchFragment();
                        fragtransac = true;
                        break;
                    case R.id.Actualizar:
                        fragment = new ShowFragment();
                        fragtransac = true;
                        break;
                    case R.id.Buscar:
                        fragment = new UpdateFragment();
                        fragtransac = true;
                        break;
                }
                return false;
            }
        });
    }

    private void initialize(){
        mdrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    //funcion para hacer que funcione el boton para mostrar el drawerlayout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mactionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

