package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOSimulator implements IO {
    private ArrayList<String> inputStrings;
    private ArrayList<String> outputStrings;
    private int inputIndex;
    private int outputIndex;

    public IOSimulator(List<String> inputStrings) {
        this.inputStrings = new ArrayList<>(inputStrings);
        this.outputStrings = new ArrayList<>();         
        this.inputIndex = 0;
        this.outputIndex = 0;
    }
    
    @Override
    public void mostraMessaggio(String messaggio) {
        outputStrings.add(messaggio);
    }

    @Override
    public String leggiRiga() {
        if (inputIndex < inputStrings.size()) {
            return inputStrings.get(inputIndex++);
        }
        return "fine";
    }

    public ArrayList<String> getInputStrings() {
        return inputStrings;
    }

    public ArrayList<String> getOutputStrings() {
        return outputStrings;
    }
}