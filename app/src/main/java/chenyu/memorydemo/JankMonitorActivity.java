package chenyu.memorydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chenyu.memorydemo.JankMonitor.UiPerfMonitor;

public class JankMonitorActivity extends AppCompatActivity {
  @BindView(R.id.button_start_monitor) Button btnStartMonitor;
  @BindView(R.id.button_stop_monitor) Button btnStopMonitor;
  @BindView(R.id.button_delay) Button btnDelay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_jank_monitor);
    ButterKnife.bind(this);
  }

  @OnClick({R.id.button_start_monitor, R.id.button_stop_monitor}) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button_start_monitor:
        UiPerfMonitor.getmInstance().startMonitor();
        break;
      case R.id.button_stop_monitor:
        UiPerfMonitor.getmInstance().stopMonitor();
        break;
    }
  }
  @OnClick(R.id.button_delay) public void onClickDelay(View view) {
    try {
      Thread.sleep(5000);

    }catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
