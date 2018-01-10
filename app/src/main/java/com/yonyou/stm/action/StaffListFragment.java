package com.yonyou.stm.action;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.yonyou.stm.R;
import com.yonyou.stm.ctx.Constants;
import com.yonyou.stm.widget.BaseFragment;
import com.yonyou.stm.widget.StaffListAdapter;

import org.apache.commons.lang.StringUtils;

/**
 * Created by lsq on 2018/1/7
 */

public class StaffListFragment extends BaseFragment {

    private View view;
    private ListView listView;
    private StaffListAdapter adapter;

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
        initListView();
        initEventListaner();
        return view;
    }

    private void initEventListaner(){
        ((Button)view.findViewById(R.id.btn_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = ((EditText)StaffListFragment.this.view.findViewById(R.id.edit_key)).getText().toString();
                if(StringUtils.isNotBlank(key)){
                    adapter.notifyDataChanged(key);
                }
            }
        });
    }

    private void initListView(){
        adapter = new StaffListAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                long staffId = Long.parseLong(((TextView)view.findViewById(R.id.item_id)).getText().toString());
                Intent intent = new Intent();
                intent.setClass(getActivity(), StaffSetActivity.class);
                intent.putExtra(Constants.BUNDLE_KEY_STAFFID,  staffId);
                startActivity(intent);
            }
        });
    }

}
