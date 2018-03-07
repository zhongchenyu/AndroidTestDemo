package chenyu.memorydemo.JankMonitor.sampling;

import java.util.ArrayList;

/**
 * Created by chenyu on 2017/9/11.
 */

public class CpuInfoSampler extends BaseSample {
  private ArrayList<CpuInfo> mCpuInfoList = new ArrayList<CpuInfo>();

  @Override void doSample() {

  }

  public ArrayList<CpuInfo> getStatCpuInfoList(){
    return mCpuInfoList;
  }
}
