package me.alfredis.monkey011;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
