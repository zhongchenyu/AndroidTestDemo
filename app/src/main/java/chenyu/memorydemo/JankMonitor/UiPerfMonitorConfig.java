package chenyu.memorydemo.JankMonitor;

import android.os.Environment;

/**
 * Created by chenyu on 2017/9/11.
 */

public interface UiPerfMonitorConfig {
  public int TIME_WARNING_LEVEL_1 = 100;
  public int TIME_WARNING_LEVEL_2 = 300;

  public final int UI_PERF_LEVEL_0 = 0;
  public final int UI_PERF_LEVEL_1 = 1;
  public final int UI_PERF_LEVEL_2 = 2;
  public String LOG_PATH = Environment.getExternalStorageDirectory().getPath() + "androidtech/uiperf";

  public final int UI_PERF_MONITER_START = 0x01;
  public final int UI_PERF_MONITER_STOP = 0x01 << 1;

  public final String FILENAME = "UiMonitorLog";
}
