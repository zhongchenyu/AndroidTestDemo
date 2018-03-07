package chenyu.memorydemo.JankMonitor;

import android.os.Looper;
import android.util.Log;
import chenyu.memorydemo.JankMonitor.sampling.CpuInfo;
import chenyu.memorydemo.JankMonitor.sampling.CpuInfoSampler;
import java.io.File;

/**
 * Created by chenyu on 2017/9/11.
 */

public class UiPerfMonitor implements UiPerfMonitorConfig, LogPrinterListener{
  private static UiPerfMonitor mInstance = null;
  private final String TAG = "UiPerfMonitor";
  private LogPrinter mLogPrinter;
  private LogWriteThread mLogWriteThread;
  private int monitorState = UI_PERF_MONITER_STOP;
  private CpuInfoSampler mCpuInfoSampler = null;

  public synchronized static UiPerfMonitor getmInstance() {
    if(null == mInstance) {
      mInstance = new UiPerfMonitor();
    }
    return mInstance;
  }

  public UiPerfMonitor() {
    mCpuInfoSampler = new CpuInfoSampler();
    mLogPrinter = new LogPrinter(this);
    mLogWriteThread = new LogWriteThread();
    initLogpath();
  }

  public void startMonitor() {
    Looper.getMainLooper().setMessageLogging(mLogPrinter);
    monitorState = UI_PERF_MONITER_START;
  }

  public void stopMonitor() {
    Looper.getMainLooper().setMessageLogging(null);
    mCpuInfoSampler.stop();
    monitorState = UI_PERF_MONITER_STOP;
  }

  public boolean isMonitoring() {
    return monitorState == UI_PERF_MONITER_START;
  }

  private void initLogpath() {
    File logpath = new File(LOG_PATH);
    if(!logpath.exists()) {
      boolean mkdir = logpath.mkdir();
      Log.d(TAG, "mkdir: " + mkdir + ": " + LOG_PATH);
    }
  }

  @Override public void onStartLoop() {
    mCpuInfoSampler.start();
  }

  @Override public void onEndLoop(long starttime, long endtime, String loginfo, int level) {
    mCpuInfoSampler.stop();
    switch (level) {
      case UI_PERF_LEVEL_1:
        Log.d(TAG, "onEndLoop TIME_WARNING_LEVEL_1 & cupsize: " + mCpuInfoSampler.getStatCpuInfoList().size());
        StringBuffer stringBuffer = new StringBuffer("startTime:");
        stringBuffer.append(starttime);
        stringBuffer.append(" endTime:");
        stringBuffer.append(endtime);
        stringBuffer.append(" handlerTime");
        stringBuffer.append(endtime - starttime);
        for(CpuInfo info: mCpuInfoSampler.getStatCpuInfoList()) {
          stringBuffer.append("\r\n");
          stringBuffer.append(info.toString());
        }
        mLogWriteThread.saveLog(stringBuffer.toString());
        break;
      case UI_PERF_LEVEL_2:
        break;
      default:
    }
  }
}
