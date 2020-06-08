package com.skowronsky.snkrs.ui.profile;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelperAdapter extends ItemTouchHelper.SimpleCallback {


    public ItemTouchHelperAdapter(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;  //do not allow move
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        float translationX = dX;
        View itemView = viewHolder.itemView;
        float height = (float)itemView.getBottom() - (float)itemView.getTop();

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX <= 0) {
            translationX = -Math.min(-dX, height);
            itemView.setTranslationX(translationX);
        }

        super.onChildDraw(c, recyclerView, viewHolder, translationX, dY, actionState, isCurrentlyActive);
    }
}
