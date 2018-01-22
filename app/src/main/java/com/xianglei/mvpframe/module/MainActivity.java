package com.xianglei.mvpframe.module;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xianglei.mvpframe.R;
import com.xianglei.mvpframe.base.BaseActivity;
import com.xianglei.mvpframe.module.home.HomeFragment;
import com.xianglei.mvpframe.utils.Const;

import butterknife.BindView;
import skin.support.SkinCompatManager;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected void recycleRes() {

    }


    private void initFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment homeFragment = HomeFragment.newInstance(Const.ANDROID);
        ft.add(R.id.content, homeFragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
        }else if(id == R.id.theme_black){
            SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
            return true;
        }else if(id == R.id.theme_red){
            return true;
        }else if(id == R.id.theme_blue){
            SkinCompatManager.getInstance().restoreDefaultTheme();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        String type = "";
        if (id == R.id.nav_all) {
            type = Const.ALL;
        } else if (id == R.id.nav_android) {
            type = Const.ANDROID;
        } else if (id == R.id.nav_ios) {
            type = Const.IOS;
        } else if (id == R.id.nav_qianduan) {
            type = Const.QIANDUAN;
        } else if (id == R.id.nav_tuozhan) {
            type = Const.TUOZHAN;
        } else if (id == R.id.nav_fuli) {
            type = Const.FULI;
        } else if (id == R.id.nav_shipin) {
            type = Const.SHIPIN;
        } else if (id == R.id.nav_tuijian) {
            type = Const.TUIJIAN;
        }
        Fragment homeFragment = HomeFragment.newInstance(type);
        ft.replace(R.id.content, homeFragment);
        ft.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
