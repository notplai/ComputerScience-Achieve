import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Q2 {
    static void main(String[] args) {
        Question[] quiz = new Question[4];
        quiz[0] = new Question("Who was the inventor of Java?");
        quiz[0].setAnswer("James Gosling");

        ChoiceQuestion question = new ChoiceQuestion("In which country was the inventor of Java born?");
        question.addChoice("Australia", false);
        question.addChoice("Canada", true);
        question.addChoice("Denmark", false);
        question.addChoice("United States", false);

        quiz[1] = question;
        quiz[2] = new NumericQuestion("4.5 - 2.3 = ?");
        quiz[2].setAnswer("2.2");
        FillInQuestion fiQuestion = new FillInQuestion();
        fiQuestion.constructQuestionAndAnswer("The method that returns a string representation for each object is _toString_");
                quiz[3] = fiQuestion;
        Scanner in = new Scanner(System.in);
        for (Question q : quiz) {
            q.display();
            System.out.print("Your answer: ");
            String response = in.nextLine();
            System.out.println(q.checkAnswer(response));
        }
    }
}

class Question {
    protected String question, answer;

    Question(String question) {
        this.question = question;
    }
    Question() {}

    void setQuestion(String question) {
        this.question = question;
    }

    void setAnswer(String answer) {
        this.answer = answer;
    }

    boolean checkAnswer(String response) {
        return this.answer.equals(response);
    }

    void display() {System.out.println(this.question);}
}

class NumericQuestion extends Question {

    NumericQuestion(String question) { super(question); }

    @Override
    boolean checkAnswer(String response) {
        return Math.abs(Double.parseDouble(response) - Double.parseDouble(super.answer)) <= 0.01;
    }
}

class ChoiceQuestion extends Question {
    private ArrayList<String> choices = new ArrayList<>();

    ChoiceQuestion(String question) { super(question); }

    void addChoice(String choice, boolean correct) {
        choices.add(choice);
        if (correct) super.setAnswer(choice);
    }

    @Override
    boolean checkAnswer(String response) {
        return super.answer.equals(choices.get(Integer.parseInt(response) - 1));
    }

    @Override
    void display() {
        System.out.println(this.question);
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }
    }

}

class FillInQuestion extends Question {
    public FillInQuestion() {
        super();
    }
    public void constructQuestionAndAnswer(String questionText) {
        Scanner parser = new Scanner(questionText);
        parser.useDelimiter("_");
        StringBuilder question = new StringBuilder(parser.next());
        String answer = parser.next();
        parser.close();
        question.append(" ");
        question.append("_ ".repeat(answer.length()));
        this.setText(question.toString());
        this.setAnswer(answer);
    }

    private void setText(String string) {
        this.setQuestion(string);
    }
}