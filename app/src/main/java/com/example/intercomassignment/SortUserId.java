package com.example.intercomassignment;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUserId {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Person> sort_userId(List <Person> data){
        Collections.sort(data, Comparator.comparingLong(Person::getUserId));

        return data;
    }
}
