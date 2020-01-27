package co.id.githubusers.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.githubusers.CoreApp;
import co.id.githubusers.listener.PaginationScrollListener;
import co.id.githubusers.R;
import co.id.githubusers.adapter.ListAdapter;
import co.id.githubusers.data.ResultData;
import co.id.githubusers.di.module.MainActivityModule;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private static final String TAG = "MainActivity";

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.et_search)
    EditText mSearchEditText;

    private MainContract.UserActionListener mUserActionListener;
    private LinearLayoutManager mLayoutManager;
    private ListAdapter mListAdapter;
    private Timer timer;

    private static final int TOTAL_PAGES = 5;
    private static final int PER_PAGE = 15;
    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupActivityComponent();
        mUserActionListener = mainPresenter;
        mainPresenter.setView(this);
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void setAdapter(List<ResultData> resultData) {
        if (currentPage == 1) {
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mListAdapter = new ListAdapter(resultData, this);
            mRecyclerView.setAdapter(mListAdapter);

            mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
                @Override
                protected void loadMoreItems() {
                    Log.d(TAG, "currentPage = " + currentPage);
                    currentPage += 1;

                    if (currentPage != TOTAL_PAGES)
                        mUserActionListener.getData(PER_PAGE, currentPage, mSearchEditText.getText().toString());
                    else
                        isLastPage = true;
                }

                @Override
                public int getTotalPageCount() {
                    return TOTAL_PAGES;
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });

        } else {
            mListAdapter.addAll(resultData);
        }

        hideProgressBar();
    }

    @Override
    public void initializeData() {
        setupOnFocusListener();
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        isLoading = true;
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        isLoading = false;
    }

    private void setupOnFocusListener() {
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                if (mSearchEditText.getText().toString().length() > 1) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    currentPage = 1;
                                    mUserActionListener.getData(PER_PAGE, currentPage, mSearchEditText.getText().toString());
                                }
                            });

                            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            in.hideSoftInputFromWindow(mSearchEditText.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }, 2000);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                if (timer != null) {
                    timer.cancel();
                }
            }
        });
    }
}
