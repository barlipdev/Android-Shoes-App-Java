package com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skowronsky.snkrs.storage.NavigationStorage;


public class EurSizesViewModel extends AndroidViewModel {

    NavigationStorage navigationStorage;
    MutableLiveData<Size> size;
    MutableLiveData<Boolean> checked;
    MutableLiveData<Integer> pos;

    public EurSizesViewModel(@NonNull Application application) {
        super(application);
        navigationStorage = NavigationStorage.getInstance();
    }

    public MutableLiveData<Boolean> getChecked(){
        if (checked == null){
            checked = new MutableLiveData<Boolean>();
        }
        return checked;
    }

    public MutableLiveData<Integer> getPos() {
        if (pos == null){
            pos = new MutableLiveData<>();
            //pos.setValue(navigationStorage.getSize_pos());
        }
        return pos;
    }

    public MutableLiveData<Size> getSize(){
        if (size == null)
            size = navigationStorage.getSizes();
        return size;
    }

    public boolean check(Size size){
        if (size.equals(navigationStorage.getSizes().getValue()))
            return true;
        return false;
    }

    public void eventSetChcecked(){
        checked.setValue(true);
    }
    public void eventSetUnChecked(){
        checked.setValue(false);
    }
    public void eventSetPos(int pos){
        this.pos.setValue(pos);
    }
    public int eventGetPos(){
        return navigationStorage.getSize_pos();
    }

}
