package space.levan.memory.dao;

import android.provider.BaseColumns;

/**
 * 封装数据库模式
 *
 * Created by WangZhiYao on 2016/12/10.
 */

final class BookDatabase {

    private BookDatabase() {}

    static final class BookInfo implements BaseColumns
    {
        private BookInfo() {}
        static final String BOOK_ID = "_id";
        static final String BOOK_AUTHOR = "author";
        static final String BOOK_TITLE = "title";
        static final String BOOK_IMG = "img";
        static final String BOOK_PUBLISHER = "publisher";
        static final String BOOK_SUBTITLE = "subtitle";
        static final String BOOK_ORIGIN_TITLE = "origin_title";
        static final String BOOK_TRANSLATOR = "translator";
        static final String BOOK_PUBLISH_DATE = "publish_date";
        static final String BOOK_PAGES = "pages";
        static final String BOOK_ISBN = "isbn";
        static final String BOOK_SUMMARY = "summary";
        static final String BOOK_REVIEW = "review";
    }
}
