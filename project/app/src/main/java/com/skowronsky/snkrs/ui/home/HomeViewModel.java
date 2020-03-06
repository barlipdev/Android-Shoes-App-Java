package com.skowronsky.snkrs.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skowronsky.snkrs.Company;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Company>> CompanyLiveData;
    private MutableLiveData<Boolean> ShoesNav;
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

    public void eventNavToShoes(){
        ShoesNav.setValue(true);
    }
    public void eventNavToShoesFinished(){
        ShoesNav.setValue(false);
    }

    public void init(){
        CompanyList();
        CompanyLiveData.setValue(CompanyArrayList);
    }
    public void CompanyList(){
        Company Company = new Company();
        Company.setCompany_name("Nike");

        CompanyArrayList = new ArrayList<>();
        CompanyArrayList.add(Company);
        CompanyArrayList.add(Company);
        CompanyArrayList.add(Company);
        CompanyArrayList.add(Company);
        CompanyArrayList.add(Company);
        CompanyArrayList.add(Company);
        CompanyArrayList.add(Company);
        CompanyArrayList.add(Company);
    }
}