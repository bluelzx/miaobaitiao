package cn.hjf.bixia.adapter;

import java.util.List;

import cn.hjf.bixia.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StatisticSelectMenuAdapter extends BaseAdapter {
	
	private List<String> mMenuList;
	private Context mContext;
	
	public StatisticSelectMenuAdapter(Context context, List<String> menuList) {
		this.mContext = context;
		this.mMenuList = menuList;
	}

	@Override
	public int getCount() {
		return mMenuList == null ? 0 : mMenuList.size();
	}

	@Override
	public Object getItem(int position) {
		return mMenuList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_statistic_select_menu, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView.findViewById(R.id.iv_drawer_name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.mTextView.setText(mMenuList.get(position));
		
		return convertView;
	}
	
	private class ViewHolder {
		public TextView mTextView;
	}

}
