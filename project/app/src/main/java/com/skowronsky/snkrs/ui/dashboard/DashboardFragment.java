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

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.SnkrsClient;
import com.skowronsky.snkrs.databinding.FragmentDashboardBinding;

import java.io.IOException;

public class DashboardFragment extends Fragment {

    private DashboardViewModel viewModel;
    private FragmentDashboardBinding binding;

    SnkrsClient client;

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
                    navigateToHome();
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
                    try {
                        client.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return binding.getRoot();
    }

    private void navigateToHome(){
        client = new SnkrsClient(getActivity());
        client.connect();

        //NavHostFragment.findNavController(this).navigate(R.id.action_navigation_dashboard_to_navigation_home);
    }
}