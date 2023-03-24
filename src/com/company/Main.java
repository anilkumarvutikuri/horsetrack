package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Map<Integer, Horse> horseMap;

    public static void main(String[] args) {
        Map<Integer, Horse> horseMap = getIntegerHorseMap();
        Map<Integer, Integer> inventoryMap = getInventory();
        printDashboard(horseMap, inventoryMap);
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        if(sc.hasNextInt()) {
            int bet = sc.nextInt();
            if (Character.isDigit(c)) {
                int id = Integer.parseInt(String.valueOf(c));
                Horse winner = findWinner(horseMap);
                if (winner.getId() == id) {
                    int dispense = winner.getOdds() * bet;
                    System.out.println("payout:" + winner.getName() + "," + dispense);
                    List<Integer> invent = inventoryMap.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                    for (Integer o : invent) {
                        int change = dispense / o;
                        int setValue = inventoryMap.get(o) - change;
                        inventoryMap.put(o, setValue);
                        dispense = dispense % (int) o;

                        System.out.println(o + "," + change);
                    }
                    System.out.println("Inventory");
                    printInventory(inventoryMap);
                } else {
                    System.out.println("No Payout:" + horseMap.get(id).getName());
                }
            } else {
                Horse horse = horseMap.get(bet);
                Horse winner = findWinner(horseMap);
                winner.setStatus("lost");
                horse.setStatus("won");
                horseMap.put(winner.getId(), winner);
                horseMap.put(horse.getId(), horse);
                printDashboard(horseMap, inventoryMap);
            }
        }else{
            System.out.println("Invalid Input");
        }
    }

    private static Horse findWinner(Map<Integer, Horse> horseMap) {
        Horse winner = horseMap.entrySet().stream().filter(x -> x.getValue().getStatus().equals("won")).
                collect(Collectors.toSet()).stream().findFirst().get().getValue();
        return winner;
    }

    private static void printInventory(Map<Integer, Integer> inventoryMap) {
        for (Map.Entry h : inventoryMap.entrySet()) {
            System.out.println("$" + h.getKey() + "," + h.getValue());
        }
    }

    private static void printDashboard(Map<Integer, Horse> horseMap, Map<Integer, Integer> inventoryMap) {
        printInventory(inventoryMap);
        for (Horse h : horseMap.values()) {
            System.out.println(h.toString());
        }
    }

    private static Map<Integer, Horse> getIntegerHorseMap() {
        Horse horse1 = new Horse(1, "That Darn Gray Cat", 5, "won");
        Horse horse2 = new Horse(2, "Fort Utopia", 10, "lost");
        Horse horse3 = new Horse(3, "Count Sheep", 9, "lost");
        Horse horse4 = new Horse(4, "Ms Traitour", 4, "lost");
        Horse horse5 = new Horse(5, "Real Princess", 3, "lost");
        Horse horse6 = new Horse(6, "Pa Kettle", 5, "lost");
        Horse horse7 = new Horse(7, "Gin Stinger", 6, "lost");
        Map<Integer, Horse> horseMap = new LinkedHashMap<>();
        horseMap.put(horse1.getId(), horse1);
        horseMap.put(horse2.getId(), horse2);
        horseMap.put(horse3.getId(), horse3);
        horseMap.put(horse4.getId(), horse4);
        horseMap.put(horse5.getId(), horse5);
        horseMap.put(horse6.getId(), horse6);
        horseMap.put(horse7.getId(), horse7);
        return horseMap;
    }

    private static Map<Integer, Integer> getInventory() {
        Map<Integer, Integer> inventoryMap = new LinkedHashMap<>();
        inventoryMap.put(1, 10);
        inventoryMap.put(5, 10);
        inventoryMap.put(10, 10);
        inventoryMap.put(20, 10);
        inventoryMap.put(100, 10);
        return inventoryMap;
    }
}
