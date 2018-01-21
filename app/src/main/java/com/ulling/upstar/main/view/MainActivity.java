package com.ulling.upstar.main.view;

import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ulling.lib.core.base.QcBaseLifeActivity;
import com.ulling.lib.core.util.QcActivityUtils;
import com.ulling.lib.core.util.QcBackPressClose;
import com.ulling.lib.core.util.QcLog;
import com.ulling.upstar.R;
import com.ulling.upstar.databinding.ActivityMainBinding;

/**
 * 메인화면
 */
public class MainActivity extends QcBaseLifeActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int FRAG_TYPE_MARKET_PRICE = 1;
    public static final int FRAG_TYPE_TALK = 2;
    public static final int FRAG_TYPE_COIN_CALCULATOR = 3;

    public static final int ACTIONBAR_SHORT = 0;
    public static final int ACTIONBAR_LONG = 1;

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

        mDrawerMenuFragment = DrawerMenuFragment.getInstance(viewBinding.drawerLayout);
        mDrawerMenuFragment.setListener(new DrawerMenuFragment.OnMenuListener() {

            @Override
            public void onSelected(int type, int sub) {
                if (viewBinding.drawerLayout.isDrawerOpen(GravityCompat.START))
                    viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
                setFragment(type);
            }
        });

        QcActivityUtils.replaceFragment(getSupportFragmentManager(),
                mDrawerMenuFragment,
                R.id.frameDrawer,
                DrawerMenuFragment.class.getSimpleName());


        setFragment(FRAG_TYPE_MARKET_PRICE);
    }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    private void setFragment(int type) {
        QcLog.e("setFragment === " + type);
        if (type == FRAG_TYPE_MARKET_PRICE) {
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    marketPriceFragment,
                    R.id.frameContainer,
                    marketPriceFragment.getFragmentTag());
            setToolBar(ACTIONBAR_LONG);

        } else if (type == FRAG_TYPE_TALK) {
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    talkFragment,
                    R.id.frameContainer,
                    talkFragment.getFragmentTag());
            setToolBar(ACTIONBAR_SHORT);

        } else if (type == FRAG_TYPE_COIN_CALCULATOR) {
            QcActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    coinCalculatorFragment,
                    R.id.frameContainer,
                    coinCalculatorFragment.getFragmentTag());
            setToolBar(ACTIONBAR_SHORT);
        }
    }
}
