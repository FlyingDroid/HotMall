package com.hotmall.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hotmall.adapter.MainFragmentAdapter;
import com.hotmall.R;
import com.hotmall.base.BaseActivity;
import com.hotmall.base.BaseFragment;
import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialCommunityIcons;
import com.joanzapata.iconify.fonts.MaterialIcons;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements OnMenuItemClickListener {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.ll_time)
    LinearLayout llTime;
    @Bind(R.id.ll_season)
    LinearLayout llSeason;
    @Bind(R.id.ll_explore)
    LinearLayout llExplore;
    @Bind(R.id.ll_mine)
    LinearLayout llMine;
    private TextView tvFont;
    private TextView tvText;
    private ContextMenuDialogFragment menuDialogFragment;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupMenu();
        ArrayList<BaseFragment> baseFragments = new ArrayList<>();
        baseFragments.add(new NewsFragment());
        baseFragments.add(new CommunityFragment());
        baseFragments.add(new ExploreFragment());
        baseFragments.add(new MineFragment());
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), baseFragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        llTime.performClick();
                        break;
                    case 1:
                        llSeason.performClick();
                        break;
                    case 2:
                        llExplore.performClick();
                        break;
                    case 3:
                        llMine.performClick();
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        llTime.performClick();
    }

    private void setupMenu() {
        List<MenuObject> menuObjects = new ArrayList<>();
        MenuObject close = new MenuObject();
        close.setDrawable(new IconDrawable(this, MaterialIcons.md_close).color(R.color.colorPrimaryDark).actionBarSize());
        close.setDividerColor(R.color.colorAccent);
        close.setMenuTextAppearanceStyle(R.style.TextViewStyle);
        menuObjects.add(close);

        menuObjects.add(createDialogMenu("有事记录", MaterialIcons.md_edit));
        menuObjects.add(createDialogMenu("扫一扫", MaterialCommunityIcons.mdi_qrcode_scan));
        menuObjects.add(createDialogMenu("雷达", MaterialCommunityIcons.mdi_satellite_variant));

        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.toolbar_height));
        menuParams.setMenuObjects(menuObjects);
        menuParams.setClosableOutside(true);
        // set other settings to meet your needs
        menuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        menuDialogFragment.setItemClickListener(this);
    }

    private MenuObject createDialogMenu(String title, Icon icon) {
        MenuObject menu = new MenuObject(title);
        menu.setDrawable(new IconDrawable(this, icon).color(R.color.colorPrimaryDark).actionBarSize());
        menu.setDividerColor(R.color.colorAccent);
        menu.setMenuTextAppearanceStyle(R.style.TextViewStyle);
        return menu;
    }

    public void clickMenu(View view) {
        LinearLayout llMenu = (LinearLayout) view;
        if (tvFont != null && tvText != null) {
            setTextViewColor(false);
        }
        tvFont = (TextView) llMenu.getChildAt(0);
        tvText = (TextView) llMenu.getChildAt(1);
        setTextViewColor(true);
        int index = 0;
        switch (view.getId()) {
            case R.id.ll_time:
                index = 0;
                break;
            case R.id.ll_season:
                index = 1;
                break;
            case R.id.ll_explore:
                index = 2;
                break;
            case R.id.ll_mine:
                index = 3;
                break;
        }
        viewPager.setCurrentItem(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.more_menu).setIcon(
                new IconDrawable(this, MaterialCommunityIcons.mdi_windows)
                        .colorRes(R.color.white)
                        .actionBarSize());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.more_menu) {
            menuDialogFragment.show(getSupportFragmentManager(), "ContextMenuDialogFragment");
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTextViewColor(boolean isFocus) {
        if (isFocus) {
            tvFont.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tvText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            tvFont.setTextColor(getResources().getColor(R.color.darker_gray));
            tvText.setTextColor(getResources().getColor(R.color.darker_gray));
        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Log.d(TAG, "onMenuItemClick: " + position);
        switch (position) {
            case 1://有事记录
                break;
            case 2://扫一扫
                break;
            case 3://雷达
                break;
        }
    }
}
