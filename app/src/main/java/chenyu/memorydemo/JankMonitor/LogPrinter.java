package chenyu.memorydemo.JankMonitor;

import android.util.Log;
import android.util.Printer;

/**
 * Created by chenyu on 2017/9/11.
 */

public class LogPrinter implements Printer, UiPerfMonitorConfig{
  private final String TAG = "LogPrinter";
  private LogPrinterListener mLogPrinter = null;
  private long startTime = 0;

  public LogPrinter(LogPrinterListener listener) {
    mLogPrinter = listener;
  }

  @Override public void println(String x) {
    if (startTime <=0) {
      startTime = System.currentTimeMillis();
      mLogPrinter.onStartLoop();
    } else  {
      long endTime = System.currentTimeMillis() ;
      execuTime(x, startTime, endTime);
      startTime = 0;
    }
  }

  private void execuTime(String loginfo, long startTime, long endTime) {
    int level = 0;
    long time = endTime - startTime;
    Log.d(TAG, "dispatch handler time: " + time);
    if(time > TIME_WARNING_LEVEL_2) {
      Log.e(TAG, "Warning_LEVEL_2: \r\n" + "println: " + loginfo);
      level = UI_PERF_LEVEL_2;
    }else if(time > TIME_WARNING_LEVEL_1) {
      Log.e(TAG, "Warning_LEVEL_1: \r\n" + "println: " + loginfo);
      level = UI_PERF_LEVEL_1;
    }
    mLogPrinter.onEndLoop(startTime, endTime, loginfo, level);
  }
}
