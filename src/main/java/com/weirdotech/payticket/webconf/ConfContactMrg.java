package com.weirdotech.payticket.webconf;

import android.content.Context;

import com.weirdotech.payticket.utils.contact.BaseContactItem;
import com.weirdotech.payticket.webconf.model.ConferContactItem;
import com.weirdotech.payticket.webconf.model.ConferEnterprise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Bingo on 17/5/31.
 */
public class ConfContactMrg {
    private static final String TAG = ConfContactMrg.class.getSimpleName();
    private IConfContactService mService;
    private LoginResult mResult;

    private ConferEnterprise mCompanyInfo = null;
    private List<ConferContactItem> mContactGroups = new ArrayList<>();
    private HashMap<String, ConferContactItem> mContactGroupMap = new LinkedHashMap<>();
    private HashMap<String, ConferContactItem> mContactNumberMap = new LinkedHashMap<>();
    private HashMap<String, ConferContactItem> mContactLookupMap = new LinkedHashMap<>();
    private Map<String, List<BaseContactItem>> mNumberListMap = new HashMap<>();
    private List<ConferContactItem> mAllContacts = new ArrayList<>();

    public static ConfContactMrg createInstance(Context context) {
        return new ConfContactMrg(context);
    }

    private ConfContactMrg(Context context) {
        mService = RetrofitWrapper.getInstance().create(IConfContactService.class);
    }

    public void login(String mobile, String password, Subscriber<LoginResult> subscriber) {

        mService.login(mobile, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void synEnterprise(String token, SynBody body, Subscriber<SynResult> subscriber) {
        mService.synEnterprise(token, body)
//                .flatMap(mParserFun1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
//                .subscribe(mContactSubscriber);
    }

    private Func1<SynResult, Observable<ConferContactParser>> mParserFun1 =
            new Func1<SynResult, Observable<ConferContactParser>>() {
        @Override
        public Observable<ConferContactParser> call(SynResult synResult) {
            // TODO: 17/5/31 这里在工作线程，先进行数据的转化，在进行数据库的刷新
            return Observable.just(ConferContactParser.parser(synResult));
        }
    };


    private Subscriber<ConferContactParser> mContactSubscriber = new Subscriber<ConferContactParser>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ConferContactParser parser) {
            // TODO: 17/5/31 这里进行数据的刷新

        }
    };



}
