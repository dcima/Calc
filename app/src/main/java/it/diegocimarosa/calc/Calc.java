package it.diegocimarosa.calc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Calc extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Home home = new Home();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.mainLayout, home, home.getTag())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            // int countFragment = getFragmentManager().getBackStackEntryCount();
            int countFragment = fm.getBackStackEntryCount();
            if (countFragment > 0) {
                //fm.popBackStack();
                super.onBackPressed();
            } else {
                askConfirm();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        int entry = -1;

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            entry = searchFragment(fm, "Home");
            if (entry == -1) {
                Fragment home = new Home();
                fm.beginTransaction()
                        .replace(R.id.mainLayout, home)
                        .addToBackStack("Home")
                        .commit();
            } else {
                fm.popBackStack("Home", 0);
            }
        } else if (id == R.id.nav_info) {
            entry = searchFragment(fm, "Info");
            if (entry == -1) {
                Fragment info = new Info();
                fm.beginTransaction()
                        .replace(R.id.mainLayout, info)
                        .addToBackStack("Info")
                        .commit();
            } else {
                fm.popBackStack("Info", 0);
            }
        } else {
            if (id == R.id.nav_author) {
                entry = searchFragment(fm, "Author");
                if (entry == -1) {
                    Fragment author = new Author();
                    fm.beginTransaction()
                            .replace(R.id.mainLayout, author)
                            .addToBackStack("Author")
                            .commit();
                } else {
                    fm.popBackStack("Author", 0);
                }
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private int searchFragment(FragmentManager fm, String name) {
        FragmentManager.BackStackEntry bse = null;
        String fragmentName = null;
        int entry;

        for (entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            bse = fm.getBackStackEntryAt(entry);
            fragmentName = bse.getName();
            if (fragmentName == name) {
                Log.i("FRAGMENT", "Found fragment: " + fragmentName);
                return entry;
            }
        }

        return -1;

    }

    private void askConfirm() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.ask_exit_title)
                .setMessage(R.string.ask_exit_confirm)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

}
