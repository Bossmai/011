package me.alfredis.monkey011;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        ActivityManager activityManager =  (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
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

        double latitude = 0;
        double longtitude = 0;
        LocationManager locationManager = (LocationManager) getSystemService((Context.LOCATION_SERVICE));
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();
            longtitude = location.getLongitude();
        }


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
        infoTableLayout.addView(createInforowTextView("T", "gps", longtitude + ", " + latitude));
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
        infoTableLayout.addView(createInforowTextView("TBD", "isUserAMonkey", String.valueOf(activityManager.isUserAMonkey())));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.CODENAME", String.valueOf(Build.VERSION.CODENAME)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.INCREMENTAL", String.valueOf(Build.VERSION.INCREMENTAL)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.RELEASE", String.valueOf(Build.VERSION.RELEASE)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.SDK", String.valueOf(Build.VERSION.SDK)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.SDK_INT", String.valueOf(Build.VERSION.SDK_INT)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.BOARD", String.valueOf(Build.BOARD)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.BOOTLOADER", String.valueOf(Build.BOOTLOADER)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.BRAND", String.valueOf(Build.BRAND)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.CPU_ABI", String.valueOf(Build.CPU_ABI)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.CPU_ABI2", String.valueOf(Build.CPU_ABI2)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.DEVICE", String.valueOf(Build.DEVICE)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.DISPLAY", String.valueOf(Build.DISPLAY)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.FINGERPRINT", String.valueOf(Build.FINGERPRINT)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.HARDWARE", String.valueOf(Build.HARDWARE)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.HOST", String.valueOf(Build.HOST)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.ID", String.valueOf(Build.ID)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.MANUFACTURER", String.valueOf(Build.MANUFACTURER)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.MODEL", String.valueOf(Build.MODEL)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.PRODUCT", String.valueOf(Build.PRODUCT)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.RADIO", String.valueOf(Build.RADIO)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.SERIAL", String.valueOf(Build.SERIAL)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.TAGS", String.valueOf(Build.TAGS)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.TIME", String.valueOf(Build.TIME)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.TYPE", String.valueOf(Build.TYPE)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.USER", String.valueOf(Build.USER)));
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.radioVersion", String.valueOf(Build.getRadioVersion())));


        /*String[] temp = Build.SUPPORTED_32_BIT_ABIS;
        StringBuilder sb = new StringBuilder();
        for (String s : temp) {
            sb.append(s + "\n");
        }
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.SUPPORTED_32_BIT_ABIS", sb.toString()));

        String[] temp2 = Build.SUPPORTED_64_BIT_ABIS;
        StringBuilder sb2 = new StringBuilder();
        for (String s : temp) {
            sb.append(s + "\n");
        }
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.SUPPORTED_64_BIT_ABIS", sb2.toString()));

        String[] temp3 = Build.SUPPORTED_ABIS;
        StringBuilder sb3 = new StringBuilder();
        for (String s : temp) {
            sb.append(s + "\n");
        }
        infoTableLayout.addView(createInforowTextView("TBD", "Build.VERSION.SUPPORTED_ABIS", sb3.toString()));*/



        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader("/sys/class/net/wlan0/address");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            sb.append(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        infoTableLayout.addView(createInforowTextView("T", "file", sb.toString()));

        if (systemPropertiesClass != null && systemProperties != null && systemPropertiesGetMethod != null) {
            try {
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.bat", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.bat", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.gps", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.gps", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.acc", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.acc", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.cam", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.cam", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.scr", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.scr", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.rmt", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.rmt", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.did", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.did", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.net", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.net", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genymotion.version", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.genyd.caps.bat", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.hardware", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"ro.boot.hardware", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"androVM.gles.first_try", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"androVM.gles.renderer", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"androVM.gles", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"androVM.inited", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"androVM.vbox_dpi", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"androVM.vbox_graph_mode", "no message"})));
                infoTableLayout.addView(createInforowTextView("T", "ro.genyd.caps.baseband", (String) systemPropertiesGetMethod.invoke(systemProperties, new String[] {"androVM.vkeyboard_mode", "no message"})));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
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
