package com.skowronsky.snkrs;

import android.content.Context;

import com.skowronsky.snkrs.model.Brand;
import com.skowronsky.snkrs.model.Shoes;
import com.skowronsky.snkrs.model.User;
import com.skowronsky.snkrs.storage.Storage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StorageUnitTest {

    Storage storage = Storage.getInstance();

    @Test
    public void storageBrandList(){
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1,"BrandName","Test"));
        storage.setBrandList(brands);

        assertEquals("Storage BrandList brandName","BrandName",storage.getBrandList().get(0).getName());
    }

    @Test
    public void storageShoesList(){
        List<Shoes> shoes = new ArrayList<>();
        shoes.add(new Shoes(1,"BrandName","ModelName",1,"Test"));
        storage.setShoesList(shoes);

        assertEquals("Storage Shoes List BrandName check","BrandName",storage.getShoesList().get(0).getBrandName());
    }

    @Test
    public void storageUser(){
        User user = new User("email","username","password","photo");
        storage.setUser(user);
        assertEquals("Storage user email check","email",storage.getUser().getEmail());
    }
}
