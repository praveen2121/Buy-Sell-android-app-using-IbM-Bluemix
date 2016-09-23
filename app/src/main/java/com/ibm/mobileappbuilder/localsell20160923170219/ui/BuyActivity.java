

package com.ibm.mobileappbuilder.localsell20160923170219.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.localsell20160923170219.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * BuyActivity list activity
 */
public class BuyActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.buyActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return BuyFragment.class;
    }

}

