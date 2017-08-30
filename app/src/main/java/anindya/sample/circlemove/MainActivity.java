package anindya.sample.circlemove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appyvet.rangebar.RangeBar;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Canvas canvas;
    Paint paint;
    int radius;

    ImageView imageView;
    RelativeLayout mContainer;
    int dimensionToPixel, radiusCircle;
    private float xCoOrdinate, yCoOrdinate;
    int mParentHeight, mParentWidth;
    float touchX = 0;
    float touchY = 0;
    float lastTouchX = 0;
    float lastTouchY = 0;
    float circleCenterX, circleCenterY, viewX, viewY;
    // Initializes the RangeBar in the application
    private RangeBar rangebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get device screen size
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // and calculate for circle size
        int width = (metrics.widthPixels);
        int height = (metrics.heightPixels);
        Log.d("screen_xmpp: ", "Width: " + width + " Height: " + height);

        imageView = (ImageView) findViewById(R.id.imageView);
        dimensionToPixel = dp2px(180);
        radiusCircle = dimensionToPixel / 2;
        Log.d("circle_diameter_xmpp:", String.valueOf(dimensionToPixel));
        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionToPixel, dimensionToPixel);
        imageView.setLayoutParams(parms);

        mContainer = (RelativeLayout) findViewById(R.id.container);
        mContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                //now we can retrieve the width and height
                mParentWidth = mContainer.getWidth();
                mParentHeight = mContainer.getHeight();
                Log.d("parent_view_xmpp: ", "Width: " + mParentWidth + " Height: " + mParentHeight);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                    mContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    mContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });


        // Gets the RangeBar
        rangebar = (RangeBar) findViewById(R.id.rangebar);
        rangebar.setRangePinsByValue(0f, 1.0f);

        // Sets the display values of the indices
        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                int rangeValue = rightPinIndex;
                if (rangeValue == 0) {
                    Log.d("circle_range_xmpp: ", String.valueOf(rangeValue));
                    dimensionToPixel = dp2px(160);
                    radiusCircle = dimensionToPixel / 2;
                    Log.d("circle_diameter_xmpp:", String.valueOf(dimensionToPixel));
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionToPixel, dimensionToPixel);
                    imageView.setLayoutParams(parms);
                } else if (rangeValue == 1) {
                    Log.d("circle_range_xmpp: ", String.valueOf(rangeValue));
                    dimensionToPixel = dp2px(180);
                    radiusCircle = dimensionToPixel / 2;
                    Log.d("circle_diameter_xmpp:", String.valueOf(dimensionToPixel));
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionToPixel, dimensionToPixel);
                    imageView.setLayoutParams(parms);
                } else if (rangeValue == 2) {
                    Log.d("circle_range_xmpp: ", String.valueOf(rangeValue));
                    dimensionToPixel = dp2px(200);
                    radiusCircle = dimensionToPixel / 2;
                    Log.d("circle_diameter_xmpp:", String.valueOf(dimensionToPixel));
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionToPixel, dimensionToPixel);
                    imageView.setLayoutParams(parms);
                } else if (rangeValue == 3) {
                    Log.d("circle_range_xmpp: ", String.valueOf(rangeValue));
                    dimensionToPixel = dp2px(220);
                    radiusCircle = dimensionToPixel / 2;
                    Log.d("circle_diameter_xmpp:", String.valueOf(dimensionToPixel));
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionToPixel, dimensionToPixel);
                    imageView.setLayoutParams(parms);
                } else if (rangeValue == 4) {
                    Log.d("circle_range_xmpp: ", String.valueOf(rangeValue));
                    dimensionToPixel = dp2px(240);
                    radiusCircle = dimensionToPixel / 2;
                    Log.d("circle_diameter_xmpp:", String.valueOf(dimensionToPixel));
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionToPixel, dimensionToPixel);
                    imageView.setLayoutParams(parms);
                } else if (rangeValue == 5) {
                    Log.d("circle_range_xmpp: ", String.valueOf(rangeValue));
                    dimensionToPixel = dp2px(260);
                    radiusCircle = dimensionToPixel / 2;
                    Log.d("circle_diameter_xmpp:", String.valueOf(dimensionToPixel));
                    RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(dimensionToPixel, dimensionToPixel);
                    imageView.setLayoutParams(parms);
                }

            }

        });


        // create bitmap dynamically
        //createBitMap();

        // create blur effect over the video view
        Picasso.with(this)
                .load(R.drawable.mosaic_main)
                .transform(new BlurTransformation(100))   // blur density
                .into(imageView);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if ((circleCenterX + radiusCircle) > mParentWidth) {
                            touchX = event.getX();
                            if (touchX > lastTouchX) {
                                //don't move
                            } else {
                                view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                                circleCenterX = view.getX() + radiusCircle;
                                circleCenterY = view.getY() + radiusCircle;
                                touchX = event.getX();
                                lastTouchX = touchX;
                                touchY = event.getY();
                                lastTouchY = touchY;
                                viewX = view.getX();
                                Log.d("circle_current_xmpp:: ", " circle center X: " + circleCenterX + " circle center Y: " + circleCenterY + " touch center X: " + touchX + " touch center Y: " + touchY);
                            }
                        }
                        if (circleCenterX < radiusCircle) {
                            touchX = event.getX();
                            if (touchX < lastTouchX) {
                                //don't move
                            } else {
                                view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                                circleCenterX = view.getX() + radiusCircle;
                                circleCenterY = view.getY() + radiusCircle;
                                touchX = event.getX();
                                lastTouchX = touchX;
                                touchY = event.getY();
                                lastTouchY = touchY;
                                viewX = view.getX();
                                Log.d("circle_current_xmpp:: ", " circle center X: " + circleCenterX + " circle center Y: " + circleCenterY + " touch center X: " + touchX + " touch center Y: " + touchY);
                            }
                        }
                        else if ((circleCenterY + radiusCircle) > mParentHeight) {
                            touchY = event.getY();
                            if (touchY > lastTouchY) {
                                //don't move
                            } else {
                                view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                                circleCenterX = view.getX() + radiusCircle;
                                circleCenterY = view.getY() + radiusCircle;
                                touchX = event.getX();
                                lastTouchX = touchX;
                                touchY = event.getY();
                                lastTouchY = touchY;
                                viewX = view.getX();
                                Log.d("circle_current_xmpp:: ", " circle center X: " + circleCenterX + " circle center Y: " + circleCenterY + " touch center X: " + touchX + " touch center Y: " + touchY);
                            }
                        }
                        else if (circleCenterY < radiusCircle) {
                            touchY = event.getY();
                            if (touchY < lastTouchY) {
                                //don't move
                            } else {
                                view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                                circleCenterX = view.getX() + radiusCircle;
                                circleCenterY = view.getY() + radiusCircle;
                                touchX = event.getX();
                                lastTouchX = touchX;
                                touchY = event.getY();
                                lastTouchY = touchY;
                                viewX = view.getX();
                                Log.d("circle_current_xmpp:: ", " circle center X: " + circleCenterX + " circle center Y: " + circleCenterY + " touch center X: " + touchX + " touch center Y: " + touchY);
                            }
                        }
                        else {
                            view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                            circleCenterX = view.getX() + radiusCircle;
                            circleCenterY = view.getY() + radiusCircle;
                            touchX = event.getX();
                            lastTouchX = touchX;
                            touchY = event.getY();
                            lastTouchY = touchY;
                            viewX = view.getX();
                            Log.d("circle_current_xmpp:: ", " circle center X: " + circleCenterX + " circle center Y: " + circleCenterY + " touch center X: " + touchX + " touch center Y: " + touchY);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    // convert dp to pixel
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mosaic_main, myOptions);
        Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

        // Construct a .canvas with the specified bitmap to draw into
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
        radius = Math.min(canvas.getWidth(), canvas.getHeight() / 2);

        // draw circle with radius 30
        canvas.drawCircle(canvas.getWidth() / 2,
                canvas.getHeight() / 2, radius, paint);
        // set on ImageView or any other view
        imageView.setImageBitmap(mutableBitmap);

    }
}
