package com.weirdotech.payticket.utils.contact;


public class CalllogItem extends BaseContactItem {
    private CalllogType mType;
    private long mDate;
    private long mDuration;
    private ContactItem mContact;


    public CalllogItem(long id, CalllogType type, long date, long duration) {
        super(id, LookupPrefix.CALL_LOG_PREF + id);
        mType = type;
        mDate = date;
        mDuration = duration;
    }

    public void addChildren(CalllogItem child) {
        if (child.mDate > mDate) {
            addNumber(child.getNumber());
            mDate = child.mDate;
        }
        super.addChildren(child);
    }

    @Override
    public String getNumber() {
        if (mNumbers.size() > 0) {
            return mNumbers.get(mNumbers.size() - 1);
        }
        return "8899";
    }

    @Override
    public boolean isBlack() {
        int childCount = mChildrens.size();
        if(childCount > 0) {
            mIsBlack = mChildrens.get(childCount -1).mIsBlack;
        }
        return mIsBlack;
    }

    @Override
    public void setBlack(boolean isBlack) {
        super.setBlack(isBlack);

        for(BaseContactItem item: mChildrens) {
            item.setBlackWithoutUpdate(mIsBlack);
        }
    }

    public CalllogType getType() {
        return mType;
    }

    public long getDuration() {
        return mDuration;
    }

    public long getDate() {
        return mDate;
    }

    public boolean hasContact() {
        return mContact != null;
    }

    public void setContact(ContactItem contact) {
        mContact = contact;
        if (contact != null) {
            setName(contact.getName());
            setSortKey(contact.getSortKey(), contact.getNameIndex(), contact.getPinyinIndex());
        }
    }

    public ContactItem getContact() {
        return mContact;
    }

    public void update(CalllogItem calllog) {
        mName = calllog.mName;
        mNumbers = calllog.mNumbers;
        mContact = calllog.mContact;
        mDate = calllog.mDate;
        mType = calllog.mType;
        mDuration = calllog.mDuration;
        mId = calllog.mId;
        mPhotoId = calllog.mPhotoId;
        mIsBlack = calllog.mIsBlack;
        setSortKey(calllog.getSortKey(), calllog.getNameIndex(), calllog.getPinyinIndex());
    }

    @Override
    public String toString() {
        return "CalllogItem{" +
                "Child:" + getSize() +
                "Name:" + getName() +
                "Number:" + getNumber() +
                "Type:" + getType() +
                "hasContact:" + hasContact() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
