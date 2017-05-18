package com.weirdotech.payticket.activity;

import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.bean.PayTicketInfo;
import com.weirdotech.payticket.service.IBookService;
import com.weirdotech.payticket.service.IPayTicketService;
import com.weirdotech.payticket.service.RetrofitWrapper;
import com.weirdotech.widgets.topbar.TopBarBaseActivity;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends TopBarBaseActivity {
    private static final String TAG  = MainActivity.class.getSimpleName();
    @Bind(R.id.queryBtn)
    protected Button mQueryBtn;

    @Bind(R.id.queryKey)
    protected EditText mQueryKey;
    private IBookService mBookService;
    private TextView mTv;

    @Override
    protected int getContentView() {
        return R.layout.main_activity_layout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("罚单搜索");

        setTopLeftButton(R.drawable.back_icon, new OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "左边按钮被点击", Toast.LENGTH_SHORT).show();
            }
        });

        setTopRightButton("Refresh", R.drawable.refresh_icon, new OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "Refresh按钮被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.queryBtn)
    public void queryKeyBtn(Button btn) {
        final String bookId = mQueryKey.getText().toString().trim();

//        Call<Book> callBook = mBookService.getBook(bookId);
//
//        callBook.enqueue(new Callback<Book>() {
//            @Override
//            public void onResponse(Call<Book> call, Response<Book> response) {
//                Book book = response.body();
//                mTv.append(",is In Main Thread: " + isMainThread());
//                if(book != null) {
//                    mTv.append("book.name" + book.getSummary());
//                } else {
//                    mTv.append("无数据,");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Book> call, Throwable t) {
//                mTv.append(" error: " + t.toString());
//            }
//        });

        Call<PayTicketInfo> callInfo = RetrofitWrapper.getInstance()
                .create(IPayTicketService.class).listTickets("8888");

        callInfo.enqueue(new Callback<PayTicketInfo>() {
            @Override
            public void onResponse(Call<PayTicketInfo> call, Response<PayTicketInfo> response) {
                PayTicketInfo info = response.body();

                if(info != null && info.getData() != null) {
                    for(PayTicketInfo.DataBean bean: info.getData()) {
                        mTv.append(bean.getPlate_details() + ",\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<PayTicketInfo> call, Throwable t) {
                mTv.append("罚单出错");
            }
        });


    }

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }


}
