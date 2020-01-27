package co.id.githubusers.repository;

import java.util.List;

import co.id.githubusers.data.ResultData;
import co.id.githubusers.helper.Constant;
import co.id.githubusers.network.ApiResponse;
import co.id.githubusers.network.NetworkService;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class MainRepository extends BaseRepository {
    public MainRepository(NetworkService networkService) {
        super(networkService);
    }

    /**
     * Get Data
     * */
    public Flowable<ApiResponse> getData(int perPage, int page, String keyword) {
        return networkService.getData("token " + Constant.API_KEY, perPage, page, keyword)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
