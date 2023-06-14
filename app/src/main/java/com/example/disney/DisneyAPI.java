package com.example.disney;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class DisneyAPI extends AsyncTaskLoader<String> {
    private String mQueryString;
    DisneyAPI(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return Connection.buscaPersona(mQueryString);
    }
}
