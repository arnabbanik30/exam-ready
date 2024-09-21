package com.du.yiit.examReady.question;

public class QuestionWithoutCorrectDTO {
    private int id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String selected;



    public QuestionWithoutCorrectDTO(Question question){
        this.id=question.getId();
        this.question=question.getQuestion();
        this.optionA=question.getOptionA();
        this.optionB=question.getOptionB();
        this.optionC=question.getOptionC();
        this.optionD=question.getOptionD();
        this.selected=question.getSelected();
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getSelected() {
        return selected;
    }
}
