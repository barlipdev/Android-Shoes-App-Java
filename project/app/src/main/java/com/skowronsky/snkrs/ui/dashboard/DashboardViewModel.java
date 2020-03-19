package com.skowronsky.snkrs.ui.dashboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<Boolean> eventConnect;
    private MutableLiveData<Boolean> eventDisconnect;

    public String siema = "siema";

    public MutableLiveData<Boolean> getEventConnect(){
        if(eventConnect == null)
            eventConnect = new MutableLiveData<Boolean>();
        return eventConnect;
    }

    public void setEventConnect(){
        eventConnect.setValue(true);
    }
    public void connectFinished(){
        eventConnect.setValue(false);
    }

    public MutableLiveData<Boolean> getEventDisconnect() {
        if(eventDisconnect == null)
            eventDisconnect = new MutableLiveData<Boolean>();
        return eventDisconnect;
    }

    public void setEventDisconnect(){
        eventConnect.setValue(true);
    }
    public void disconnectFinished(){
        eventConnect.setValue(false);
    }
}