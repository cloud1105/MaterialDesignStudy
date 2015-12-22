package com.android.leo.material.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.leo.material.R;
import com.android.leo.material.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 15/12/20.
 */
public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;
    private Activity mParent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mParent = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        //1.set Adapter
        recyclerView.setAdapter(new StanRecyClerViewAdapter(initData()));
        //2.set LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(mParent));
        //3.add ItemDecoration
        recyclerView.addItemDecoration(new DividerItemDecoration(mParent,DividerItemDecoration.VERTICAL_LIST));
        //4.set ItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        return view;
    }

    private List<String> initData() {
        List<String> list = new ArrayList<String>();
        list.add("Java");
        list.add("Ruby");
        list.add("Star");
        list.add("Swift");
        list.add("ObjectC");
        list.add("Android");
        list.add("IOS");
        list.add("Windows");
        list.add("Go");
        list.add("Docker");
        list.add("Wowwww");
        list.add("Hello");
        list.add("Tyler");
        list.add("Swift");
        list.add("JustWe");
        list.add("Arival");
        list.add("Felle");
        list.add("Olic");
        return list;
    }

    class StanRecyClerViewAdapter extends RecyclerView.Adapter<StanRecyClerViewAdapter.CardHold>{
        private List<String> source;

        public StanRecyClerViewAdapter(List<String> list){
            this.source = list;
        }

        @Override
        public CardHold onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =  LayoutInflater.from(mParent).inflate(R.layout.recycler_item, parent, false);
            return new CardHold(view);
        }

        @Override
        public void onBindViewHolder(CardHold holder, int position) {
           holder.txv.setText(source.get(position));
        }

        @Override
        public int getItemCount() {
            return source.size();
        }

        class CardHold extends RecyclerView.ViewHolder{

            TextView txv;

            public CardHold(View itemView) {
                super(itemView);
                txv = (TextView) itemView.findViewById(R.id.txv);
            }


        }

    }

}
