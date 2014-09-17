package net.atlassc.imagepicker.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import com.github.ShinChven.util.BitmapUtil;

import java.io.FileNotFoundException;


public class MainActivity extends ActionBarActivity {

    private static final int SELECT_IMAGE = 0x001;
    private static final String TAG = "MainActivity";
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = ((ImageView) findViewById(R.id.img));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.add_image) {
            Intent intt = new Intent(Intent.ACTION_GET_CONTENT);
            intt.setType("image/*");
            startActivityForResult(Intent.createChooser(intt, "select your atlas"), SELECT_IMAGE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_IMAGE && data != null && data.getData() != null) {
            Toast.makeText(this, "image arrived", Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap = BitmapUtil.zoomIn(this, data.getData(), 1000, 500);
                Log.i(TAG, bitmap.getWidth() + " " + bitmap.getHeight());
                mImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
//        Bitmap bmp= BitmapFactory.de

        super.onActivityResult(requestCode, resultCode, data);
    }


}
