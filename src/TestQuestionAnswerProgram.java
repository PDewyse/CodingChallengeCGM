import java.util.List;
import java.util.Map;

public class TestQuestionAnswerProgram {
    public static void main(String[] args) {
        int maxLength = QuestionAnswerProgram.getMaxLength();

        testAddQuestionWithValidInput();
        testAddQuestionWithInvalidInput();
        testAskQuestionExists();
        testAskQuestionDoesNotExist();
        testQuestionMaxLength(maxLength);
        testAnswerMaxLength(maxLength);
        testDuplicateQuestion();
    }

    private static void testAddQuestionWithValidInput() {
        System.out.println("Running test: testAddQuestionWithValidInput");

        String input = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
        QuestionAnswerProgram.clearQuestions(); // Clear previous questions
        QuestionAnswerProgram.addQuestionFromTest(input);

        Map<String, Question> questions = QuestionAnswerProgram.getQuestions();
        Question question = questions.get("What is Peters favorite food?");

        assert question != null : "Failed to add question.";
        assert question.getAnswers().contains("Pizza") : "Answer 'Pizza' was not added.";
        assert question.getAnswers().contains("Spaghetti") : "Answer 'Spaghetti' was not added.";
        assert question.getAnswers().contains("Ice cream") : "Answer 'Ice cream' was not added.";

        System.out.println("Test passed!");
    }

    private static void testAddQuestionWithInvalidInput() {
        System.out.println("Running test: testAddQuestionWithInvalidInput");

        String input = "What is Peters favorite food \"Pizza\" \"Spaghetti\"";
        QuestionAnswerProgram.clearQuestions();
        boolean added = QuestionAnswerProgram.addQuestionFromTest(input);

        assert !added : "Questions with invalid format should not be added.";

        System.out.println("Test passed!");
    }

    private static void testAskQuestionExists() {
        System.out.println("Running test: testAskQuestionExists");

        String input = "What is Peters favorite color? \"Blue\" \"Green\"";
        QuestionAnswerProgram.clearQuestions();
        QuestionAnswerProgram.addQuestionFromTest(input);

        String askedQuestion = "What is Peters favorite color?";
        List<String> answers = QuestionAnswerProgram.askQuestionFromTest(askedQuestion);

        assert answers.contains("Blue") : "Expected answer 'Blue' not found.";
        assert answers.contains("Green") : "Expected answer 'Green' not found.";

        System.out.println("Test passed!");
    }

    private static void testAskQuestionDoesNotExist() {
        System.out.println("Running test: testAskQuestionDoesNotExist");

        QuestionAnswerProgram.clearQuestions();
        String askedQuestion = "What is Peters birthday?";
        String answer = QuestionAnswerProgram.askQuestionNotFoundFromTest(askedQuestion);

        assert "The answer to life, universe, and everything is 42".equals(answer) : "Default answer not returned.";

        System.out.println("Test passed!");
    }

    private static void testQuestionMaxLength(int maxLength) {
        System.out.println("Running test: testQuestionMaxLength");

        String longQuestion = "Q".repeat(maxLength + 1) + "? \"Answer\""; 
        boolean added = QuestionAnswerProgram.addQuestionFromTest(longQuestion);

        assert !added : "Question exceeding " + maxLength + " max length should not be added.";

        System.out.println("Test passed!");
    }

    private static void testAnswerMaxLength(int maxLength) {
        System.out.println("Running test: testAnswerMaxLength");

        String longAnswer = "\"" + "A".repeat(maxLength + 1) + "\""; 
        String input = "Is this answer too long? " + longAnswer;
        boolean added = QuestionAnswerProgram.addQuestionFromTest(input);

        assert !added : "Answer exceeding " + maxLength + " should not be added.";

        System.out.println("Test passed!");
    }

    private static void testDuplicateQuestion() {
        System.out.println("Running test: testDuplicateQuestion");

        String input = "What is Peters favorite drink? \"Water\"";
        QuestionAnswerProgram.clearQuestions();
        QuestionAnswerProgram.addQuestionFromTest(input);

        boolean addedAgain = QuestionAnswerProgram.addQuestionFromTest(input);

        assert !addedAgain : "Duplicate question should not be added.";

        System.out.println("Test passed!");
    }
}