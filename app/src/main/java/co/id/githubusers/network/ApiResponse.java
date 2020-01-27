package co.id.githubusers.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.id.githubusers.data.ResultData;
import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

@Data
public class ApiResponse {
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<ResultData> items = null;
}
