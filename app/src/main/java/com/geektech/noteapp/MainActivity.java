package com.geektech.noteapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavController();
   // TODO: 6th Home Work - first run the board fragment
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            navController.navigate(R.id.phone_fragment);
        }
        Prefs prefs = new Prefs(this);
        if (!prefs.isShown()) {
            navController.navigate(R.id.board_fragment);
        }
    }

    private void initNavController() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.phone_fragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination,
                                             @Nullable Bundle arguments) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(R.id.navigation_home);
                list.add(R.id.navigation_dashboard);
                list.add(R.id.navigation_notifications);
                list.add(R.id.navigation_profile);
                if (list.contains(destination.getId())) {
                    navView.setVisibility(View.VISIBLE);
                } else {
                    navView.setVisibility(View.GONE);
                }

                if (destination.getId() == R.id.board_fragment) {
                    getSupportActionBar().hide();
                } else {
                    getSupportActionBar().show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    //TODO: 3rd Home Work - adding Menu Log out
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.logout) {
            new Prefs(this).deleteSavedStatus();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            navController.navigateUp();
            finish();
            return true;
        }
        return false;
    }
}