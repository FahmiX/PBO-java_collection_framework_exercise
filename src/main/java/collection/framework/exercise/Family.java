package collection.framework.exercise;

import java.util.*;

public class Family {
    private static Set<Uncle> uncles = new TreeSet<>();
    private static Set<Niece> nieces = new TreeSet<>();

    // Getter
    public static Set<Uncle> getUncles() {
        return uncles;
    }

    public static Set<Niece> getNieces() {
        return nieces;
    }

    // Private Constructor
    private Family() {
       
    }

    // Add a new niece. If there is already a niece of this name, false is returned and nothing is added.
    public static boolean addNiece(String name, int day, int month) {
        // check if name is already in nieces
        for (Niece niece : nieces) {
            if (niece.getName().equals(name)) {
                return false;
            }
        }

        if (nieces.add(new Niece(name, day, month))){
            return true;
        } else {
            return false;
        }
    }

    // Add a new uncle. If there is already an uncle of this name, false is returned and nothing is added.
    public static boolean addUncle(String name) {
        Uncle uncle = new Uncle(name);
        if (uncles.contains(uncle)) {
            return false;
        }
        uncles.add(uncle);
        return true;
    }

    // Lookup a niece by name return null if not found.
    public static Niece findNiece(String name) {
        for (Niece niece : nieces) {
            if (niece.getName().equals(name)) {
                return niece;
            }
        }
        return null;
    }

    // Lookup an uncle by name return null if not found.
    public static Uncle findUncle(String name) {
        for (Uncle uncle : uncles) {
            if (uncle.getName().equals(name)) {
                return uncle;
            }
        }
        return null;
    }

    // List all nieces
    public static void listNieces() {
        System.out.println("============ List of Nieces =============");
        for (Niece niece : nieces) {
            System.out.println(niece);
        }
    }

    // List all uncles
    public static void listUncles() {
        System.out.println("============ List of Uncles =============");
        for (Uncle uncle : uncles) {
            System.out.println(uncle);
        }
    }

}
