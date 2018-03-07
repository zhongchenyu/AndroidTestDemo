package chenyu.memorydemo;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chenyu.memorydemo.Monitor.TimeMonitorConfig;
import chenyu.memorydemo.Monitor.TimeMonitorManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  TextView txtHeapSize;
  Button btnOOMActivity;
  Button btnAnimationActivity;
  Button btnANR;
  @BindView(R.id.button_tel) Button btnTel;
  @BindView(R.id.button_life) Button btnLifeActivity;
  @BindView(R.id.button_intent) Button btnIntent;
  @BindView(R.id.button_start_service) Button btnStartService;
  @BindView(R.id.button_stop_service) Button btnStopService;
  @BindView(R.id.button_bind_service_activity) Button btnBindServiceActivity;
  @BindView(R.id.button_register_receiver) Button btnRegisterReceiver;
  @BindView(R.id.button_send_broadcast) Button btnSendBroadcast;
  @BindView(R.id.button_async_activity) Button btnAsyncActivity;
  @BindView(R.id.button_jank_monitor_acitivity) Button btnJankActivity;
  @BindView(R.id.button_web_view) Button btnWebView;
  LinearLayout l;
  private MyReceiver receiver;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    TimeMonitorManager.getInstance()
        .getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START)
        .recodingTimeTag("MainActivity_create");

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    txtHeapSize = (TextView) findViewById(R.id.heep_size);
    btnOOMActivity = (Button) findViewById(R.id.oom_activity_button);
    btnAnimationActivity = (Button) findViewById(R.id.button_animation_activity);
    btnANR = (Button) findViewById(R.id.button_ANR);

    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    int heepSize = manager.getMemoryClass();
    txtHeapSize.setText(String.valueOf(heepSize));
    btnOOMActivity.setOnClickListener(this);
    btnAnimationActivity.setOnClickListener(this);
    btnANR.setOnClickListener(this);
    btnLifeActivity.setOnClickListener(this);
    TimeMonitorManager.getInstance()
        .getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START)
        .recodingTimeTag("MainActivity_createOver");
  }

  @Override protected void onStart() {
    super.onStart();
    TimeMonitorManager.getInstance()
        .getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START)
        .end("MainActivity_start", false);
  }

  @Override public void onClick(View view) {
    //Toast.makeText(this, "onclick", Toast.LENGTH_SHORT).show();
    switch (view.getId()) {
      case R.id.oom_activity_button:
        //Toast.makeText(this, "OOM", Toast.LENGTH_SHORT).show();
        OOMActivity.startActivity(this);
        break;
      case R.id.button_animation_activity:
        AnimationActivity.startActivity(this);
        break;
      case R.id.button_ANR:
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        break;
      case R.id.button_life:
        Intent intent = new Intent(this, LifecycleActivity.class);
        startActivity(intent);
        break;
    }
  }

  @OnClick(R.id.button_tel) public void onClickBtn(View view) {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, 1);
    } else {
      Intent intent = new Intent();
      intent.setAction(Intent.ACTION_CALL);
      intent.setData(Uri.parse("tel://10086"));
      startActivity(intent);
    }
  }

  @OnClick(R.id.button_intent) public void onClickIntent(View view) {
    Intent intent = new Intent(this, IntentActivity.class);
    intent.putExtra("name", "赵云");
    intent.putExtra("age", 12);
    startActivity(intent);
  }

  @OnClick({R.id.button_start_service, R.id.button_stop_service, R.id.button_bind_service_activity})
  public void onClickService(View view) {
    switch (view.getId()) {
      case R.id.button_start_service:
        Intent intent1 = new Intent(this, MyService.class);
        startService(intent1);
        break;
      case R.id.button_stop_service:
        Intent intent2 = new Intent(this, MyService.class);
        stopService(intent2);
        break;
      case R.id.button_bind_service_activity:
        Intent intent3 = new Intent(this, BinderServiceActivity.class);
        startActivity(intent3);
        break;
    }
  }

  @OnClick({R.id.button_register_receiver, R.id.button_send_broadcast})
  public void onClickBroadcast(View view) {
    switch (view.getId()) {
      case R.id.button_register_receiver:
        IntentFilter filter = new IntentFilter("chenyu.memorydemo.test.broadcast");
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);
        break;
      case R.id.button_send_broadcast:
        Intent intent = new Intent("chenyu.memorydemo.test.broadcast");
        sendBroadcast(intent);
        break;
    }
  }

  @OnClick(R.id.button_async_activity) public void onClickAsync(View view) {
    Intent intent = new Intent(this, AsyncActivity.class);
    startActivity(intent);
  }
  @OnClick(R.id.button_jank_monitor_acitivity) public void onClickJank(View view) {
    Intent intent = new Intent(this, JankMonitorActivity.class);
    startActivity(intent);
  }
  @OnClick(R.id.button_web_view) public void onClickWebView(View view) {
    Intent intent = new Intent(this, WebViewActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
  @Override protected void onDestroy() {
    super.onDestroy();
    if(receiver != null) {
      unregisterReceiver(receiver);
    }
  }


}
