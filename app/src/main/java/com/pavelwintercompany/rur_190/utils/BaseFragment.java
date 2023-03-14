package com.pavelwintercompany.rur_190.utils;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pavelwintercompany.rur_190.entity.IdIdModel;

import rx.Observable;

public class BaseFragment extends Fragment {

    private View childView;
    private int recyclerViewItemPosition;

    /**
     * Sets up listener for recyclerview
     * @param recyclerView - RecyclerView which gets listener
     * @return position of clicked item when it clicked
     * */
    public Observable<IdIdModel> setupRvListener(RecyclerView recyclerView ){
        return Observable.unsafeCreate(subscriber -> {
            if(recyclerView != null) {
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    final GestureDetector gestureDetector = new GestureDetector(recyclerView.getContext()
                            , new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public boolean onSingleTapUp(MotionEvent motionEvent) {
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                        childView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                        if (childView != null && gestureDetector.onTouchEvent(motionEvent)) {
                            recyclerViewItemPosition = Recyclerview.getChildAdapterPosition(childView);
                           // Timber.d("Click on position: %d", recyclerViewItemPosition);
                            subscriber.onNext(new IdIdModel(recyclerViewItemPosition, recyclerView));
                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });
            }
        });
    }



}
