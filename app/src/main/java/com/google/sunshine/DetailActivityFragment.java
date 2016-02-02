package com.google.sunshine;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private String forecastStr;

    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        forecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
        ((TextView) rootView.findViewById(R.id.fragment_detail_textView))
                .setText(forecastStr);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItem itemShare = menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(itemShare);

        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(createShareIntent());
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    private Intent createShareIntent() {
        //Log.d("DetailActivity", "Message: " + message);
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        //sendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, forecastStr + FORECAST_SHARE_HASHTAG);
        return sendIntent;
    }
}
