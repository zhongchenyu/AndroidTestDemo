package chenyu.memorydemo.JankMonitor;

/**
 * Created by chenyu on 2017/9/11.
 */

public interface LogPrinterListener {
  void onStartLoop();
  void onEndLoop(long starttime, long endtime, String loginfo, int level);
}
