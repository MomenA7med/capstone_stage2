package com.example.momen.capstone_stage2.ui;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.momen.capstone_stage2.R;
import com.example.momen.capstone_stage2.adapter.CatalogAdapter;
import com.example.momen.capstone_stage2.model.Category;
import com.example.momen.capstone_stage2.model.Help;
import com.example.momen.capstone_stage2.widget.AppWidget;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,CatalogAdapter.CatagoryClick {

    List<String> names,images,categoryNum;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(getResources().getString(R.string.Users)).child(Help.userName).child(getResources().getString(R.string.category));

        names = getListName();
        images = getListImage();
        categoryNum = getListCategory();
        RecyclerView catalog = findViewById(R.id.categoryRV);
        CatalogAdapter adapter = new CatalogAdapter(this,names,images,this);
        catalog.setAdapter(adapter);
        catalog.setHasFixedSize(false);
        catalog.setLayoutManager(new LinearLayoutManager(this));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getMaxDegree();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void getMaxDegree(){
        final int[] max = {0};
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Category category = dataSnapshot.getValue(Category.class);
                if (category != null){
                    if (category.getMaxDegree() >= max[0]){
                        max[0] = category.getMaxDegree();
                        String categ = category.getCategoryName();
                        addToWidget(max[0],categ);
                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void addToWidget(int max, String categ) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MainActivity.this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, AppWidget.class));
        AppWidget.updateScoreWidgets(this,appWidgetManager,max,categ,appWidgetIds);
    }

    private List<String> getListName(){
        List<String> names = new ArrayList<>();
        names.add("Sports");
        names.add("Arts");
        names.add("Animals");
        names.add("Since & Nature");
        names.add("Music");
        names.add("General knowledge");
        names.add("Books");
        return names;
    }
    private List<String> getListImage(){
        List<String> images = new ArrayList<>();
        images.add("https://www.thinkwy.org/wp-content/uploads/2017/10/hpfulq-1234.jpg                                                          ");
        images.add("https://encoreatlanta.com/wp-content/uploads/2017/10/arts.jpg                                                             ");
        images.add("http://www3.canisius.edu/~grandem/animalshabitats/JungleAnimalsBorder.jpg                                                 ");
        images.add("https://gfb.global.ssl.fastly.net/wp-content/uploads/2017/06/Mountain_Gfb.jpg                                             ");
        images.add("http://pamis.org.uk/site/uploads/musicworkshop-image.jpg                                                                  ");
        images.add("https://highwaymail.co.za/wp-content/uploads/sites/50/2016/02/iq-test.jpg                                                 " );
        images.add("https://www.amnesty.org.uk/files/styles/poster/s3/2018-08/books-548x331.jpg?6_RB5rrWNmvYuI7IRX8j5R0lElIJ7DqP&itok=mgh-vdvG");
        return images;
    }
    private List<String> getListCategory(){
        List<String> categoryNum = new ArrayList<>();
        categoryNum.add("21");
        categoryNum.add("25");
        categoryNum.add("27");
        categoryNum.add("17");
        categoryNum.add("12");
        categoryNum.add("9");
        categoryNum.add("10");
        return categoryNum;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

       if (id == R.id.profile) {
           Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
           startActivity(intent);
       }
       else if (id == R.id.logOut){
           Intent intent = new Intent(MainActivity.this,LoginActivity.class);
           startActivity(intent);
           finish();
       }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMaxDegree();
    }

    @Override
    public void catagoryOnClick(int postion) {
        Intent intent = new Intent(this,LevelActivity.class);
        intent.putExtra(getResources().getString(R.string.image),images.get(postion));
        intent.putExtra(getResources().getString(R.string.name),names.get(postion));

        Help.categoryName = names.get(postion);
        intent.putExtra(getResources().getString(R.string.categoryNum),categoryNum.get(postion));
        startActivity(intent);
    }
}
