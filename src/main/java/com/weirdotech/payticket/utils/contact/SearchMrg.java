package com.weirdotech.payticket.utils.contact;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;

import com.weirdotech.payticket.utils.Log;

import java.util.List;

import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.*;

public class SearchMrg {
    private Context mContext;
    private SearchCallback mCallback;

    public SearchMrg(Context context) {
        mContext = context;

        context.getContentResolver().registerContentObserver(SEARCH_CHANGE_URI, true, new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                String searchKey = uri.getQuery();
                String searchType = uri.getFragment();

                Log.e("", " testhb uri: " + uri
                        + ", searchKey: " + searchKey
                        + ",searchType: " + searchType);


                List<SearchItem> searchItems =
                        ContactProviderUtils.getSearchResult(mContext, searchKey, searchType);

                if(mCallback != null) {
                    mCallback.onSearchFinish(searchKey, searchType, searchItems);
                }


            }
        });
    }

    public void startSearch(String searchKey, String searchType) {
        ContactProviderUtils.startSearch(mContext, searchKey, searchType);
    }

    public void addCallback(SearchCallback callback) {
        mCallback = callback;
    }

    public interface SearchCallback {
        void onSearchFinish(String searchKey, String searchType, List<SearchItem> searchItems);
    }

}
