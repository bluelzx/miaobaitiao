package fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.Michael.xianjinka.R;
import com.Michael.MySQLiteOpenHelper;
import com.Michael.outArcChartView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OutPutFragment extends Fragment {

    private String dateBeginString;
    private String dateEndString;
    private int eatSum = 0;
    private int wearSum = 0;
    private int liveSum = 0;
    private int trafficSum = 0;

    @Bind(R.id.text_arc_incomeBegin)
    TextView textArcIncomeBegin;
    @Bind(R.id.text_arc_incomeEnd)
    TextView textArcIncomeEnd;
    @Bind(R.id.textButton_arc_incomBegin)
    TextView textButtonArcIncomBegin;
    @Bind(R.id.textButton_arc_incomEnd)
    TextView textButtonArcIncomEnd;
    @Bind(R.id.button_arc_search)
    Button buttonArcSearch;
    @Bind(R.id.arcChartView_arc_outarcChartView)
    outArcChartView arcChartViewArcOutarcChartView;

    //吃
    private List<Map<String, Object>> eatList;
    //穿
    private List<Map<String, Object>> wearList;
    //住
    private List<Map<String, Object>> liveList;
    //行
    private List<Map<String, Object>> trafficList;

    private MySQLiteOpenHelper dbHelper;

    public OutPutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_out_put, container, false);
        ButterKnife.bind(this, view);
        dbHelper = new MySQLiteOpenHelper(getActivity());
        eatList = new ArrayList<Map<String, Object>>();
        wearList = new ArrayList<Map<String, Object>>();
        liveList = new ArrayList<Map<String, Object>>();
        trafficList = new ArrayList<Map<String, Object>>();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.textButton_arc_incomBegin, R.id.textButton_arc_incomEnd, R.id.button_arc_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textButton_arc_incomBegin:

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int monthOfYear = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateBeginString = year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth;
                                textArcIncomeBegin.setText(dateBeginString);
                            }
                        }, year, monthOfYear, dayOfMonth);
                dDialog.show();
                break;
            case R.id.textButton_arc_incomEnd:

                Calendar calendar1 = Calendar.getInstance();
                int year1 = calendar1.get(Calendar.YEAR);
                int monthOfYear1 = calendar1.get(Calendar.MONTH);
                int dayOfMonth1 = calendar1.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dDialog1 = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateEndString = year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth;
                                textArcIncomeEnd.setText(dateEndString);
                            }
                        }, year1, monthOfYear1, dayOfMonth1);
                dDialog1.show();
                break;
            case R.id.button_arc_search:


                eatList = dbHelper.selectList(
                        "select money from tb_myAccount where bigKind= ?",
                        new String[]{"吃"});

                for (int i = 0; i < eatList.size(); i++) {
                    int s = Integer.parseInt(eatList.get(i).get("money").toString());
                    eatSum += s;
                }
                wearList = dbHelper.selectList(
                        "select money from tb_myAccount  where bigKind= ?",
                        new String[]{"穿"});
                for (int i = 0; i < wearList.size(); i++) {
                    int s = Integer.parseInt(wearList.get(i).get("money").toString());
                    wearSum += s;
                }
                liveList = dbHelper.selectList(
                        "select money from tb_myAccount  where bigKind= ?",
                        new String[]{"住"});
                for (int i = 0; i < liveList.size(); i++) {
                    int s = Integer.parseInt(liveList.get(i).get("money").toString());
                    liveSum += s;
                }
                trafficList = dbHelper.selectList(
                        "select money from tb_myAccount  where bigKind= ?",
                        new String[]{"行"});
                for (int i = 0; i < trafficList.size(); i++) {
                    int s = Integer.parseInt(trafficList.get(i).get("money").toString());
                    trafficSum += s;
                }

                arcChartViewArcOutarcChartView.setData(new int[]
                        {eatSum, wearSum, liveSum, trafficSum}
                );

                break;
        }
    }
}
