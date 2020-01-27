package co.id.githubusers.ui.main;

import java.util.List;

import co.id.githubusers.data.ResultData;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class MainContract {

    public interface View{
        void setAdapter(List<ResultData> resultData);
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener{
        void getData(int perPage, int page, String keyword);
        void saveData(List<ResultData> resultDataList);
    }
}
