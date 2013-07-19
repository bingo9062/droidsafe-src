package android.graphics.drawable.shapes;

// Droidsafe Imports
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import droidsafe.annotations.DSC;
import droidsafe.annotations.DSGeneratedField;
import droidsafe.annotations.DSGenerator;
import droidsafe.annotations.DSModeled;

public class RectShape extends Shape {
    @DSGeneratedField(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:12.809 -0400", hash_original_field = "995C0FB7B00A0A1E82DD4C30778697FC", hash_generated_field = "E080B89EC7A364FFC5B71FB812B38393")

    private RectF mRect = new RectF();
    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:12.809 -0400", hash_original_method = "86D7394A029CE4FDE438585868C447F4", hash_generated_method = "296628B3E8AE7C2CA6ACF988628F6E64")
    public  RectShape() {
        // ---------- Original Method ----------
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:12.810 -0400", hash_original_method = "6BDD25C71E92DEE23AC033397583FF85", hash_generated_method = "F42A2E1D82E1E835634E2FB3B371471C")
    @Override
    public void draw(Canvas canvas, Paint paint) {
        addTaint(paint.getTaint());
        addTaint(canvas.getTaint());
        canvas.drawRect(mRect, paint);
        // ---------- Original Method ----------
        //canvas.drawRect(mRect, paint);
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:12.810 -0400", hash_original_method = "62B0D2225FCA5D4099BD1D1E514DF479", hash_generated_method = "9CB3A42F067008BE08F833A4D48BAF78")
    @Override
    protected void onResize(float width, float height) {
        //DSFIXME:  CODE0009: Possible callback target function detected
        addTaint(height);
        addTaint(width);
        mRect.set(0, 0, width, height);
        // ---------- Original Method ----------
        //mRect.set(0, 0, width, height);
    }

    
    @DSModeled(DSC.SAFE)
    @DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:12.811 -0400", hash_original_method = "B472369E445B34AFDD84E5B389A9601D", hash_generated_method = "55A97005DF57FAE45C1D373FCC0BC040")
    protected final RectF rect() {
RectF var23E085E00370717E44C5AC6A506C1823_902565364 =         mRect;
        var23E085E00370717E44C5AC6A506C1823_902565364.addTaint(taint);
        return var23E085E00370717E44C5AC6A506C1823_902565364;
        // ---------- Original Method ----------
        //return mRect;
    }

    
        @DSModeled(DSC.SAFE)
@DSGenerator(tool_name = "Doppelganger", tool_version = "0.4.2", generated_on = "2013-07-17 10:23:12.811 -0400", hash_original_method = "F35F96D345FDD22204E61750D42DDCE2", hash_generated_method = "860A8D26812784255DD95E2EAF11917D")
    @Override
    public RectShape clone() throws CloneNotSupportedException {
        final RectShape shape = (RectShape) super.clone();
        shape.mRect = new RectF(mRect);
RectShape var1E03F90B1057A29F071E0E1A1AD4365B_1085467840 =         shape;
        var1E03F90B1057A29F071E0E1A1AD4365B_1085467840.addTaint(taint);
        return var1E03F90B1057A29F071E0E1A1AD4365B_1085467840;
        // ---------- Original Method ----------
        //final RectShape shape = (RectShape) super.clone();
        //shape.mRect = new RectF(mRect);
        //return shape;
    }

    
}

