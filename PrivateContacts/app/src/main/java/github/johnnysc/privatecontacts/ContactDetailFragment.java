package github.johnnysc.privatecontacts;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import github.johnnysc.privatecontacts.Contacts.ContactContent;

public class ContactDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    public static String phoneNumber;
    public static ContactContent.ContactItem mItem;

    public ContactDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = ContactContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.contact_detail)).setText(mItem.number);
            phoneNumber = mItem.number;
        }

        return rootView;
    }
}
