package anindya.sample.circlemove;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.appyvet.rangebar.RangeBar;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    //ImageView imageView;
    CircleImageView imageView;
    Canvas canvas;
    Paint paint;
    int radius;
    private float xCoOrdinate, yCoOrdinate;

    // Initializes the RangeBar in the application
    private RangeBar rangebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imageView = (ImageView)findViewById(R.id.imageView);
        imageView = (CircleImageView) findViewById(R.id.imageView);

        // Gets the RangeBar
        rangebar = (RangeBar) findViewById(R.id.rangebar);
        rangebar.setRangePinsByValue(0f, 1.0f );

        // Sets the display values of the indices
        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                int rangeValue = rightPinIndex;
                if(rangeValue==0){
                    Log.d("right_range_xmpp: " , String.valueOf(rangeValue));
                    int dimensionInPixel = 150;
                    int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionInDp, dimensionInDp);
                    imageView.setLayoutParams(parms);
                }
                else if(rangeValue==1){
                    Log.d("right_range_xmpp: " , String.valueOf(rangeValue));
                    int dimensionInPixel = 175;
                    int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionInDp, dimensionInDp);
                    imageView.setLayoutParams(parms);
                }
                else if(rangeValue==2){
                    Log.d("right_range_xmpp: " , String.valueOf(rangeValue));
                    int dimensionInPixel = 200;
                    int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionInDp, dimensionInDp);
                    imageView.setLayoutParams(parms);
                }
                else if(rangeValue==3){
                    Log.d("right_range_xmpp: " , String.valueOf(rangeValue));
                    int dimensionInPixel = 225;
                    int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionInDp, dimensionInDp);
                    imageView.setLayoutParams(parms);
                }
                else if(rangeValue==4){
                    Log.d("right_range_xmpp: " , String.valueOf(rangeValue));
                    int dimensionInPixel = 250;
                    int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionInDp, dimensionInDp);
                    imageView.setLayoutParams(parms);
                }
                else if(rangeValue==5){
                    Log.d("right_range_xmpp: " , String.valueOf(rangeValue));
                    int dimensionInPixel = 275;
                    int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionInDp, dimensionInDp);
                    imageView.setLayoutParams(parms);
                }

            }

        });

        //createBitMap();

        // create blur effect over the video view
        Picasso.with(this)
                .load(R.drawable.mosaic_main)
                .transform(new BlurTransformation(50))   // blur density
                .into(imageView);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoOrdinate = view.getX() - event.getRawX();
                        Log.d("xCoOrdinate_xmpp: ", String.valueOf(event.getX()));
                        yCoOrdinate = view.getY() - event.getRawY();
                        Log.d("yCoOrdinate_xmpp: ", String.valueOf(event.getY()));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

    }

    // create bitmap programmatically
    private void createBitMap() {
        // Create a mutable bitmap (default, without drawable image)
        //Bitmap bitMap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        //bitMap = bitMap.copy(bitMap.getConfig(), true);

        BitmapFactory.Options myOptions = new BitmapFactory.Options();
        myOptions.inDither = true;
        myOptions.inScaled = false;
        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
        myOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mosaic_main,myOptions);
        Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        // Construct a canvas with the specified bitmap to draw into
        canvas = new Canvas(mutableBitmap);

        // Create a new paint with default settings
        // Initialize a new Paint instance to draw the Circle
        paint = new Paint();

        // smooths out the edges of what is being drawn
        paint.setAntiAlias(true);

        // set color
        paint.setColor(Color.BLACK);

        // set style
        paint.setStyle(Paint.Style.FILL);

        // set alpha transparent
        paint.setAlpha(100);

        // Calculate the available radius of canvas
        radius = Math.min(canvas.getWidth(),canvas.getHeight()/2);

        // draw circle with radius 30
        canvas.drawCircle(canvas.getWidth() / 2,
                canvas.getHeight() / 2, radius , paint);
        // set on ImageView or any other view
        imageView.setImageBitmap(mutableBitmap);

    }
}
