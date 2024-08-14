import java.util.*;

class Question {
    private String question;
    private List<String> answers;

    public Question(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }
}

public class QuestionAnswerProgram {
    private static final int MAX_LENGTH = 255;
    private static Map<String, Question> questions = new HashMap<>(); // Will store questions

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose from the following options: \n1. Ask a question.\n2. Add a question and answer(s).\n3. Exit the program.");
            String userOption = scanner.nextLine();

            switch (userOption) {
                case "1":
                    askQuestion(scanner);
                    break;
                case "2":
                    addQuestion(scanner);
                    break;
                case "3":
                    System.out.println("Exiting program.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between option 1, 2 and 3.");
                    break;
            }
        }
        scanner.close();
    }

    private static void askQuestion(Scanner scanner) {
        System.out.println("Enter your question:");
        String askedQuestion = scanner.nextLine();

        if (questions.containsKey(askedQuestion)) { // Exact match
            Question question = questions.get(askedQuestion);
            question.getAnswers().forEach(x -> System.out.println(x));
        } else {
            System.out.println("The answer to life, universe, and everything is 42");
            System.out.println(answerToAll());
        }
    }

    private static void addQuestion(Scanner scanner) {
        while (true) {
            System.out.println("Enter your question followed by answer(s) or type b to go back (format: <question>? \"<answer1>\" \"<answer2>\"):");
            String input = scanner.nextLine();

            if (input.equals("b")) {
                break;
            }

            // So it splits on the first question mark
            int questionMarkIndex = input.indexOf('?');
            if (questionMarkIndex != -1) {
                String questionText = input.substring(0, questionMarkIndex + 1).trim();
                String answersText = input.substring(questionMarkIndex + 1).trim();

                if (!isValidQuestion(questionText)) {
                    continue;
                }

                List<String> answersList = parseAnswers(answersText);
                if (answersList == null) {
                    continue;
                }

                Question question = new Question(questionText, answersList);
                questions.put(questionText, question);
                System.out.println("Question and answers added successfully.");
                break;

            } else {
                System.out.println("Invalid format. Please try again. (format: <question>? \"<answer1>\" \"<answer2>\")");
            }
        }
    }

    private static boolean isValidQuestion(String question) {
        if (question.length() > MAX_LENGTH) {
            System.out.println("Questions can only have a max of " + MAX_LENGTH + " characters.");
            return false;
        }
        if (questions.containsKey(question)) {
            System.out.println("Question already exists. Please try again.");
            return false;
        }
        return true;
    }

    private static boolean isValidAnswer(String answer) {
        if (answer.length() > MAX_LENGTH) {
            System.out.println("Answers can only have a max of " + MAX_LENGTH + " characters.");
            return false;
        }
        return true;
    }

    /**
     * Parses the answers text and returns a list of answers.
     * @param answersText
     * @return List of answers or null if the answers are invalid.
     */
    private static List<String> parseAnswers(String answersText) {
        String[] answersArray = answersText.split("\"");
        List<String> answersList = new ArrayList<>();

        for (int i = 1; i < answersArray.length; i += 2) { // skip the empty strings between answers
            String answer = answersArray[i].trim();
            if (!isValidAnswer(answer)) {
                return null;
            }
            answersList.add(answer);
        }

        return answersList;
    }

    private static String answerToAll() {
        return """
         @@@@    @@@@@@@@      
        @@@@@    @    @@@@     
       @@@ @@          @@@     
      @@@  @@         @@@      
     @@    @@        @@@       
    @@@@@@@@@@@    @@@@        
           @@    @@@@          
           @@   @@@@@@@@@@     
                               
    """;
    }

    // Methods for testing ----------------------------------------------
    public static int getMaxLength() {
        return MAX_LENGTH;
    }
    
    public static Map<String, Question> getQuestions() {
        return questions;
    }

    public static void clearQuestions() {
        questions.clear();
    }

    /**
     * Adds a question and answers from a test input.
     * @param input
     * @return true if the question was added successfully, false otherwise.
     */
    public static boolean addQuestionFromTest(String input) {
        // So it splits on the first question mark
        int questionMarkIndex = input.indexOf('?');
        if (questionMarkIndex != -1) {
            String questionText = input.substring(0, questionMarkIndex + 1).trim();
            String answersText = input.substring(questionMarkIndex + 1).trim();

            if (!isValidQuestion(questionText)) {
                return false;
            }

            List<String> answersList = parseAnswers(answersText);
            if (answersList == null) {
                return false;
            }

            Question question = new Question(questionText, answersList);
            questions.put(questionText, question);
            return true;
        }
        
        return false;
    }


    /**
     * Asks a question from the test questions.
     * @param askedQuestion
     * @return List of answers or null if the question does not exist.
     */
    public static List<String> askQuestionFromTest(String askedQuestion) {
        if (questions.containsKey(askedQuestion)) {
            return questions.get(askedQuestion).getAnswers();
        }
        return null;
    }

    /**
     * Asks a question that does not exist from the test questions.
     * @param askedQuestion
     * @return The answer to life, universe, and everything if the question does not exist.
     */
    public static String askQuestionNotFoundFromTest(String askedQuestion) {
        if (!questions.containsKey(askedQuestion)) {
            return "The answer to life, universe, and everything is 42";
        }
        return null;
    }
}
