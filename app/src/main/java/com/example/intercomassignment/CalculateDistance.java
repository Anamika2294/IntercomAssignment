package com.example.intercomassignment;

public class CalculateDistance {

    public static int RadiusOfEarthInKM=6367;
    public static Double CordiLatiSource =  Double.parseDouble("53.339428");
    public static Double CordiLongiSource =  Double.parseDouble("-6.257664");


    public static Long distanceFrom(String cordiLatiDes,String cordiLongiDes){

        Double cordiLatiDesD = Double.parseDouble (cordiLatiDes);
        Double cordiLongiDesD = Double.parseDouble(cordiLongiDes);
        Double phi_1 = Math.toRadians(CordiLatiSource);
        Double phi_2 = Math.toRadians(cordiLatiDesD);
        Double delta_lambda =Math.abs( Math.toRadians(cordiLongiDesD - CordiLongiSource));
        Double delta_sigma = Math.acos(((Math.sin(phi_1)*Math.sin(phi_2))+(Math.cos(phi_1)*Math.cos(phi_2)*Math.cos(delta_lambda))));

        return ((new Double (RadiusOfEarthInKM*delta_sigma)).longValue());
    }
}
