package chenyu.memorydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IntentActivity extends AppCompatActivity {
  @BindView(R.id.text_name) TextView txtName;
  @BindView(R.id.text_age) TextView txtAge;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intent);
    ButterKnife.bind(this);
    txtName.setText(getIntent().getStringExtra("name"));
    txtAge.setText(String.valueOf(getIntent().getIntExtra("age",0)));
  }
}
