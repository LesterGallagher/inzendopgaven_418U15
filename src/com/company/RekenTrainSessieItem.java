package com.company;

/**
 * Een opdracht item in een RekenTrainSessie object.
 * @author Sem Postma
 */
public class RekenTrainSessieItem {
    /**
     * De eerste term van de opdracht.
     * Bijvoorbeeld 1 in de som: "1 + 2 = 3".
     */
    public int firstArgument;
    /**
     * De tweede term van de opdracht.
     * Bijvoorbeeld 2 in de som: "1 + 2 = 3".
     */
    public int secondArgument;
    /**
     * Het antwoord van de opdracht.
     * Bijvoorbeeld 3 in de som: "1 + 2 = 3".
     */
    public int answer;
    /**
     * De basisoperatie van de opdracht.
     * Bijvoorbeeld "+" in de som: "1 + 2 = 3".
     * Deze waarde is altijd meer dan nul.
     */
    public char operator;
    /**
     * Het antwoord van de leerling.
     */
    public int userAnswer;
    /**
     * De naam van de opdracht.
     * Bijvoorbeeld: "Optellen".
     */
}
