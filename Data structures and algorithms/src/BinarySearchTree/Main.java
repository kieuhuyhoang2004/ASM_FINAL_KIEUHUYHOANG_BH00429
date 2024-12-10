/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinarySearchTree;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Main {
     private static BinarySearchTree bst = new BinarySearchTree();
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
        int numStudents = getNumberOfStudents();
        inputStudentDetails(numStudents); 
        bst.displayStudentsAndRank(); 
        showMenu();
    }

    // Get number of students with validation
    private static int getNumberOfStudents() {
        int num = -1;
        while (num <= 0) {
            try {
                System.out.print("Enter number of students to add: ");
                num = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (num <= 0) {
                    System.out.println("The number must be greater than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return num;
    }

    // Input details for multiple students
    private static void inputStudentDetails(int numStudents) {
        for (int i = 1; i <= numStudents; i++) {
            System.out.println("\nInput details for student " + i + ":");
            String name = getStudentName();
            String studentCode = getValidStudentCode();
            double score = getValidScore();
            bst.insert(new Student(name, studentCode, score));
        }
    }

    // Get student's name
    private static String getStudentName() {
        System.out.print("Enter student name: ");
        return scanner.nextLine();
    }

    // Validate and get student code
    private static String getValidStudentCode() {
        String code;
        while (true) {
            System.out.print("Enter student code: ");
            code = scanner.nextLine().trim();
            if (code.matches("^[a-zA-Z0-9]+$")) {
                return code;
            } else {
                System.out.println("Code must contain only alphanumeric characters.");
            }
        }
    }

    // Get valid student score
    private static double getValidScore() {
        double score = -1;
        while (score < 0) {
            try {
                System.out.print("Enter score: ");
                score = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (score < 0) {
                    System.out.println("Score cannot be negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a valid number for score.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        return score;
    }

    // Display the menu and handle choices
    private static void showMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add a student");
            System.out.println("2. View students (In-order, Pre-order, Post-order)");
            System.out.println("3. Search for a student");
            System.out.println("4. Update student details");
            System.out.println("5. Delete a student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            handleMenuOption(option);
        }
    }

    // Handle the selected menu option
    private static void handleMenuOption(int option) {
        switch (option) {
            case 1:
                addNewStudent();
                break;
            case 2:
                System.out.println("In-order students list:");
                bst.inorderTraversal();

                System.out.println("Pre-order students list:");
                bst.preorderTraversal();

                System.out.println("Post-order students list:");
                bst.postorderTraversal();
                break;
            case 3:
                searchStudent();
                break;
            case 4:
                updateStudentDetails();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Add a new student
    private static void addNewStudent() {
        System.out.println("\nEnter details for new student:");
        String name = getStudentName(); 
        String studentCode = getValidStudentCode(); 
        double score = getValidScore();
        bst.insert(new Student(name, studentCode, score));
        System.out.println("Student added successfully.");
        System.out.println("List of students after added:");
        bst.displayStudentsAndRank();
    }

    // Search a student by code
    private static void searchStudent() {
        System.out.print("Enter student code to search: ");
        String code = scanner.nextLine();
        Student student = bst.search(code);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Update student details
    private static void updateStudentDetails() {
        System.out.print("Enter student code to update: ");
        String studentCode = scanner.nextLine(); 
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine(); 
        double newScore = getValidScore(); 
        bst.updateStudent(studentCode, newName, newScore);
    }

    // Delete student by code
    private static void deleteStudent() {
        System.out.print("Enter student code to delete: ");
        String studentCode = scanner.nextLine();
        bst.deleteStudent(studentCode);
    }
}
