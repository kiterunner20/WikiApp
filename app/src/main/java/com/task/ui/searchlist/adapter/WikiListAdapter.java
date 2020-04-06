package com.task.ui.searchlist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.task.R;
import com.task.model.response.domain.WikiResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiListAdapter extends RecyclerView.Adapter<WikiListAdapter.ViewHolder> {

    private final WikiResultItemSelectedListner listener;
    private ArrayList<WikiResult.DataList> wikiDataList = new ArrayList<>();

    public WikiListAdapter(WikiResultItemSelectedListner listener) {
        this.listener = listener;
    }

    public void addData(ArrayList<WikiResult.DataList> wikiList) {
        if (wikiDataList.size() > 0) {

            int listSize = wikiDataList.size();
            wikiDataList.clear();
            notifyItemRangeRemoved(0, listSize - 1);
        }
        this.wikiDataList.addAll(wikiList);

        notifyDataSetChanged();
    }

    public List<WikiResult.DataList> getWikiData() {
        return wikiDataList;
    }

    @NonNull
    @Override
    public WikiListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wikiresult, parent,
                false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull WikiListAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(wikiDataList.get(position).title());
        holder.tvDescription.setText(wikiDataList.get(position).description());
        holder.tvId.setText("ID : " + wikiDataList.get(position).pageId());

        if(wikiDataList.get(position).imageUrl().equals("")){
            holder.imageWiki.setImageResource(R.drawable.wikiimage);
        }else{
            Glide.with(holder.imageWiki.getContext())
                    .load(wikiDataList.get(position).imageUrl())
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(holder.imageWiki);
        }

    }

    @Override
    public int getItemCount() {
        return wikiDataList.size();
    }

    public interface WikiResultItemSelectedListner {
        void onItemSelected(int selectedItemPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_wiki)
        ImageView imageWiki;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_id)
        TextView tvId;

        public ViewHolder(@NonNull View itemView, WikiResultItemSelectedListner listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                listener.onItemSelected(getAdapterPosition());
            });
        }
    }
}
