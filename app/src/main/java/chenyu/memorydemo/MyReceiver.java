package chenyu.memorydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by chenyu on 2017/9/6.
 */

public class MyReceiver extends BroadcastReceiver {
  @Override public void onReceive(Context context, Intent intent) {
    Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
    Log.d("MyReceiver", "onReceive");
  }
}
