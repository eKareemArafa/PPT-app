package com.example.hp.opencv_2;

import java.util.ArrayList;

/**
 * Created by HP on 29-Apr-17.
 */

public class to_binary {
    public ArrayList<Integer> dec2bin_8(ArrayList<Integer> p) {
        ArrayList<Integer> u=new ArrayList();
        for (int i=0;i<p.size();i++){
            int temp=p.get(i);
            String str=Integer.toBinaryString(temp);
            int len=str.length();
            if(len<8){
                do{
                    str="0"+str;
                    len++;
                }while(len<8);
            }
            for(int j=0;j<8;j++){
                int x;
                x = Integer.parseInt(String.valueOf(str.charAt(j)));
                u.add(x);
            }
        }
        return (u);
    }
    public ArrayList<Integer> dec2bin_10(int key1) {
        ArrayList<Integer> p=new ArrayList<Integer>();
        p.add(key1);
        ArrayList<Integer> u=new ArrayList();
        for (int i=0;i<p.size();i++){
            int temp=p.get(i);
            String str=Integer.toBinaryString(temp);
            int len=str.length();
            if(len<10){
                do{
                    str="0"+str;
                    len++;
                }while(len<10);
            }
            for(int j=0;j<10;j++){
                int x;
                x = Integer.parseInt(String.valueOf(str.charAt(j)));
                u.add(x);
            }
        }
        return (u);
    }
    public ArrayList<Integer> dec2bin_6( ArrayList<Integer>p ) {

        ArrayList<Integer> u=new ArrayList();
        for (int i=0;i<p.size();i++){
            int temp=p.get(i);
            String str=Integer.toBinaryString(temp);
            int len=str.length();
            if(len<6){
                do{
                    str="0"+str;
                    len++;
                }while(len<6);
            }
            for(int j=0;j<6;j++){
                int x;
                x = Integer.parseInt(String.valueOf(str.charAt(j)));
                u.add(x);
            }
        }
        return (u);
    }
}
