package co.id.githubusers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.githubusers.R;
import co.id.githubusers.data.ResultData;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ResultData> mResultData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ListAdapter(List<ResultData> resultData, Context context){
        mResultData = resultData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_list_activity, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultData resultData = mResultData.get(position);

        holder.resultData = resultData;
        holder.mTxtTitle.setText(resultData.getLogin());

        Picasso.with(mContext)
                .load(resultData.getAvatarUrl())
                .into(holder.mImageView);
    }

    public void add(ResultData resultData) {
        mResultData.add(resultData);
        notifyItemInserted(mResultData.size() - 1);
    }

    public void addAll(List<ResultData> moveResults) {
        for (ResultData result : moveResults) {
            add(result);
        }
    }

    @Override
    public int getItemCount() {
        return mResultData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_thumbnail)
        ImageView mImageView;
        @BindView(R.id.txt_title)
        TextView mTxtTitle;

        ResultData resultData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
