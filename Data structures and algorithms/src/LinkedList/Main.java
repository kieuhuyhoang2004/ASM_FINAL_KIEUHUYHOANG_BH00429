/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentLinkedList studentList = new StudentLinkedList();
        boolean isRunning = true; 

        while (isRunning) {
            System.out.println("\n===== Student Management Program =====");
            System.out.println("1. Start the program");
            System.out.println("2. Continue the program");
            System.out.println("3. Exit completely");
            System.out.print("Your choices: ");

            try {
                int mainChoice = sc.nextInt();
                sc.nextLine(); 

                switch (mainChoice) {
                    case 1: // Start the program
                        System.out.println("Enter student information. Enter 'q' to stop entering.");

                        while (true) {
                            System.out.print("Enter student ID (or 'q' to stop): ");
                            if (sc.hasNextInt()) {
                                int id = sc.nextInt();
                                sc.nextLine();

                                // Check student name (only contains letters and spaces)
                                String name;
                                while (true) {
                                    System.out.print("Enter student name: ");
                                    name = sc.nextLine();
                                    if (name.matches("[a-zA-Z\\s]+")) {
                                        break; 
                                    } else {
                                        System.out.println("Error: Name can only contain letters and spaces. Please try again.");
                                    }
                                }

                                // Check student score (just a number from 0 to 10)
                                double score;
                                while (true) {
                                    System.out.print("Enter student score (0-10): ");
                                    if (sc.hasNextDouble()) {
                                        score = sc.nextDouble();
                                        sc.nextLine(); 
                                        if (score >= 0 && score <= 10) {
                                            break; 
                                        } else {
                                            System.out.println("Error: Score must be between 0 and 10. Please try again.");
                                        }
                                    } else {
                                        System.out.println("Error: Invalid input. Please enter a valid score.");
                                        sc.next(); 
                                    }
                                }

                                // Add students to the list
                                studentList.addStudent(new Student(id, name, score));
                                System.out.println("Student added successfully.");
                            } else if (sc.hasNextLine() && sc.nextLine().equals("q")) {
                                break; 
                            } else {
                                System.out.println("Invalid input. Please enter a valid ID or 'q' to stop.");
                            }
                        }

                        // Display list after input
                        System.out.println("\nList of students:");
                        studentList.displayStudents();
                        handleSubMenu(sc, studentList);
                        break;

                    case 2: // Continue the program
                        System.out.println("\nContinuing the program...");
                        handleSubMenu(sc, studentList);
                        break;

                    case 3: // Exit the program
                        System.out.println("Exiting the program. Goodbye!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please try again.");
                sc.nextLine();
            }
        }
        sc.close();
    }

    // Function to handle sub menu (add, edit, delete, sort, search)
    private static void handleSubMenu(Scanner sc, StudentLinkedList studentList) {
        boolean isSubmenuRunning = true;

        while (isSubmenuRunning) {
            System.out.println("\nSelect action:");
            System.out.println("1. Add student");
            System.out.println("2. Update student information");
            System.out.println("3. Delete student");
            System.out.println("4. Display all students");
            System.out.println("5. Sort students by score");
            System.out.println("6. Search student");
            System.out.println("7. Return to main menu");
            System.out.print("Your choices: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); 
                switch (choice) {
                    
                    case 1: // Add Student
                      try {
                        System.out.print("Enter student ID: ");
                        int id;
                        while (true) {
                            id = sc.nextInt();
                            sc.nextLine();
                            if (!studentList.containsId(id)) {
                                break;
                            } else {
                                System.out.println("Error: ID already exists. Please re-enter.");
                                System.out.print("Enter student ID: ");
                            }
                        }
                        // Check if student name is valid and not duplicate
                        String name;
                        while (true) {
                            System.out.print("Enter student name: ");
                            name = sc.nextLine();
                            // Check for valid names containing only letters
                            if (!name.matches("[a-zA-Záàạảãâầấậẩẫăằắặẳẵêềếệểễìíịỉĩôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹ]+")) {
                                System.out.println("Error: Name can only contain letters. Please re-enter.");
                            } else if (studentList.isNameDuplicate(name)) {
                                System.out.println("Error: Name already exists. Please re-enter.");
                            } else {
                                break;
                            }
                        }
                        // Check valid student scores
                        double score;
                        while (true) {
                            System.out.print("Enter student scores: ");
                            score = sc.nextDouble();
                            if (score >= 0 && score <= 10) {
                                break;
                            } else {
                                System.out.println("Error: Score must be between 0 and 10. Please re-enter.");
                            }
                        }
                        Student newStudent = new Student(id, name, score);
                        studentList.addStudent(newStudent);

                        System.out.println("Successfully added student.");
                        System.out.println("\nAdded list of students:");
                        studentList.displayStudents();

                    } catch (Exception e) {
                        System.out.println("Error: Invalid input.");
                        sc.nextLine();
                    }
                    break;

                    
                    case 2: // Updated Student
                        while (true) {
                            System.out.print("Enter the student ID to update: ");
                            int updateId = sc.nextInt();
                            sc.nextLine();
                            if (!studentList.containsId(updateId)) {
                                System.out.println("Error: ID not found. Please enter a valid ID.");
                            } else {
                                System.out.print("Enter the new student name: ");
                                String newName = sc.nextLine();

                                double newScore;
                                while (true) {
                                    System.out.print("Enter the new student score: ");
                                    newScore = sc.nextDouble();
                                    if (newScore >= 0 && newScore <= 10) {
                                        break;
                                    } else {
                                        System.out.println("Error: Score must be between 0 and 10. Please re-enter.");
                                    }
                                }
                                if (studentList.updateStudent(updateId, newName, newScore)) {
                                    System.out.println("Update Student Successfully");
                                    System.out.println("\nUpdated list of students:");
                                    studentList.displayStudents();
                                } else {
                                    System.out.println("Error: Could not update student information.");
                                }
                                break; 
                            }
                        }
                        break;

                   
                        case 3: // Delete Student
                            List<Integer> deletedIds = new ArrayList<>();

                            while (true) {
                                try {
                                    System.out.print("Enter the student ID to delete: ");
                                    int deleteId = sc.nextInt();

                                    if (deletedIds.contains(deleteId)) {
                                        System.out.println("Error: This student has been deleted. Please re-enter.");
                                    } else if (!studentList.deleteStudent(deleteId)) {
                                        System.out.println("Student not found. Please enter a valid ID.");
                                    } else {
                                        System.out.println("Student deleted successfully.");
                                        deletedIds.add(deleteId);
                                        // Display the updated student list after deletion
                                        System.out.println("\nList of students after deleted:");
                                        studentList.displayStudents();
                                        break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Error: Invalid input. Please enter a valid numeric ID.");
                                    sc.nextLine(); // Clear invalid input
                                }
                            }
                            break;


                    case 4: // Show all students
                        System.out.println("\nShow all students");
                        studentList.displayStudents();
                        break;

                    case 5: // Sorts students
                        System.out.println("\nChoose sorting method:");
                        System.out.println("1. BubbleSort");
                        System.out.println("2. QuickSort");
                        System.out.print("Your choice: ");
                        try {
                            int sortChoice = sc.nextInt();
                            switch (sortChoice) {
                                case 1:
                                    studentList.bubbleSort(true); // Ascending order
                                    System.out.println("\nSorted students by score (BubbleSort):");
                                    studentList.displayStudents();
                                    break;
                                    case 2:
                                    studentList.quickSort(); // QuickSort
                                    System.out.println("\nSorted students by score (QuickSort):");
                                    studentList.displayStudents();
                                    break;
                                    default:
                                    System.out.println("Invalid choice. Please select 1 or 2.");
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Please enter a valid number (1 or 2).");
                                        sc.next(); // Clear invalid input
                        }
                        break;

                    case 6: // Search students
                        try {
                            System.out.print("Enter the student ID to search: ");
                            int searchId = sc.nextInt();
                            Node foundStudent = studentList.findStudent(searchId);
                            if (foundStudent != null) {
                                System.out.println("Student found: " + foundStudent.student);
                            } else {
                                System.out.println("Student not found.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Invalid input. Please enter a valid student ID.");
                            sc.nextLine(); // Clear the buffer to avoid further input issues
                        }
                        break;

                    case 7: // Return the menu 
                        isSubmenuRunning = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please try again.");
                sc.nextLine(); 
            }
        }
    }
}
