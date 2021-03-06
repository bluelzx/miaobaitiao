package cn.hjf.xinyongka.adapter;

import java.util.List;

import cn.hjf.xinyongka.R;
import cn.hjf.xinyongka.businessmodel.MonthStatisticData;
import cn.hjf.xinyongka.util.NumberUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MonthStatisticAdapter extends BaseAdapter {
    
    private Context mContext;
    private List<MonthStatisticData> mDatas;
    
    public MonthStatisticAdapter(Context context, List<MonthStatisticData> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_month_statistic, parent, false);
            holder.month = (TextView) convertView.findViewById(R.id.tv_month);
            holder.ratio = (TextView) convertView.findViewById(R.id.tv_ratio);
            holder.sum = (TextView) convertView.findViewById(R.id.tv_sum);
            holder.ratioBar = (ProgressBar) convertView.findViewById(R.id.pb_ratio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.month.setText(NumberUtil.formatTwoInt(mDatas.get(position).getConsumeMonth() + 1));
        holder.sum.setText(NumberUtil.formatValue(mDatas.get(position).getTypeSum()));
        holder.ratio.setText(String.format(mContext.getString(R.string.label_ratio), NumberUtil.formatValue(mDatas.get(position).getAllSum() == 0 ? 0 : mDatas.get(position).getTypeSum() * 100 / mDatas.get(position).getAllSum())));
        holder.ratioBar.setMax((int) mDatas.get(position).getAllSum());
        holder.ratioBar.setProgress((int) mDatas.get(position).getTypeSum());
        
        return convertView;
    }
    
    private class ViewHolder {
        TextView month, ratio, sum;
        ProgressBar ratioBar;
    }

}
