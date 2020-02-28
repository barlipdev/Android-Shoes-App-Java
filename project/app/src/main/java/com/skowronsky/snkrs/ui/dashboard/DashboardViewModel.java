package com.skowronsky.snkrs.ui.dashboard;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<Boolean> eventNav;

    public String siema = "siema";

    public MutableLiveData<Boolean> getEventNav(){
        if(eventNav == null)
            eventNav = new MutableLiveData<Boolean>();
        return eventNav;
    }

    public void setEventNav(){
        eventNav.setValue(true);
    }
    public void navFinished(){
        eventNav.setValue(false);
    }
}