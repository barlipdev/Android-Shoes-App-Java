package com.skowronsky.snkrs.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel viewModel;
    private FragmentDashboardBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        binding.setLifecycleOwner(this);
        binding.setDashboardViewModel(viewModel);





        return binding.getRoot();
    }
}