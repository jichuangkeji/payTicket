package com.weirdotech.payticket.service;

import com.weirdotech.payticket.bean.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Bingo on 17/5/13.
 */
public interface IBookService {

    @GET("/v2/book/{id}")
    Call<Book> getBook(@Path("id") String bookId);
}
