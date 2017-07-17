package space.levan.memory.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;

import io.realm.RealmRecyclerViewAdapter;
import space.levan.memory.R;
import space.levan.memory.bean.realm.Book;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class MainAdapter extends RealmRecyclerViewAdapter<Book, RecyclerView.ViewHolder>
{
    private static final int TYPE_EMPTY   = 0;
    private static final int TYPE_DEFAULT = 1;

    public MainAdapter(OrderedRealmCollection<Book> data)
    {
        super(data, true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        if (viewType == TYPE_DEFAULT)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
            return new MainHolder(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false);
            return new EmptyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ((EmptyHolder) holder).iv_item_logo.setImageResource(R.mipmap.img_collection);
        ((EmptyHolder) holder).tv_item_tips.setText(R.string.ac_shelf_no_collection);
    }

    @Override
    public int getItemViewType(int position)
    {
        return TYPE_EMPTY;
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }

    private class MainHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_book_img;
        private final TextView tv_book_title;
        private final TextView tv_book_info;
        private final TextView tv_book_description;

        MainHolder(View itemView)
        {
            super(itemView);
            iv_book_img = (ImageView) itemView.findViewById(R.id.iv_book_img);
            tv_book_title = (TextView) itemView.findViewById(R.id.tv_book_title);
            tv_book_info = (TextView) itemView.findViewById(R.id.tv_book_info);
            tv_book_description = (TextView) itemView.findViewById(R.id.tv_book_description);
        }
    }

    private class EmptyHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_item_logo;
        private final TextView tv_item_tips;

        EmptyHolder(View itemView)
        {
            super(itemView);
            iv_item_logo = (ImageView) itemView.findViewById(R.id.iv_item_logo);
            tv_item_tips = (TextView) itemView.findViewById(R.id.tv_item_tips);
        }
    }
}
