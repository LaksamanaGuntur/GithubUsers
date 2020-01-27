package co.id.githubusers.ui.main;

import java.lang.ref.WeakReference;
import java.util.List;

import co.id.githubusers.data.ResultData;
import co.id.githubusers.model.DataModel;
import co.id.githubusers.network.ApiResponse;
import co.id.githubusers.repository.MainRepository;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class MainPresenter implements MainContract.UserActionListener {
    private static WeakReference<MainContract.View> mView;
    private static MainRepository mMainRepository;
    private static DataModel mDataModel;

    public MainPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public MainPresenter() {

    }

    public void setView(MainContract.View view) {
        mView = new WeakReference<>(view);
    }

    public MainContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(int perPage, int page, String keyword) {
        if (page == 1)
            mDataModel.deleteDataList();

        getView().showProgressBar();
        mMainRepository.getData(perPage, page, keyword)
                .subscribe(new ResourceSubscriber<ApiResponse>() {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        if (!apiResponse.getItems().isEmpty()) {
                            getView().setAdapter(apiResponse.getItems());
                            saveData(apiResponse.getItems());
                        } else {
                            getView().hideProgressBar();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        //Handle when onErrorResponse From API
                        getView().hideProgressBar();
                        getView().setAdapter(mDataModel.getAllData());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void saveData(List<ResultData> resultDataList) {
        for(ResultData resultData : resultDataList){
            mDataModel.insertData(resultData);
        }
    }
}
