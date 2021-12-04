package me.shoptastic.app.data.model;

import android.content.Context;

import java.lang.ref.WeakReference;

public class Resources {

    private static WeakReference<Context> context;
    public final static String FireBaseLink = "https://shoptastic-1a860-default-rtdb.firebaseio.com/";

    public static void setContext(Context context) {
        Resources.context = new WeakReference<>(context.getApplicationContext());
    }

    public static String getString(Integer i) {
        if (Resources.context == null)
            throw new NullPointerException("Context was never initialized");
        return Resources.context.get().getResources().getString(i);
    }

    public static String getString(Integer i, Object... format) {
        if (Resources.context == null)
            throw new NullPointerException("Context was never initialized");
        return Resources.context.get().getResources().getString(i, format);
    }

    public static Boolean getBoolean(Integer i) {
        if (Resources.context == null)
            throw new NullPointerException("Context was never initialized");
        return Resources.context.get().getResources().getBoolean(i);
    }

    public static Float getFloat(Integer i) {
        if (Resources.context == null)
            throw new NullPointerException("Context was never initialized");
        return Resources.context.get().getResources().getFloat(i);
    }

    public static Integer getInteger(Integer i) {
        if (Resources.context == null)
            throw new NullPointerException("Context was never initialized");
        return Resources.context.get().getResources().getInteger(i);
    }

    public static String[] getStringArray(Integer i) {
        if (Resources.context == null)
            throw new NullPointerException("Context was never initialized");
        return Resources.context.get().getResources().getStringArray(i);
    }

    public static int[] getIntegerArray(Integer i) {
        if (Resources.context == null)
            throw new NullPointerException("Context was never initialized");
        return Resources.context.get().getResources().getIntArray(i);
    }

}
