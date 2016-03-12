package com.hotmall.library;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhsheng on 2015/9/13.
 */
public class ViewFinder {
    private final ViewFinder.FindWrapper wrapper;

    public ViewFinder(View view) {
        this.wrapper = new ViewFinder.ViewWrapper(view);
    }

    public ViewFinder(Window window) {
        this.wrapper = new ViewFinder.WindowWrapper(window);
    }

    public ViewFinder(Activity activity) {
        this(activity.getWindow());
    }

    public <V extends View> V find(int id) {
        return (V) this.wrapper.findViewById(id);
    }

    public ImageView imageView(int id) {
        return (ImageView)this.find(id);
    }

    public CompoundButton compoundButton(int id) {
        return (CompoundButton)this.find(id);
    }

    public TextView textView(int id) {
        return (TextView)this.find(id);
    }

    public TextView setText(int id, CharSequence content) {
        TextView text = (TextView)this.find(id);
        text.setText(content);
        return text;
    }

    public TextView setText(int id, int content) {
        return this.setText(id, this.wrapper.getResources().getString(content));
    }

    public View onClick(int id, View.OnClickListener listener) {
        View clickable = this.find(id);
        clickable.setOnClickListener(listener);
        return clickable;
    }

    public View onClick(int id, final Runnable runnable) {
        return this.onClick(id, new View.OnClickListener() {
            public void onClick(View v) {
                runnable.run();
            }
        });
    }

    public void onClick(View.OnClickListener listener, int... ids) {
        int[] arr$ = ids;
        int len$ = ids.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            int id = arr$[i$];
            this.find(id).setOnClickListener(listener);
        }

    }

    public void onClick(final Runnable runnable, int... ids) {
        this.onClick(new View.OnClickListener() {
            public void onClick(View v) {
                runnable.run();
            }
        }, ids);
    }

    public ImageView setDrawable(int id, int drawable) {
        ImageView image = this.imageView(id);
        image.setImageDrawable(image.getResources().getDrawable(drawable));
        return image;
    }

    public CompoundButton onCheck(int id, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton checkable = (CompoundButton)this.find(id);
        checkable.setOnCheckedChangeListener(listener);
        return checkable;
    }

    public CompoundButton onCheck(int id, final Runnable runnable) {
        return this.onCheck(id, new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                runnable.run();
            }
        });
    }

    public void onCheck(CompoundButton.OnCheckedChangeListener listener, int... ids) {
        int[] arr$ = ids;
        int len$ = ids.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            int id = arr$[i$];
            this.compoundButton(id).setOnCheckedChangeListener(listener);
        }

    }

    public void onCheck(final Runnable runnable, int... ids) {
        this.onCheck(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                runnable.run();
            }
        }, ids);
    }

    private static class ViewWrapper implements ViewFinder.FindWrapper {
        private final View view;

        ViewWrapper(View view) {
            this.view = view;
        }

        public View findViewById(int id) {
            return this.view.findViewById(id);
        }

        public Resources getResources() {
            return this.view.getResources();
        }
    }

    private static class WindowWrapper implements ViewFinder.FindWrapper {
        private final Window window;

        WindowWrapper(Window window) {
            this.window = window;
        }

        public View findViewById(int id) {
            return this.window.findViewById(id);
        }

        public Resources getResources() {
            return this.window.getContext().getResources();
        }
    }

    private interface FindWrapper {
        View findViewById(int var1);

        Resources getResources();
    }
}
