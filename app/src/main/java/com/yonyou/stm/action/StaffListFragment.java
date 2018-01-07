package com.yonyou.stm.action;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.yonyou.stm.R;
import com.yonyou.stm.ctx.Constants;
import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.service.StaffService;
import com.yonyou.stm.widget.BaseFragment;
import com.yonyou.stm.widget.StaffListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lsq on 2018/1/7.`73wwe+ ./w4rfdxz
 */

public class StaffListFragment extends BaseFragment {

    private View view;
    private ListView listView;
    private StaffListAdapter adapter;

    private StaffService staffService;

    public static StaffListFragment newInstance() {
        StaffListFragment fragment = new StaffListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.staff_list, container, false);
        listView =  view.findViewById(R.id.listView);
        staffService = new StaffService(getContext());
        initListView();
        return view;
    }

    private void initListView(){
        adapter = new StaffListAdapter(getActivity(),staffService);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int staffId = Integer.parseInt(((TextView)view.findViewById(R.id.item_id)).getText().toString());
                Intent intent = new Intent();
                intent.setClass(getActivity(), StaffSetActivity.class);
                intent.putExtra(Constants.BUNDLE_KEY_STAFF,  staffService.load(staffId));
                startActivity(intent);
            }
        });
    }

}
