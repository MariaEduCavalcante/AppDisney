package com.example.disney;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class MusicAPI extends AsyncTaskLoader<String> {
    private String mQueryString;
    MusicAPI(Context context, String queryString) {
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
        return Connection2.buscaMusica(mQueryString);
    }

    public String loadInBackground3() {
        return Connection2.buscaComposer(mQueryString);
    }

}
