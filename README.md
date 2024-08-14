# QuestionAnswerProgram

## Overview

`QuestionAnswerProgram` is a Java command-line application that allows users to ask specific questions and add new questions with multiple answers. If a question is not found, the program returns a predefined response inspired by "The Hitchhiker's Guide to the Galaxy."

## Features

1. **Ask a Question**
   - Retrieve the answers to a specific question.
   - If the question is not found, the program responds with: "The answer to life, universe, and everything is 42."

2. **Add a Question and Answers**
   - Format: `<question>? "<answer1>" "<answer2>" ... "<answerX>"`
   - Each question must have at least one answer and can have multiple answers.

3. **Restrictions**
   - Questions and answers are limited to a maximum of 255 characters.
   - Questions are exact matches; no "best match" search is performed.

## Usage

### Running the Program

1. Compile the Java classes:
    ```sh
    javac QuestionAnswerProgram.java
    javac TestQuestionAnswerProgram.java
    ```

2. Run the main application:
    ```sh
    java QuestionAnswerProgram
    ```

3. Follow the prompts to either ask a question, add a question and answers, or exit the program.

### Example

- **Adding a Question:**
    ```
    What is Peters favorite food? "Pizza" "Spaghetti" "Ice cream"
    ```

- **Asking a Question:**
    ```
    What is Peters favorite food?
    ```
    Output:
    ```
    Pizza
    Spaghetti
    Ice cream
    ```

- **Question Not Found:**
    ```
    When is Peters birthday?
    ```
    Output:
    ```
    The answer to life, universe, and everything is 42
    ```

## Testing

The `TestQuestionAnswerProgram` class includes methods to validate the functionality of the `QuestionAnswerProgram`. It tests:

- Valid question addition
- Invalid input handling
- Answer retrieval for existing questions
- Handling of non-existent questions
- Maximum length constraints for questions and answers
- Duplicate question prevention