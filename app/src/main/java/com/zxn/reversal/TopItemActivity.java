package com.zxn.reversal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dinuscxj.refresh.RecyclerRefreshLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zxn on 2018-10-22 17:21:23.
 */
public class TopItemActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.rrl)
    RecyclerRefreshLayout rrl;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_msg);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        recyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(data);
//        getData();
        rrl.setRefreshing(false);
    }

    ArrayList<ItemInfo> data = new ArrayList<>();

    private void getData() {
        for (int i = items * (page - 1); i < items * page; i++) {
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.name = "名字" + i;
            data.add(itemInfo);
        }
        mAdapter.notifyDataSetChanged();
    }

    private int page = 1;
    private int items = 10;

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        addItem();
    }

    private void addItem() {
        Random random = new Random();
        int i = random.nextInt();
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.name = "名字:" + i;
        Collections.reverse(data);
        data.add(itemInfo);
        //LinkedList<ItemInfo> linkedList = new LinkedList<>();
        Collections.reverse(data);
        mAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(0);
    }

//    @Override
//    public void onRefresh() {
//        page++;
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getData();
//            }
//        }, 2 * 1000);
//    }

//    private Handler handler = new Handler();

}
