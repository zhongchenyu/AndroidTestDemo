package chenyu.memorydemo;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import java.util.Random;

/**
 * Created by chenyu on 2017/9/5.
 */

public class BinderService extends MyService {
  public class MyBinder extends Binder {
    public BinderService getService() {
      return BinderService.this;
    }
  }

  private MyBinder binder = new MyBinder();
  private final Random generator = new Random();

  @Override public IBinder onBind(Intent intent) {
    Log.i("MyService", "BinderService - onBind - Thread = " + Thread.currentThread().getId());
    return binder;
  }

  @Override public boolean onUnbind(Intent intent) {
    Log.i("MyService", "BinderService - onUnbind - from = " + intent.getStringExtra("from"));
    return false;
  }

  public int getRandomNumber() {
    return generator.nextInt();
  }

  public void startCountInNewThread() {

    new Thread(new Runnable() {
      @Override public void run() {
        for(int i=0;i<100;i++) {
          try{
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          Log.i("BinderService", "Thread ID: " + Thread.currentThread().getId() + " " + String.valueOf(i));
        }
      }
    }).start();
  }

  public void startCount(int n) {
    for(int i=0;i<n;i++) {
      try{
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Log.i("BinderService", "Thread ID: " + Thread.currentThread().getId() + " " + String.valueOf(i));
    }
  }
}
