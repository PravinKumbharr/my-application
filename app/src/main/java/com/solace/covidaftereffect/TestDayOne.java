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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class TestDayOne extends Fragment {

    RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4,radioGroup5,radioGroup6;
    DatabaseReference databaseReference;
    DatabaseReference dbref1,dbref2,dbref3,dbref4,dbref5;
    FirebaseUser user;
    String uid;
    Button button;
    TextView textView;
    private String mservices1,mservices2,mservices3,survey4,survey5,survey6;
    String queid=" ";
    Integer selectid;
    RadioButton radioButton;
    int i=0;
    long count = 1;
    long count1=0;
    FirebaseAuth firebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_test_day_one, container, false);
        radioGroup1 = view.findViewById(R.id.radioGroup1);
        radioGroup2 = view.findViewById(R.id.radioGroup2);
        radioGroup3 = view.findViewById(R.id.radioGroup3);
        radioGroup4 = view.findViewById(R.id.radioGroup4);
        radioGroup5 = view.findViewById(R.id.radioGroup5);
        radioGroup6 = view.findViewById(R.id.radioGroup6);

        button=view.findViewById(R.id.save);
        //textView = view.findViewById(R.id.queone);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (account != null)
        {
            uid=account.getId();

        }
        //firebaseAuth = FirebaseAuth.getInstance();
       // uid = firebaseAuth.getCurrentUser().getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Day1");
        dbref1=FirebaseDatabase.getInstance().getReference().child("Count1");
        dbref2=FirebaseDatabase.getInstance().getReference().child("Count2");
        dbref3=FirebaseDatabase.getInstance().getReference().child("Count3");
        dbref4=FirebaseDatabase.getInstance().getReference().child("Count4");
        dbref5=FirebaseDatabase.getInstance().getReference().child("Count5");
       // databaseReference2=FirebaseDatabase.getInstance().getReference().child("Users").child("Count2");
         selectid = radioGroup1.getCheckedRadioButtonId();
         radioButton= view.findViewById(selectid);

       // selectradiobtn();


       /* dbref.child("Users/Count1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get total available quest
                //int sum=0;
                //Integer role = (Integer) dataSnapshot.getValue();


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    int ultimaVersion =  dataSnapshot1.getValue(Integer.class);
                   // Map<String, Object> map = (Map<String, Object>) dataSnapshot1.getValue();
                    //Object count = map.get("Count1");
                    //int value =Integer.parseInt(String.valueOf(count));
                    System.out.println("count="+ultimaVersion);
                }

                //count = (int) dataSnapshot.getChildrenCount();
                //System.out.println("In onDataChange, count="+count);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        getUserInfo();

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.queoneradio0:
                        databaseReference.child("Que1").setValue("Yes");
                        dbref1.child("Count").setValue(count++);
                        //child(String.valueOf(count+1))
                        break;
                    case R.id.queoneradio1:
                        databaseReference.child("Que1").setValue("Little bit");
                        break;
                    case R.id.queoneradio2:
                        databaseReference.child("Que1").setValue("No");

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
                        databaseReference.child("Que2").setValue("Often");
                        dbref2.child("Count").setValue(count++);
                        break;
                    case R.id.queTwoRadio1:
                        databaseReference.child("Que2").setValue("Sometime");
                        break;
                    case R.id.queTwoRadio2:
                        databaseReference.child("Que2").setValue("Never");

                        //dbref.child("Count").setValue(count++);
                        //databaseReference2.child(String.valueOf(count++)).child("Que2").setValue("Never");
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
                        databaseReference.child("Que3").setValue("Yes");
                        dbref3.child("Count").setValue(count++);
                        break;
                    case R.id.queThreeRadio1:

                        databaseReference.child("Que3").setValue("Sometime");
                        break;
                    case R.id.queThreeRadio2:
                        databaseReference.child("Que3").setValue("Never");
                       // dbref.child("Count").setValue(count1+1);
                       // dbref.child(String.valueOf(count++)).child("Que3").setValue("Never");
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
                        databaseReference.child("Que4").setValue("Yes");
                        dbref4.child("Count").setValue(count++);
                        break;
                    case R.id.queFourRadio1:

                        databaseReference.child("Que4").setValue("Sometime");
                        break;
                    case R.id.queFourRadio2:
                        databaseReference.child("Que4").setValue("Never");
                       // dbref.child(String.valueOf(count++)).child("Que4").setValue("Never");
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
                        databaseReference.child("Que5").setValue("Yes");
                        dbref5.child("Count").setValue(count++);
                        break;
                    case R.id.queFiveRadio1:
                        databaseReference.child("Que5").setValue("Little bit");
                        break;
                    case R.id.queFiveRadio2:
                        databaseReference.child("Que5").setValue("No");
                       // dbref.child(String.valueOf(count++)).child("Que5").setValue("Never");
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
                        databaseReference.child("Que6").setValue("Lungs");
                        break;
                    case R.id.queSixRadio1:

                        databaseReference.child("Que6").setValue("Heart");
                        break;
                    case R.id.queSixRadio2:
                        databaseReference.child("Que6").setValue("Nervous System");
                       // dbref.child(String.valueOf(count++)).child("Que6").setValue("System");
                        break;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TestDayTwo testDayTwo = new TestDayTwo();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,testDayTwo);
                transaction.commit();
                /*Integer selectid = radioGroup.getCheckedRadioButtonId();
                final RadioButton radioButton = view.findViewById(selectid);

                mservices = radioButton.getText().toString();
                Map userInfo = new HashMap();
                userInfo.put("Que1", mservices);
                databaseReference.updateChildren(userInfo);*/

            }
        });

      /* radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               checkedId = group.getCheckedRadioButtonId();
               final RadioButton radioButton = view.findViewById(checkedId);

               mservices1 = radioButton.getText().toString();
               Map userInfo = new HashMap();
               userInfo.put("Que1", mservices1);
               databaseReference.updateChildren(userInfo);
               if (radioButton.getText() == null)
               {
                   return;
               }

           }
       });*/

        return view;

    }
    public static TestDayOne newInstance(String text) {

        TestDayOne f = new TestDayOne();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

/*
    private void selectradiobtn() {
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child("Day1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    // i = (int) dataSnapshot.getChildrenCount();

                    radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                    {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId){
                                case R.id.queoneradio0:

                                    databaseReference.child(String.valueOf(i+1)).child("Que1").setValue("Yes");
                                    break;
                                case R.id.queoneradio1:

                                   databaseReference.child("Que1").setValue("Little bit");
                                    break;
                                case R.id.queoneradio2:
                                    databaseReference.child("Que1").setValue("No");
                                    break;
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
*/


    private void getUserInfo() {

        //databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Day1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    // i = (int) dataSnapshot.getChildrenCount();
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    // Map userInfo = new HashMap();
                    if(map.get("Que1")!=null){
                        mservices1 = map.get("Que1").toString();
                        // textView.setText(dataSnapshot.child("name").getValue().toString());
                        switch (mservices1)
                        {
                            case "Yes":
                                radioGroup1.check(R.id.queoneradio0);
                                // databaseReference.push().setValue("Yes");
                                break;
                            case "Little bit":
                                radioGroup1.check(R.id.queoneradio1);
                                break;
                            case "No":
                                radioGroup1.check(R.id.queoneradio2);
                                // databaseReference.push().setValue("No");
                                break;
                        }

                    }

                    if(map.get("Que2")!=null){
                        mservices2 = map.get("Que2").toString();
                        // textView.setText(dataSnapshot.child("name").getValue().toString());
                        switch (mservices2)
                        {
                            case "Often":
                                radioGroup2.check(R.id.queTwoRadio0);
                                // databaseReference.push().setValue("Yes");
                                break;
                            case "Sometime":
                                radioGroup2.check(R.id.queTwoRadio1);
                                break;
                            case "Never":
                                radioGroup2.check(R.id.queTwoRadio2);
                                // databaseReference.push().setValue("No");
                                break;
                        }

                    }
                    if(map.get("Que3")!=null){
                        mservices3 = map.get("Que3").toString();
                        // textView.setText(dataSnapshot.child("name").getValue().toString());
                        switch (mservices3)
                        {
                            case "Yes":
                                radioGroup3.check(R.id.queThreeRadio0);
                                // databaseReference.push().setValue("Yes");
                                break;
                            case "Sometime":
                                radioGroup3.check(R.id.queThreeRadio1);
                                break;
                            case "Never":
                                radioGroup3.check(R.id.queThreeRadio2);
                                // databaseReference.push().setValue("No");
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


}