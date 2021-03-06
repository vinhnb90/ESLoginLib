package com.esolutions.esloginlib.lib;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import esolutions.com.esloginlib.R;

public class DepartUpdateFragment<T> extends ModuleFragment {
    private List<T> mListDepart = new ArrayList<>();
    private DepartViewEntity viewEntity;

    private boolean isShowModule = false;

    public DepartUpdateFragment() {
        super();
    }

    public DepartUpdateFragment<T> setmListDepart(List<T> mListDepart) throws Exception {
        //check empty
        if (mListDepart.size() == 0)
            return this;


        //check to String override of T class
        //if we use method toString of class T and it return not object then class T really overrided toString method
        T tClass = (T) mListDepart.get(0).getClass();
        if ((mListDepart.get(0).getClass().getClass().getMethod("toString").getDeclaringClass() == Object.class))
            throw new RuntimeException("Class " + mListDepart.get(0).getClass().getSimpleName() + " must be override method toString to show content on spinner!");


//        this.mListDepart.clear();
//        this.mListDepart.addAll(mListDepart);
        this.mListDepart = mListDepart;
        fillData();
        return this;
    }

    public DepartViewEntity getViewEntity() {
        return viewEntity;
    }

    public boolean isShowModule() {
        return isShowModule;
    }

    public DepartUpdateFragment<T> setShowModule(boolean showModule) {
        isShowModule = showModule;
        return this;
    }

    public DepartUpdateFragment setViewEntity(DepartViewEntity viewEntity) {
        this.viewEntity = viewEntity;
        return this;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = null;
        try {
            if (viewEntity != null)
                viewRoot = viewEntity.getViewLayout();
            else
                viewRoot = inflater.inflate(R.layout.fragment_login, container, false);

            fillData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return viewRoot;
    }

    private void fillData() {
        try {
            ArrayAdapter arrayAdapter = new ArrayAdapter<T>(getContext(), R.layout.row_spin_type_1, R.id.tv_spin, mListDepart);

            //set full screen rows
            Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            if (viewEntity == null)
                return;
            if (viewEntity.getSpDvi() == null)
                return;
            viewEntity.getSpDvi().setDropDownWidth(size.x);
            viewEntity.getSpDvi().setGravity(RelativeLayout.CENTER_HORIZONTAL);
            viewEntity.getSpDvi().setAdapter(arrayAdapter);
            viewEntity.getSpDvi().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> getmListDepart() {
        return mListDepart;
    }
}

