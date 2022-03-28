package com.linkanalyzer.linkanalyzer.ui.wifiDetails;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.linkanalyzer.linkanalyzer.R;
import com.linkanalyzer.linkanalyzer.databinding.FragmentGalleryBinding;
import com.linkanalyzer.linkanalyzer.linkInfo.ScanWifi;
import com.linkanalyzer.linkanalyzer.linkInfo.WiFiDetails;

import java.util.LinkedHashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    TableLayout table;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                table = (TableLayout)getView().findViewById(R.id.table);
                WiFiDetails wiFiDetails = new WiFiDetails();
                Map<String, String> data = new LinkedHashMap<String, String>();
                data = wiFiDetails.wifi_details(getView().getContext());

//
//                TableRow row = new TableRow(getActivity());
//                TextView item = new TextView(getActivity());
////                item.setId(200);
//                item.setText(data);
//
//                row.addView(item);
//                table.addView(row);
//
                int i = 0;
                for (Map.Entry<String, String> entry : data.entrySet()){
                    TableRow tbrow = new TableRow(getActivity());
                    if (i % 2 == 0) {
                        tbrow.setBackgroundColor(Color.rgb(211, 211, 211));
                    } else {
                        tbrow.setBackgroundColor(Color.rgb(192, 192, 192));
                    }

                    tbrow.setBackgroundResource(R.drawable.border);
                    tbrow.setPadding(0, 25, 0, 25);
                    TextView key_view = new TextView(getActivity());
                    key_view.setText(entry.getKey());
                    key_view.setTextSize(15);
                    key_view.setTextColor(Color.BLACK);
                    key_view.setGravity(Gravity.LEFT);
                    key_view.setPadding(10,10,10, 0);
                    tbrow.addView(key_view);
                    TextView val_view = new TextView(getActivity());
                    val_view.setText(entry.getValue());
                    val_view.setTextSize(15);
                    val_view.setTextColor(Color.BLACK);
                    val_view.setGravity(Gravity.RIGHT);
                    val_view.setPadding(10,10,10, 0);
                    tbrow.addView(val_view);
                    table.addView(tbrow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    i = i + 1;

                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}