package space.levan.memory.biz.widgets;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import space.levan.memory.R;

/**
 * @author WangZhiYao
 * @date 2019/7/5
 */
public class EmptyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_empty_img)
    public ImageView mIvEmptyImg;

    public EmptyViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
