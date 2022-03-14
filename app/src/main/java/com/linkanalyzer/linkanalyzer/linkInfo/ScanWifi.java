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
        String data = "";
        String conneted_bssid = wifiManager.getConnectionInfo().getBSSID();

        Map<String, String> scan_data = new LinkedHashMap<String, String>();
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
//            int channel = sr.
            if (conneted_bssid.equals(bssid)) {
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

            data = "SSID: " + '\"' + ssid + '\"' + "\nbssid: " + bssid + "\ncenterFreq0: " +
                    centerFreq0 + "\tcenterFreq1: " + centerFreq1 + "\nchannelWidth: " + channelWidth +
                    "\t\uD83D\uDCF6 " + level + "\nFrequency " + frequency + "\tage⏱ " + age +
                    "\t\t\tdistance: " + dist_in_meters + "m\n" + "\uD83D\uDD12 " + capability;
            scan_data.put(String.valueOf(i + 1), String.valueOf(data));
//            System.out.println("Scan data: " + data);
        }
        return scan_data;
    }
}
