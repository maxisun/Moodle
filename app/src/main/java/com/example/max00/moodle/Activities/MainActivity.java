package com.example.max00.moodle.Activities;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.max00.moodle.Entity_Class.Student;
import com.example.max00.moodle.Fragments.InsertFragment;
import com.example.max00.moodle.Fragments.SearchFragment;
import com.example.max00.moodle.Fragments.ShowFragment;
import com.example.max00.moodle.Fragments.UpdateFragment;
import com.example.max00.moodle.R;
import com.example.max00.moodle.SQLiteHelper.DAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mactionBarDrawerToggle;
    private NavigationView navigationView;
    private ArrayList<Student> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        DAO.getInstance(this);
        list = DAO.myDB.getAllElements();
        mactionBarDrawerToggle = new ActionBarDrawerToggle(this,mdrawerLayout,R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(mactionBarDrawerToggle);
        //Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout.
        mactionBarDrawerToggle.syncState();
        //Set whether home should be displayed as an "up" affordance.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setFragmentByDefault();
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
                        fragment = ShowFragment.newInstance(list);
                        fragtransac = true;
                        break;
                    case R.id.Actualizar:
                        fragment = new UpdateFragment();
                        fragtransac = true;
                        break;
                    case R.id.Buscar:
                        fragment = new SearchFragment();
                        fragtransac = true;
                        break;
                }
                if (fragtransac){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main,fragment).commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    mdrawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    private void initialize(){
        mdrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    private void setFragmentByDefault(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new InsertFragment()).commit();
        MenuItem item = navigationView.getMenu().getItem(0);
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
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

