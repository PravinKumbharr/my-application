package com.solace.covidaftereffect;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class HomeActivitivity extends Fragment {

    private TextView mLowLabel, mMidLabel, mHighLabel,quefour,quefive;
    private BarView mLowBar, mMidBar, mHighBar,que4,que5;

    //Some sample percentage values
    int q1;
    private final int mid = 90;
    private final int high = 50;
    private final int c4 = 30;
    private final int c5 = 40;
    DatabaseReference databaseref;
  // long count;
   // private String count;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_activitivity, container, false);
        mLowBar = (BarView) view.findViewById(R.id.low_bar);
        mMidBar = (BarView)  view.findViewById(R.id.mid_bar);
        mHighBar = (BarView) view.findViewById(R.id.high_bar);
        que4 = (BarView)  view.findViewById(R.id.q4_bar);
        que5 = (BarView)  view.findViewById(R.id.q5_bar);

        mLowLabel = (TextView)  view.findViewById(R.id.low_text);
        mMidLabel = (TextView)  view.findViewById(R.id.mid_text);
        mHighLabel = (TextView)  view.findViewById(R.id.high_text);
        quefour = (TextView)  view.findViewById(R.id.q4_text);
        quefive = (TextView)  view.findViewById(R.id.q5_text);


        databaseref= FirebaseDatabase.getInstance().getReference();
        databaseref.child("Users/Count1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get total available quest
                //int sum=0;

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                     q1 = dataSnapshot1.getValue(Integer.class);

                    getcount(q1);
                    //Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    //Object count = map.get("Count1");
                    //int value =Integer.parseInt(String.valueOf(count));
                    System.out.println("Data count="+q1);
                }

                //count = (int) dataSnapshot.getChildrenCount();
                //System.out.println("In onDataChange, count="+count);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

      //  getcount();

        mMidBar.set(Color.RED, mid);
        mHighBar.set(Color.RED, high);
        que4.set(Color.RED,c4);
        que5.set(Color.RED,c5);


        mMidLabel.setText(getPercentage(mid));
        mHighLabel.setText(getPercentage(high));
        quefour.setText(getPercentage(c4));
        quefive.setText(getPercentage(c5));



        return view;
    }

    private void getcount(int q1) {
        mLowBar.set(Color.RED, q1);
        mLowLabel.setText(getPercentage(q1));
    }


    private String getPercentage(int per) {
        return per + "%";
    }


}
