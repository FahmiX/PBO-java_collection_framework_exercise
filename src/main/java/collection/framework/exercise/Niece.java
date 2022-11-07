package collection.framework.exercise;

import java.util.*;

public class Niece implements Comparable<Niece> {
    private String name;
    private int day;
    private int month;
    private Map<Uncle, String> presents;

    // Constructor
    protected Niece(String name, int day, int month) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.presents = new HashMap<>();
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Map<Uncle, String> getPresents() {
        return presents;
    }

    public void setPresents(Map<Uncle, String> presents) {
        this.presents = presents;
    }

    // Delete all the presents chosen for this niece. Return the number removed.
    public int clearPresents() {
        int count = presents.size();
        presents.clear();
        return count;
    }

    // Lists (to the console) the presents to be received by this niece, showing the giver. Uncles with no present for this niece are also listed.
    public void listPresents() {
        for (Uncle uncle : Family.getUncles()) {
            if (presents.containsKey(uncle)) {
                System.out.println(uncle.getName() + " - " + presents.get(uncle));
            } else {
                System.out.println(uncle.getName() + " - No present");
            }
        }
    }

    // Receive a present from an uncle. Return true if the present is allowed.
    public boolean receivePresent(Uncle uncle, String present) {
        if (presents.containsKey(uncle)) {
            return false;
        }

        if (presents.containsValue(present)) {
            return false;
        }

        presents.put(uncle, present);
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Niece other = (Niece) obj;
        if (this.day != other.day) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.presents, other.presents);
    }

    @Override
    public String toString() {
        return "Niece{" + "name=" + name + ", day=" + day + ", month=" + month + '}';
    }

    @Override
    public int compareTo(Niece o) {
        if (month == o.month) {
            return day - o.day;
        }
        return month - o.month;
    }
}

