package com.journal.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.journal.app.activities.JNMainActivity;
import com.journal.app.activities.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public class JNUserDiaryHistory extends Fragment{
   private AbsListView mainFragmentLayout ;
   private JNHistoryAdapter adapter;
    List<User> ary = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jn_fragment_diary_activities, container, false);
        mainFragmentLayout = (ListView) view.findViewById(R.id.listView_activity);
        mainFragmentLayout.getAdapter();

        return view;
    }





    public void onCollectionDataLoadFinished(Collection<? extends User> jnUser) {

        this.setAdapter((Queue<User>) jnUser);
    }



    private void onDataLoadStart (View view) {
        this.onDataLoadStart(view);


    }

    public void setAdapter(Queue<User> data) {

        if (data != null) {
            Log.d("COUNT", "" + data.size());
            List<User> ary = new ArrayList<>(data);
            initializeAdapter(ary);
        }
    }





    private void initializeAdapter(List<User> ary) {
        adapter = new JNHistoryAdapter(ary, (JNMainActivity) this.getActivity());
        mainFragmentLayout.setAdapter(adapter);

    }



        public class  JNHistoryAdapter extends BaseAdapter {
            private List<User> activities = new ArrayList<>();
            private JNMainActivity context = new JNMainActivity();
            private LayoutInflater inflater = null;

            public JNHistoryAdapter(List<User> user, JNMainActivity context) {
                super();
                this.activities = user;
                this.context = context;
                this.inflater = (LayoutInflater) context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            }

            @Override
            public int getCount() {
                return activities.size();
            }

            @Override
            public Object getItem(int position) {
                return activities.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                    final ListItemHolder listItemHolder;

                    if (convertView == null) {
                        //inflate the layout
                        convertView = inflater.inflate(R.layout.jn_history_list_item, parent, false);

                        //setup the viewholder
                        listItemHolder = new ListItemHolder();
                        listItemHolder.name = (TextView) convertView.findViewById(R.id.receiver_name);
                        listItemHolder.message = (TextView) convertView.findViewById(R.id.message);
                        listItemHolder.itemView = convertView;

                        convertView.setTag(listItemHolder);
                    } else {
                        listItemHolder = (ListItemHolder) convertView.getTag();
                    }

                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getInstance().getReference("Diary/Users");
                databaseReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded( DataSnapshot dataSnapshot,  String s) {
                        User newUser = dataSnapshot.getValue(User.class);
                        listItemHolder.name.setText("User" +newUser.email);
                        listItemHolder.message.setText("Diary" +newUser.message);


                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot,  String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                adapter = new JNHistoryAdapter(activities, context);
                mainFragmentLayout.setAdapter(adapter);

       return convertView;

            }
        }


        public static class User {
            private String email;
            private String message;

            public User(String email, String message) {
                this.email = email;
                this.message = message;
            }
        }

    static class ListItemHolder {
        View itemView;
        TextView name;
        TextView message;


    }

}
