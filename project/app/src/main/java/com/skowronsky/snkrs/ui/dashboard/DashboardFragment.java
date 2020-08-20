package com.skowronsky.snkrs.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.database.Base;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.database.SizeChart;
import com.skowronsky.snkrs.databinding.FragmentDashboardBinding;

import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel viewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        binding.setDashboardViewModel(viewModel);
        binding.setLifecycleOwner(this);


        final LiveData<Boolean> navigation = viewModel.getEventConnect();
        navigation.observe(getViewLifecycleOwner(), new Observer<Boolean>(){
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Log.i("myTag", "This is my message");
                    viewModel.connect();
                    viewModel.text.setValue(viewModel.text.getValue() + "\na no nic");
                    viewModel.connectFinished();
                }
            }
        });

        final LiveData<Boolean> disconnect = viewModel.getEventDisconnect();
        disconnect.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    viewModel.text.setValue("");
                    viewModel.disconnectFinished();
                    disconnectFromDataServer();
                }
            }
        });

        final LiveData<List<SizeChart>> sizeChartLiveData = viewModel.getAllSizeChart();
        sizeChartLiveData.observe(getViewLifecycleOwner(), sizeCharts -> {
            for (SizeChart item :
                    sizeCharts) {

                Log.i("Snkrs", item.getBrandName());
                Log.i("Snkrs", String.valueOf(item.getUs()));
            }
        });

        final LiveData<List<Base>> baseLiveData = viewModel.getAllBases();
        baseLiveData.observe(getViewLifecycleOwner(), bases -> {
            for(Base item : bases){
                Log.i("Snkrs", String.valueOf(item.getIdBase()));
                Log.i("Snkrs", String.valueOf(item.getIdSize()));
                Log.i("Snkrs", String.valueOf(item.getIdHiddenSize()));
            }

        });

        return binding.getRoot();
    }


    private void disconnectFromDataServer(){
//        viewModel.deleteAllBrand();
//        viewModel.deleteAllShoes();

        final LiveData<Shoes> shoesLiveData = viewModel.getShoes(1);
        shoesLiveData.observe(getViewLifecycleOwner(), new Observer<Shoes>() {
            @Override
            public void onChanged(Shoes shoes) {
                Log.i("Snkrs", "\n"+shoes.getModelName());

            }
        });

    }
}