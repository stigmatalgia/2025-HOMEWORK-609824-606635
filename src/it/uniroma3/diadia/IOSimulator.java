package it.uniroma3.diadia;

import java.util.Scanner;

public class IOSimulator implements IO {
    private String[] inputStrings;
    private String[] outputStrings;
    private int inputIndex;
    private int outputIndex;

    public IOSimulator(String[] inputStrings) {
        this.inputStrings = inputStrings;
        // aggiungo 3 perche' +1 e' il messaggio di benvenuto +1 e' il messaggio di vittoria o fine cfu e +1 e' il messaggio di fine aggiunto da iosimulator
        this.outputStrings = new String[(inputStrings.length)+3];
        this.inputIndex = 0;
        this.outputIndex = 0;
    }

    @Override
    public void mostraMessaggio(String messaggio) {
        outputStrings[outputIndex++] = messaggio;
    }

    @Override
    public String leggiRiga() {
        if (inputIndex < inputStrings.length) {
            return inputStrings[inputIndex++];
        }
        return "fine";
    }

    public String[] getInputStrings() {
        return inputStrings;
    }

    public String[] getOutputStrings() {
        return outputStrings;
    }
}