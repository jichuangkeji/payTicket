package com.weirdotech.payticket.constant;

/**
 * Created by Bingo on 17/5/13.
 */
public class RequestConstant {
    //测试用的
    public static final String SERVER_URL = "http://54.144.70.64/api/";
//    public static final String SERVER_URL = "https://www.guufar.com/api/";

    //查询罚单 QUERY
    public static final String SEARCH_KEY = "searchKey";
    //根据车牌
    public static final String LIST_TICKET_PLATE_PATH = "ticket_list/plate/{" + SEARCH_KEY + "}";

    //根据罚单号
    public static final String LIST_TICKET_NUM_PATH = "ticket_list/ticket_num/{" + SEARCH_KEY + "}";

    //用户注册 POST
    public static final String REGISTER_PATH         = "user/register";
    public static final String REGISTER_KEY_EMAIL    = "email";
    public static final String REGISTER_KEY_USERNAME = "username";
    public static final String REGISTER_KEY_PASSWORD = "password";


    //用户注册 POST
    public static final String LOGIN_PATH         = "user/login";

    //用户注销 GET
    public static final String LOGOUT_PATH        = "user/logout";

    //查询记录
    public static final String SEARCH_LOG_PATH = "search_history";

    //add Card
    public static final String CREATE_CARD = "card/add";

}
