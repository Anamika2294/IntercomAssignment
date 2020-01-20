package com.example.intercomassignment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Get100Customer {


    public static List<Person> getCustomers(JSONArray result){
         List<Person> personList= new ArrayList<>();
        //JSONArray array=new JSONArray();
        for (Object o : result){
            JSONObject person = (JSONObject) o;


            // String name = (String)person.get("name");

            String latitude = (String)person.get("latitude");
            String longitude = (String)person.get("longitude");

            Long dist=CalculateDistance.distanceFrom(latitude,longitude);
            if(dist<=100){
                Person p= new Person();
                p.setName((String)person.get("name"));
                p.setUserId((Long) person.get("user_id"));
                personList.add(p);
            }



            // String user_id = Long.toString((Long)(person.get("user_id")));


        }
        return personList;
    }
}
