package com.weirdotech.payticket.utils.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.CallLogField.DATE;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.CallLogField.DURATION;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.CallLogField.NAME;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.CallLogField.NUMBER;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.CallLogField.TYPE;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.QueryType.GET_CONTACT_BY_LOOKUP;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.QueryType.GET_CONTACT_BY_NUMBER;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.QueryType.GET_DO_SEARCH;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.QueryType.GET_PHOTO_BY_LOOKUP;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.QueryType.GET_SEARCH_RESULT;
import static com.weirdotech.payticket.utils.contact.ContactProviderUtils.QueryType.IS_BLACK;

public class ContactProviderUtils {
    private static final String TAG = ContactProviderUtils.class.getSimpleName();
    public static final String HOST = "content://";
    public static final String AUTHORITY = "com.starnet.contactservice.contact.provider";
    public static final String SEARCH_PATH = "/search";
    public static final String CONTACT_PATH = "/change";
    public static final Uri SEARCH_RESULT_URI = Uri.parse(HOST + AUTHORITY + SEARCH_PATH);
    public static final Uri CHANGE_URI = Uri.parse(HOST + AUTHORITY + CONTACT_PATH);

    public static final String LOCAL = "local";
    public static final String CALLLOG = "calllog";
    public static final String EAB = "eab";

    public static final Uri URI = Uri.parse(HOST + AUTHORITY);

    public static final String LOCAL_CHANGE_TYPE        = "localChange";
    public static final String EAB_CHANGE_TYPE          = "eabChange";
    public static final String EAB_FAVORITE_CHANGE_TYPE = "eabFavoriteChange";

    public static class UpdateType {
        public static final String INSERT_CALLLOG   = "insertCallLog";
    }

    public static class QueryType {
        public static final String IS_BLACK   = "isBlack";
        public static final String GET_CONTACT_BY_LOOKUP = "getContactByLookup";
        public static final String GET_CONTACT_BY_NUMBER = "getContactByNumber";

        public static final String GET_PHOTO_BY_LOOKUP = "getPhotoByLookup";

        public static final String GET_DO_SEARCH = "doSearch";
        public static final String GET_SEARCH_RESULT = "getSearchResult";
    }

    public class LookupPrefix {
        public static final String CALL_LOG_PREF = "calllog_";
        public static final String LOCAL_CONTACT_PREF = "local_contact_";
        public static final String EAB_LOG_PREF = "eab_";
    }

    public enum CalllogType {
        MISSED_TYPE,
        DIALED_TYPE,
        RECEIVED_TYPE,
        ALL_TYPE,
        ALL_TYPE_SORTED_BY_TYPE;

        CalllogType() {
        }
    }

    //Insert CallLog
    public class CallLogField {
        public static final String NUMBER = "number";
        public static final String NAME = "name";
        public static final String DATE = "date";
        public static final String TYPE = "type";
        public static final String DURATION = "duration";
    }

    public static ContactItem getContactByNumber(Context context, String number) {
        Uri uri = Uri.parse(HOST + AUTHORITY + "/" + GET_CONTACT_BY_NUMBER);
        Cursor cursor =  context.getContentResolver().query(uri,
                new String[]{GET_CONTACT_BY_NUMBER}, null, new String[]{number}, null);

        ContactItem contactItem = null;

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String jsonStr = cursor.getString(cursor.getColumnIndex(GET_CONTACT_BY_NUMBER));
            Gson gson = new Gson();
            contactItem = gson.fromJson(jsonStr, ContactItem.class);
            cursor.close();
        }

        return contactItem;
    }


    public static ContactItem getContactByLookup(Context context, String lookUp) {
        Uri uri = Uri.parse(HOST + AUTHORITY + "/" + GET_CONTACT_BY_LOOKUP);
        Cursor cursor =  context.getContentResolver().query(uri,
                new String[]{GET_CONTACT_BY_LOOKUP}, null, new String[]{lookUp}, null);

        ContactItem contactItem = null;

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String jsonStr = cursor.getString(cursor.getColumnIndex(GET_CONTACT_BY_LOOKUP));
            Gson gson = new Gson();
            contactItem = gson.fromJson(jsonStr, ContactItem.class);
            cursor.close();
        }

        return contactItem;
    }

    public static boolean isBlack(Context context, String number) {
        Uri uri = Uri.parse(HOST + AUTHORITY + "/" + IS_BLACK);

        Cursor cursor = context.getContentResolver().query(uri,
                new String[]{IS_BLACK}, null, new String[]{number}, null);

        boolean isBlack = false;

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            isBlack = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(IS_BLACK)));
            cursor.close();
        }

        return isBlack;
    }


    public static int insertCalllog(Context context, String number, String name,
                                    long date, CalllogType type, long duration) {

        Uri uri = Uri.parse(HOST + AUTHORITY + "/" + UpdateType.INSERT_CALLLOG);

        ContentValues values = new ContentValues();
        values.put(NUMBER, number);
        values.put(NAME, name);
        values.put(DATE, date);
        values.put(TYPE, type.name());
        values.put(DURATION, duration);
        return context.getContentResolver().update(uri, values, null, null);
    }

    public static Bitmap getPhotoByLookup(Context context, String lookup) {
        Uri uri = Uri.parse(HOST + AUTHORITY + "/" + GET_PHOTO_BY_LOOKUP);
        Bitmap contactPhoto = null;

        Cursor cursor = context.getContentResolver().query(uri,
                new String[]{GET_PHOTO_BY_LOOKUP}, null, new String[]{lookup}, null);

        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            contactPhoto = getPicFromBytes(cursor.getBlob(cursor.getColumnIndex(GET_PHOTO_BY_LOOKUP)), null);
            cursor.close();
        }
        return contactPhoto;
    }

    public static void startSearch(Context context, String searchKey, String searchType) {
        Uri uri = Uri.parse(HOST + AUTHORITY + "/" + GET_DO_SEARCH);

        context.getContentResolver().query(uri,
                new String[]{GET_DO_SEARCH}, null, new String[]{searchKey, searchType}, null);

    }

    public static void getSearchResult(Uri uri, SearchMrg.SearchResult result) {
        String[] paths = uri.getPath().split("/");

        if(paths != null) {
            result.searchType = paths[2];
            result.searchKey = paths[3];
            result.itemsJson = paths[4];
        }

    }

    public static List<SearchItem> getSearchResult(String itemsJson) {
        Gson gson = new Gson();
        return gson.fromJson(itemsJson, new TypeToken<List<SearchItem>>(){}.getType());
    }

    public static List<SearchItem> getSearchResult(Context context, String searchKey, String searchType) {
        Uri uri = Uri.parse(HOST + AUTHORITY + "/" + GET_SEARCH_RESULT);

        Cursor cursor = context.getContentResolver().query(uri,
                new String[]{GET_SEARCH_RESULT}, null, new String[]{searchKey, searchType}, null);

        List<SearchItem> searchItems = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String jsonStr = cursor.getString(cursor.getColumnIndex(GET_SEARCH_RESULT));
            Gson gson = new Gson();
            searchItems = gson.fromJson(jsonStr, new TypeToken<List<SearchItem>>(){}.getType());
            Log.d(TAG, "getSearchResult: jsonStr: " + jsonStr);

            if(searchItems != null) {
                Log.d(TAG, "getSearchResult: searchItems: " + searchItems);
            }
            cursor.close();
        }


        return searchItems;
    }

    /**
     * 把Bitmap转Byte
     */

    public static byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 将字节数组转换为ImageView可调用的Bitmap对象
     * @param bytes
     * @param opts
     * @return Bitmap
     */
    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }



}
