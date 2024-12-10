/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinarySearchTree;

/**
 *
 * @author PC
 */
public class Student {
    String name;
    String studentCode;
    double score;
    String rank;
    public int id;

    public Student(String name, String studentCode, double score) {
        this.name = name;
        this.studentCode = studentCode;
        this.score = score;
        updateRank();
    }

    public Student(int id, String name, double score) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Cập nhật xếp hạng của sinh viên
    public void updateRank() {
        if (this.score <= 5.0) this.rank = "Fail";
        else if (this.score <= 6.5) this.rank = "Medium";
        else if (this.score <= 7.5) this.rank = "Good";
        else if (this.score <= 9.0) this.rank = "Very Good";
        else this.rank = "Excellent";
    }
    
    
    @Override
    public String toString() {
        return name + ", Code: " + studentCode + ", Score: " + score + ", Rank: " + rank;
    }
    
    
    public String getName() {
        return name;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public double getScore() {
        return score;
    }

    public String getRank() {
        return rank;
    }

    void setScore(double newScore) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void setName(String newName) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
