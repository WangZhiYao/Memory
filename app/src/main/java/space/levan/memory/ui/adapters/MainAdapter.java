package space.levan.memory.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;
import space.levan.memory.model.bean.project.Project;

/**
 * MainAdapter
 *
 * @author WangZhiYao
 * @date 2017/12/2
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTY = 0;
    private static final int PROJECT = 1;

    private Context mContext;
    private List<Project> mProjects;
    private LayoutInflater mInflater;

    public MainAdapter(Context context, List<Project> projects) {
        this.mContext = context;
        this.mProjects = projects;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY) {
            return new EmptyHolder(mInflater.inflate(R.layout.item_empty, parent, false));
        } else {
            return new ItemHolder(mInflater.inflate(R.layout.item_project, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmptyHolder) {
            ((EmptyHolder) holder).mTvEmptyMsg.setText(mContext.getString(R.string.main_empty_project));
            Drawable drawable = mContext.getDrawable(R.mipmap.img_main);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            ((EmptyHolder) holder).mTvEmptyMsg.setCompoundDrawables(null, drawable, null, null);
        } else {
            ((ItemHolder) holder).mTvName.setText(mProjects.get(position).getName());
            ((ItemHolder) holder).mTvStatus.setText(mProjects.get(position).getStatus());
            ((ItemHolder) holder).mTvStartTime.setText(mProjects.get(position).getStart_time());
            ((ItemHolder) holder).mTvEndTime.setText(mProjects.get(position).getEnd_time());
            ((ItemHolder) holder).mTvMember.setText(mProjects.get(position).getMember());
            ((ItemHolder) holder).mTvNotes.setText(mProjects.get(position).getNotes());
        }
    }

    @Override
    public int getItemCount() {
        return mProjects.isEmpty() ? 1 : mProjects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mProjects.size() == 0) {
            return EMPTY;
        } else {
            return PROJECT;
        }
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_project_name)
        TextView mTvName;
        @BindView(R.id.tv_project_status)
        TextView mTvStatus;
        @BindView(R.id.tv_project_start_time)
        TextView mTvStartTime;
        @BindView(R.id.tv_project_end_time)
        TextView mTvEndTime;
        @BindView(R.id.tv_project_member)
        TextView mTvMember;
        @BindView(R.id.tv_project_notes)
        TextView mTvNotes;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class EmptyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_empty_msg)
        TextView mTvEmptyMsg;

        EmptyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
