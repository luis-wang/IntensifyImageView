package me.kareluo.intensify.image;

import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;

/**
 * Created by felix on 16/1/16.
 */
public class Utils {

    public static void center(RectF rect, Rect frame) {
        rect.offset(frame.centerX() - rect.centerX(), frame.centerY() - rect.centerY());
    }

    public static void center(Rect rect, Rect frame) {
        rect.offset(frame.centerX() - rect.centerX(), frame.centerY() - rect.centerY());
    }

    public static void centerVertical(RectF rect, Rect frame) {
        rect.offset(0, frame.centerY() - rect.centerY());
    }

    public static void centerHorizontal(RectF rect, Rect frame) {
        rect.offset(frame.centerX() - rect.centerX(), 0);
    }

    public static void home(RectF rect, Rect border) {
        if (rect.height() < border.height()) {
            Utils.centerVertical(rect, border);
        } else {
            if (rect.top > border.top) {
                rect.offset(0, border.top - rect.top);
            } else if (rect.bottom < border.bottom) {
                rect.offset(0, border.bottom - rect.bottom);
            }
        }

        if (rect.width() < border.width()) {
            Utils.centerHorizontal(rect, border);
        } else {
            if (rect.left > border.left) {
                rect.offset(border.left - rect.left, 0);
            } else if (rect.right < border.right) {
                rect.offset(border.right - rect.right, 0);
            }
        }
    }

    public static boolean contains(RectF rect, Rect r) {
        return rect.contains(r.left, r.top, r.right, r.bottom);
    }

    public static boolean contains(Rect r, RectF rect) {
        return r.contains(Math.round(rect.left), Math.round(rect.top),
                Math.round(rect.right), Math.round(rect.bottom));
    }

    public static RectF evaluate(float fraction, RectF startValue, RectF endValue, @NonNull RectF reuseRect) {
        float left = startValue.left + (endValue.left - startValue.left) * fraction;
        float top = startValue.top + (endValue.top - startValue.top) * fraction;
        float right = startValue.right + (endValue.right - startValue.right) * fraction;
        float bottom = startValue.bottom + (endValue.bottom - startValue.bottom) * fraction;
        reuseRect.set(left, top, right, bottom);
        return reuseRect;
    }

    public static boolean isEmpty(Rect rect) {
        return rect == null || rect.isEmpty();
    }

    /**
     * 获取接近size的2的幂的数字,如1、2、4
     *
     * @param size
     * @return
     */
    public static int getSampleSize(int size) {
        if (size <= 1) return 1;
        int sampleSize = 1;
        while (size > 1) {
            size >>= 1;
            sampleSize <<= 1;
        }
        return sampleSize;
    }

    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static Rect round(RectF rect) {
        return new Rect(Math.round(rect.left), Math.round(rect.top),
                Math.round(rect.right), Math.round(rect.bottom));
    }

    public static Rect blockRect(int x, int y, float size, int offsetX, int offsetY) {
        Rect result = new Rect();
        new RectF(x * size + offsetX, y * size + offsetY,
                (x + 1) * size + offsetX, (y + 1) * size + offsetY).round(result);
        return result;
    }

    public static Rect blocks(RectF rect, float size) {
        return new Rect(
                floor(rect.left / size), floor(rect.top / size),
                IntensifyImageManager.ceil(rect.right / size), IntensifyImageManager.ceil(rect.bottom / size)
        );
    }

    public static int floor(float value) {
        return (int) Math.floor(value);
    }
}
