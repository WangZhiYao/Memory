package space.levan.memory.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import space.levan.memory.R;

/**
 * Created by WangZhiYao on 2017/5/2.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private static final int TYPE_EMPTY = 0;
    private static final int TYPE_DEFAULT = 1;

    public MainAdapter(Context context)
    {
        this.mContext = context;
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
    public int getItemViewType(int position)
    {
        return TYPE_EMPTY;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ((EmptyHolder) holder).iv_item_logo.setImageResource(R.mipmap.img_collection);
        ((EmptyHolder) holder).tv_item_tips.setText(R.string.ac_shelf_no_collection);
    }

    @Override
    public int getItemCount()
    {
        return 1;
    }

    class MainHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_book_img;
        private final TextView tv_book_title;
        private final TextView tv_book_info;
        private final TextView tv_book_description;

        public MainHolder(View itemView)
        {
            super(itemView);
            iv_book_img = (ImageView) itemView.findViewById(R.id.iv_book_img);
            tv_book_title = (TextView) itemView.findViewById(R.id.tv_book_title);
            tv_book_info = (TextView) itemView.findViewById(R.id.tv_book_info);
            tv_book_description = (TextView) itemView.findViewById(R.id.tv_book_description);
        }
    }

    class EmptyHolder extends RecyclerView.ViewHolder
    {
        private final ImageView iv_item_logo;
        private final TextView tv_item_tips;

        public EmptyHolder(View itemView)
        {
            super(itemView);
            iv_item_logo = (ImageView) itemView.findViewById(R.id.iv_item_logo);
            tv_item_tips = (TextView) itemView.findViewById(R.id.tv_item_tips);
        }
    }
}
