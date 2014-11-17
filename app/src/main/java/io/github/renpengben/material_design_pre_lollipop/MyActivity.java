package io.github.renpengben.material_design_pre_lollipop;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author ben.ren
 * @blog http://renpengben.github.io/
 * @github https://github.com/renpengben
 */

public class MyActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle("Drawer Close");
        setSupportActionBar(toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                toolbar.setSubtitle("Drawer Open");
                invalidateOptionsMenu();//调用onPrepareOptionsMenu()方法
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                toolbar.setSubtitle("Drawer Close");
                invalidateOptionsMenu();//调用onPrepareOptionsMenu()方法
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        //抽屉菜单内容设置
        String[] drawerItems = {"RecyclerView", "CardView", "Palette"};
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_item, drawerItems));

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        /**
         * Call syncState() from your activity's onPostCreate to set the state of the indicator based on whether the drawerlayout is in open or closed state once the activity has been restored with onRestoreInstanceState.
         */
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //抽屉菜单打开时隐藏ToolBar Search菜单
        boolean drawerOpen = drawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.menu_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.my, menu);

        return true;
    }
}
