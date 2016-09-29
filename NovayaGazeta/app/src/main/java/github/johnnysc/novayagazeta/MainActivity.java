package github.johnnysc.novayagazeta;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static Context context;
    public static String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.INVISIBLE);//Don't forget to remove this if need fab

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setBackgroundResource(R.color.colorPrimary);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView linkText = (TextView) headerView.findViewById(R.id.linkTextView);
        linkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.novayagazeta.ru/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                try{
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"Браузер не найден",Toast.LENGTH_LONG).show();
                }
            }
        });

        displayView(R.id.main_info);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Выход из приложения")
                    .setMessage("Уверены, что хотите выйти из приложения?")
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displayView(item.getItemId());
        return true;
    }

    public void displayView(int viewId){
        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId){
            case R.id.main_info:
                fragment = new MainInfoFragment();
                title = getString(R.string.main);
                link = "main";
                break;

            case R.id.politics_in_russia:
                fragment = new MainInfoFragment();
                title = getString(R.string.politics_in_russia);
                link = "politics/russia/";
                break;

            case R.id.politics_in_the_world:
                fragment = new MainInfoFragment();
                title = getString(R.string.politics_in_the_world);
                link = "politics/world/";
                break;

            case R.id.inquests:
                fragment = new MainInfoFragment();
                title = getString(R.string.inquests);
                link = "inquests/";
                break;

            case R.id.columns:
                fragment = new MainInfoFragment();
                title = getString(R.string.columns);
                link = "columns/";
                break;

            case R.id.economy:
                fragment = new MainInfoFragment();
                title = getString(R.string.economy);
                link = "economy/";
                break;

            case R.id.comments:
                fragment = new MainInfoFragment();
                title = getString(R.string.comments);
                link = "comments/";
                break;

            case R.id.society:
                fragment = new MainInfoFragment();
                title = getString(R.string.society);
                link = "society/";
                break;

            case R.id.arts:
                fragment = new MainInfoFragment();
                title = getString(R.string.arts);
                link = "arts/";
                break;

            case R.id.sport:
                fragment = new MainInfoFragment();
                title = getString(R.string.sports);
                link = "sports/";
                break;

            default:
                fragment = new MainInfoFragment();
                title = "Oops, error?";
                link = "main";
                break;
        }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();

        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
