package com.yjx.androidword.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.MyView.MyEditText;
import com.yjx.androidword.R;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.SQLiteUtils;
import com.yjx.androidword.Utils.ToastUtils;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.Viewholder> {

    private Context mContext;
    private List<WordsBean> mList;

    // 对话框View
    private TextView mTxvDel;
    private TextView mTxvEnglish;
    private TextView mTxvChinese;
    private TextView mTxvModify;
    private MyEditText mEditEnglish;
    private MyEditText mEditChinese;
    private TextView mTxvCancel;

    public DictionaryAdapter(Context context, List<WordsBean> wordsBeanList) {
        mContext = context;
        mList = wordsBeanList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false);
        return new Viewholder(mView);
    }

    //在Activity中调用方法，实现搜索并高亮显示
    private int posit = -1;
    public void setItem(int position) {
        this.posit = position;
        notifyItemChanged(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        holder.mTxvEnglish.setText(mList.get(position).getWord());
        holder.mTxvChinese.setText(mList.get(position).getChinese());

        if (posit == position) {
            holder.mItemView.setBackgroundResource(R.color.btn_blue);
        } else {
            holder.mItemView.setBackground(null);
        }

        //子项长按事件 单词管理  删除 & 修改
        holder.mItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_dictionary_menu, null);
                final Dialog dialog = DialogUtils.show(mContext, view);
                mTxvDel = view.findViewById(R.id.txv_del);
                mTxvEnglish = view.findViewById(R.id.edit_english);
                mTxvChinese = view.findViewById(R.id.edit_from);
                mTxvModify = view.findViewById(R.id.txv_modify);
                mTxvEnglish.setText(mList.get(position).getWord());
                mTxvChinese.setText(mList.get(position).getChinese());
                mTxvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        removeData(position, mList.get(position).getWord());
                    }
                });
                mTxvModify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        @SuppressLint("InflateParams") View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_modify, null);
                        final Dialog dialog = DialogUtils.show(mContext, view);
                        mEditEnglish = view.findViewById(R.id.edit_english);
                        mEditChinese = view.findViewById(R.id.edit_from);
                        mTxvModify = view.findViewById(R.id.txv_modify);
                        mTxvCancel = view.findViewById(R.id.txv_cancel);
                        mEditEnglish.setText(mList.get(position).getWord());
                        mEditChinese.setText(mList.get(position).getChinese());
                        mTxvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        mTxvModify.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!TextUtils.isEmpty(mEditEnglish.getText().toString()) && !TextUtils.isEmpty(mEditChinese.getText().toString())) {
                                    modifyData(mList.get(position).getWord(), mEditEnglish.getText().toString(), mEditChinese.getText().toString());
                                    ToastUtils.show(mContext, "修改成功！");
                                    dialog.dismiss();
                                    mList.get(position).setWord(mEditEnglish.getText().toString());
                                    mList.get(position).setChinese(mEditChinese.getText().toString());
                                    notifyItemChanged(position);
                                } else {
                                    ToastUtils.show(mContext, "修改后的单词或翻译不能为空噢！");
                                }
                            }
                        });
                    }
                });
                return true;
            }
        });

    }

    //删除单词
    private void removeData(int position, String str_del) {
        //当前List删除
        mList.remove(position);
        //RecycleView移除
        notifyItemRemoved(position);
        //调用局部刷新.防止position错乱
        notifyItemRangeChanged(position, mList.size() - position);
        //数据库同步删除
        SQLiteUtils.delete(mContext, str_del);
    }

    //修改单词
    private void modifyData(String oldEnglish, String newEnglish, String newChinese) {
        ContentValues cv = new ContentValues();
        cv.put(DictionaryHelper.ENGLISH, newEnglish);
        cv.put(DictionaryHelper.CHINESE, newChinese);
        SQLiteUtils.upData(cv, oldEnglish);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private LinearLayout mItemView;
        private TextView mTxvEnglish;
        private TextView mTxvChinese;

        Viewholder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView.findViewById(R.id.item_view);
            mTxvEnglish = itemView.findViewById(R.id.edit_english);
            mTxvChinese = itemView.findViewById(R.id.edit_from);
        }
    }

}