package chenyu.memorydemo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
//import rx.functions.Action2;

public class AsyncActivity extends AppCompatActivity {
  @BindView(R.id.text) TextView textView;
  @BindView(R.id.button) Button button;
  @BindView(R.id.button_async) Button btnAsync;
  @BindView(R.id.button_rxjava) Button btnRxJava;
  int i = 0;

  private   Handler myHandler = new Handler() {
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case 1:
          textView.setText(msg.getData().getString("text") );
          break;
      }
      super.handleMessage(msg);
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_async);
    ButterKnife.bind(this);
    //rxJavaData();
  }

  @OnClick(R.id.button) public void onClick(View view) {
    //Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
    new Thread(new Runnable() {
      @Override public void run() {
        Message message = new Message();
        message.what = 1;
        Bundle bundle = new Bundle();
        bundle.putString("text","from new Thread " + String.valueOf(i++));
        message.setData(bundle);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        myHandler.sendMessage(message);
      }
    }).start();
  }

  @OnClick(R.id.button_async) public void onClickAsync(View view) {
    new MyAsyncTask().execute();
  }

  class MyAsyncTask extends AsyncTask<Void, Integer, Boolean>{

    @Override protected Boolean doInBackground(Void... params) {
      Log.d("Async", String.valueOf(Thread.currentThread().getId()));
      for(int i=0;i<100;i++) {
        try{Thread.sleep(100);}
        catch (InterruptedException e) {
          e.printStackTrace();
        };
        publishProgress(i);
      }
      return null;
    }

    @Override protected void onProgressUpdate(Integer... values) {
      textView.setText(Thread.currentThread().getName()+" "+values[0] + "%");
    }

    @Override protected void onPostExecute(Boolean aBoolean) {
      textView.setText("完成");
    }
  }

  public Observable<ArrayList<String>> getData() {
    ArrayList<String> list = new ArrayList<>();
    list.add("aaa");
    list.add("bbb");
    Log.d("Async", "getData Thread id = "+Thread.currentThread().getName());
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return Observable.just(list);
  }

  private void rxJavaData() {
    //getData()
     //ArrayList<String> list = new ArrayList<>();
    //list.add("ccc");
    //list.add("ddd");
    //Observable.just(list)
    /*
        Observable.create(new ObservableOnSubscribe<String>() {
          @Override public void subscribe(@NonNull ObservableEmitter<String> e)
              throws Exception {

           Log.d("Async", Thread.currentThread().getName() + " " + Thread.currentThread().getId());
            Thread.sleep(11000);
            e.onNext("aaa");
            e.onComplete();
          }
        })*/
    getDataCreate()
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
        new Observer<String>() {
          @Override public void onSubscribe(@NonNull Disposable d) {

          }

          @Override public void onNext(@NonNull String string) {

            textView.setText(string);
          }

          @Override public void onError(@NonNull Throwable e) {

          }

          @Override public void onComplete() {

          }
        });
  }

  private Observable<String> getDataCreate() {
    return Observable.create(new ObservableOnSubscribe<String>() {
      @Override public void subscribe(@NonNull ObservableEmitter<String> e)
          throws Exception {

        Log.d("Async", Thread.currentThread().getName() + " " + Thread.currentThread().getId());
        Thread.sleep(11000);
        e.onNext("aaa");
        e.onComplete();
      }
    });
  }
  @OnClick(R.id.button_rxjava) public void onClickRxJava(View view) {
    rxJavaData();
  }
}
