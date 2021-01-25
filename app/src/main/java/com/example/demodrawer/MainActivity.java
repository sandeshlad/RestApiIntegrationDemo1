package com.example.demodrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.demodrawer.fragment.AlbumByIdFragment;
import com.example.demodrawer.fragment.AllAlbumsFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_menu);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AllAlbumsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_allalbums);
        }
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }


            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                switch (Item.getItemId())
                {
                    case R.id.nav_allalbums:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AllAlbumsFragment()).commit();
                        break;

                    case R.id.nav_allalbumbyid  :
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AlbumByIdFragment()).commit();
                        break;

                    case R.id.exit:
                        finish();
                        break;

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

}



