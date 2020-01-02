package com.quiz.app.sara;

public class Question {
    int id;
    String question;
    String answer;
    String falseOne;
    String falseTow;
    String falseThre;


    public Question(int id, String question, String answer, String falseOne, String falseTow, String falseThre) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.falseOne = falseOne;
        this.falseTow = falseTow;
        this.falseThre = falseThre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFalseOne() {
        return falseOne;
    }

    public void setFalseOne(String falseOne) {
        this.falseOne = falseOne;
    }

    public String getFalseTow() {
        return falseTow;
    }

    public void setFalseTow(String falseTow) {
        this.falseTow = falseTow;
    }

    public String getFalseThre() {
        return falseThre;
    }

    public void setFalseThre(String falseThre) {
        this.falseThre = falseThre;
    }
}
