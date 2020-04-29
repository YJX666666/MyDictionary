package com.yjx.androidword.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.R;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.Viewholder> {

    private List<WordsBean> mWordsBeanList;
    private View mView;

    private OnItemClick mOnItemClick;
    private OnItemClick mOnItemLongClick;

    public void setOnItemLongClickListener(OnItemClick onItemLongClickListener) {
        this.mOnItemLongClick = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClick onItemClickListener) {
        this.mOnItemClick = onItemClickListener;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public DictionaryAdapter(List<WordsBean> wordsBeanList) {
        mWordsBeanList = wordsBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_words, parent, false);
        return new Viewholder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int position) {
        holder.mTxvEnglish.setText(mWordsBeanList.get(position).getEnglish());
        holder.mTxvChinese.setText(mWordsBeanList.get(position).getChinses());

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.onItemClick(position);
            }
        });

        mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnItemLongClick.onItemClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWordsBeanList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView mTxvEnglish;
        private TextView mTxvChinese;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            mTxvEnglish = itemView.findViewById(R.id.edit_english);
            mTxvChinese = itemView.findViewById(R.id.edit_chinese);
        }
    }

}
