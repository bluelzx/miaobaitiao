package com.example.apple.weijie.fragment;

import android.animation.Animator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.weijie.R;
import com.example.apple.weijie.Adapter.ExpandableDetailAdapter;
import com.example.apple.weijie.dao.BillItem;
import com.example.apple.weijie.dao.TimeItem;
import com.example.apple.weijie.model.DBManager;
import com.example.apple.weijie.util.AnimatorTools;

import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends Fragment implements View.OnClickListener {

    private ImageView expandDeleteImageView = null;
    private ImageView expandEditImageView = null;
    private View parentView;
    private ExpandableListView elvDetail;
    private ExpandableDetailAdapter expandableDetailAdapter;
    private TextView tvDetailAction;

    private DBManager dbManager;
    private List<List<BillItem>> billItemLists = new ArrayList<>();
    private List<TimeItem> timeItemList = new ArrayList<>();

    public DetailFragment() {
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_detail, container, false);
        initView();
        return parentView;
    }

    private void initView() {
        elvDetail = (ExpandableListView) parentView.findViewById(R.id.lv_detail);
        elvDetail.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.layout_detail_head, null));
        tvDetailAction = (TextView) parentView.findViewById(R.id.tv_detail_action);

        initData();
        tvDetailAction.setOnClickListener(this);
        expandableDetailAdapter = new ExpandableDetailAdapter(this.getActivity(), timeItemList, billItemLists);
        elvDetail.setAdapter(expandableDetailAdapter);
        elvDetail.setGroupIndicator(null);
        for (int i = 0; i < expandableDetailAdapter.getGroupCount(); i++) {
            elvDetail.expandGroup(i);
        }
        expandableDetailAdapter.setOnBillItemTypeClickListener(new ExpandableDetailAdapter.OnBillItemTypeClickListener() {
            @Override
            public void onClick(View convertView, final int groupPosition, final int childPosition) {

                if (expandDeleteImageView != null && expandEditImageView != null) {
                    AnimatorTools.startTranslationAndRotationAnimator(expandEditImageView, expandEditImageView.getX()
                            , expandEditImageView.getY(), expandEditImageView.getX() - 200f, expandEditImageView.getY(), 300, new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    if (expandEditImageView != null) {
                                        expandEditImageView.setVisibility(View.GONE);
                                        expandEditImageView = null;
                                    }
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                    AnimatorTools.startTranslationAndRotationAnimator(expandDeleteImageView, expandDeleteImageView.getX()
                            , expandDeleteImageView.getY(), expandDeleteImageView.getX() + 200f, expandDeleteImageView.getY(), 300, new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    if (expandDeleteImageView != null) {
                                        expandDeleteImageView.setVisibility(View.GONE);
                                        expandDeleteImageView = null;
                                    }
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                } else {
                    ImageView ivBillType = (ImageView) convertView.findViewById(R.id.iv_detail_type);
                    LinearLayout llBillType = (LinearLayout) convertView.findViewById(R.id.ll_detail_type);
                    final ImageView ivBillEdit = (ImageView) convertView.findViewById(R.id.iv_detail_edit);
                    final ImageView ivBillDelete = (ImageView) convertView.findViewById(R.id.iv_detail_delete);
                    ivBillDelete.setVisibility(View.VISIBLE);
                    ivBillEdit.setVisibility(View.VISIBLE);
                    float fromX = llBillType.getX();
                    float fromY = ivBillType.getY();
                    AnimatorTools.startTranslationAndRotationAnimator(ivBillEdit, fromX, fromY
                            , fromX + 200f, fromY, 300, new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    expandEditImageView = ivBillEdit;
                                    ivBillEdit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Toast.makeText(DetailFragment.this.getActivity(), "editgroup:" + groupPosition + "editchild:" + childPosition, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                    AnimatorTools.startTranslationAndRotationAnimator(ivBillDelete, fromX, fromY
                            , fromX - 200f, fromY, 300, new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    expandDeleteImageView = ivBillDelete;
                                    ivBillDelete.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Toast.makeText(DetailFragment.this.getActivity(), "deletegroup:"
                                                    + groupPosition + "deletechild:" + childPosition, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });
                }
            }
        });

        expandableDetailAdapter.setOnBillItemDeleteClickListener(new ExpandableDetailAdapter.OnBillItemDeleteClickListener() {
            @Override
            public void onClick(int groupPosition, int childPosition) {
                Toast.makeText(DetailFragment.this.getActivity(), "group:" + groupPosition + "child:" + childPosition, Toast.LENGTH_SHORT).show();
            }
        });

        expandableDetailAdapter.setOnBillItemEditClickListener(new ExpandableDetailAdapter.OnBillItemEditClickListener() {
            @Override
            public void onClick(int groupPosition, int childPosition) {
                Toast.makeText(DetailFragment.this.getActivity(), "group:" + groupPosition + "child:" + childPosition, Toast.LENGTH_SHORT).show();
            }
        });
        elvDetail.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(final ExpandableListView expandableListView, View view, int i, long l) {
                Toast.makeText(DetailFragment.this.getActivity(), "group:" + i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        elvDetail.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(DetailFragment.this.getActivity(), "group:" + i + "child:" + i1, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        elvDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                colseExpandBillMenu();
                return false;
            }
        });
    }

    private void colseExpandBillMenu() {
        if (expandDeleteImageView != null && expandEditImageView != null) {
            AnimatorTools.startTranslationAndRotationAnimator(expandEditImageView, expandEditImageView.getX()
                    , expandEditImageView.getY(), expandEditImageView.getX() - 200f, expandEditImageView.getY(), 300, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            if (expandEditImageView != null) {
                                expandEditImageView.setVisibility(View.GONE);
                                expandEditImageView = null;
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
            AnimatorTools.startTranslationAndRotationAnimator(expandDeleteImageView, expandDeleteImageView.getX()
                    , expandDeleteImageView.getY(), expandDeleteImageView.getX() + 200f, expandDeleteImageView.getY(), 300, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            if (expandDeleteImageView != null) {
                                expandDeleteImageView.setVisibility(View.GONE);
                                expandDeleteImageView = null;
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
        }
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void refreshData(){
        initData();
        if(expandableDetailAdapter != null){
            expandableDetailAdapter.notifyDataSetChanged();
        }
        for(int i=0; i<timeItemList.size(); i++){
            elvDetail.expandGroup(i);
        }
    }

    private void  initData() {
        dbManager = new DBManager(getActivity());
        List<BillItem> billItemList = dbManager.queryAllBill();
        Log.e("---->OUT", "size:" + billItemList.size());
        if (billItemList.size() > 0) {
            int dayAmount = 0;
            int billTime = billItemList.get(0).getBillTime();
            List<BillItem> billItemChildList = new ArrayList<>();
            for (int i = 0; i < billItemList.size(); i++) {
                BillItem billItem = billItemList.get(i);
                Log.e("---->OUT", "isspend:" + billItem.isSpend());
                if (billTime == billItem.getBillTime()) {
                    billItemChildList.add(billItem);
                    if(billItem.isSpend()){
                        dayAmount = dayAmount + billItem.getAmount();
                    }else{
                        dayAmount = dayAmount - billItem.getAmount();
                    }

                    if (i == billItemList.size() - 1) {
                        billItemLists.add(billItemChildList);
                        timeItemList.add(new TimeItem(dayAmount, billTime));
                    }
                } else {
                    billItemLists.add(billItemChildList);
                    timeItemList.add(new TimeItem(dayAmount, billTime));
                    billTime = billItem.getBillTime();
                    billItemChildList = new ArrayList<>();
                    dayAmount = 0;
                    billItemChildList.add(billItem);
                    if(billItem.isSpend()){
                        dayAmount = dayAmount + billItem.getAmount();
                    }else{
                        dayAmount = dayAmount - billItem.getAmount();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_detail_action:

                break;
        }
    }

}
