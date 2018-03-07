package chenyu.memorydemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OOMActivity extends AppCompatActivity implements View.OnClickListener{
  Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_oom);
    button = (Button) findViewById(R.id.button);
    button.setOnClickListener(this);
    LeakClass leakClass = new LeakClass();
    LeakClass leakClass1 = new LeakClass();
    leakClass.start();
    //leakClass1.start();

    //LeakClass leakClass2 = new LeakClass();
    //LeakClass leakClass3 = new LeakClass();
    //LeakClass leakClass4 = new LeakClass();
    //LeakClass leakClass5 = new LeakClass();
    //LeakClass leakClass6 = new LeakClass();
    //LeakClass leakClass7 = new LeakClass();
    //LeakClass leakClass8 = new LeakClass();
    //LeakClass leakClass9 = new LeakClass();
  }

  public static void startActivity(Context context) {
    Intent intent = new Intent(context, OOMActivity.class);
    context.startActivity(intent);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button:
        OOMActivity.startActivity(this);
    }
  }


  class LeakClass extends Thread {
    String[] a = new String[10000000];
    String[] b = new String[10000000];
    //String[] c = new String[10000000];
    //String[] d = new String[10000000];
    @Override public void run() {
      while(true) {
        try {

          Thread.sleep(60 * 60 * 1000);
        }catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
