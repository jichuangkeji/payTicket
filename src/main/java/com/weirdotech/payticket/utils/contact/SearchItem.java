package com.weirdotech.payticket.utils.contact;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import java.util.List;


/**
 * Created by joans on 16-11-15.
 */
public class SearchItem implements IContactSimple {
    private static final String TAG = "SearchItem";

    @Override
    public int compareTo(Object another) {
        if (getName() == null) {
            return -1;
        }
        if (another instanceof IContactSimple) {
            return getName().equals(((IContactSimple) another).getName()) ? 0 : 1;
        }

        return -1;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private static class FixedData {
        private BaseContactItem mContactSimple;
        // 拼音和首字母 T9 数字
        private String mPinyinT9;
        private String mPinyinAbbrT9;
        private String mNumber;
    }

    private FixedData mFixedData;

    /*
     * 匹配的分数 :  0 ~ 100
     * 匹配程度越高，分数越高
     */
    private int scored;

    private int mNumberMatchStart = -1;
    private int mNumberMatchEnd = -1;
    private int mNameMatchStart = -1;
    private int mNameMatchEnd = -1;

    public SearchItem(BaseContactItem contactSimple) {
        mFixedData = new FixedData();
        mFixedData.mContactSimple = contactSimple;
        initData();
    }

    public SearchItem(SearchItem old) {
        mFixedData = old.mFixedData;
        initData();
    }

    private void initData() {
        scored = 0;

//        mFixedData.mPinyinT9 = SearchUtils.getT9(getPinyin());
//        mFixedData.mPinyinAbbrT9 = SearchUtils.getT9(getPinyinAbbr());
    }

    @Override
    public String getSortKey() {
        return mFixedData.mContactSimple.getSortKey();
    }

    @Override
    public String getName() {
        return mFixedData.mContactSimple.getName();
    }

    @Override
    public void addNumber(String number) {
        mFixedData.mNumber = number;
    }

    @Override
    public String getNumber() {
        return mFixedData.mNumber;
    }


    @Override
    public String getPinyin() {
        return mFixedData.mContactSimple.getPinyin();
    }

    @Override
    public String getPinyinAbbr() {
        return mFixedData.mContactSimple.getPinyinAbbr();
    }

    @Override
    public List<Integer> getNameIndex() {
        return mFixedData.mContactSimple.getNameIndex();
    }

    @Override
    public List<Integer> getPinyinIndex() {
        return mFixedData.mContactSimple.getPinyinIndex();
    }

    @Override
    public char getHead() {
        if (getPinyin() != null) {
            return Character.isLetter(getPinyin().charAt(0)) ? getPinyin().charAt(0) : '#';
        }
        return '#';
    }

    @Override
    public List<String> getNumbers() {
        return mFixedData.mContactSimple.getNumbers();
    }

    @Override
    public boolean hasPhoto() {
        return mFixedData.mContactSimple.hasPhoto();
    }

    @Override
    public String getPhotoKey() {
        return mFixedData.mContactSimple.getPhotoKey();
    }

    @Override
    public Bitmap getPhotoBitmap(ContentResolver resolver) {
        return mFixedData.mContactSimple.getPhotoBitmap(resolver);
    }

    @Override
    public SpannableStringBuilder getNameSpan(ForegroundColorSpan span) {
        if (mNameMatchStart >= 0) {
            SpannableStringBuilder spannable = new SpannableStringBuilder(getName());
            spannable.setSpan(span, mNameMatchStart, mNameMatchEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannable;
        }
        return  mFixedData.mContactSimple.getNameSpan(span);
    }

    @Override
    public SpannableStringBuilder getNumberSpan(ForegroundColorSpan span) {
        if (mNumberMatchStart >= 0) {
            SpannableStringBuilder spannable = new SpannableStringBuilder(getNumber());

            spannable.setSpan(span, mNumberMatchStart, mNumberMatchEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannable;
        }
        return new SpannableStringBuilder(getNumber());
    }

    @Override
    public void setTag(Object tag) {

    }

    @Override
    public Object getTag() {
        return mFixedData.mContactSimple;
    }

    public boolean isCallLogSearchItem() {
        if(getTag() instanceof CalllogItem) {
            return true;
        }
        return false;
    }

    public String getPinyinT9() {
        return mFixedData.mPinyinT9;
    }

    public String getPinyinAbbrT9() {
        return mFixedData.mPinyinAbbrT9;
    }

    public int getScored() {
        return scored;
    }

    public boolean setNumberMatch(int start, int end) {
        mNumberMatchStart = start;
        mNumberMatchEnd = end;
        scored += 1;
        return true;
    }

    public boolean setAbbrMatch(int start, int end) {
        mNameMatchStart = getNameIndex().get(start);
        mNameMatchEnd = getNameIndex().get(end-1) + 1;
        scored += 10;
        return true;
    }

    public boolean setFullNameMatch(int start, int end) {
        List<Integer> sortIndex = getPinyinIndex();
        int nameIndexStart = sortIndex.indexOf(start);
//        logger.d(TAG , " setFullNameMatch nameIndexStart=" + nameIndexStart + ", start=" + start + ", end=" + end);
//        logger.d(TAG, "sort index : " + sortIndex.size() + ", name " + getName() + ", pinyin " + getPinyin());
//        for (Integer i : sortIndex) {
//            logger.d(TAG, " i = "  + i);
//        }
//        logger.d(TAG, " =========== ");
        if (nameIndexStart < 0) {

            return false;
        }

        mNameMatchStart = getNameIndex().get(nameIndexStart);
        scored += 100;

        // 找到姓名匹配的末尾
        int nameIndexEnd = nameIndexStart + 1;
        while (nameIndexEnd < sortIndex.size() && end > sortIndex.get(nameIndexEnd)) {
            nameIndexEnd++;
        }

        mNameMatchEnd = getNameIndex().get(nameIndexEnd-1) + 1;

        // 最后匹配的字母也高亮
        int sortEnd = sortIndex.get(nameIndexEnd-1) + 1;
        int offset = end - sortEnd;
        if (offset > 0) {
            String str = getPinyin().substring(sortEnd, sortEnd+offset);
            boolean match = getName().substring(mNameMatchEnd).toUpperCase().startsWith(str);
            if (match) {
                mNameMatchEnd += offset;
            }
        }
        return true;
    }

    public boolean setChineseMatch(int start, int end) {
        mNameMatchStart = start;
        mNameMatchEnd = end;
        scored += 1000;
        return true;
    }


    @Override
    public String toString() {
        return "SearchItem{name=" + getName() +
                ",Pinyin=" + getPinyin() +
                ",PinyinAbbr=" + getPinyinAbbr() +
                ",PinyinT9=" + getPinyinT9() +
                ",PinyinAbbrT9=" + getPinyinAbbrT9() +
                ",scored=" + scored +
                "}";
    }

}
