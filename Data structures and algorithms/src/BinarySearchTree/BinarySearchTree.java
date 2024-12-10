/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class BinarySearchTree {
    private TreeNode root;
    private boolean showTitle;

    public BinarySearchTree() {
        this.root = null;
    }
    // Add a student after validating input
    public void addStudent(int numberOfStudents) {
            Scanner scanner = new Scanner(System.in);
        // Loop to add the specified number of students
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Input details for student " + (i + 1) + ":");
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            String studentCode = getValidStudentCode(scanner);
            double score = getValidScore(scanner);
            Student student = new Student(name, studentCode, score);
            insert(student);
            System.out.println("Student added successfully.");
        }
        // Display the list of students after adding
        System.out.println("List of students after added:");
        displayStudentsAndRank();
    }
    // Insert student into BST
    void insert(Student student) {
        root = insertRec(root, student);
    }
    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) {
            return new TreeNode(student);
        }
        if (student.studentCode.compareTo(node.student.studentCode) < 0) {
            node.left = insertRec(node.left, student);
        } else if (student.studentCode.compareTo(node.student.studentCode) > 0) {
            node.right = insertRec(node.right, student);
        } else {
            System.out.println("Duplicate student code. Cannot insert.");
        }
        return node; 
    } 
    
    
    // Get valid student code (non-empty input)
    private String getValidStudentCode(Scanner scanner) {
        String studentCode;
        while (true) {
            System.out.print("Enter student code: ");
            studentCode = scanner.nextLine().trim();
            if (!studentCode.isEmpty()) break;
            System.out.println("Student code cannot be empty.");
        }
        return studentCode;
    }
    // Get valid student score (positive number)
    private double getValidScore(Scanner scanner) {
        double score;
        while (true) {
            System.out.print("Enter student score: ");
            if (scanner.hasNextDouble()) {
                score = scanner.nextDouble();
                if (score >= 0) break;
                System.out.println("Score must be non-negative.");
            } else {
                System.out.println("Invalid score input. Please enter a numeric value.");
                scanner.next(); // Consume invalid input
            }
        }
        return score;
    }
    
    

    // Display students and their rank based on score
    public void displayStudentsAndRank() {
       
        System.out.println("List of Students:");
        List<Student> students = new ArrayList<>();
        collectStudents(root, students);
        students.sort((s1, s2) -> Double.compare(s2.score, s1.score));
        for (Student student : students) {
            updateRank(student);
            System.out.println("Student ID: " + student.studentCode + ", Name: " + student.name + ", Score: " + student.score + ", Rank: " + student.rank);
        }
    }

    // Rank the student based on score
    private void updateRank(Student student) {
        if (student.score <= 5.0) 
            student.rank = "Fail";
        else if (student.score <= 6.5) 
            student.rank = "Medium";
        else if (student.score <= 7.5) 
            student.rank = "Good";
        else if (student.score <= 9.0) 
            student.rank = "Very Good";
        else 
            student.rank = "Excellent";
    }

    // Helper method to collect all students from the BST
    private void collectStudents(TreeNode node, List<Student> students) {
            if (node != null) {
            collectStudents(node.left, students);
            students.add(node.student);
            collectStudents(node.right, students);
        }
    }
    
    // Update student rank based on score
    private void updateRank(Student student, int rank) {
        student.rank = Integer.toString(rank);  // Assign rank as a string
    }

    // Search for a student by code
    public void searchStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student code to search: ");
        String studentCode = scanner.nextLine();
        Student student = search(studentCode);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Binary Search for student
    Student search(String studentCode) {
    TreeNode current = root;
    while (current != null) {
        int comparison = studentCode.compareTo(current.student.studentCode);
        if (comparison == 0) return current.student;
        current = (comparison < 0) ? current.left : current.right; 
    }
    return null; 
}


    // Update student details
    public void updateStudent(String studentCode, String newName, double newScore) {
        if (newScore < 0) {
            System.out.println("Invalid score. Score must be non-negative.");
            return;
        }

        Student student = search(studentCode); 
        if (student != null) {
            student.name = newName; 
            student.score = newScore;
            updateRank(student); 
            System.out.println("Student updated successfully.");
            displayStudentsAndRank();
        } else {
            System.out.println("Student not found."); 
        }
    }

    // Delete student by code
    public void deleteStudent(String studentCode) {
        root = deleteRec(root, studentCode);
        System.out.println("Student deleted successfully.");
        displayStudentsAndRank();
    }

    private TreeNode deleteRec(TreeNode node, String studentCode) {
        if (node == null) return node;

        int comparison = studentCode.compareTo(node.student.studentCode);
        if (comparison < 0) node.left = deleteRec(node.left, studentCode);
        else if (comparison > 0) node.right = deleteRec(node.right, studentCode);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;

            node.student = minValue(node.right);
            node.right = deleteRec(node.right, node.student.studentCode);
        }
        return node;
    }
    
    
    
    private Student minValue(TreeNode node) {
        while (node.left != null) node = node.left;
        return node.student;
    }
    
    
    
    
    // Helper methods to traverse the BST and display students
    public void inorderTraversal() {
        inorderRec(root);
    }
    private void inorderRec(TreeNode node) {
        if (node != null) {
            inorderRec(node.left);
            // Only show ID, name, and score without rank for tree traversal
            System.out.println("Student ID: " + node.student.studentCode + ", Name: " 
                    + node.student.name + ", Score: " + node.student.score);
            inorderRec(node.right);
        }
    }
    public void preorderTraversal() {
        preorderRec(root);
    }
    private void preorderRec(TreeNode node) {
        if (node != null) {
            // Only show ID, name, and score without rank for tree traversal
            System.out.println("Student ID: " + node.student.studentCode + ", Name: " 
                    + node.student.name + ", Score: " + node.student.score);
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }
    public void postorderTraversal() {
        postorderRec(root);
    }
    private void postorderRec(TreeNode node) {
        if (node != null) {
            postorderRec(node.left);
            postorderRec(node.right);
            // Only show ID, name, and score without rank for tree traversal
            System.out.println("Student ID: " + node.student.studentCode + ", Name: " 
                    + node.student.name + ", Score: " + node.student.score);
        }
    }


    void update(String studentCode, String newName, double newScore) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void delete(String studentCode) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void displayStudentsAndRank(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // TreeNode class for BST
    private static class TreeNode {
        Student student;
        TreeNode left, right;

        TreeNode(Student student) {
            this.student = student;
            this.left = this.right = null;
        }
    }
}
