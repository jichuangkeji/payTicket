package com.weirdotech.payticket.utils.contact;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BaseContactItem implements IContactSimple {
    private static final String TAG = BaseContactItem.class.getCanonicalName();
    public static final String LOCAL_PHOTO = "photo-";

    protected long mId;                 //row id
    protected String mName;
    protected Object mTag;
    protected List<String> mNumbers = new ArrayList<>();
    protected List<BaseContactItem> mChildrens = new ArrayList<>();
    protected boolean mIsFavorite = false;
    protected boolean mIsCamera = false;
    protected String mSortKey;               //拼音
    protected String mAbbrKey;               //拼音首字母 缩写
    protected List<Integer> mNameIndex;     //中文对应的拼音的缩写 下标
    protected List<Integer> mSortIndex;     //中文对应的拼音首字母 的下标
    protected String mLookup;
    protected String mLocalRawLookup;
    protected long mPhotoId;
    protected String mPhotoUri;
    protected boolean mIsBlack = false;

    public BaseContactItem(long id, String lookup) {
        mId = id;
        mLookup = lookup;
    }

    public void setBlackWithoutUpdate(boolean isBlack) {
        mIsBlack = isBlack;
    }

    public void setBlack(boolean isBlack) {
        // TODO: 17/5/8  
//        logger.d(TAG, " setBlack name:" + getName()
//                + ", number: "+ getNumber()
//                + ", mIsBlack:" + mIsBlack
//                + ", isBlack:" + isBlack);
//
//        if (mIsBlack == isBlack)
//            return;
//
//        mIsBlack = isBlack;
//
//        if (mNumbers.size() > 0) {
//            if (isBlack) {
//                ContactHelper.addBlack(mNumbers);
//            } else {
//                ContactHelper.delBlack(mNumbers);
//            }
//        }
    }

    public boolean isBlack() {
        return mIsBlack;
    }

    /**
     * 引用 contactservice模块的 第三方应用
     * 利用该方式来查找 ContactItem
     * @return
     */
    public String getLookup() {
        return mLookup;
    }

    /**
     * contactservice模块 内部查找的使用
     * 引用 contactservice模块的 应用无法使用该方法查找到ContactItem
     * @return
     */
    public String getLocalRawLookup() {
        return mLocalRawLookup;
    }

    public long getId() {
        return mId;
    }

    public void addNumber(String number) {
        mNumbers.add(number);
    }

    public void clearNumbers() {
        mNumbers.clear();
    }

    public boolean isGroup() {
        return mChildrens.size() > 0;
    }

    public BaseContactItem get(int i) {
        return mChildrens.get(i);
    }

    public void addChildren(BaseContactItem child) {
        mChildrens.add(child);
    }

    public List<BaseContactItem> getChildrens() {
        return mChildrens;
    }

    public int getSize() {
        return mChildrens.size();
    }

    public void setSortKey(String sortKey, List<Integer> nameIndex, List<Integer> sortIndex) {
        final StringBuilder sb = new StringBuilder();

        mSortKey = sortKey.toUpperCase();
        mNameIndex = nameIndex;
        mSortIndex = sortIndex;
        for(Integer i : mSortIndex) {
            sb.append(mSortKey.charAt(i));
        }
        mAbbrKey = sb.toString();
    }

    public void setName(String name) {
        mName = name;
    }

    public void setCamera(boolean isCamera) {
        mIsCamera = isCamera;
    }

    public boolean isCamera() {
        return mIsCamera;
    }

    public void setFavorite(boolean isFavorite) {
        mIsFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public void setPhotoId(long photoId) {
        mPhotoId = photoId;
    }

    public void setPhotoUri(String uri) {
        mPhotoUri = uri;
    }

    public long getPhotoId() {
        return mPhotoId;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getNumber() {
        if (mNumbers.size() == 0)
            return "";
        return mNumbers.get(0);
    }

    @Override
    public List<String> getNumbers() {
        return mNumbers;
    }

    @Override
    public boolean hasPhoto() {
        if (mPhotoId > 0)
            return true;
        return false;
    }

    @Override
    public String getPhotoKey() {
        if (mPhotoId > 0) {
            return LOCAL_PHOTO + mPhotoId;
        }
        return null;
    }

    public String getNumbersString() {
        String numbers = "";
        synchronized (mNumbers) {
            for (String value : mNumbers) {
                numbers = numbers + value.toString() + ",";
            }
        }
        return numbers;
    }

    private Bitmap getBitmapById(ContentResolver resolver, long id) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        Log.d("testphoto", "getBitmapById: .uri : " + uri);
        Bitmap contactPhoto = null;
        InputStream input = null;

        try {
            input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
            contactPhoto = BitmapFactory.decodeStream(input);
            Log.d("testphoto", "getBitmapById: .contactPhoto : " + contactPhoto);
        } catch (Exception e) {
            Log.d("testphoto", "getBitmapById: error ");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                }
            }
        }
        return contactPhoto;
    }

    @Override
    public Bitmap getPhotoBitmap(ContentResolver resolver) {
        Log.d("testphoto", "getPhotoBitmap: .mPhotoId : " + mPhotoId);
        if (mPhotoId > 0) {
            return getBitmapById(resolver, mId);
        }
        return null;
    }

    @Override
    public String getSortKey() {
        return mSortKey;
    }

    @Override
    public String getPinyin() {
        return mSortKey;
    }

    @Override
    public char getHead() {
        if (getPinyin() != null) {
            return Character.isLetter(getPinyin().charAt(0)) ? getPinyin().charAt(0) : '#';
        }
        return '#';
    }

    @Override
    public String getPinyinAbbr() {
        return mAbbrKey;
    }

    @Override
    public List<Integer> getNameIndex() {
        return mNameIndex;
    }

    @Override
    public List<Integer> getPinyinIndex() {
        return mSortIndex;
    }

    @Override
    public SpannableStringBuilder getNameSpan(ForegroundColorSpan span) {
        return new SpannableStringBuilder(getName());
    }

    @Override
    public SpannableStringBuilder getNumberSpan(ForegroundColorSpan span) {
        return new SpannableStringBuilder(getNumber());
    }

    @Override
    public void setTag(Object tag) {
        mTag = tag;
    }

    @Override
    public Object getTag() {
        return mTag;
    }

    @Override
    public int compareTo(Object another) {
        if (getName() == null) {
            return 0;
        }
        IContactSimple contactItem = (IContactSimple) another;
        return getName().equals(contactItem.getName()) ? 0 : 1;
    }
}
