package collection.framework.exercise;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        String uncleName;
        String nieceName;
        String present;

        while (true) {
            System.out.println("======== Family Presents Program ========");
            System.out.println("|   1. Add Uncle                        |");
            System.out.println("|   2. Add Niece                        |");
            System.out.println("|   3. List Uncles                      |");
            System.out.println("|   4. List Nieces                      |");
            System.out.println("|   5. Add Present                      |");
            System.out.println("|   6. List Presents                    |");
            System.out.println("|   7. Clear Presents                   |");
            System.out.println("|   8. Exit                             |");
            System.out.println("=========================================\n");

            System.out.print("Enter your choice: ");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            scan.nextLine();
            System.out.println();

            switch (choice) {
                // Add a new Uncle
                case 1:
                    // Uncle name
                    System.out.print("Enter Uncle's name: ");
                    uncleName = scan.nextLine().toUpperCase();
                    if (Family.addUncle(uncleName) && !uncleName.isEmpty()) {
                        System.out.println("Uncle added");
                    } else {
                        System.out.println("Uncle already exists");
                    }
                    break;

                // Add a new niece
                case 2:
                    try {
                        // Niece Birthday (day)
                        System.out.print("Enter Niece's birthday (day): ");
                        int day = scan.nextInt();
                        try{
                            if (day < 1 || day > 31) {
                                throw new Exception("Invalid day");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        // Niece Birthday (month)
                        System.out.print("Enter Niece's birthday (month): ");
                        int month = scan.nextInt();
                        try{
                            if (month < 1 || month > 12) {
                                throw new Exception("Invalid month");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        // Niece Name
                        scan.nextLine();
                        System.out.print("Enter Niece's name: ");
                        nieceName = scan.nextLine().toUpperCase();
                        if (Family.addNiece(nieceName, day, month) && !nieceName.isEmpty()) {
                            System.out.println("Niece added");
                        } else {
                            System.out.println("Niece already exists");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input");
                    }
                    break;

                // List all Uncles
                case 3:
                    Family.listUncles();
                    break;

                // List all Nieces
                case 4:
                    Family.listNieces();
                    break;

                // Add Present from an Uncle to a Niece
                case 5:
                    try {
                        // Uncle Name
                        System.out.print("Enter Uncle's name: ");
                        uncleName = scan.nextLine().toUpperCase();
                        if (Family.findUncle(uncleName) == null) {
                            throw new Exception("Uncle not found");
                        }

                        // Niece Name
                        System.out.print("Enter Niece's name: ");
                        nieceName = scan.nextLine().toUpperCase();
                        if (Family.findNiece(nieceName) == null) {
                            throw new Exception("Niece not found");
                        }

                        // Present
                        System.out.print("Enter Present: ");
                        present = scan.nextLine().toUpperCase();

                        // Check if present already given to other niece from same uncle
                        boolean isSuccess = Family.findUncle(uncleName).checkPresent(present);
                        if (!isSuccess) {
                            throw new Exception("Present already given to other niece");
                        }

                        // Uncle add present to Niece and Niece receive present from Uncle
                        isSuccess = Family.findUncle(uncleName).addPresent(Family.findNiece(nieceName), present);
                        isSuccess = Family.findNiece(nieceName).receivePresent(Family.findUncle(uncleName), present);
                        if (isSuccess) {
                            System.out.println("Present added");
                        } else {
                            throw new Exception("Present already exists");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // Show presents by uncle or niece
                case 6:
                    try {
                        System.out.println("List presents by:");
                        System.out.println("1. Uncle");
                        System.out.println("2. Niece");
                        System.out.print("Enter your choice: ");
                        int listChoice = scan.nextInt();
                        scan.nextLine();
                        System.out.println();
                        switch (listChoice) {
                            case 1:
                                System.out.print("Enter Uncle's name: ");
                                uncleName = scan.nextLine().toUpperCase();
                                if (Family.findUncle(uncleName) == null) {
                                    throw new Exception("Uncle not found");
                                }
                                System.out.println("================== " + uncleName + " Presents ==================");
                                Family.findUncle(uncleName).listPresents();
                                break;
                            case 2:
                                System.out.print("Enter Niece's name: ");
                                nieceName = scan.nextLine().toUpperCase();
                                if (Family.findNiece(nieceName) == null) {
                                    throw new Exception("Niece not found");
                                }
                                System.out.println("================== " + nieceName + " Presents ==================");
                                Family.findNiece(nieceName).listPresents();
                                break;
                            default:
                                throw new Exception("Invalid choice");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // Clear all presents from all uncles and nieces
                case 7:
                    try {
                        System.out.print("Enter Niece's name: ");
                        nieceName = scan.nextLine().toUpperCase();
                        if (Family.findNiece(nieceName) == null) {
                            throw new Exception("Niece not found");
                        } else {
                            int amount = Family.findNiece(nieceName).clearPresents();
                            final String temp_name = nieceName;
                            Family.getUncles().forEach((uncle) -> {
                                uncle.removePresent(Family.findNiece(temp_name));
                            });
                            System.out.println(amount + " presents cleared from " + nieceName);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // Exit Program
                case 8:
                    scan.close();
                    System.exit(0);
                    break;

                // Invalid choice
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            System.out.print("\nPress any key to continue...");
            scan.nextLine();
            System.out.println("\n");
        }
    }
}