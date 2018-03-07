package chenyu.memorydemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
  public MyService() {
  }

  @Override
  public IBinder onBind(Intent intent) {
    // TODO: Return the communication channel to the service.
    Log.i("MyService", "onBind - Thread ID = " + Thread.currentThread().getId());
    return null;
  }

  @Override public void onCreate() {
    super.onCreate();
    Log.i("MyService", "onCreate - Thread ID = " + Thread.currentThread().getId());

  }

  @Override public int onStartCommand(Intent intent,
      //@IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY}, flag = true)
      int flags, int startId) {
    Log.i("MyService",
        "onStartCommand - startId = " + startId + ", Thread ID = " + Thread.currentThread()
            .getId());
    return super.onStartCommand(intent, flags, startId);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    Log.i("MyService", "onDestroy - Thread ID = " + Thread.currentThread().getId());
  }
}
