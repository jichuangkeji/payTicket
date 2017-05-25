package com.weirdotech.payticket.constant;

/**
 * Created by Bingo on 17/5/13.
 */
public class RequestConstant {
    public static final String SERVER_URL = "https://www.guufar.com/api/";

    //查询罚单 QUERY
    public static final String QUERY_KEY        = "queryKey";
    public static final String LIST_TICKET_PATH = "ticket_list/plate/{" + QUERY_KEY + "}";

    //用户注册 POST
    public static final String REGISTER_PATH         = "user/register";
    public static final String REGISTER_KEY_EMAIL    = "email";
    public static final String REGISTER_KEY_USERNAME = "username";
    public static final String REGISTER_KEY_PASSWORD = "password";


    //用户注册 POST
    public static final String LOGIN_PATH         = "user/login";
    public static final String LOGIN_KEY_EMAIL    = "email";
    public static final String IS_LOGINED    = "isLogined";

}
