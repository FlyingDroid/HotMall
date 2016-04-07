package com.hotmall.widget;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;

import com.hotmall.R;
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;

/**
 * Created by zhangsheng on 2016/4/7.
 */
public class LoadingDialog extends DialogFragment {

    private Dialog dialog;
    private GraduallyTextView graduallyTextView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (dialog == null) {
            dialog = new Dialog(getActivity(), R.style.loading_dialog);
            dialog.setContentView(R.layout.layou_loading_dialog);
            dialog.setCanceledOnTouchOutside(true);
            dialog.getWindow().setGravity(Gravity.CENTER);
            View view = dialog.getWindow().getDecorView();
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.loading_progress);
            progressBar.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(getActivity()).build());
            graduallyTextView = (GraduallyTextView) view.findViewById(R.id.tv_loading_tips);
        }
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        graduallyTextView.startLoading();
    }

    @Override
    public void onPause() {
        super.onPause();
        graduallyTextView.startLoading();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dialog = null;
        System.gc();
    }
}
