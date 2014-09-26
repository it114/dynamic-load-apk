package com.it114.app.zb.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.it114.app.zb.R;
import com.it114.app.zb.main.PhoneIncomingCallsActivity;


public class PhoneLockView extends RelativeLayout
{
    private static int BACK_DURATION = 0;
    private static String TAG;
    private static float VE_HORIZONTAL = 0.0f;
    public static final int call = 0;
    public static final int middle = 2;
    public static final int sms = 1;
    private Runnable BackDragImgTask;
    private Bitmap dragBitmap;
    boolean isHit;
    private boolean isIn;
    boolean isReset;
    LinearLayout lin;
    PhoneLockView lockview;
    private Context mContext;
    private Handler mHandler;
    private int mLastMoveY;
    private Handler mainHandler;
    Message msg;
    private RelativeLayout parent;
    private int sourceY;
    private int type;

    static {
        PhoneLockView.TAG = "lockview";
        PhoneLockView.BACK_DURATION = 20;
        PhoneLockView.VE_HORIZONTAL = 0.7f;
    }

    public PhoneLockView(final Context mContext) {
        super(mContext);
        this.dragBitmap = null;
        this.mContext = null;
        this.mainHandler = null;
        this.isIn = false;
        this.mLastMoveY = -10000;
        this.msg = Message.obtain();
        this.isReset = false;
        this.BackDragImgTask = new Runnable() {
            @Override
            public void run() {
                final PhoneLockView localLockView = PhoneLockView.this;
                localLockView.mLastMoveY += (int)(PhoneLockView.BACK_DURATION * PhoneLockView.VE_HORIZONTAL);
                PhoneLockView.this.invalidate();
                int n;
                if (PhoneLockView.this.mLastMoveY > PhoneLockView.this.sourceY) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n == 0) {
                    PhoneLockView.this.mHandler.postDelayed(PhoneLockView.this.BackDragImgTask, (long) PhoneLockView.BACK_DURATION);
                    return;
                }
                PhoneLockView.this.resetViewState();
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(final Message message) {
                Log.i(PhoneLockView.TAG, "handleMessage :  #### ");
            }
        };
        this.mContext = mContext;
    }

    public PhoneLockView(final Context mContext, final AttributeSet set) {
        super(mContext, set, 0);
        this.dragBitmap = null;
        this.mContext = null;
        this.mainHandler = null;
        this.isIn = false;
        this.mLastMoveY = -10000;
        this.msg = Message.obtain();
        this.isReset = false;
        this.BackDragImgTask = new Runnable() {
            @Override
            public void run() {
                final PhoneLockView localLockView = PhoneLockView.this;
                localLockView.mLastMoveY += (int)(PhoneLockView.BACK_DURATION * PhoneLockView.VE_HORIZONTAL);
                PhoneLockView.this.invalidate();
                int n;
                if (PhoneLockView.this.mLastMoveY > PhoneLockView.this.sourceY) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n == 0) {
                    PhoneLockView.this.mHandler.postDelayed(PhoneLockView.this.BackDragImgTask, (long) PhoneLockView.BACK_DURATION);
                    return;
                }
                PhoneLockView.this.resetViewState();
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(final Message message) {
                Log.i(PhoneLockView.TAG, "handleMessage :  #### ");
            }
        };
        this.mContext = mContext;
    }

    public PhoneLockView(final Context mContext, final AttributeSet set, final int n) {
        super(mContext, set, n);
        this.dragBitmap = null;
        this.mContext = null;
        this.mainHandler = null;
        this.isIn = false;
        this.mLastMoveY = -10000;
        this.msg = Message.obtain();
        this.isReset = false;
        this.BackDragImgTask = new Runnable() {
            @Override
            public void run() {
                final PhoneLockView localLockView = PhoneLockView.this;
                localLockView.mLastMoveY += (int)(PhoneLockView.BACK_DURATION * PhoneLockView.VE_HORIZONTAL);
                PhoneLockView.this.invalidate();
                int n;
                if (PhoneLockView.this.mLastMoveY > PhoneLockView.this.sourceY) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n == 0) {
                    PhoneLockView.this.mHandler.postDelayed(PhoneLockView.this.BackDragImgTask, (long) PhoneLockView.BACK_DURATION);
                    return;
                }
                PhoneLockView.this.resetViewState();
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(final Message message) {
                Log.i(PhoneLockView.TAG, "handleMessage :  #### ");
            }
        };
        this.mContext = mContext;
    }

    private boolean handleActionDownEvenet(final MotionEvent motionEvent) {
        this.isHit = new Rect(0, 0, this.getWidth(), this.getHeight() / 2).contains((int)motionEvent.getX(), (int)motionEvent.getY());
        if (this.isHit) {
            final int n = (int)motionEvent.getX();
            this.isReset = false;
            this.isIn = false;
            this.parent.setBackgroundResource(R.drawable.phone_j1);
            if (this.dragBitmap != null) {
                this.dragBitmap.recycle();
                this.dragBitmap = null;
            }
            if (this.dragBitmap == null) {
                this.parent.setDrawingCacheEnabled(true);
                this.dragBitmap = Bitmap.createBitmap(this.parent.getDrawingCache());
                this.parent.setDrawingCacheEnabled(false);
            }
            this.parent.setVisibility(4);
        }
        Log.e(PhoneLockView.TAG, "handleActionDownEvenet : isHit" + this.isHit);
        return this.isHit;
    }

    private void handleActionUpEvent(final MotionEvent motionEvent) {
        Log.e(PhoneLockView.TAG, "handleActionUpEvent : y -->" + (int)motionEvent.getY());
        int n;
        if (Math.abs(this.mLastMoveY) >= 1.5 * (this.getHeight() / 2)) {
            n = 1;
        }
        else {
            n = 0;
        }
        final boolean b = Math.abs(this.mLastMoveY) <= 2 * (this.getHeight() / 25);
        if (n != 0) {
            this.lockview = PhoneIncomingCallsActivity.mPhoneLockView;
            this.lin = PhoneIncomingCallsActivity.mLinearLayoutCalledContainer;
            this.lockview.setVisibility(8);
            this.lin.setVisibility(0);
            this.msg.what = PhoneIncomingCallsActivity.PHONE_INCOMING_OPENED;
            this.mainHandler.sendMessage(this.msg);
            return;
        }
        if (b) {
            this.msg.what = 11;
            this.mainHandler.sendMessage(this.msg);
            return;
        }
        this.resetViewState();
    }

    private void invalidateDragImg(final Canvas canvas) {
        boolean b;
        if (Math.abs(this.mLastMoveY) >= 1.5 * (this.getHeight() / 2)) {
            b = true;
        }
        else {
            b = false;
        }
        if (b && !this.isIn && !this.isReset) {
            this.isIn = true;
        }
        canvas.drawBitmap(this.dragBitmap, (float)this.parent.getLeft(), (float)(this.parent.getTop() + (this.mLastMoveY - this.sourceY)), (Paint)null);
    }

    private void resetViewState() {
        this.isReset = true;
        this.mLastMoveY = 10000;
        this.parent.setBackgroundResource(R.drawable.phone_u22_normal);
        this.parent.setVisibility(0);
        this.invalidate();
    }

    private void virbate() {
        ((Vibrator)this.mContext.getSystemService("vibrator")).vibrate(100L);
    }

    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.isHit) {
            this.invalidateDragImg(canvas);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.parent = (RelativeLayout)this.findViewById((R.id.parent));
    }

    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final int n = (int)motionEvent.getX();
        final int mLastMoveY = (int)motionEvent.getY();

        switch (motionEvent.getAction()) {
            default: {
                return super.onTouchEvent(motionEvent);
            }
            case 0: {
                final int n2 = (int)motionEvent.getY();
                this.mLastMoveY = n2;
                this.sourceY = n2;
                return this.handleActionDownEvenet(motionEvent);
            }
            case 2: {
                this.mLastMoveY = mLastMoveY;
                if (this.mLastMoveY < 0) {
                    this.mLastMoveY = 0;
                }
                this.invalidate();
                return true;
            }
            case 1: {
                this.handleActionUpEvent(motionEvent);
                return true;
            }
        }
    }

    public void setMainHandler(final Handler mainHandler) {
        this.mainHandler = mainHandler;
    }

    public void showToast(final String s) {
        Toast.makeText(this.mContext, (CharSequence)s, 1).show();
    }
}