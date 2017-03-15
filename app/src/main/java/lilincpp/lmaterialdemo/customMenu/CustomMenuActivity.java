package lilincpp.lmaterialdemo.customMenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import lilincpp.lmaterialdemo.R;

/**
 * Created by lilin on 2017/3/13.
 */

public class CustomMenuActivity extends AppCompatActivity {

    private static final String TAG = "CustomMenuActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_menu);

        final ImageView imageView = (ImageView) findViewById(R.id.iv);
        final CustomMenuManager manager = CustomMenuManager.maekView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manager.isShowing()) {
                    manager.dismiss();
                } else {
                    manager.show();
                }
            }
        });

    }
}
