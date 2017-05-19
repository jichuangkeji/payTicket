package com.weirdotech.payticket.utils.contact;

public class ContactItem extends BaseContactItem {
    private static final String TAG = "ContactItem";

    protected ContactType mType;
    protected String mRingtone = null;

    protected boolean mReadOnly = false;

    public ContactItem(long id, String rawLookup, ContactType type) {
        super(id, LookupPrefix.LOCAL_CONTACT_PREF + id);
        mType = type;
        mLocalRawLookup = rawLookup;

    }

    public void update(ContactItem contact) {
        mType = contact.mType;
        mIsBlack = contact.mIsBlack;
        mRingtone = contact.mRingtone;
        mPhotoId = contact.mPhotoId;
        mReadOnly = contact.mReadOnly;
        mPhotoUri = contact.mPhotoUri;
        mIsCamera = contact.mIsCamera;
        mIsFavorite = contact.mIsFavorite;
//        mNumbers = new ArrayList<>(contact.mNumbers);  //  在  fillContactDetail()函数中进行赋值
        mName = contact.mName;

    }

    public void setType(ContactType type) {
        mType = type;
    }

    public ContactType getType() {
        return mType;
    }

    public void setRingtone(String ringtone) {
        mRingtone = ringtone;
    }

    public void setReadOnly(boolean readOnly) {
        mReadOnly = readOnly;
    }

    public boolean isReadOnly() {
        return mReadOnly;
    }

    @Override
    public String toString() {
        return "{ContactItem<displayName=" + mName +
                ",mSortKey=" + mSortKey +
                ",lookup=" + mLookup +
                ",localRawLookup=" + mLocalRawLookup +
                ",id=" + mId +
                ",isCamera=" + mIsCamera +
                ",isFavorite=" + mIsFavorite +
                ",photoUri=" + mPhotoUri +
                ",photoId=" + mPhotoId +
                ",numbers=" + getNumbersString()
                 + "}";
    }
}
