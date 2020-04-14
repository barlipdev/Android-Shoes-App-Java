package com.skowronsky.snkrs.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.Company;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Company>> CompanyLiveData;
    private MutableLiveData<Boolean> ShoesNav;
    private MutableLiveData<String> CompanyName;
    public ArrayList<Company> CompanyArrayList;

    public HomeViewModel() {
        CompanyLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Company>> getCompanyLiveData()
    {
        if (CompanyLiveData == null)
                CompanyLiveData = new MutableLiveData<>();
        return CompanyLiveData;
    }

    public MutableLiveData<Boolean> getEventShoesNav(){
        if(ShoesNav == null)
            ShoesNav = new MutableLiveData<Boolean>();
        return ShoesNav;
    }

    public MutableLiveData<String> getEventCompanyName(){
        if (CompanyName == null)
            CompanyName = new MutableLiveData<String>();
        return CompanyName;
    }

    public void eventNavToShoes(){
        ShoesNav.setValue(true);
    }
    public void eventNavToShoesFinished(){
        ShoesNav.setValue(false);
    }
    public void eventCompanyName(String name){ CompanyName.setValue(name);}

    public void init(){
        CompanyList();
        CompanyLiveData.setValue(CompanyArrayList);
    }
    public void CompanyList(){
        Company Company = new Company();
        Company Company2 = new Company();
        Company.setCompany_name("Nike");
        Company2.setCompany_name("Adidas");

        CompanyArrayList = new ArrayList<>();
        CompanyArrayList.add(Company);
        CompanyArrayList.add(Company2);
    }
}