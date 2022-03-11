package com.linkanalyzer.linkanalyzer.linkInfo;

import android.content.Context;
import android.net.TrafficStats;

import androidx.appcompat.app.AppCompatActivity;

import com.linkanalyzer.linkanalyzer.MainActivity;

public class UploadDownload extends AppCompatActivity {

    public String[] uploaddownload(Context context){

            long now = System.currentTimeMillis();
            double TimeDifference = now - MainActivity.last_bps_time;
            if (TimeDifference == 0) {
                return new String[]{"0", "0", "0", "0"}; // no div by zero error!
            }
            String Tx;
            String Rx;
            long rx_bytes = TrafficStats.getTotalRxBytes();
            long tx_bytes = TrafficStats.getTotalTxBytes();

            double rxDiff = rx_bytes - MainActivity.last_rx_bytes;
            double txDiff = tx_bytes - MainActivity.last_tx_bytes;
            double txbits = ((txDiff) * 1000 / TimeDifference) * 8;
            double rxbits = ((rxDiff) * 1000 / TimeDifference) * 8;

            MainActivity.last_rx_bytes = rx_bytes;
            MainActivity.last_tx_bytes = tx_bytes;
            MainActivity.last_bps_time = now;

            // TODO:  More efficient to test for high numbers first and only assing Rx/Tx string once
            if (rxbits >= 1000) {
                double rxKb = rxbits / 1000;
                Rx = String.format("%.2f", rxKb) + " Kbps";
                if (rxKb >= 1000) {
                    double rxMb = rxKb / 1000;
                    Rx = String.format("%.2f", rxMb) + " Mbps";
                    if (rxMb >= 1000) {
                        double rxGb = rxMb / 1000;
                        Rx = String.format("%.2f", rxGb) + " Gbps";
                    }
                }
            } else {
                Rx = (long) (rxbits) + " bps";
            }

            if (txbits >= 1000) {
                double txKb = txbits / 1000;
                Tx = String.format("%.2f", txKb) + " Kbps";
                if (txKb >= 1000) {
                    double txMb = txKb / 1000;
                    Tx = String.format("%.2f", txMb) + " Mbps";
                    if (txMb >= 1000) {
                        double txGb = txKb / 1000;
                        Tx = String.format("%.2f", txGb) + " Gbps";
                    }
                }
            } else {
                Tx = (long) (txbits) + " bps";
            }


            //System.out.println("count: " + count);
//            MainActivity.link_speed.setTextSize(15);
//            MainActivity.link_speed.setText(Rx + "/" + Tx);

            String unitRx = Rx.substring(Rx.length() - 4);
            double downlink = 0;
            if (unitRx.equals(" bps")) {
                downlink = 0;
            } else if (unitRx.equals("Mbps")) {
                downlink = Double.parseDouble(Rx.substring(0, Rx.length() - 4));
            }

            String unitTx = Tx.substring(Tx.length() - 4);
            double uplink = 0;
            if (unitTx.equals(" bps")) {
                uplink = 0;
            } else if (unitTx.equals("Mbps")) {
                uplink = Double.parseDouble(Tx.substring(0, Tx.length() - 4));
            }
            return new String[]{String.valueOf((int) downlink), String.valueOf((int) uplink), Rx, Tx};

    }
}
