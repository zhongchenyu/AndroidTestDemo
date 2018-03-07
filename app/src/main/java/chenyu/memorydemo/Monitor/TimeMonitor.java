package chenyu.memorydemo.Monitor;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by chenyu on 2017/8/29.
 */

public class TimeMonitor {
  private final String TAG = "TimeMonitor";
  private int monitorId = -1;
  private HashMap<String, Long> mTimeTag = new HashMap<>();
  private long mStartTime = 0;

  public TimeMonitor(int id) {
    Log.d(TAG," init TimeMonitor id: " + id);
    monitorId = id;
    startMonitor();
  }

  public int getMonitorId() {
    return monitorId;
  }
  public void startMonitor() {
    if (mTimeTag.size() > 0) {
      mTimeTag.clear();
    }
    mStartTime = System.currentTimeMillis();
  }

  public void recodingTimeTag(String tag) {
    if(mTimeTag.get(tag) != null) {
      mTimeTag.remove(tag);
    }
    long time = System.currentTimeMillis() - mStartTime;
    Log.d(TAG, tag + ": " + time);
    mTimeTag.put(tag, time);
  }

  public void end(String tag, boolean writeLog) {
    recodingTimeTag(tag);
    end(writeLog);
  }

  public void end(boolean writeLog) {
    if(writeLog) {
      //Write log
    }
    testShowData();
  }

  public void testShowData() {
    if (mTimeTag.size() <= 0) {
      Log.e(TAG, "mTimeTag is empty!");
      return;
    }
    Iterator iterator = mTimeTag.keySet().iterator();
    while(iterator != null && iterator.hasNext()) {
      String tag = (String) iterator.next();
      Log.d(TAG, tag + ": " + mTimeTag.get(tag));
    }
  }

  public HashMap<String, Long> getmTimeTag() {
    return mTimeTag;
  }
}
