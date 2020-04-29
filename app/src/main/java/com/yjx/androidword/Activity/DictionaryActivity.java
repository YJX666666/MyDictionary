package com.yjx.androidword.Activity;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yjx.androidword.Adapter.DictionaryAdapter;
import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.MyView.MyEditText;
import com.yjx.androidword.R;
import com.yjx.androidword.SQLiteHelper.DictionaryHelper;
import com.yjx.androidword.Utils.DialogUtils;
import com.yjx.androidword.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class DictionaryActivity extends BaseActivity {

    private androidx.recyclerview.widget.RecyclerView mRecycleWords;
    private WordsBean mWordsBean;
    private DictionaryAdapter mWordsAdapter;
    private List<WordsBean> mWordsBeanList = new ArrayList<>();
    private DictionaryHelper mSQWordsHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private Cursor mCursor;
    private LinearLayoutManager mLinearLayoutManager;

    //View
    private TextView mTxvDel;
    private TextView mTxvEnglish;
    private TextView mTxvChinese;
    private TextView mTxvModify;
    private MyEditText mEditEnglish;
    private MyEditText mEditChinese;
    private TextView mTxvCancel;

    @Override
    protected void initData() {
        mSQWordsHelper = new DictionaryHelper(mContext);
        mSQLiteDatabase = mSQWordsHelper.getWritableDatabase();

        //获取数据库中的数据,传入List
        getData();

        mWordsAdapter = new DictionaryAdapter(mWordsBeanList);
        mRecycleWords.setAdapter(mWordsAdapter);

        //布局管理器
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecycleWords.setLayoutManager(mLinearLayoutManager);
        //子项动画
        mRecycleWords.setItemAnimator(new DefaultItemAnimator());
        //子项分割线
        mRecycleWords.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));


        mWordsAdapter.setOnItemClickListener(new DictionaryAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "点击的位置：" + position, Toast.LENGTH_SHORT).show();
            }
        });


        mWordsAdapter.setOnItemLongClickListener(new DictionaryAdapter.OnItemClick() {
            @Override
            public void onItemClick(final int position) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_words_menu, null);
                final Dialog dialog = DialogUtils.show(mContext, view);
                mTxvDel = view.findViewById(R.id.txv_del);
                mTxvEnglish = view.findViewById(R.id.edit_english);
                mTxvChinese = view.findViewById(R.id.edit_chinese);
                mTxvModify = view.findViewById(R.id.txv_modify);
                mTxvEnglish.setText(mWordsBeanList.get(position).getEnglish());
                mTxvChinese.setText(mWordsBeanList.get(position).getChinses());
                mTxvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        removeData(position, mWordsBeanList.get(position).getEnglish());
                    }
                });
                mTxvModify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_modify, null);
                        final Dialog dialog = DialogUtils.show(mContext, view);
                        mEditEnglish = view.findViewById(R.id.edit_english);
                        mEditChinese = view.findViewById(R.id.edit_chinese);
                        mTxvModify = view.findViewById(R.id.txv_modify);
                        mTxvCancel = view.findViewById(R.id.txv_cancel);
                        mEditEnglish.setText(mWordsBeanList.get(position).getEnglish());
                        mEditChinese.setText(mWordsBeanList.get(position).getChinses());
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
                                    modifyData(mWordsBeanList.get(position).getEnglish(), mEditEnglish.getText().toString(), mEditChinese.getText().toString());
                                    ToastUtils.show(mContext, "修改成功！");
                                    dialog.dismiss();
                                    mWordsBeanList.get(position).setEnglish(mEditEnglish.getText().toString());
                                    mWordsBeanList.get(position).setChinses(mEditChinese.getText().toString());
                                    mWordsAdapter.notifyItemChanged(position);
                                } else {
                                    ToastUtils.show(mContext, "修改后的单词或翻译不能为空噢！");
                                }
                            }
                        });
                    }
                });
            }
        });

    }

    private void modifyData(String oldEnglish, String newEnglish, String newChinese) {
        ContentValues values = new ContentValues();
        values.put(DictionaryHelper.ENGLISH, newEnglish);
        values.put(DictionaryHelper.CHINESE, newChinese);
        mSQLiteDatabase.update(DictionaryHelper.TABLE_NAME, values, "word=?", new String[]{oldEnglish});
    }

    private void removeData(int position, String str_del) {
        //当前List删除
        mWordsBeanList.remove(position);
        //RecycleView移除
        mWordsAdapter.notifyItemRemoved(position);
        //调用局部刷新.防止position错乱
        mWordsAdapter.notifyItemRangeChanged(position, mWordsBeanList.size() - position);
        //数据库同步删除
        String clause = DictionaryHelper.ENGLISH + "=?";
        mSQLiteDatabase.delete(DictionaryHelper.TABLE_NAME, clause, new String[]{str_del});
    }

    private void getData() {
        mCursor = mSQLiteDatabase.query(DictionaryHelper.TABLE_NAME, null, null, null, null, null, null);
        while (mCursor.moveToNext()) {
            mWordsBean = new WordsBean();
            mWordsBean.setEnglish(mCursor.getString(0));
            mWordsBean.setChinses(mCursor.getString(1));
            mWordsBeanList.add(mWordsBean);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.layout_words;
    }

    @Override
    protected void initView() {
        mRecycleWords = findViewById(R.id.recycle_words);
    }
}