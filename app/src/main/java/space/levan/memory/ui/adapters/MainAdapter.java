package space.levan.memory.ui.adapters;

import android.content.Context;
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

    private List<Project> mProjects;
    private LayoutInflater inflater;

    public MainAdapter(Context context, List<Project> projects) {
        this.mProjects = projects;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_project, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).mTvName.setText(mProjects.get(position).getName());
        ((ViewHolder) holder).mTvStatus.setText(mProjects.get(position).getStatus());
        ((ViewHolder) holder).mTvStartTime.setText(mProjects.get(position).getStart_time());
        ((ViewHolder) holder).mTvEndTime.setText(mProjects.get(position).getEnd_time());
        ((ViewHolder) holder).mTvMember.setText(mProjects.get(position).getMember());
        ((ViewHolder) holder).mTvNotes.setText(mProjects.get(position).getNotes());
    }

    @Override
    public int getItemCount() {
        return mProjects.isEmpty() ? 0 : mProjects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
