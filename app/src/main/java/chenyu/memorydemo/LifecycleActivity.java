package chenyu.memorydemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LifecycleActivity extends AppCompatActivity {
  @BindView(R.id.button_dialog) Button btnDialog;
  @BindView(R.id.button_trans) Button btnTransActivity;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lifecycle);
    ButterKnife.bind(this);
    Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    Log.d("Demo Lifecycle: ", "onCreate");
  }

  @Override protected void onStart() {
    super.onStart();
    Log.d("Demo Lifecycle: ", "onStart");
  }

  @Override protected void onRestart() {
    super.onRestart();
    Log.d("Demo Lifecycle: ", "onRestart");
  }

  @Override protected void onResume() {
    super.onResume();
    Log.d("Demo Lifecycle: ", "onResume");
  }

  @Override protected void onPause() {
    super.onPause();
    Log.d("Demo Lifecycle: ", "onPause");
  }

  @Override protected void onStop() {
    super.onStop();
    Log.d("Demo Lifecycle: ", "onStop");
  }

  @Override public void finish() {
    super.finish();
    Log.d("Demo Lifecycle: ", "finish");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    Log.d("Demo Lifecycle: ", "onDestroy");
  }

  @Override public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    super.onSaveInstanceState(outState, outPersistentState);
    Log.d("Demo Lifecycle: ", "onSaveInstanceState");
  }

  @Override
  public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onRestoreInstanceState(savedInstanceState, persistentState);
    Log.d("Demo Lifecycle: ", "onRestoreInstanceState");
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    Log.d("Demo Lifecycle: ", "onConfigurationChanged");
  }

  @OnClick({R.id.button_dialog, R.id.button_trans}) public void onClick(View view) {
    switch(view.getId()) {
      case R.id.button_dialog:
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog");
        builder.setMessage("OK?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        });
        builder.create().show();
        break;
      case R.id.button_trans:
        Intent intent = new Intent(this, TransActivity.class);
        startActivity(intent);
        break;
    }

  }
}
