package co.id.githubusers.network;

import java.util.List;

import co.id.githubusers.data.ResultData;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public interface NetworkService {
    @GET("search/users")
    Flowable<ApiResponse> getData(@Header("Authorization") String token,
                                  @Query("per_page") int perPage,
                                  @Query("page") int page,
                                  @Query("q") String keyword);

}
