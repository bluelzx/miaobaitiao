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

import com.Michael.AccountBook.R;
import com.Michael.ArcChartView;
import com.Michael.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InputFragment extends Fragment {
    @Bind(R.id.text_arc_incomeBegin)
    TextView textArcIncomeBegin;
    @Bind(R.id.text_arc_incomeEnd)
    TextView textArcIncomeEnd;
    @Bind(R.id.arcChartView_arc_arcChartView)
    ArcChartView arcChartViewArcArcChartView;
    @Bind(R.id.textButton_arc_incomBegin)
    TextView textButtonArcIncomBegin;
    @Bind(R.id.textButton_arc_incomEnd)
    TextView textButtonArcIncomEnd;
    @Bind(R.id.button_arc_search)
    Button buttonArcSearch;
    private MySQLiteOpenHelper dbHelper;
    private String dateBeginString;
    private String dateEndString;
    private int salarySum = 0;
    private int perkSum = 0;
    //工资集合
    private List<Map<String, Object>> salaryList;
    //外快集合
    private List<Map<String, Object>> perkList;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this, view);
        dbHelper = new MySQLiteOpenHelper(getActivity());

        salaryList = new ArrayList<Map<String, Object>>();
        perkList = new ArrayList<Map<String, Object>>();
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

                salaryList = dbHelper.selectList(
                        "select money from tb_myAccount where bigKind= ?",
                        new String[]{"工资"});

                for (int i = 0; i < salaryList.size(); i++) {
                    int s = Integer.parseInt(salaryList.get(i).get("money").toString());
                    salarySum += s;
                }
                perkList = dbHelper.selectList(
                        "select money from tb_myAccount  where bigKind= ?",
                        new String[]{"外快"});
                for (int i = 0; i < perkList.size(); i++) {
                    int s = Integer.parseInt(perkList.get(i).get("money").toString());
                    perkSum += s;
                }

                arcChartViewArcArcChartView.setData(new int[]
                        {salarySum, perkSum}
                );
                break;
        }
    }
}
