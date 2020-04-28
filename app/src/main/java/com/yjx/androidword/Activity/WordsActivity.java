package com.yjx.androidword.Activity;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yjx.androidword.Adapter.WordsAdapter;
import com.yjx.androidword.BaseActivity;
import com.yjx.androidword.Bean.WordsBean;
import com.yjx.androidword.R;
import com.yjx.androidword.SQLiteHelper.SQWordsHelper;
import com.yjx.androidword.Utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class WordsActivity extends BaseActivity {

    private androidx.recyclerview.widget.RecyclerView mRecycleWords;
    private WordsBean mWordsBean;
    private WordsAdapter mWordsAdapter;
    private List<WordsBean> mWordsBeanList = new ArrayList<>();
    private SQWordsHelper mSQWordsHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private Cursor mCursor;
    private LinearLayoutManager mLinearLayoutManager;

    //View
    private TextView mTxvDel;
    private TextView mTxvEnglish;
    private TextView mTxvChinese;
    private TextView mTxvModify;

    @Override
    protected void initData() {
        mSQWordsHelper = new SQWordsHelper(mContext);
        mSQLiteDatabase = mSQWordsHelper.getWritableDatabase();

        //获取数据库中的数据,传入List
        Data();

        mWordsAdapter = new WordsAdapter(mWordsBeanList);
        mRecycleWords.setAdapter(mWordsAdapter);

        //布局管理器
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecycleWords.setLayoutManager(mLinearLayoutManager);
        //子项动画
        mRecycleWords.setItemAnimator(new DefaultItemAnimator());
        //子项分割线
        mRecycleWords.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));


        mWordsAdapter.setOnItemClickListener(new WordsAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "点击的位置：" + position, Toast.LENGTH_SHORT).show();
            }
        });


        mWordsAdapter.setOnItemLongClickListener(new WordsAdapter.OnItemClick() {
            @Override
            public void onItemClick(final int position) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_words_menu, null);
                final Dialog dialog = DialogUtils.show(mContext, view);
                mTxvDel = view.findViewById(R.id.txv_del);
                mTxvEnglish = view.findViewById(R.id.txv_english);
                mTxvChinese = view.findViewById(R.id.txv_chinese);
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
            }
        });

    }

    private void removeData(int position, String str_del) {
        //当前List删除
        mWordsBeanList.remove(position);
        //RecycleView移除
        mWordsAdapter.notifyItemRemoved(position);
        //调用局部刷新.防止position错乱
        mWordsAdapter.notifyItemRangeChanged(position, mWordsBeanList.size() - position);
        //数据库同步删除
        String clause = SQWordsHelper.WORD + "=?";
        mSQLiteDatabase.delete(SQWordsHelper.TABLE_NAME, clause, new String[]{str_del});
    }

    private void Data() {
        mCursor = mSQLiteDatabase.query(SQWordsHelper.TABLE_NAME, null, null, null, null, null, null);
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
