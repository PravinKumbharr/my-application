package com.solace.covidaftereffect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class TestDayTwo extends Fragment {

    RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4,radioGroup5,radioGroup6;
    DatabaseReference databaseReference2;
    DatabaseReference dbref;
    Button button;
    TextView textView;
    private String survey1,survey2,survey3,survey4,survey5,survey6;
    Integer selectid;
    RadioButton radioButton;
    long count = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_day_two, container, false);

        radioGroup1 = view.findViewById(R.id.radioGroup1);
        radioGroup2 = view.findViewById(R.id.radioGroup2);
        radioGroup3 = view.findViewById(R.id.radioGroup3);
        radioGroup4 = view.findViewById(R.id.radioGroup4);
        radioGroup5 = view.findViewById(R.id.radioGroup5);
        radioGroup6 = view.findViewById(R.id.radioGroup6);

        button=view.findViewById(R.id.save);

        databaseReference2=FirebaseDatabase.getInstance().getReference().child("Users").child("Day2");
       // dbref=FirebaseDatabase.getInstance().getReference().child("Users").child("Count");

        selectid = radioGroup1.getCheckedRadioButtonId();
        radioButton= view.findViewById(selectid);

        getUserInfo();

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.queoneradio0:
                        databaseReference2.child("Que1").setValue("Yes");
                        break;
                    case R.id.queoneradio1:

                        databaseReference2.child("Que1").setValue("Little bit");
                        break;
                    case R.id.queoneradio2:
                        databaseReference2.child("Que1").setValue("No");
                        break;
                }
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.queTwoRadio0:

                        databaseReference2.child("Que2").setValue("Often");
                        break;
                    case R.id.queTwoRadio1:

                        databaseReference2.child("Que2").setValue("Sometime");
                        break;
                    case R.id.queTwoRadio2:
                        databaseReference2.child("Que2").setValue("Never");
                        break;
                }
            }
        });
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.queThreeRadio0:

                        databaseReference2.child("Que3").setValue("Yes");
                        break;
                    case R.id.queThreeRadio1:

                        databaseReference2.child("Que3").setValue("Sometime");
                        break;
                    case R.id.queThreeRadio2:
                        databaseReference2.child("Que3").setValue("Never");
                        dbref.child(String.valueOf(count++)).child("Que2").setValue("Never");
                        break;
                }
            }
        });
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.queFourRadio0:

                        databaseReference2.child("Que4").setValue("Often");
                        break;
                    case R.id.queFourRadio1:

                        databaseReference2.child("Que4").setValue("Sometime");
                        break;
                    case R.id.queFourRadio2:
                        databaseReference2.child("Que4").setValue("Never");
                        break;
                }
            }
        });

        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.queFiveRadio0:

                        databaseReference2.child("Que5").setValue("Yes");
                        break;
                    case R.id.queFiveRadio1:

                        databaseReference2.child("Que5").setValue("Little bit");
                        break;
                    case R.id.queFiveRadio2:
                        databaseReference2.child("Que5").setValue("No");
                        break;
                }
            }
        });
        radioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.queSixRadio0:

                        databaseReference2.child("Que6").setValue("Lungs");
                        break;
                    case R.id.queSixRadio1:

                        databaseReference2.child("Que6").setValue("Heart");
                        break;
                    case R.id.queSixRadio2:
                        databaseReference2.child("Que6").setValue("Nervous System");
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDayThree testDayThree = new TestDayThree();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,testDayThree);
                transaction.commit();
            }
        });
        return view;
    }

    private void getUserInfo() {
        //String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //databaseReference2=FirebaseDatabase.getInstance().getReference().child("Users").child("Day1");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    // i = (int) dataSnapshot.getChildrenCount();
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    // Map userInfo = new HashMap();
                    if(map.get("Que1")!=null){

                        survey1 = map.get("Que1").toString();
                        switch (survey1)
                        {
                            case "Yes":
                                radioGroup1.check(R.id.queoneradio0);
                                break;
                            case "Little bit":
                                radioGroup1.check(R.id.queoneradio1);
                                break;
                            case "No":
                                radioGroup1.check(R.id.queoneradio2);
                                break;
                        }

                    }

                    if(map.get("Que2")!=null){
                        survey2 = map.get("Que2").toString();

                        switch (survey2)
                        {
                            case "Often":
                                radioGroup2.check(R.id.queTwoRadio0);
                                break;
                            case "Sometime":
                                radioGroup2.check(R.id.queTwoRadio1);
                                break;
                            case "Never":
                                radioGroup2.check(R.id.queTwoRadio2);
                                break;
                        }

                    }

                    if(map.get("Que3")!=null)
                    {
                        survey3 = map.get("Que3").toString();
                        switch (survey3)
                        {
                            case "Yes":
                                radioGroup3.check(R.id.queThreeRadio0);
                                break;
                            case "Sometime":
                                radioGroup3.check(R.id.queThreeRadio1);
                                break;
                            case "Never":
                                radioGroup3.check(R.id.queThreeRadio2);
                                break;
                        }

                    }
                    if(map.get("Que4")!=null){
                        survey4 = map.get("Que4").toString();
                        // textView.setText(dataSnapshot.child("name").getValue().toString());
                        switch (survey4)
                        {
                            case "Yes":
                                radioGroup4.check(R.id.queFourRadio0);
                                // databaseReference.push().setValue("Yes");
                                break;
                            case "Sometime":
                                radioGroup4.check(R.id.queFourRadio1);
                                break;
                            case "Never":
                                radioGroup4.check(R.id.queFourRadio2);
                                // databaseReference.push().setValue("No");
                                break;
                        }

                    }
                    if(map.get("Que5")!=null){
                        survey5 = map.get("Que5").toString();
                        // textView.setText(dataSnapshot.child("name").getValue().toString());
                        switch (survey5)
                        {
                            case "Yes":
                                radioGroup5.check(R.id.queFiveRadio0);
                                // databaseReference.push().setValue("Yes");
                                break;
                            case "Sometime":
                                radioGroup5.check(R.id.queFiveRadio1);
                                break;
                            case "Never":
                                radioGroup5.check(R.id.queFiveRadio2);
                                // databaseReference.push().setValue("No");
                                break;
                        }

                    }
                    if(map.get("Que6")!=null){
                        survey6 = map.get("Que6").toString();
                        // textView.setText(dataSnapshot.child("name").getValue().toString());
                        switch (survey6)
                        {
                            case "Lungs":
                                radioGroup6.check(R.id.queSixRadio0);
                                // databaseReference.push().setValue("Yes");
                                break;
                            case "Heart":
                                radioGroup6.check(R.id.queSixRadio1);
                                break;
                            case "Nervous System":
                                radioGroup6.check(R.id.queSixRadio2);
                                // databaseReference.push().setValue("No");
                                break;
                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public static TestDayTwo newInstance(String text) {

        TestDayTwo f = new TestDayTwo();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }
}
