package me.alfredis.monkey011;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MainActivity extends ActionBarActivity {
    private TableLayout infoTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTableLayout = (TableLayout) findViewById(R.id.information_table_layout);

        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        CellLocation cellLocation = tm.getCellLocation();
        int lac = 0, cid = 0;
        GsmCellLocation gsmCellLocation = null;
        if (cellLocation != null && cellLocation instanceof GsmCellLocation) {
            gsmCellLocation = (GsmCellLocation) cellLocation;
            lac = gsmCellLocation.getLac();
            cid = gsmCellLocation.getCid();
        }

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        Class systemPropertiesClass = null;
        Object systemProperties = null;
        Method systemPropertiesGetMethod = null;
        try {
            systemPropertiesClass = Class.forName("android.os.SystemProperties");
            systemProperties = systemPropertiesClass.newInstance();
            Class[] argClasses = new Class[2];
            argClasses[0] = String.class;
            argClasses[1] = String.class;
            systemPropertiesGetMethod = systemPropertiesClass.getMethod("get", argClasses);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Object[] args = new Object[2];
        args[0] = "gsm.version.baseband";
        args[1] = "no message";


        infoTableLayout.addView(createInforowTextView("TBD", "connect_mode", "Not found."));
        infoTableLayout.addView(createInforowTextView("T", "density", String.valueOf(metric.density)));
        infoTableLayout.addView(createInforowTextView("T", "densityDpi", String.valueOf(metric.densityDpi)));
        if (systemPropertiesClass != null && systemProperties != null && systemPropertiesGetMethod != null) {
            try {
                infoTableLayout.addView(createInforowTextView("T", "get", (String) systemPropertiesGetMethod.invoke(systemProperties, args)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        infoTableLayout.addView(createInforowTextView("T", "getBSSID", wifiInfo.getBSSID()));
        infoTableLayout.addView(createInforowTextView("T", "getDeviceId", tm.getDeviceId()));
        infoTableLayout.addView(createInforowTextView("T", "getJiZhan", lac + "_" + cid));
        infoTableLayout.addView(createInforowTextView("T", "getLine1Number", tm.getLine1Number()));
        infoTableLayout.addView(createInforowTextView("T", "getMacAddress", wifiInfo.getMacAddress()));
        infoTableLayout.addView(createInforowTextView("T", "getMetrics", metric.widthPixels + "x" + metric.heightPixels));
        infoTableLayout.addView(createInforowTextView("T", "getNetworkCountryIso", tm.getNetworkCountryIso()));
        infoTableLayout.addView(createInforowTextView("T", "getNetworkOperator", tm.getNetworkOperator()));
        infoTableLayout.addView(createInforowTextView("T", "getNetworkOperatorName", tm.getNetworkOperatorName()));
        infoTableLayout.addView(createInforowTextView("T", "getNetworkType", String.valueOf(tm.getNetworkType())));
        infoTableLayout.addView(createInforowTextView("T", "getPhoneType", String.valueOf(tm.getPhoneType())));
        infoTableLayout.addView(createInforowTextView("T", "getRadioVersion", Build.getRadioVersion()));
        infoTableLayout.addView(createInforowTextView("T", "getSSID", wifiInfo.getSSID()));
        infoTableLayout.addView(createInforowTextView("T", "getSimCountryIso", tm.getSimCountryIso()));
        infoTableLayout.addView(createInforowTextView("T", "getSimOperator", tm.getSimOperator()));
        infoTableLayout.addView(createInforowTextView("T", "getSimOperatorName", tm.getSimOperatorName()));
        infoTableLayout.addView(createInforowTextView("T", "getSimSerialNumber", tm.getSimSerialNumber()));
        infoTableLayout.addView(createInforowTextView("T", "getSimState", String.valueOf(tm.getSimState())));
        infoTableLayout.addView(createInforowTextView("T", "getString", Settings.Secure.getString(getContentResolver(), "android_id")));
        infoTableLayout.addView(createInforowTextView("T", "getSubscriberId", tm.getSubscriberId()));
        infoTableLayout.addView(createInforowTextView("TBD", "gps", "Not found."));
        infoTableLayout.addView(createInforowTextView("T", "scaledDensity", String.valueOf(metric.scaledDensity)));
        infoTableLayout.addView(createInforowTextView("TBD", "setCpuName", "ASM code."));
        infoTableLayout.addView(createInforowTextView("TBD", "sign", "Not found."));
        infoTableLayout.addView(createInforowTextView("T", "xdpi", String.valueOf(metric.xdpi)));
        infoTableLayout.addView(createInforowTextView("T", "ydpi", String.valueOf(metric.ydpi)));
        infoTableLayout.addView(createInforowTextView("T", "ARCH", Build.CPU_ABI + "_" + Build.CPU_ABI2));
        infoTableLayout.addView(createInforowTextView("T", "BRAND", Build.BRAND));
        infoTableLayout.addView(createInforowTextView("T", "DEVICE", Build.DEVICE));
        infoTableLayout.addView(createInforowTextView("T", "FINGERPRINT", Build.FINGERPRINT));
        infoTableLayout.addView(createInforowTextView("T", "HARDWARE", Build.HARDWARE));
        infoTableLayout.addView(createInforowTextView("T", "MANUFACTURER", Build.MANUFACTURER));
        infoTableLayout.addView(createInforowTextView("T", "MODEL", Build.MODEL));
        infoTableLayout.addView(createInforowTextView("T", "PRODUCT", Build.PRODUCT));
        infoTableLayout.addView(createInforowTextView("T", "RELEASE", Build.VERSION.RELEASE));
        infoTableLayout.addView(createInforowTextView("T", "SDK", Build.VERSION.SDK));
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
