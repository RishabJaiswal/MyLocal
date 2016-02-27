package hub.com.statushub;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by Rishab on 24-10-2015.
 */
public class DrawableTinting
{
    Context context;
    public DrawableTinting(Context context)
    {
        this.context = context;
    }
    public Drawable tint( int drawablrRes, int color)
    {
        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, drawablrRes));
        DrawableCompat.setTint(drawable,color);
        return drawable;
    }

}
