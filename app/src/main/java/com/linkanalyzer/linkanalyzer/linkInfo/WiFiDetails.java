package com.linkanalyzer.linkanalyzer.linkInfo;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.Formatter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.LinkedHashMap;
import java.util.Map;

public class WiFiDetails extends AppCompatActivity {

    public Map<String, String> wifi_details(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiinfo = wifiManager.getConnectionInfo();
        String IP = null;
        String SSID = null;
        String BSSID = null;
        String WiFiStandard = "Unknown";
        int Rssi = 0;
        String LinkSpeed = null;
        String channel = null;
//        Hashtable<String, String> frequency_to_channel = new Hashtable<String, String>();
//        frequency_to_channel.put("2412", "1");
        if (wifiinfo.getSupplicantState() == SupplicantState.COMPLETED) {
            IP = Formatter.formatIpAddress(wifiinfo.getIpAddress());
            SSID = wifiinfo.getSSID();
            BSSID = wifiinfo.getBSSID();
            Rssi = wifiinfo.getRssi();
            LinkSpeed = wifiinfo.getLinkSpeed() + " Mbps";
            if (Build.VERSION.SDK_INT >= 21) {
                channel = wifiinfo.getFrequency() + " MHz";
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                int wifistandard =  wifiinfo.getWifiStandard();
                if (wifistandard == 4){
                    WiFiStandard = "Wi-Fi 802.11n";
                }
                else if (wifistandard == 5){
                    WiFiStandard = "Wi-Fi 802.11ac";
                }
                else if (wifistandard == 6){
                    WiFiStandard = "Wi-Fi 802.11ax";
                }
                else if (wifistandard == 7){
                    WiFiStandard = "Wi-Fi 802.11ad";
                }
                else if (wifistandard == 8){
                    WiFiStandard = "Wi-Fi 802.11be";
                }
            }

//                System.out.println("wifiinfo.getNetworkId(): " + wifiinfo.getNetworkId());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                System.out.println("wifiinfo.getNetworkId(): " + wifiinfo.getCurrentSecurityType());
            }
        }
        DhcpInfo Dhcp_details = wifiManager.getDhcpInfo();
        String dns1 = Formatter.formatIpAddress(Dhcp_details.dns1);
        String dns2 = Formatter.formatIpAddress(Dhcp_details.dns2);
        String serverAddress = Formatter.formatIpAddress(Dhcp_details.serverAddress);
        String gateway = Formatter.formatIpAddress(Dhcp_details.gateway);

        int leaseDuration = Dhcp_details.leaseDuration;

        long availMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long totalMem = Runtime.getRuntime().totalMemory();
        long usedMem = totalMem - availMem;
        String cpu_used_percent = String.format("%.2f", (usedMem / (double) totalMem) * 100);
        Map<String, String> live_data = new LinkedHashMap<String, String>();

        live_data.put("IP", String.valueOf(IP));
        live_data.put("SSID", SSID);
        live_data.put("BSSID", BSSID);
        live_data.put("Signal", Rssi + " dBm");
        live_data.put("LinkSpeed", LinkSpeed);
        live_data.put("Channel", channel);
        live_data.put("WiFiStandard", WiFiStandard);
        live_data.put("CPU util", cpu_used_percent + " %");
        live_data.put("DNS1", dns1);
        live_data.put("DNS2", dns2);
        live_data.put("DHCP Server", serverAddress);
        live_data.put("Gateway", gateway);
        live_data.put("LeaseDuration", leaseDuration + " Sec");
        return live_data;
    }
}
