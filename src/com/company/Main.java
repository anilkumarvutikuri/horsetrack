package com.company;

import java.util.*;

/*  @author: Anil K V
Horse Track Main Class
 */
public class Main {
    /* Horse track main method */
    public static void main(String[] args) {
        HorseUtil horseutil = new HorseUtil();
        Map<Integer, Horse> horseMap = horseutil.getIntegerHorseMap();
        Map<Integer, Integer> inventoryMap = horseutil.getInventory();
        horseutil.printDashboard(horseMap, inventoryMap);
        Scanner sc = new Scanner(System.in);
        while (true) {
            char c = sc.next().charAt(0);
            //Checking whether passed value is digit or not
            final String invalid_input = "Invalid Input";
            if (sc.hasNextInt()) {
                processHorseRace(horseutil, horseMap, inventoryMap, sc, c, invalid_input);
            } else {
                horseutil.printString(invalid_input);
            }
        }
    }

    private static void processHorseRace(HorseUtil horseutil, Map<Integer, Horse> horseMap, Map<Integer, Integer> inventoryMap, Scanner sc, char c, String invalid_input) {
        int bet = sc.nextInt();
        //dispensing cash if passed bet is valid and hose number is digit
        if (Character.isDigit(c)) {
            int id = Integer.parseInt(String.valueOf(c));
            if (horseMap.containsKey(id)) {
                processHorseRace(horseutil, horseMap, inventoryMap, bet, id);
            } else {
                horseutil.printString(invalid_input);
            } //ReSetting horse based on input
        } else {
            validateInput(horseutil, horseMap, inventoryMap, c, bet);
        }
    }

    private static void processHorseRace(HorseUtil horseutil, Map<Integer, Horse> horseMap, Map<Integer, Integer> inventoryMap, int bet, int id) {
        Horse winner = horseutil.findWinner(horseMap);
        //checking the winner horse name
        if (winner.getId() == id)
            horseutil.processBet(inventoryMap, bet, winner);
        else {
            System.out.println("No Payout:" + horseMap.get(id).getName());
        } //printing invalid input
    }

    private static void validateInput(HorseUtil horseutil, Map<Integer, Horse> horseMap, Map<Integer, Integer> inventoryMap, char c, int bet) {
        String input = String.valueOf(c);
        switch (input) {
            //Using String Literal in Switch case
            case "W" -> horseutil.assignWinner(horseMap, inventoryMap, bet);
            case "Q" -> {
                horseutil.printString("Quit");
                System.exit(0);
            }
            case "R" -> horseutil.resetHorseRace();
            default -> horseutil.printString("Invalid Input");
        }
    }

}
