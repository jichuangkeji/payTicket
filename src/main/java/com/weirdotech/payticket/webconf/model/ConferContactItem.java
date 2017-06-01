package com.weirdotech.payticket.webconf.model;

import com.weirdotech.payticket.utils.contact.BaseContactItem;

/**
 * Created by Bingo on 17/5/31.
 */
public class ConferContactItem extends BaseContactItem{


    private boolean mIsGroup;
    private long mDepId;     //部门id
    private long mPid = -1;  //父部门的id

    public ConferContactItem(long id, String lookup) {
        super(id, lookup);
    }


    public boolean isIsGroup() {
        return mIsGroup;
    }

    public void setGroup(boolean mIsGroup) {
        this.mIsGroup = mIsGroup;
    }

    public long getDepId() {
        return mDepId;
    }

    public void setDepId(long depId) {
        this.mDepId = depId;
    }

    public long getParentId() {
        return mPid;
    }

    public void setParentId(long mPId) {
        this.mPid = mPId;
    }
}
