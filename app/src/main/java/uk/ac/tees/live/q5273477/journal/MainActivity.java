package uk.ac.tees.live.q5273477.journal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    final private  int NUM_OF_ITEMS = 4;

    private String[] drawerItemsNames;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ListItem[] itemsForDrawer;
    Toolbar toolbar;
    private CharSequence drawerTitle;
    private CharSequence title;

    android.support.v7.app.ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = drawerTitle = getTitle();
        drawerItemsNames  = getResources().getStringArray(R.array.drawer_items);
        drawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer);

        drawerList = (ListView) findViewById(R.id.drawer);

        setupToolbar();
        setupDrawerItems();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        Adapter adapter = new Adapter(this, R.layout.list_items,itemsForDrawer);

        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(new drawerClickListener());
        drawerLayout.setDrawerListener(drawerToggle);
        setupToggle();

    }

    private void setupDrawerItems(){
        itemsForDrawer = new ListItem[NUM_OF_ITEMS];
        itemsForDrawer[0] = new ListItem(R.drawable.logo_small, drawerItemsNames[0]);
        itemsForDrawer[1] = new ListItem(R.drawable.ic_doc, drawerItemsNames[1]);
        itemsForDrawer[2] = new ListItem(R.drawable.ic_plus, drawerItemsNames[2]);
        itemsForDrawer[3] = new ListItem(R.drawable.ic_settings, drawerItemsNames[3]);

    }

    private void selectListItem(int posIn){

        Fragment pageFragement = null;

        if(posIn >= 0 && posIn <= NUM_OF_ITEMS ){
            switch (posIn){
                case 0:
                    pageFragement = new HomePage();
                    break;
                case 1:
                    pageFragement = new ListPage();
                    break;
                case 2:
                    pageFragement = new addEditPage();
                    break;
                case 3:
                    pageFragement = new settingsPage();
                    break;
                default:
                    pageFragement = new HomePage();
                    break;
            }
        }else{
            Log.d("MainActivity","Fragment Switch has an invalid number");
        }

        if(!pageFragement.equals(null)){
            FragmentManager fragManger = getSupportFragmentManager();
            fragManger.beginTransaction().replace(R.id.fragment_frame, pageFragement).commit();

            drawerList.setItemChecked(posIn, true);
            drawerList.setSelection(posIn);
            setTitle(drawerItemsNames[posIn]);
            drawerLayout.closeDrawer(drawerList);

        } else {
            Log.d("MainActivity","Fragment Creation Error");
        }


    }

    void setupToggle(){
        drawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name);
        drawerToggle.syncState();
    }

    @Override
    public void setTitle(CharSequence titleIn){
        title = titleIn;
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();

    }

    public class drawerClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long idIn){
                selectListItem(pos);
        }

    }


}
