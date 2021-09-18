package com.example.zakat.views.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.zakat.BaseActivity;
import com.example.zakat.R;
import com.example.zakat.databinding.ActivityAdminHomeBinding;
import com.google.android.material.navigation.NavigationView;

public class AdminHome extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityAdminHomeBinding binding;

    private NavController navController;
    private NavHostFragment navHostController;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = binding.drawerLayout;
        navigationView = binding.navView;

        navHostController = (NavHostFragment)getSupportFragmentManager().findFragmentById(binding.navHostFragment.getId());
        navController = navHostController.getNavController();

        initNavigation();

    }

    private void initNavigation(){
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                sessionManager.logout(sessionManager.getAuthUser());
                break;
            case android.R.id.home:{
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,drawerLayout);
    }

//    private boolean isValidDestination(int destination){
//        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // if(isValidDestination(R.id.postsScreen)){
        //                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postsScreen);
        //                }




        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);

//        switch (item.getItemId()){
        //R.id.nav_profile:{
        //
        //                NavOptions navOptions = new NavOptions.Builder()
        //                        .setPopUpTo(R.id.main, true)
        //                        .build();
        //
        //                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
        //                        R.id.profileScreen,
        //                        null,
        //                        navOptions
        //                );
        //
        //                break;
        //            }
//
//        }


        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

}