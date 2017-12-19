package com.example.wangyuan.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.wangyuan.viewpagerdemo.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.widget.TabLayout.OnTabSelectedListener;
import static android.support.design.widget.TabLayout.Tab;

public class MainActivity extends AppCompatActivity {
    List<Fragment> list = new ArrayList<>();
    private TabLayout tablayout;
    private ViewPager viewpager;
    private ArrayList<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablayout = findViewById(R.id.tablayoyt);
        viewpager = findViewById(R.id.viewpager);
        //数据适配器
        initTab();
        initData();
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(0);
        tablayout.setupWithViewPager(viewpager);
        tablayout.getTabAt(0).select();


        //tab监听
        tablayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                viewpager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });


    }

    //添加布局
    private void initData() {
        list.add(MainFragment.newInstance("1"));
        list.add(MainFragment.newInstance("2"));
        list.add(MainFragment.newInstance("3"));
        list.add(MainFragment.newInstance("4"));
        list.add(MainFragment.newInstance("5"));
        list.add(MainFragment.newInstance("6"));
        list.add(MainFragment.newInstance("7"));
    }


    //添加tab
    private void initTab() {
        stringList = new ArrayList<>();
        stringList.add("猪");
        stringList.add("香");
        stringList.add("香");
        stringList.add("香");
        stringList.add("香");
        stringList.add("香");
        stringList.add("香");
    }


    /**
     * 适配器
     */
    class TabPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> list;

        public TabPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size() > 0 ? list.size() : 0;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }
    }
}
