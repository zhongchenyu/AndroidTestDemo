package chenyu.memorydemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BinderServiceActivity extends AppCompatActivity {
  private BinderService binderService = null;
  private boolean isBind = false;
  private boolean flag = false;
  @BindView(R.id.button_start_service) Button btnStartService;
  @BindView(R.id.button_stop_service) Button btnStopService;
  @BindView(R.id.button_bind_service) Button btnBindService;
  @BindView(R.id.button_unbind_service) Button btnUnBindService;

  private ServiceConnection connection = new ServiceConnection() {
    @Override public void onServiceConnected(ComponentName name, IBinder binder) {
      isBind = true;
      BinderService.MyBinder myBinder = (BinderService.MyBinder) binder;
      binderService = myBinder.getService();
      Log.i("MyService", "Activity - onServiceConnected");
      int num = binderService.getRandomNumber();
      Log.i("MyService", "Activity - getRandomNumber = " + num);
      binderService.startCountInNewThread();
    }

    @Override public void onServiceDisconnected(ComponentName name) {
      isBind = false;
      Log.i("MyService", "Activity - onServiceDisconnected");
    }
  };

  private ServiceConnection coon = new ServiceConnection() {
    @Override public void onServiceConnected(ComponentName name, IBinder service) {
      BindService.MyBinder binder = (BindService.MyBinder) service;
      BindService bindService = binder.getService();
      bindService.MyMethod();
      flag = true;
    }

    @Override public void onServiceDisconnected(ComponentName name) {
      flag = false;
    }
  };
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_binder_service);
    ButterKnife.bind(this);

    Log.i("MyService", "Activity - onCreate - Thread ID = " +
        Thread.currentThread().getId());
  }

  @OnClick({R.id.button_start_service, R.id.button_stop_service, R.id.button_bind_service,
      R.id.button_unbind_service}) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button_bind_service:
        Toast.makeText(this, "bind", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(
            BinderServiceActivity.this, BinderService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
        break;
      case R.id.button_unbind_service:
        Toast.makeText(this, "unbind", Toast.LENGTH_SHORT).show();
        if(isBind) {
          unbindService(connection);
          isBind = false;
        }
        break;
      case R.id.button_start_service:
        Intent intent1 = new Intent(this, BindService.class);
        bindService(intent1,coon, BIND_AUTO_CREATE);
        break;
      case R.id.button_stop_service:
        if(flag) {
          unbindService(coon);
          flag = false;
        }
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if(isBind) {
      unbindService(connection);
    }
    if(flag) {
      unbindService(coon);
    }
  }
}
