package com.linkanalyzer.linkanalyzer.linkInfo;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import com.linkanalyzer.linkanalyzer.MainActivity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScanWifi extends AppCompatActivity {

    public Map<String, String> scan_wifi(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()){
            Map<String, String> scan_data = new LinkedHashMap<String, String>();
            scan_data.put("", "Wi-Fi  is Disabled");
            return scan_data;
        }
        wifiManager.setWifiEnabled(true);
        wifiManager.startScan();
        String data = "";
        String conneted_bssid = wifiManager.getConnectionInfo().getBSSID();

        Map<String, String> scan_data = new LinkedHashMap<String, String>();
//        wifiManager.startScan();
        List<ScanResult> scan_result = wifiManager.getScanResults();
        for(int i = 0; i < scan_result.size();i++) {
            ScanResult sr = scan_result.get(i);

            String ssid = sr.SSID; //Get the SSID
            if (ssid.equals(null) || ssid.equals("")) {
                ssid = "*hidden*";
                System.out.println("SSID::= " + ssid);
            }

            String bssid = sr.BSSID; //Get the BSSID
            String capability = sr.capabilities; //Get Wi-Fi capabilities
            if (capability.contains("WPA") && capability.contains("WPA2") && capability.contains("WPA3") ){
                capability = "WPA/WAP2/WPA3";
            }else if (capability.contains("WPA") && capability.contains("WPA2") ){
                capability = "WPA/WAP2";
            }else if (capability.contains("WPA")){
                capability = "WPA";
            }else if (capability.contains("WPA") && capability.contains("WPA2")){
                capability = "WAP2";
            }else if (capability.contains("OPEN")){
                capability = "OPEN";
            }
            int centerFreq0 = 0;
            int centerFreq1 = 0;
            int channelWidth = 0;
            if (Build.VERSION.SDK_INT >= 23) {
                centerFreq0 = sr.centerFreq0; //Get centerFreq0
                centerFreq1 = sr.centerFreq1; //Get centerFreq1
                channelWidth = sr.channelWidth; //Get channelWidth
            }
            int level = sr.level; //Get level/rssi
            int frequency = sr.frequency; //Get frequency
            int channels = 1;
            float frequency_ghz = 0.0F;
            if (frequency> 2411 && frequency< 2485){
                channels = (frequency - 2412)/5 + 1;
                frequency_ghz = 2.4F;
            }else if (frequency> 3657.5 && frequency< 3692.5){
//                channels = (frequency - 3657.5) / 2.5;
                frequency_ghz = 3.6F;
            }else if (frequency> 4940 && frequency< 4991){
                frequency_ghz = 4.9F;
            }else if (frequency> 5150 && frequency< 5725){
                frequency_ghz = 5.0F;
            }
//            int channel = sr.
            if (conneted_bssid != null && conneted_bssid.equals(bssid)) {
                ssid += "(connected)";
            }
            // timestamp is usec since boot.
            //java.lang.System.currentTimeMillis() - android.os.SystemClock.elapsedRealtime();
            long age = android.os.SystemClock.elapsedRealtime() - (sr.timestamp / 1000);
            age = age / 1000; //convert to seconds.

            //float dist = (float) Math.pow(10.0d, (27.55d - 40d * Math.log10(frequency) + 6.7d - level) / 20.0d) * 1000;
            //            double dist = getDistance(level, frequency);
            double dist = 0.00;
            String dist_in_meters = String.format("%.02f", dist);

            data = "〖\n\t\tSSID: " + ssid + "\n\t\tBSSID: " + bssid + "\n\t\tCenterFreq0: " +
                    centerFreq0 + " GHz\n\t\tCenterFreq1: " + centerFreq1 + " GHz\n\t\tChannelWidth: "
                    + channelWidth + " GHz\n\t\tRSSI: " + level + " dBm" + "\n\t\tAge: " + age +
                    " Sec\n\t\tdistance: " + dist_in_meters + " meter" + "\n\t\tFrequency: " +
                    frequency_ghz + " GHz" + "\n\t\tChannel: " + channels + "\n\t\tSecurities: " + capability + "\n\t〗";
            scan_data.put(String.valueOf(i + 1), String.valueOf(data));
//            System.out.println("Scan data: " + data);
        }
        return scan_data;
    }
}
