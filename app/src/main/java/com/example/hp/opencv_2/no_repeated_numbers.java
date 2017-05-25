package com.example.hp.opencv_2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by HP on 29-Apr-17.
 */

public class no_repeated_numbers {
    public ArrayList<Integer> generte (int key,int size,int pixel_nom){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        randomGenerator.setSeed(key);
        while (numbers.size() < pixel_nom) {

            int random = randomGenerator .nextInt(size)+30;
            if (!numbers.contains(random)) {
                numbers.add(random);

            }
        }
        return (numbers);
    }
}
