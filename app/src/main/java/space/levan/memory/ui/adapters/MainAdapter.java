package space.levan.memory.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import space.levan.memory.model.bean.project.Project;

/**
 * File description
 *
 * @author WangZhiYao
 * @date 2017/12/2
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Project> mProjects;
    private LayoutInflater inflater;

    private static final int EMPTY_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    public MainAdapter(Context context, List<Project> projects) {
        this.mContext = context;
        this.mProjects = projects;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if (mProjects.isEmpty()) {
            return EMPTY_VIEW;
        } else {
            return ITEM_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return mProjects.isEmpty() ? 1 : mProjects.size();
    }

    class EmptyView extends RecyclerView.ViewHolder {
        EmptyView(View itemView) {
            super(itemView);
        }
    }

    class ItemView extends RecyclerView.ViewHolder {
        ItemView(View itemView) {
            super(itemView);
        }
    }
}
