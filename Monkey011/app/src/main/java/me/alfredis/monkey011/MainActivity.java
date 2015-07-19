package me.alfredis.monkey011;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private TableLayout infoTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTableLayout = (TableLayout) findViewById(R.id.information_table_layout);

        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();


        infoTableLayout.addView(createInforowTextView("TBD", "connect_mode", "TBD"));
        infoTableLayout.addView(createInforowTextView("F", "density", String.valueOf(metric.density)));
        infoTableLayout.addView(createInforowTextView("F", "densityDpi", String.valueOf(metric.densityDpi)));
        infoTableLayout.addView(createInforowTextView("TBD", "get", "TBD"));
        infoTableLayout.addView(createInforowTextView("T", "getBSSID", wifiInfo.getBSSID()));
        infoTableLayout.addView(createInforowTextView("T", "getDeviceId", tm.getDeviceId()));
        infoTableLayout.addView(createInforowTextView("TBD", "getJiZhan", "TBD"));
        infoTableLayout.addView(createInforowTextView("T", "getLine1Number", tm.getLine1Number()));
        infoTableLayout.addView(createInforowTextView("T", "getMacAddress", wifiInfo.getMacAddress()));
        infoTableLayout.addView(createInforowTextView("TBD", "getMetrics", "TBD"));
        infoTableLayout.addView(createInforowTextView("T", "getNetworkCountryIso", tm.getNetworkCountryIso()));
        infoTableLayout.addView(createInforowTextView("T", "getNetworkOperator", tm.getNetworkOperator()));
        infoTableLayout.addView(createInforowTextView("T", "getNetworkOperatorName", tm.getNetworkOperatorName()));
        infoTableLayout.addView(createInforowTextView("F", "getNetworkType", String.valueOf(tm.getNetworkType())));
        infoTableLayout.addView(createInforowTextView("F", "getPhoneType", String.valueOf(tm.getPhoneType())));

        infoTableLayout.addView(createInforowTextView("F", "getRadioVersion", String.valueOf(Build.getRadioVersion())));
        infoTableLayout.addView(createInforowTextView("T", "getSSID", wifiInfo.getSSID()));
        infoTableLayout.addView(createInforowTextView("T", "getSimCountryIso", tm.getSimCountryIso()));
        infoTableLayout.addView(createInforowTextView("T", "getSimOperator", tm.getSimOperator()));
        infoTableLayout.addView(createInforowTextView("T", "getSimOperatorName", tm.getSimOperatorName()));
        infoTableLayout.addView(createInforowTextView("T", "getSimSerialNumber", tm.getSimSerialNumber()));
        infoTableLayout.addView(createInforowTextView("F", "getSimState", String.valueOf(tm.getSimState())));
        infoTableLayout.addView(createInforowTextView("TBD", "getString", "TBD"));
        infoTableLayout.addView(createInforowTextView("T", "getSubscriberId", tm.getSubscriberId()));
        infoTableLayout.addView(createInforowTextView("TBD", "gps", "TBD"));
        infoTableLayout.addView(createInforowTextView("F", "scaledDensity", String.valueOf(metric.scaledDensity)));
        infoTableLayout.addView(createInforowTextView("TBD", "setCpuName", "TBD"));
        infoTableLayout.addView(createInforowTextView("TBD", "sign", "TBD"));
        infoTableLayout.addView(createInforowTextView("TBD", "xdpi", "TBD"));
        infoTableLayout.addView(createInforowTextView("TBD", "ydpi", "TBD"));
        infoTableLayout.addView(createInforowTextView("TBD", "ARCH", "TBD"));
        infoTableLayout.addView(createInforowTextView("F", "BRAND", Build.BRAND));
        infoTableLayout.addView(createInforowTextView("F", "DEVICE", Build.DEVICE));
        infoTableLayout.addView(createInforowTextView("F", "FINGERPRINT", Build.FINGERPRINT));
        infoTableLayout.addView(createInforowTextView("F", "HARDWARE", Build.HARDWARE));
        infoTableLayout.addView(createInforowTextView("F", "MANUFACTURER", Build.MANUFACTURER));
        infoTableLayout.addView(createInforowTextView("F", "MODEL", Build.MODEL));
        infoTableLayout.addView(createInforowTextView("F", "PRODUCT", Build.PRODUCT));
        infoTableLayout.addView(createInforowTextView("TBD", "RELEASE", "TBD"));
        infoTableLayout.addView(createInforowTextView("TBD", "SDK", "TBD"));







        infoTableLayout.addView(createInforowTextView("F", "Manufacturer", Build.MANUFACTURER));
        infoTableLayout.addView(createInforowTextView("F", "ModelName", Build.MODEL));
        infoTableLayout.addView(createInforowTextView("F", "ModelId", Build.ID));

        infoTableLayout.addView(createInforowTextView("T", "DeviceId", tm.getDeviceId()));
        infoTableLayout.addView(createInforowTextView("T", "SimOperatorName", tm.getSimOperatorName()));
        infoTableLayout.addView(createInforowTextView("T", "SimSerialNumber", tm.getSimSerialNumber()));
        //TODO: int value hook
        infoTableLayout.addView(createInforowTextView("F", "SimState", String.valueOf(tm.getSimState())));
        infoTableLayout.addView(createInforowTextView("T", "SubscriberId", tm.getSubscriberId()));

        //sb.append("manufacturer:" + Build.MANUFACTURER + "\n");
        //sb.append("modelName:" + Build.MODEL + "\n");
        //sb.append("modelId:" + Build.ID + "\n");

        //sb.append("\n");

        //from TelephonyManager
        //sb.append("IMEI:" + tm.getDeviceId() + "\n");
        //sb.append("SimOperatorName:" + tm.getSimOperatorName() + "\n");
        //sb.append("SimSerialNumber:" + tm.getSimSerialNumber() + "\n");
        //sb.append("SimState:" + tm.getSimState() + "\n");
        //sb.append("SubscriberId:" + tm.getSubscriberId() + "\n");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private TextView createInfoContentTextView(String text, int color) {
        TextView temp = new TextView(this);
        temp.setText(text);
        temp.setBackgroundColor(color);

        TableRow.LayoutParams tableRowParam = new TableRow.LayoutParams();
        tableRowParam.setMargins(2, 2, 2, 2);
        temp.setLayoutParams(tableRowParam);

        return temp;
    }

    private TableRow createInforowTextView(String isDone, String key, String value) {
        TableRow row = new TableRow(this);

        TableLayout.LayoutParams tableLayoutParam = new TableLayout.LayoutParams();
        tableLayoutParam.setMargins(2, 2, 2, 2);
        row.setLayoutParams(tableLayoutParam);
        if (isDone.equals("T")) {
            row.setBackgroundColor(getResources().getColor(android.R.color.white));
            row.addView(createInfoContentTextView(isDone, getResources().getColor(android.R.color.holo_orange_light)));
            row.addView(createInfoContentTextView(key, getResources().getColor(android.R.color.holo_orange_light)));
            row.addView(createInfoContentTextView(value, getResources().getColor(android.R.color.holo_orange_light)));
        } else {
            row.setBackgroundColor(getResources().getColor(android.R.color.white));
            row.addView(createInfoContentTextView(isDone, getResources().getColor(android.R.color.white)));
            row.addView(createInfoContentTextView(key, getResources().getColor(android.R.color.white)));
            row.addView(createInfoContentTextView(value, getResources().getColor(android.R.color.white)));
        }


        return row;

    }

}
