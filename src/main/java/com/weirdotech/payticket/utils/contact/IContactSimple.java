package com.weirdotech.payticket.utils.contact;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import java.util.List;

/**
 * Created by joans on 16-11-19.
 *
 * 联系人， 群组， 通话记录 全部继承这个接口
 *
 */
public interface IContactSimple extends Comparable {
    /*
     *  联系人/群组/通话记录名称
     */
    public String getName();

    /*
     * 联系人号码，当存在多个号码时返回其中一个
     */
    public void addNumber(String number);
    public String getNumber();
    public List<String> getNumbers();


    public boolean hasPhoto();
    public String getPhotoKey();
    public Bitmap getPhotoBitmap(ContentResolver resolver);


    /*
     * 搜索排序相关
     *
     * sortKet : 用来排序的关键字
     */
    public String getSortKey();
    public String getPinyin();
    public String getPinyinAbbr();
    public List<Integer> getNameIndex();
    public List<Integer> getPinyinIndex();
    public char getHead();

    /*
     * 搜索时匹配的姓名或者号码高亮显示
     * UI 应该使用这两个接口获取号码和名称，而不是使用 getName 和 getNumber
     */
    public SpannableStringBuilder getNameSpan(ForegroundColorSpan span);
    public SpannableStringBuilder getNumberSpan(ForegroundColorSpan span);

    /*
     * 搜索时保存原有对象:
     *   ContactItem : 通讯录
     *   CalllogItem : 通话记录
     */
    public void setTag(Object tag);
    public Object getTag();

    public enum ContactType {
        LOCAL,
        EAB
    }

    public class LookupPrefix {
        public static final String CALL_LOG_PREF = "calllog_";
        public static final String LOCAL_CONTACT_PREF = "local_contact_";
        public static final String EAB_LOG_PREF = "eab_";
    }
}
