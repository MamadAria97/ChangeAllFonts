package ir.mamadariakia.changefontfamily;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

public class FontHelper {

    public static void applyCustomFont(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        int fontResId = sharedPreferences.getInt("fontResId", -1);

        if (fontResId != -1) {
            Typeface typeface = ResourcesCompat.getFont(activity, fontResId);
            if (typeface != null) {
                ViewGroup rootView = activity.findViewById(android.R.id.content);
                applyFontRecursively(rootView, typeface);
            }
        } else {

        }
    }

    private static void applyFontRecursively(ViewGroup viewGroup, Typeface typeface) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(typeface);
            } else if (view instanceof ViewGroup) {
                applyFontRecursively((ViewGroup) view, typeface);
            }
        }
    }

    public static void resetToDefaultFont(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("fontResId");
        editor.apply();


        activity.recreate();
    }
}
