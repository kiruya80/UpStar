package com.ulling.upstar.main.view;

import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ulling.lib.core.base.QcBaseLifeActivity;
import com.ulling.lib.core.util.QcActivityUtils;
import com.ulling.lib.core.util.QcBackPressClose;
import com.ulling.lib.core.util.QcLog;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import static com.ulling.upstar.main.adapter.MenuAdapter.TYPE_MENU_MAIN;

/**
 * 메인화면
 */
public class MainActivity extends QcBaseLifeActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int FRAG_TYPE_MARKET_PRICE = 1;
    public static final int FRAG_TYPE_TALK = 2;
    public static final int FRAG_TYPE_COIN_CALCULATOR = 3;

    public static final int ACTIONBAR_SHORT = 0;
    public static final int ACTIONBAR_LONG = 1;

    private ArrayList<Integer> priceId = new ArrayList<>();
    private ArrayList<Integer> caculatorId = new ArrayList<>();
    private ArrayList<Integer> talkId = new ArrayList<>();

    private ActivityMainBinding viewBinding;

    private DrawerMenuFragment mDrawerMenuFragment;
    private MarketPriceFragment marketPriceFragment;
    private TalkFragment talkFragment;
    private CoinCalculatorFragment coinCalculatorFragment;

    @Override
    protected void needInitToOnCreate() {
//        Intent intent = getIntent();
//        TransitionName_id = intent.getStringExtra("TransitionName_id");
//        item = (Answer) intent.getSerializableExtra("item");
//        if (item != null)
//            QcLog.e("item == " + item.toString());
//
//        qApp = QUllingApplication.getInstance();
//        APP_NAME = QUllingApplication.getAppName();
//        if (viewModel == null) {
//            viewModel = ViewModelProviders.of(this).get(DetailRetrofitLiveViewModel.class);
//            viewModel.needInitViewModel(this);
//            viewModel.needDatabaseModel(DB_TYPE_LOCAL_ROOM, REMOTE_TYPE_RETROFIT, ApiUrl.BASE_URL);
//        }

        marketPriceFragment = MarketPriceFragment.getInstance(1);
        talkFragment = TalkFragment.getInstance();
        coinCalculatorFragment = CoinCalculatorFragment.getInstance();
    }

    @Override
    protected void needResetData() {

    }

    @Override
    protected void needInitViewModel() {

    }

    @Override
    protected int needGetLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void needUIBinding() {
        viewBinding = (ActivityMainBinding) getViewDataBinding();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            viewBinding.tvUserId.setTransitionName(TransitionName_id);
//        }

        setSupportActionBar(viewBinding.includedAppBarMain.toolbar);

        viewBinding.drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, viewBinding.drawerLayout, viewBinding.includedAppBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        viewBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewBinding.navView.setNavigationItemSelectedListener(this);
        setNavigationMenu();

        mDrawerMenuFragment = DrawerMenuFragment.getInstance(viewBinding.drawerLayout);
//        mDrawerMenuFragment.setListener(new DrawerMenuFragment.OnMenuListener() {
//
//            @Override
//            public void onSelected(int type, int sub) {
//                if (viewBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
//                    viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
//                setFragment(type);
//            }
//        });

//        QcActivityUtils.replaceFragment(getSupportFragmentManager(),
//                mDrawerMenuFragment,
//                R.id.frameDrawer,
//                DrawerMenuFragment.class.getSimpleName());


        setFragment(FRAG_TYPE_MARKET_PRICE, 0);


    }


    private void setNavigationMenu() {
        Menu naviMenu = viewBinding.navView.getMenu();

        SubMenu subMenu1 = naviMenu.addSubMenu(getResources().getString(R.string.menu_market_price));
        String[] menu_market_price_sub = getResources().getStringArray(R.array.menu_market_price_sub);
        priceId = new ArrayList<>();
        for (int i = 0; i < menu_market_price_sub.length; i++) {
            QcLog.e("menu_market_price_sub === " + priceMenuItemId);
            MenuItem item = subMenu1.add(FRAG_TYPE_MARKET_PRICE, priceMenuItemId, 0, menu_market_price_sub[i]);
            item.setIcon(R.drawable.ic_menu_camera); // add icon with drawable resource
            priceId.add(priceMenuItemId);
            priceMenuItemId++;
        }


        SubMenu subMenu2 = naviMenu.addSubMenu(getResources().getString(R.string.menu_coin_calculator));
        String[] menu_talk_sub = getResources().getStringArray(R.array.menu_talk_sub);
        caculatorId = new ArrayList<>();
        for (int i = 0; i < menu_talk_sub.length; i++) {
            QcLog.e("menu_talk_sub === " + caculatorMenuItemId);
            MenuItem item = subMenu2.add(FRAG_TYPE_COIN_CALCULATOR, caculatorMenuItemId, 0, menu_talk_sub[i]);
            item.setIcon(R.drawable.ic_menu_slideshow); // add icon with drawable resource
            caculatorId.add(caculatorMenuItemId);
            caculatorMenuItemId++;
        }


        SubMenu subMenu3 = naviMenu.addSubMenu(getResources().getString(R.string.menu_talk));
        String[] menu_coin_calculator_sub = getResources().getStringArray(R.array.menu_coin_calculator_sub);
        talkId = new ArrayList<>();
        for (int i = 0; i < menu_coin_calculator_sub.length; i++) {
            QcLog.e("menu_coin_calculator_sub === " + talkMenuItemId);
            MenuItem item = subMenu3.add(FRAG_TYPE_TALK, talkMenuItemId, 0, menu_coin_calculator_sub[i]);
            item.setIcon(R.drawable.ic_menu_camera); // add icon with drawable resource
            talkId.add(talkMenuItemId);
            talkMenuItemId++;
        }


//        MenuItem edit_item = menu.add(0, MenuItem_EditId, 0, R.string.menu_market_price);
//        edit_item.setIcon(R.drawable.ic_menu_camera);
//        edit_item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//
//        MenuItem delete_item = menu.add(0, MenuItem_DeleteId, 1, R.string.menu_talk);
//        delete_item.setIcon(R.drawable.ic_menu_slideshow);
//        delete_item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    private int priceMenuItemId = 10;
    private int caculatorMenuItemId = 100;
    private int talkMenuItemId = 1000;


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (priceId.contains(id)) {
            setFragment(FRAG_TYPE_MARKET_PRICE, id);
            return true;
        }

        if (caculatorId.contains(id)) {
            setFragment(FRAG_TYPE_COIN_CALCULATOR, id);
            return true;
        }

        if (talkId.contains(id)) {
            setFragment(FRAG_TYPE_TALK, id);
        }
        return true;
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }

    public void setToolBar(int type) {
        if (type == ACTIONBAR_LONG) {
            float heightDp = getResources().getDimension(R.dimen.p800);
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) viewBinding.includedAppBarMain.appBarLayout.getLayoutParams();
            lp.height = (int) heightDp;
            viewBinding.includedAppBarMain.imageCollapsingToolbar.setVisibility(View.VISIBLE);

        } else {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) viewBinding.includedAppBarMain.appBarLayout.getLayoutParams();
            lp.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT;
            viewBinding.includedAppBarMain.imageCollapsingToolbar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void needUIEventListener() {

    }

    @Override
    protected void needSubscribeUiFromViewModel() {

    }


    @Override
    public void onBackPressed() {
        if (viewBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (QcBackPressClose.getInstance().isBackPress(qCon.getString(R.string.app_name))) {
                super.onBackPressed();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 프레그먼트 화면 설정
     *
     * @param type
     */
    private void setFragment(int type, int subType) {
        QcLog.e("setFragment === " + type + " , " + subType);
        if (type == FRAG_TYPE_MARKET_PRICE) {
            marketPriceFragment.setSubType(subType);
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    marketPriceFragment,
                    R.id.frameContainer,
                    marketPriceFragment.getFragmentTag());
            setToolBar(ACTIONBAR_LONG);

        } else if (type == FRAG_TYPE_COIN_CALCULATOR) {
            coinCalculatorFragment.setSubType(subType);
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    coinCalculatorFragment,
                    R.id.frameContainer,
                    coinCalculatorFragment.getFragmentTag());
            setToolBar(ACTIONBAR_SHORT);

        } else if (type == FRAG_TYPE_TALK) {
            talkFragment.setSubType(subType);
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    talkFragment,
                    R.id.frameContainer,
                    talkFragment.getFragmentTag());
            setToolBar(ACTIONBAR_SHORT);

        }
    }
}
