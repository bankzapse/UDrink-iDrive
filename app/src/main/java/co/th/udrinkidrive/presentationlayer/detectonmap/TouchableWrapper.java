package co.th.udrinkidrive.presentationlayer.detectonmap;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity;

public class TouchableWrapper extends FrameLayout {

    private long lastTouched = 0;
    private static final long SCROLL_TIME = 200L; // 200 Milliseconds, but you can adjust that to your liking
    private UpdateMapAfterUserInterection updateMapAfterUserInterection;

    public TouchableWrapper(Context context) {
        super(context);
        // Force the host activity to implement the UpdateMapAfterUserInterection Interface
        try {
            updateMapAfterUserInterection = (PostMapActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement UpdateMapAfterUserInterection");
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouched = SystemClock.uptimeMillis();
                updateMapAfterUserInterection.onUpdateMapAfterUserInterection("HIDE");
                break;
            case MotionEvent.ACTION_UP:
                updateMapAfterUserInterection.onUpdateMapAfterUserInterection("SHOW");
//                final long now = SystemClock.uptimeMillis();
//                if (now - lastTouched > SCROLL_TIME) {
//                    // Update the map
//                    updateMapAfterUserInterection.onUpdateMapAfterUserInterection("UP");
//                }
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    // Map Activity must implement this interface
    public interface UpdateMapAfterUserInterection {
          void onUpdateMapAfterUserInterection(String type);
    }
}