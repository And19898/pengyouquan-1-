package com.ozj.baby.mvp.model.dao;

import android.content.Context;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.ozj.baby.R;
import com.ozj.baby.mvp.model.CaiPiaoModel;
import com.ozj.baby.widget.TrendView;

public class TrendChartAdapter extends QuickAdapter<CaiPiaoModel.ShowapiResBodyBean.ResultBean> {
    public TrendChartAdapter(Context context) {
        super(context, R.layout.item_trendchar);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, CaiPiaoModel.ShowapiResBodyBean.ResultBean model) {
        helper
                .setText(R.id.tv_item_normal_title, model.getName())
                .setText(R.id.tv_item_normal_qishu, "期数：" + model.getExpect())
                .setText(R.id.tv_item_normal_time, model.getTime());
        TrendView trendView = helper.getView(R.id.trendview);
        trendView.setData(model.getOpenCode());
    }
}
