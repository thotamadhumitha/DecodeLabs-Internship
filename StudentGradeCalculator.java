import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("WELCOME TO STUDENT GRADE CALCULATOR");
        System.out.println("=" .repeat(40));
        
        // === PHASE 1: INPUT ===
        int numSubjects = 0;
        boolean validInput = false;
        
        // Get number of subjects with validation
        while (!validInput) {
            try {
                System.out.print("Enter number of subjects: ");
                numSubjects = sc.nextInt();
                
                if (numSubjects <= 0) {
                    System.out.println("Invalid input! Number of subjects must be greater than 0!");
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.next(); // Clear invalid input
            }
        }
        
        // Arrays to store subject names and marks
        String[] subjectNames = new String[numSubjects];
        int[] marks = new int[numSubjects];
        sc.nextLine(); // Consume leftover newline
        
        // Get subject names and marks
        System.out.println("\nEnter subject details:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter name of Subject " + (i + 1) + ": ");
            subjectNames[i] = sc.nextLine().trim();
            
            // Validate marks
            boolean validMark = false;
            while (!validMark) {
                try {
                    System.out.print("Enter marks for " + subjectNames[i] + " (0-100): ");
                    marks[i] = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    
                    if (marks[i] < 0 || marks[i] > 100) {
                        System.out.println("Marks must be between 0 and 100!");
                        continue;
                    }
                    validMark = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number between 0-100.");
                    sc.next(); // Clear invalid input
                }
            }
        }
        
        // === PHASE 2: PROCESS ===
        // Calculate total marks
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        
        // Calculate average (preserve decimal precision)
        double average = (double) totalMarks / numSubjects;
        
        // Determine grade using logic ladder
        char grade;
        String gradeDescription;
        
        if (average >= 90 && average <= 100) {
            grade = 'A';
            gradeDescription = "Excellent! Outstanding performance!";
        } else if (average >= 80 && average < 90) {
            grade = 'B';
            gradeDescription = "Very Good! Keep it up!";
        } else if (average >= 70 && average < 80) {
            grade = 'C';
            gradeDescription = "Good! Room for improvement!";
        } else if (average >= 60 && average < 70) {
            grade = 'D';
            gradeDescription = "Satisfactory. Needs more effort!";
        } else if (average >= 50 && average < 60) {
            grade = 'E';
            gradeDescription = "Below average. Work harder!";
        } else {
            grade = 'F';
            gradeDescription = "Fail. Must improve significantly!";
        }
        
        // Check Pass/Fail status (assuming pass mark is 40)
        boolean isPass = average >= 40;
        String status = isPass ? "PASS" : "FAIL";
        
        // Check if any subject failed (optional enhancement)
        boolean hasBacklog = false;
        for (int mark : marks) {
            if (mark < 35) {
                hasBacklog = true;
                break;
            }
        }
        String backlogStatus = hasBacklog ? "Has backlog(s)" : "All subjects cleared";
        
        // === PHASE 3: OUTPUT ===
        System.out.println("\n" + "=".repeat(50));
        System.out.println("STUDENT GRADE REPORT");
        System.out.println("=".repeat(50));
        
        // Display subject-wise marks
        System.out.println("\n Subject-wise Marks:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("   %-20s : %3d / 100%n", subjectNames[i], marks[i]);
        }
        
        // Display summary
        System.out.println("\n SUMMARY:");
        System.out.printf("   Total Marks        : %d / %d%n", totalMarks, numSubjects * 100);
        System.out.printf("   Average Percentage : %.2f%%%n", average);
        System.out.printf("   Grade              : %c (%s)%n", grade, gradeDescription);
        System.out.printf("   Status             : %s%n", status);
        System.out.printf("   Backlog Status     : %s%n", backlogStatus);
        
        // Performance message based on average
        System.out.println("\n" + "=".repeat(50));
        if (average >= 90) {
            System.out.println(" PHENOMENAL! You're a scholar!");
        } else if (average >= 75) {
            System.out.println(" GOOD JOB! Keep pushing for excellence!");
        } else if (average >= 60) {
            System.out.println(" KEEP GOING! Focus on weaker subjects!");
        } else if (average >= 40) {
            System.out.println(" WORK HARDER! You can do better!");
        } else {
            System.out.println(" NEEDS IMPROVEMENT! Seek help and practice more!");
        }
        System.out.println("=".repeat(50));
        System.out.println(" Thank you for using Student Grade Calculator!");
        
        sc.close();
    }
}