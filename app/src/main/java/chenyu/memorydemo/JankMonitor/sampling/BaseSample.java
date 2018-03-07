package chenyu.memorydemo.JankMonitor.sampling;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by chenyu on 2017/9/11.
 */

public abstract class BaseSample {
  private final String TAG = "BaseSampler";
  private Handler mControlHandler = null;
  private int intervalTime = 50; //ms
  private AtomicBoolean mIsSampling = new AtomicBoolean(false);

  public BaseSample() {
    Log.d(TAG, "Init BaseSampler");
  }

  public void start() {
    if(!mIsSampling.get()) {
      Log.d(TAG, "start Sampler");
      getmControlHandler().removeCallbacks(mRunnable);
      getmControlHandler().post(mRunnable);
      mIsSampling.set(true);

    }
  }

  public void stop() {
    if(mIsSampling.get()) {
      Log.d(TAG, "stop Sampler");
      getmControlHandler().removeCallbacks(mRunnable);
      mIsSampling.set(false);
    }
  }
  private Handler getmControlHandler() {
    if(null == mControlHandler) {
      HandlerThread mHT = new HandlerThread("SamplerThread");
      mHT.start();
      mControlHandler = new Handler(mHT.getLooper());
    }
    return mControlHandler;
  }

  abstract void doSample();

  private Runnable mRunnable = new Runnable() {
    @Override public void run() {
      doSample();
      if ((mIsSampling.get())) {
        getmControlHandler().postDelayed(mRunnable, intervalTime);
      }
    }
  };
}
