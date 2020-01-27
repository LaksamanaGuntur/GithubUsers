package co.id.githubusers.model;

import co.id.githubusers.data.DaoSession;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class BaseModel {
    protected final DaoSession mDaoSession;

    public BaseModel(DaoSession daoSession) {
        mDaoSession = daoSession;
    }
}
