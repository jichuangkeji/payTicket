package com.weirdotech.payticket.bean;

import java.util.List;

/**
 * Created by Bingo on 17/6/8.
 * 查询记录
 */
public class SearchLog {
    @Override
    public String toString() {
        return "[status_code:" + status_code
                + ",total: " + total
                + ", per_page:" + per_page
                + ", current_page:" + current_page + "]";
    }
    /**
     * status_code : 200
     * total : 26
     * per_page : 50
     * current_page : 1
     * last_page : 1
     * next_page_url : null
     * prev_page_url : null
     * data : [{"created_at":"2017-06-04 07:31:23","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"75.00"},{"created_at":"2017-06-04 07:30:54","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"75.00"},{"created_at":"2017-06-01 18:07:42","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-06-01 17:27:21","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-06-01 16:42:10","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-06-01 16:30:06","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-06-01 16:27:19","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-06-01 16:21:12","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-06-01 16:21:01","plate":"88882","ticket":null,"ticket_count":0,"pay_sum":"0.00"},{"created_at":"2017-06-01 16:04:21","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-31 18:00:03","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-31 17:55:46","plate":"4444","ticket":null,"ticket_count":0,"pay_sum":"0.00"},{"created_at":"2017-05-31 17:54:57","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-31 17:54:51","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-31 17:51:25","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-31 17:22:54","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-31 17:16:28","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-31 17:03:06","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-31 04:45:53","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-29 13:35:44","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-29 10:09:19","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-27 06:59:59","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-27 05:24:12","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-27 03:13:52","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-27 00:19:10","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"},{"created_at":"2017-05-27 00:17:06","plate":"8888","ticket":null,"ticket_count":1,"pay_sum":"65.00"}]
     */


    private int status_code;
    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private Object next_page_url;
    private Object prev_page_url;
    /**
     * created_at : 2017-06-04 07:31:23
     * plate : 8888
     * ticket : null
     * ticket_count : 1
     * pay_sum : 75.00
     */

    private List<SearchLogItem> data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public Object getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(Object next_page_url) {
        this.next_page_url = next_page_url;
    }

    public Object getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(Object prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public List<SearchLogItem> getData() {
        return data;
    }

    public void setData(List<SearchLogItem> data) {
        this.data = data;
    }
}
