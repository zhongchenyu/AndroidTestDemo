package chenyu.memorydemo.application;

import android.app.Application;
import android.content.Context;
import chenyu.memorydemo.Monitor.TimeMonitorConfig;
import chenyu.memorydemo.Monitor.TimeMonitorManager;

/**
 * Created by chenyu on 2017/8/29.
 */

public class MemoryDemoApp extends Application {

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    TimeMonitorManager.getInstance()
        .resetTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START);
  }

  public void onCreate() {
    super.onCreate();
    TimeMonitorManager.getInstance()
        .getTimeMonitor(TimeMonitorConfig.TIME_MONITOR_ID_APPLICATION_START)
        .recodingTimeTag("ApplicationCreate");
  }
}
