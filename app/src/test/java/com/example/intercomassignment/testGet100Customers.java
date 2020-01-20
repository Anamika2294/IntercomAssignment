package com.example.intercomassignment;

import android.util.Log;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class testGet100Customers{
    JSONArray jsonArray;

    @Test
    public void testGetValues1()
    {
        //Output Data
        List<Person> dataOutput = new ArrayList<Person>();
        List<String> testOutput1 = new ArrayList<String>();
        //List<String> innerListOutput2 = new ArrayList<String>();



        Person p=new Person();
        p.setName("Ian Kehoe");
        p.setUserId((long) 4);

        dataOutput.add(p);


        //input data

        try {
            JSONParser parser = new JSONParser();
            jsonArray= (JSONArray) parser.parse(readFromFile("/testFile.json"));
            //JSONArray jsonArray = new JSONArray(readFromFile("/testFile.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

      //Log.v(" Data",""+Get100Customer.getCustomers(jsonArray).get(0));

        System.out.println(""+Get100Customer.getCustomers(jsonArray).get(0).getUserId());

        assertEquals("Error in GetValues",dataOutput.get(0).getUserId(),Get100Customer.getCustomers(jsonArray).get(0).getUserId());
    }

    public String readFromFile(String filename) throws IOException {
        InputStream is = getClass().getResourceAsStream(filename);
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        byte[] b = new byte[4096];
        while ((i = is.read(b)) != -1) {
            stringBuilder.append(new String(b, 0, i));
        }
        return stringBuilder.toString();
    }


}
