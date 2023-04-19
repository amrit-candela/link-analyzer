package com.linkanalyzer.linkanalyzer.ui.cellularInfo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.linkanalyzer.linkanalyzer.R;
import com.linkanalyzer.linkanalyzer.databinding.FragmentHomeBinding;
import com.linkanalyzer.linkanalyzer.linkInfo.ScanWifi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CellularDetailsFragment extends Fragment {

    private CellularDetailsViewModel cellularDetailsViewModel;
    private FragmentHomeBinding binding;
    private String deviceId;
    TableLayout table;


    public CellularDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cellularDetailsViewModel =
                new ViewModelProvider(this).get(CellularDetailsViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cellularDetailsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @SuppressLint("HardwareIds")
            @Override
            public void onChanged(String s) {
                table = (TableLayout) getView().findViewById(R.id.PhoneInfoTable);
                ScanWifi scanWifi = new ScanWifi();

                Map<String, String> cell_info = new LinkedHashMap<String, String>();
                TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                cell_info.put("PHONE TYPE", String.valueOf(telephonyManager.getPhoneType()));
                cell_info.put("SIM OPERATOR", telephonyManager.getSimOperatorName());
                cell_info.put("SIM OPERATOR NUMBER", telephonyManager.getSimOperator());
                // Getting Device ID
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    deviceId = Settings.Secure.getString(
                            getActivity().getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                } else {
                    if (telephonyManager.getDeviceId() != null) {
                        deviceId = telephonyManager.getDeviceId();
                    } else {
                        deviceId = Settings.Secure.getString(
                                getActivity().getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                    }
                }
                cell_info.put("IEMI", deviceId);
                int data = telephonyManager.getDataState();
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                List<CellInfo> data_1 = telephonyManager.getAllCellInfo();
                System.out.println("DATA_1: " + data_1);
//                DATA_1: [CellInfoLte:{mRegistered=YES mTimeStamp=4747487116129715ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=2******16 mPci=346 mTac=3***4 mEarfcn=1415 mBands=[3] mBandwidth=2147483647 mMcc=404 mMnc=49 mAlphaLong=Airtel mAlphaShort=Airtel mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=2147483647 rsrp=-94 rsrq=-6 rssnr=1 cqiTableIndex=2147483647 cqi=2147483647 ta=4 level=3 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }}, CellInfoLte:{mRegistered=YES mTimeStamp=4746415300326799ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=1*****32 mPci=98 mTac=2***5 mEarfcn=41140 mBands=[41] mBandwidth=2147483647 mMcc=404 mMnc=07 mAlphaLong=Vi India mAlphaShort=Vi India mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=-67 rsrp=-113 rsrq=-6 rssnr=1 cqiTableIndex=2147483647 cqi=2147483647 ta=0 level=1 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }}, CellInfoLte:{mRegistered=NO mTimeStamp=4746415300326799ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=0 mPci=98 mTac=0 mEarfcn=515 mBands=[1] mBandwidth=2147483647 mMcc=null mMnc=null mAlphaLong= mAlphaShort= mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=-61 rsrp=-106 rsrq=-9 rssnr=0 cqiTableIndex=2147483647 cqi=0 ta=0 level=1 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }}, CellInfoLte:{mRegistered=NO mTimeStamp=4746415300326799ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=0 mPci=98 mTac=0 mEarfcn=1565 mBands=[3] mBandwidth=2147483647 mMcc=null mMnc=null mAlphaLong= mAlphaShort= mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=-61 rsrp=-111 rsrq=-8 rssnr=0 cqiTableIndex=2147483647 cqi=0 ta=0 level=1 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }}, CellInfoLte:{mRegistered=NO mTimeStamp=4746415300326799ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=0 mPci=17 mTac=0 mEarfcn=1 mBands=[1] mBandwidth=2147483647 mMcc=null mMnc=null mAlphaLong= mAlphaShort= mAdditionalPlmns={} mCsgInfo=null} CellSignalStrengthLte: rssi=-55 rsrp=-82 rsrq=2147483647 rssnr=0 cqiTableIndex=2147483647 cqi=0 ta=0 level=4 parametersUseForLevel=0 android.telephony.CellConfigLte :{ isEndcAvailable = false }}]
//                cell_info.put("IP", )

                int i = 0;
                for (Map.Entry<String, String> entry : cell_info.entrySet()){
                    TableRow tbrow = new TableRow(getActivity());
                    if (i % 2 == 0) {
                        tbrow.setBackgroundColor(Color.rgb(211, 211, 211));
                    } else {
                        tbrow.setBackgroundColor(Color.rgb(255, 255, 255));
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
}