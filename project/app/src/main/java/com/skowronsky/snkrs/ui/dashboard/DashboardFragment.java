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

import com.skowronsky.snkrs.MyApplication;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.database.Brand;
import com.skowronsky.snkrs.database.Shoes;
import com.skowronsky.snkrs.databinding.FragmentDashboardBinding;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class DashboardFragment extends Fragment {

    private DashboardViewModel viewModel;
    private FragmentDashboardBinding binding;

    SnkrsClient client;
    private MyApplication appState;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appState = ((MyApplication)this.getActivity().getApplication());
        client = appState.snkrsClient;

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
                    connectToDataServer();
                    viewModel.connectFinished();
                }
            }
        });

        final LiveData<Boolean> disconnect = viewModel.getEventDisconnect();
        disconnect.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    viewModel.disconnectFinished();
                    disconnectFromDataServer();
                }
            }
        });

        return binding.getRoot();
    }

    private void connectToDataServer(){
        client.connect();
    }

    private void disconnectFromDataServer(){
//        viewModel.deleteAllBrand();
//        viewModel.deleteAllShoes();

        final LiveData<Shoes> shoesLiveData = viewModel.getShoes(1);
        shoesLiveData.observe(getViewLifecycleOwner(), new Observer<Shoes>() {
            @Override
            public void onChanged(Shoes shoes) {
                Log.i("ROOM123", "\n"+shoes.modelName);

            }
        });

    }
}