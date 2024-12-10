/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author PC
 */
public class StudentLinkedList {
     Node head;
    private Iterable<Student> students;
    public StudentLinkedList() {
        head = null;
    }

    // Add a student to the linked list
    public void addStudent(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Linear search to find a student by ID with try-catch for input validation
    public Node findStudent(int id) {
        Node current = head;
        while (current != null) {
            if (current.student.id == id) {
                return current;
            }
            current = current.next;
        }
        return null;
    }


    // Update student information with validation and ranking
    public boolean updateStudent(int id, String newName, double newScore) {
        Node current = findStudent(id);
        if (current != null) {
            if (newScore < 0 || newScore > 10) {
                System.out.println("Error: Score must be between 0 and 10.");
                return false; 
            }
            current.student.name = newName;
            current.student.score = newScore;
            String rank = assignRank(newScore);
            System.out.println("Student updated successfully. New Rank: " + rank);
            return true;
        }
        System.out.println("Student not found.");
        return false;
    }

//    // Delete a student by ID
//    public boolean deleteStudent(int id) {
//        if (head == null) {
//            return false;
//        }
//        if (head.student.id == id) {
//            head = head.next;
//            return true;
//        }
//        Node current = head;
//        while (current.next != null) {
//            if (current.next.student.id == id) {
//                current.next = current.next.next;
//                return true;
//            }
//            current = current.next;
//        }
//        return false; 
//    }
    
    // Delete a student by ID
    private Set<Integer> deletedIds = new HashSet<>();
    public boolean deleteStudent(int id) {
        if (deletedIds.contains(id)) {
            System.out.println("Error: This student has been deleted. Please re-enter.");
            return false;
        }
        if (head == null) {
            return false;
        }
        if (head.student.id == id) {
            head = head.next;
            deletedIds.add(id);
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.student.id == id) {
                current.next = current.next.next;
                deletedIds.add(id);
                return true;
            }
            current = current.next;
        }
        return false;
    }


    // Display all students
    public void displayStudents() {
        if (head == null) {
            System.out.println("No students in the list.");
            return;
        }
        Node current = head;
        System.out.println("ID\tName\t\tScore\t\tRank");
        while (current != null) {
            String rank = assignRank(current.student.score); // Gọi assignRank để lấy xếp hạng
            System.out.printf("%d\t%s\t\t%.2f\t\t%s\n",
                              current.student.id,
                              current.student.name,
                              current.student.score,
                              rank);
            current = current.next;
        }
    }
    
    // BubbleSort: Sort students by score in either ascending or descending order
    public void bubbleSort(boolean ascending) {
        if (head == null || head.next == null) return;
        Node current;
        boolean swapped;
        do {
            swapped = false;
            current = head;
            while (current != null && current.next != null) {
                if ((ascending && current.student.score > current.next.student.score) ||
                    (!ascending && current.student.score < current.next.student.score)) {
                    // Swap students
                    Student temp = current.student;
                    current.student = current.next.student;
                    current.next.student = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
    
    // QuickSort: Sort students by score
    public void quickSort() {
        quickSortHelper(head, null);
    }
    private void quickSortHelper(Node start, Node end) {
        if (start != end) {
            Node pivot = partition(start, end);
            quickSortHelper(start, pivot);
            quickSortHelper(pivot.next, end);
        }
    }
    private Node partition(Node start, Node end) {
        Student pivot = start.student;
        Node current = start;
        Node prev = null;
        while (current != end) {
            if (current.student.score <= pivot.score) {
                if (prev == null) {
                    start = current;
                } else {
                    prev = current;
                }
            }
            current = current.next;
        }
        return start;
    }
    
    
    public String assignRank(double score) {
        if (score >= 0 && score <= 5.0) {
            return "Fail";
        } else if (score > 5.0 && score <= 6.5) {
            return "Medium";
        } else if (score > 6.5 && score <= 7.5) {
            return "Good";
        } else if (score > 7.5 && score <= 9.0) {
            return "Very Good";
        } else if (score > 9.0 && score <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid Score";
        }
    }
    
        // Check if ID already exists in Linked List
    public boolean containsId(int id) {
        Node current = head; 
        while (current != null) {
            if (current.student.id == id) {
                return true; 
            }
            current = current.next;
        }
        return false; 
    }

    // Check if student name is duplicated in list
    public boolean isNameDuplicate(String name) {
        Node current = head;
        while (current != null) {
            if (current.student.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false; 
    }
    void sortStudents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Object searchStudentById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Object searchStudentByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    boolean isDuplicateID(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    boolean isDuplicateName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    } 
}
