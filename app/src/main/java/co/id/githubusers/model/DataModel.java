package co.id.githubusers.model;

import java.util.List;

import co.id.githubusers.data.DaoSession;
import co.id.githubusers.data.ResultData;
import co.id.githubusers.data.ResultDataDao;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class DataModel extends BaseModel {
    private ResultDataDao mResultDataDao;

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataDao = daoSession.getResultDataDao();
    }

    public void insertData(ResultData resultData){
        mResultDataDao.insertOrReplace(resultData);
    }

    public List<ResultData> getAllData(){
        return mResultDataDao.loadAll();
    }

    public void deleteDataList() {
        mResultDataDao.deleteAll();
    }
}
