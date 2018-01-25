package com.ulling.upstar.view;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.ulling.lib.core.base.QcBaseLifeActivity;
import com.ulling.lib.core.util.QcActivityUtils;
import com.ulling.lib.core.util.QcBackPressClose;
import com.ulling.lib.core.util.QcLog;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.ActivityMainBinding;
import com.ulling.upstar.manager.MenuManager;
import com.ulling.upstar.model.Menu;

import java.util.ArrayList;

/**
 * 메인화면
 */
public class MainActivity extends QcBaseLifeActivity {

    public static final int FRAG_TYPE_MARKET_PRICE = 1;
    public static final int FRAG_TYPE_TALK = 2;
    public static final int FRAG_TYPE_COIN_CALCULATOR = 3;

    public static final int ACTIONBAR_SHORT = 0;
    public static final int ACTIONBAR_LONG = 1;

    private ActivityMainBinding viewBinding;
    private ArrayList<Menu> menuItems = new ArrayList<Menu>();

    private DrawerMenuFragment mDrawerMenuFragment;
    private MarketPriceFragment marketPriceFragment;
    private TalkFragment talkFragment;
    private CoinCalculatorFragment coinCalculatorFragment;

    private float heightDp;

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

        heightDp = getResources().getDimension(R.dimen.p800);
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

        menuItems = MenuManager.getInstance(qCon).setMenuData();

        setSupportActionBar(viewBinding.includedAppBarMain.toolbar);
        actionBar = getSupportActionBar();


        viewBinding.drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, viewBinding.drawerLayout, viewBinding.includedAppBarMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        viewBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        viewBinding.navView.setNavigationItemSelectedListener(this);
//        setNavigationMenu();

        mDrawerMenuFragment = DrawerMenuFragment.getInstance(viewBinding.drawerLayout);
        mDrawerMenuFragment.setListener(new DrawerMenuFragment.OnMenuListener() {

            @Override
            public void onSelected(Menu menu) {
                if (viewBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
                    viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
                setFragment(menu);

            }
        });

        QcActivityUtils.replaceFragment(getSupportFragmentManager(),
                mDrawerMenuFragment,
                R.id.frameDrawer,
                DrawerMenuFragment.class.getSimpleName());

        setFragment(menuItems.get(1));
    }

    public void setToolBar(int type, String title) {
        if (type == ACTIONBAR_LONG) {
            viewBinding.includedAppBarMain.rlToolbarLay.setVisibility(View.GONE);
            viewBinding.includedAppBarMain.imageCollapsingToolbar.setVisibility(View.VISIBLE);
            viewBinding.includedAppBarMain.collapsingToolbarLayout.setTitle(title);

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) viewBinding.includedAppBarMain.appBarLayout.getLayoutParams();
            lp.height = (int) heightDp;


        } else {
            viewBinding.includedAppBarMain.rlToolbarLay.setVisibility(View.VISIBLE);
            viewBinding.includedAppBarMain.imageCollapsingToolbar.setVisibility(View.GONE);
            viewBinding.includedAppBarMain.toolbarTitle.setText(title);

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) viewBinding.includedAppBarMain.appBarLayout.getLayoutParams();
            lp.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT;
//            lp.height = viewBinding.includedAppBarMain.toolbar.getHeight() + QcUtils.getStatusBarHeight(qCon);
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

    /**
     * 프레그먼트 화면 설정
     *
     * @param menu
     */
    private void setFragment(Menu menu) {
        QcLog.e("setFragment === " + menu.toString());
        if (menu.getFragType() == FRAG_TYPE_MARKET_PRICE) {
            marketPriceFragment.setSubType(menu);
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    marketPriceFragment,
                    R.id.frameContainer,
                    marketPriceFragment.getFragmentTag());
            setToolBar(ACTIONBAR_LONG, menu.getName());

        } else if (menu.getFragType() == FRAG_TYPE_TALK) {
            talkFragment.setSubType(menu);
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    talkFragment,
                    R.id.frameContainer,
                    talkFragment.getFragmentTag());
            setToolBar(ACTIONBAR_SHORT, menu.getName());

        } else if (menu.getFragType() == FRAG_TYPE_COIN_CALCULATOR) {
            coinCalculatorFragment.setSubType(menu);
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    coinCalculatorFragment,
                    R.id.frameContainer,
                    coinCalculatorFragment.getFragmentTag());
            setToolBar(ACTIONBAR_SHORT, menu.getName());

        }
    }
}
