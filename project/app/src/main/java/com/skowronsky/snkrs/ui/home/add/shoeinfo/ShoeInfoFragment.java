package com.skowronsky.snkrs.ui.home.add.shoeinfo;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.skowronsky.snkrs.R;
import com.skowronsky.snkrs.databinding.FragmentBaseselectorShoeInfoBinding;
import com.skowronsky.snkrs.storage.NavigationStorage;
import com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages.EurSizesFragment;
import com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages.UkSizesFragment;
import com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages.UsSizesFragment;
import com.squareup.picasso.Picasso;

public class ShoeInfoFragment extends Fragment implements EurSizesFragment.OnFragmentInteractionListener, UkSizesFragment.OnFragmentInteractionListener, UsSizesFragment.OnFragmentInteractionListener{

    private ShoeInfoViewModel shoeInfoViewModel;
    private FragmentBaseselectorShoeInfoBinding shoesInformationFragmentBinding;
    private NavigationStorage navigationStorage;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        navigationStorage = NavigationStorage.getInstance();
        shoeInfoViewModel = new ViewModelProvider(this).get(ShoeInfoViewModel.class);
        shoesInformationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_baseselector_shoe_info, container, false);
        shoesInformationFragmentBinding.setShoesInfoViewModel(shoeInfoViewModel);
        shoesInformationFragmentBinding.setLifecycleOwner(this);

        shoesInformationFragmentBinding.shoeModel.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));
        shoesInformationFragmentBinding.shoeBrand.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));
        shoesInformationFragmentBinding.imageView.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        shoesInformationFragmentBinding.tablayout.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));
        shoesInformationFragmentBinding.addBaseButton.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));

        NestedScrollView scrollView = shoesInformationFragmentBinding.scrollview;
        scrollView.setFillViewport(true);

        Picasso.with(getContext()).load(navigationStorage.getShoe().image).into(
                shoesInformationFragmentBinding.imageView);

        final LiveData<Boolean> navInfo = shoeInfoViewModel.getNavToBase();
        navInfo.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    navigateToBase();
                    shoeInfoViewModel.eventNavToInfoFinished();
                }
            }
        });

        shoesInformationFragmentBinding.addBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoeInfoViewModel.addShoeToBase(navigationStorage.getSizes().getUs());
                navigateToBase();
            }
        });

        return shoesInformationFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = shoesInformationFragmentBinding.viewpager;
        TabLayout tabLayout = shoesInformationFragmentBinding.tablayout;

        tabLayout.setupWithViewPager(viewPager);

        SizePagesAdapter adapter = new SizePagesAdapter(getChildFragmentManager());

        adapter.addFrag(new EurSizesFragment(), "EU");
        adapter.addFrag(new UkSizesFragment(), "UK");
        adapter.addFrag(new UsSizesFragment(), "US");

        viewPager.setAdapter(adapter);
    }

    /**
     * Metoda odpowiadająca za wykonanie nawigacji do odpowiedniego fragmentu,
     * w tym przypadku do listy baz użytkownika
     */
    private void navigateToBase(){
        NavHostFragment.findNavController(this).navigate(R.id.action_shoesInformationFragment2_to_homeBase);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
