package nehli.ma.co.appphotos.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;

import nehli.ma.co.appphotos.R;

/**
 * Created by Majid on 03/06/2016.
 */
public class MyImageView extends ImageView implements Checkable {

    private boolean checked = false;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
        if (this.getVisibility() == View.VISIBLE) {
            this.setImageResource((checked) ? R.drawable.check : R.drawable.circle);
        }
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void toggle() {
        setChecked(!checked);
    }
}
