package com.example.wangyuan.viewpagerdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangyuan.viewpagerdemo.DataInterface.Call;
import com.example.wangyuan.viewpagerdemo.R;
import com.example.wangyuan.viewpagerdemo.view.MyProgressDialog;
import com.example.wangyuan.viewpagerdemo.view.WaitDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyuan on 2017/12/18.
 */

public class MainFragment extends BaseFragment2 implements Call {

    private String type;
    private ArrayList<String>
            list = new ArrayList<>();
    private RecyclerView recyleView;
    private MyProgressDialog myProgressDialog;
    private MyAdapter myAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            msg.what = 0;
            myAdapter.notifyDataSetChanged();
        }
    };

    public static MainFragment newInstance(String type) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    /**
     * @param type
     */
    private void getData(String type) {
//        获取数据
        WaitDialog.show(getActivity());
//        myProgressDialog = new MyProgressDialog(getActivity());
//        myProgressDialog.getDialogShow();
        Log.i("haha", "getData: 显示了" + type);
        list.clear();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    SystemClock.sleep(100);
                    list.add(i + "这是猪");
                    if (i == 30) {
                        fail();
                    }
                }


                sucess("成功了");
            }
        }).start();


    }

    protected void initView(View view) {
        recyleView = view.findViewById(R.id.recyleView);
        recyleView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    protected int getResourcesId() {
        Log.i("haha", "getData:" + type);
        return R.layout.fragment;
    }


    private void initPager() {

        recyleView.setAdapter(myAdapter);

    }


    @Override
    public String sucess(String data) {
        hide();
        Message message = new Message();
        message.what = 0;
        handler.sendMessage(message);
        Log.i("haha", "发送: " + type);
        return data;
    }

    @Override
    public void fail() {

        hide();
    }

    private void hide() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                WaitDialog.hide();
//                myProgressDialog.getDialogHide();
                Log.i("haha", "getData: 消失" + type);
            }
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment;
    }

    @Override
    protected void lazyLoadData() {

        if (!isPrepared || !isVisible) {
            return;
        }


        Bundle arguments = getArguments();
        type = arguments.getString("type");
        getData(type);

    }

    @Override
    protected void initViewAndData(View view) {
        recyleView = view.findViewById(R.id.recyleView);
        recyleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myAdapter = new MyAdapter(list);
        initPager();
    }

    @Override
    public void initPresenter() {

    }


    /**
     * 数据包适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;
        }


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder myHolder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_recycle, parent, false));
            return myHolder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.text.setText(list.get(position));
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {

            private final TextView text;

            public MyHolder(View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.text);
            }
        }
    }
}
