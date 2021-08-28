package com.lyondry.lyondry.Adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.lyondry.lyondry.Fragments.CompletedFragment;
import com.lyondry.lyondry.Fragments.PendingkFragment;
import com.lyondry.lyondry.Fragments.PurchaseofferFragment;
public class Pay_Tab_Adapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public Pay_Tab_Adapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PendingkFragment pendingFragment = new PendingkFragment();
                return pendingFragment;
            case 1:
                CompletedFragment completedFragment = new CompletedFragment();
                return completedFragment;
            case 2:
                PurchaseofferFragment purchaseofferFragment = new PurchaseofferFragment();
                return purchaseofferFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}