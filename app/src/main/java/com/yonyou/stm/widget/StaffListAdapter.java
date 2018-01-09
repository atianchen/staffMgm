package com.yonyou.stm.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yonyou.stm.R;
import com.yonyou.stm.domain.Staff;
import com.yonyou.stm.service.StaffService;

import java.util.List;

/**
 * Created by lsq on 2018/1/7.
 */

public class StaffListAdapter extends BaseAdapter {

    public class ViewHolder {
        public TextView id;
        public TextView name;
    }

    private Activity context;
    private LayoutInflater inflater = null;
   private StaffService staffService;
    private List<Staff> staffs;

    public StaffListAdapter(Activity context){
        super();
        this.context = context;
       this.staffService = new StaffService();
        staffs = this.staffService.loadAll();
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return staffs.size();
    }

    @Override
    public Object getItem(int i) {
        return staffs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.id = (TextView) view.findViewById(R.id.item_id);
            holder.name = (TextView) view.findViewById(R.id.item_content);
        }
        if (staffs != null && staffs.size()>0) {
            holder.name.setText(staffs.get(i).getName());
            //holder.id.setText(staffs.get(i).getId().toString());
        }
        return view;
    }
}
