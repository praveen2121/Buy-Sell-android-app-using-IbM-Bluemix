

package com.ibm.mobileappbuilder.localsell20160923170219.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.localsell20160923170219.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;

/**
 * LocalSellFragment menu fragment.
 */
public class LocalSellFragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public LocalSellFragment(){
        super();
    }

    // Factory method
    public static LocalSellFragment newInstance(Bundle args) {
        LocalSellFragment fragment = new LocalSellFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
                }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("Buy")
            .setIcon(R.drawable.png_download30)
            .setAction(new StartActivityAction(BuyActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Sell")
            .setIcon(R.drawable.png_sellq345)
            .setAction(new StartActivityAction(SellActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_grid;
    }

    @Override
    public int getItemLayout() {
        return R.layout.localsell_item;
    }
}

