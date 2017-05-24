package com.weirdotech.payticket.utils.contact;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;

import com.weirdotech.payticket.utils.Log;

import java.util.ArrayList;
import java.util.List;

import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.SEARCH_RESULT_URI;

public class SearchMrg {
    private Context mContext;
    private SearchCallback mCallback;

    public SearchMrg(Context context) {
        this(context, null);
    }

    public SearchMrg(Context context, SearchCallback callback) {
        mContext = context;
        mCallback = callback;
        context.getContentResolver().registerContentObserver(SEARCH_RESULT_URI, true, new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);

                SearchResult result = new SearchResult();
                ContactProviderUtils.getSearchResult(uri, result);
                List<SearchItem> searchItems = ContactProviderUtils.getSearchResult(result.itemsJson);
                searchItems = searchItems != null ? searchItems: new ArrayList<SearchItem>();

                Log.e("", " onChange SearchResult: " + result);
                if (mCallback != null) {
                    mCallback.onSearchFinish(result.searchType, result.searchKey, searchItems);
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
        void onSearchFinish(String searchType, String searchKey, List<SearchItem> searchItems);
    }

    public static class SearchResult {
        public String searchType;
        public String searchKey;
        public String itemsJson;

        @Override
        public String toString() {
            return "[searchType: " + searchType
                    + ", searchKey: " + searchKey
                    + ", itemsJson:" +itemsJson
                    + "]";
        }
    }

}
