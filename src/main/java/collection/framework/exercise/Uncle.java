package collection.framework.exercise;

import java.util.*;

public class Uncle implements Comparable<Uncle> {
    private String name;
    private Map<Niece, String> presents;

    // Constructor
    protected Uncle(String name) {
        this.name = name;
        this.presents = new LinkedHashMap<>();
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Niece, String> getPresents() {
        return presents;
    }

    public void setPresents(Map<Niece, String> presents) {
        this.presents = presents;
    }

    // Adds a new present, given by this uncle. Return true if the present is allowed. If the niece already has a present from this uncle, false is returned and nothing is added.
    public boolean addPresent(Niece niece, String present) {
        if (presents.containsKey(niece)) {
            return false;
        }
        presents.put(niece, present);
        return true;
    }

    // Lists (to the console) the presents given by this uncle, showing the recipient. Nieces with no present from this uncle are also listed.
    public void listPresents() {
        for (Niece niece : Family.getNieces()) {
            if (presents.containsKey(niece)) {
                System.out.println(niece.getName() + " - " + presents.get(niece));
            } else {
                System.out.println(niece.getName() + " - No present");
            }
        }
    }

    // Remove present given by uncles to a niece. Return true if the present was removed.
    public boolean removePresent(Niece niece) {
        if (presents.containsKey(niece)) {
            presents.remove(niece);
            return true;
        }
        return false;
    }

    // Check given present to all niece by this uncle. return true if present doesn't exist.
    public boolean checkPresent(String newPresent) {
        newPresent = newPresent.toUpperCase();
        if (presents.containsValue(newPresent)) {
            return false;
        } else {
            return true;
        }
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
        final Uncle other = (Uncle) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.presents, other.presents);
    }

    @Override
    public String toString() {
        return "Uncle{" + "name=" + name + '}';
    }

    @Override
    public int compareTo(Uncle o) {
        return name.compareTo(o.name);
    }
}