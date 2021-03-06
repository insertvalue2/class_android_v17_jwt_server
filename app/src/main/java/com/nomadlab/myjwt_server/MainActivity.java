package com.nomadlab.myjwt_server;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nomadlab.myjwt_server.utils.FragmentType;

public class MainActivity extends AppCompatActivity {

    BlogListFragment blogListFragment;
    UserListFragment userListFragment;
    WebViewFragment webViewFragment;
    MyInfoFragment myInfoFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent() != null) {
            String msg = getIntent().getStringExtra("msg");
            Toast.makeText(this, msg,
                    Toast.LENGTH_LONG).show();
        }

        initData();
        addMenuEventListener();
        addFragment(FragmentType.BLOG_LIST);
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private void addFragment(FragmentType type) {
        Fragment fragment = null;

        switch (type) {
            case BLOG_LIST:
//                fragment = new BlogListFragment();
                fragment = blogListFragment;
                break;
            case USERS:
                fragment = userListFragment;
                break;
            case WEB_VIEW:
                fragment = webViewFragment;
                break;
            case MY_INFO:
                fragment = myInfoFragment;
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =  fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }


    private void initData() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        blogListFragment = new BlogListFragment();
        userListFragment = new UserListFragment();
        webViewFragment = new WebViewFragment();
        myInfoFragment = new MyInfoFragment();
    }


    private void addMenuEventListener() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        addFragment(FragmentType.BLOG_LIST);
                    break;
                    case R.id.page_2:
                        addFragment(FragmentType.USERS);
                        break;
                    case R.id.page_3:
                        addFragment(FragmentType.WEB_VIEW);
                        break;
                    case R.id.page_4:
                        addFragment(FragmentType.MY_INFO);
                        break;
                }
                return true;
            }
        });
    }

}