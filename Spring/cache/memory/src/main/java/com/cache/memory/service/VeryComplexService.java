package com.cache.memory.service;

public class VeryComplexService {

    private final BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();
    public VeryComplexService(){
    }

    public void complexBusiness(int[] array){
        bubbleSortAlgorithm.sort(array);
        // TODO: more logic here
    }
}
