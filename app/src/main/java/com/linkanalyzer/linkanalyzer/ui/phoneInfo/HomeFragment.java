package com.linkanalyzer.linkanalyzer.ui.phoneInfo;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.linkanalyzer.linkanalyzer.R;
import com.linkanalyzer.linkanalyzer.databinding.FragmentHomeBinding;
import com.linkanalyzer.linkanalyzer.linkInfo.ScanWifi;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TableLayout table;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                table = (TableLayout)getView().findViewById(R.id.PhoneInfoTable);
                ScanWifi scanWifi = new ScanWifi();

                Map<String, String> system_info = new LinkedHashMap<String, String>();
                WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiinfo = wifiManager.getConnectionInfo();
                system_info.put("MANUFACTURER", Build.MANUFACTURER);
                system_info.put("MODEL", Build.MODEL);
                system_info.put("PRODUCT", Build.PRODUCT);
                system_info.put("HOST", Build.HOST);
                system_info.put("ID", Build.ID);
                system_info.put("INCREMENTAL", Build.VERSION.INCREMENTAL);
                system_info.put("RELEASE", Build.VERSION.RELEASE);
                system_info.put("SDK No.", String.valueOf(Build.VERSION.SDK_INT));
                system_info.put("BOARD", Build.BOARD);
                system_info.put("BRAND", Build.BRAND);
                system_info.put("CPU_ABI", Build.CPU_ABI);
                system_info.put("HARDWARE", Build.HARDWARE);
                system_info.put("DEVICE", Build.DEVICE);
                system_info.put("USER", Build.USER);
                system_info.put("TAGS", Build.TAGS);
                system_info.put("TYPE", Build.TYPE);
                system_info.put("BOOTLOADER", Build.BOOTLOADER);
                system_info.put("RADIO", Build.RADIO);
                String radioversion = Build.getRadioVersion();
                String radio = "";
                List<String> radioVersionDetails = Arrays.asList(radioversion.split(","));
                for (String ch: radioVersionDetails){
                    radio += ch + "\n";
                }
                system_info.put("Radio", radio);
                String FingerPrint =  Build.FINGERPRINT;
                String fingerPrint = "";
                List<String> fingerPrintDetails = Arrays.asList(FingerPrint.split("/"));
                for (String ch: fingerPrintDetails){
                    fingerPrint += ch + "/" + "\n";
                }
                system_info.put("FINGERPRINT",fingerPrint);
//                system_info.put("FINGERPRINT",Build.FINGERPRINT);
                System.out.println("FINGERPRINT: "+Build.FINGERPRINT);

//                system_info.put("DISPLAY", Build.DISPLAY);
//                system_info.put("ODM_SKU", Build.ODM_SKU);
//                system_info.put("", Build.SOC_MANUFACTURER);
//                system_info.put("", Build.);

                Boolean AC_11 = null;
                Boolean AX_11 = null;
                Boolean N_11 = null;
                Boolean legacy = null;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    AC_11 = wifiManager.isWifiStandardSupported(ScanResult
                            .WIFI_STANDARD_11AC);
                } else {
                    AC_11 = false;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    AX_11 = wifiManager.isWifiStandardSupported(ScanResult
                            .WIFI_STANDARD_11AX);
                } else {
                    AX_11 = false;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    N_11 = wifiManager.isWifiStandardSupported(ScanResult
                            .WIFI_STANDARD_11N);
                } else {
                    N_11 = false;
                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    legacy = wifiManager.isWifiStandardSupported(ScanResult
                            .WIFI_STANDARD_LEGACY);
                } else {
                    legacy = false;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    AC_11 = wifiManager.isWifiStandardSupported(ScanResult
                            .WIFI_STANDARD_11AC);
                } else {
                    AC_11 = false;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    AX_11 = wifiManager.isWifiStandardSupported(ScanResult
                            .WIFI_STANDARD_11AX);
                } else {
                    AX_11 = false;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    N_11 = wifiManager.isWifiStandardSupported(ScanResult
                            .WIFI_STANDARD_11N);
                } else {
                    N_11 = false;
                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    legacy = wifiManager.isWifiStandardSupported(ScanResult
                            .WIFI_STANDARD_LEGACY);
                } else {
                    legacy = false;
                }

//                system_info.put("legacy", legacy.toString());
//                system_info.put("AC_11", AC_11.toString());
//                system_info.put("AX_11", AX_11.toString());
//                system_info.put("N_11", N_11.toString());
                int i = 0;
                for (Map.Entry<String, String> entry : system_info.entrySet()){
                    TableRow tbrow = new TableRow(getActivity());
                    if (i % 2 == 0) {
                        tbrow.setBackgroundColor(Color.rgb(211, 211, 211));
                    } else {
                        tbrow.setBackgroundColor(Color.rgb(255, 255, 255));
                    }

                    tbrow.setBackgroundResource(R.drawable.border);
                    tbrow.setPadding(5, 10, 0, 10);
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

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    wifi_capabilities.add(new StringKeyVal("5G", String.valueOf((wifiManager.is5GHzBandSupported()))));
//                } else {
//                    wifi_capabilities.add(new StringKeyVal("5G", String.valueOf(true)));
//                }
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    wifi_capabilities.add(new StringKeyVal("6G", String.valueOf((wifiManager.is6GHzBandSupported()))));
//                } else {
//                    wifi_capabilities.add(new StringKeyVal("6G", String.valueOf(true)));
//                }
//
//                if (Build.VERSION.SDK_INT >= 31) {
//                    // This was added in API 31, I guess before then 2.4 was always supported.
//                    wifi_capabilities.add(new StringKeyVal("2G", String.valueOf((wifiManager.is24GHzBandSupported())))); // This line gives an error
//                } else {
//                    wifi_capabilities.add(new StringKeyVal("2G", String.valueOf(true)));
//                }
//
//                wifi_mode.add(new StringKeyVal("11-AC", String.valueOf(AC_11)));
//                wifi_mode.add(new StringKeyVal("11-AX", String.valueOf(AX_11)));
//                wifi_mode.add(new StringKeyVal("11-N", String.valueOf(N_11)));
//                wifi_mode.add(new StringKeyVal("LEGACY", String.valueOf(legacy)));

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