package co.id.githubusers.repository;

import co.id.githubusers.network.NetworkService;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class BaseRepository {
    protected NetworkService networkService;

    public BaseRepository(NetworkService networkService) {
        this.networkService = networkService;
    }
}
