package space.levan.memory.api.model.impl;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

import space.levan.memory.api.ApiCompleteListener;
import space.levan.memory.api.model.IAddCollectionModel;

import space.levan.memory.bean.BaseResponse;
import space.levan.memory.common.Constant;

/**
 * Created by WangZhiYao on 2016-12-18.
 */

public class AddCollectionModelImpl implements IAddCollectionModel {

    @Override
    public void addCollection(String username, String author, String title,
                              String img, String publisher, String subtitle,
                              String origin_title, String translator, String publish_date,
                              String pages, String isbn, String summary, String review,
                              ApiCompleteListener listener)
    {
        AVObject mBook = new AVObject("Book");
        mBook.put(Constant.BOOK_AUTHOR, author);
        mBook.put(Constant.BOOK_TITLE, title);
        mBook.put(Constant.BOOK_IMG, img);
        mBook.put(Constant.BOOK_PUBLISHER, publisher);
        mBook.put(Constant.BOOK_SUBTITLE, subtitle);
        mBook.put(Constant.BOOK_ORIGIN_TITLE, origin_title);
        mBook.put(Constant.BOOK_TRANSLATOR, translator);
        mBook.put(Constant.BOOK_PUBLISH_DATE, publish_date);
        mBook.put(Constant.BOOK_PAGES, pages);
        mBook.put(Constant.BOOK_ISBN, isbn);
        mBook.put(Constant.BOOK_SUMMARY, summary);
        mBook.put(Constant.BOOK_REVIEW, review);
        mBook.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(AVException e)
            {
                if (null == e)
                {
                    listener.onComplected("加入收藏成功");
                }
                else
                {

                    listener.onFailed(new BaseResponse(e.getCode(),"加入收藏失败"));
                }
            }
        });
    }

    @Override
    public void cancelAdding()
    {
        // TODO: 2016-12-18 取消接口调用
    }
}
