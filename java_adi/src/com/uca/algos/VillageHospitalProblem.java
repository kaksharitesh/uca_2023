package com.uca.algos;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class VillageHospitalProblem {

    public static void main(String[] args) {
        int[] n = {100, 200,100};
        int m = 5;
        Queue<Village> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int i : n){
            Village v = new Village(i);
            q.add(v);
        }
        for(int i = 0;i<m-n.length;i++){
            Village v = q.poll();
            System.out.println("opening hospital in "+v.population);
            v.openHospital();
            q.add(v);
        }
        System.out.println(q.peek().getDensity());
    }


    private static class Village implements Comparable<Village> {
        private final int population;
        private int hospitalCount;

        public Village(int population){
            this.population = population;
            this.hospitalCount = 1;
        }

        public Float getDensity(){
            return population*1.0f/hospitalCount;
        }

        public void openHospital(){
            this.hospitalCount++;
        }

        @Override
        public int compareTo(Village o) {
           return this.getDensity().compareTo(o.getDensity());
        }
    }
}
