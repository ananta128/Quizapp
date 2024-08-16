import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {

    // Class to represent a Quiz Question
    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        // Constructor
        Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        // Method to display the question
        void displayQuestion() {
            System.out.println(questionText);
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
        }

        // Method to check if the selected option is correct
        boolean isCorrect(int selectedOption) {
            return selectedOption == correctAnswerIndex + 1; // +1 because options start from 1 in display
        }
    }

    public static void main(String[] args) {
        // Array to store questions
        Question[] questions = new Question[]{
                new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2),
                new Question("What is 5 + 7?", new String[]{"10", "11", "12", "13"}, 2)
        };

        // Variables to keep track of score and user input
        int score = 0;
        Scanner scanner = new Scanner(System.in);

        // Loop through each question
        for (Question question : questions) {
            question.displayQuestion();

            // Timer to limit the time to answer
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    System.exit(0); // Exit the program when time is up
                }
            }, 10000); // Set timer for 10 seconds (10000 milliseconds)

            // Get user input for the answer
            System.out.print("Your answer (1-4): ");
            int selectedOption = scanner.nextInt();

            // Stop the timer as the user has answered
            timer.cancel();

            // Check if the answer is correct and update the score
            if (question.isCorrect(selectedOption)) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong! The correct answer was " + (question.correctAnswerIndex + 1) + ".\n");
            }
        }

        // Display the final score
        System.out.println("Quiz finished! Your final score is: " + score + "/" + questions.length);
    }
}
