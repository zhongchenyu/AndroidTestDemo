package chenyu.memorydemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chenyu on 2017/9/5.
 */

public class BindService extends Service {
  private MyBinder myBinder = new MyBinder();

  @Nullable @Override public IBinder onBind(Intent intent) {
    return myBinder;
  }

  public void MyMethod() {
    Log.i("BindService", "MyMethod()");
  }
  public class MyBinder extends Binder {
    public BindService getService() {
      return BindService.this;
    }
  }
}
