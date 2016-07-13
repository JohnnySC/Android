package github.johnnysc.privatecontacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.*;

import github.johnnysc.privatecontacts.Contacts.ContactContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    public static SimpleItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        try {
            FileIO.loadFile(openFileInput(FileIO.FILE_NAME));
        } catch (FileNotFoundException e) {
            ContactContent.myMap = new HashMap<>();
            ContactContent.ITEMS = new ArrayList<>();
            ContactContent.ITEM_MAP = new HashMap<>();
            e.printStackTrace();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactListActivity.this, CreateContact.class);
                startActivity(intent);
            }
        });

        View recyclerView = findViewById(R.id.contact_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.contact_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        sortContacts();
        adapter = new SimpleItemRecyclerViewAdapter(ContactContent.ITEMS);
        recyclerView.setAdapter(adapter);
    }

    public static void sortContacts(){
        Collections.sort(ContactContent.ITEMS, new Comparator<ContactContent.ContactItem>() {
            @Override
            public int compare(ContactContent.ContactItem lhs, ContactContent.ContactItem rhs) {
                return lhs.name.compareTo(rhs.name);
            }
        });
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<ContactContent.ContactItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<ContactContent.ContactItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contact_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mContentView.setText(mValues.get(position).name);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ContactDetailFragment.ARG_ITEM_ID, holder.mItem.number);
                        ContactDetailFragment fragment = new ContactDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contact_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ContactDetailActivity.class);
                        intent.putExtra(ContactDetailFragment.ARG_ITEM_ID, holder.mItem.number);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;
            public ContactContent.ContactItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
