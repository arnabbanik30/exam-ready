package com.du.yiit.examReady.exam.ExamSubmission.ExamEvaluation;

import com.du.yiit.examReady.exam.Exam;
import com.du.yiit.examReady.exam.ExamRepository;
import com.du.yiit.examReady.question.Question;
import com.du.yiit.examReady.question.QuestionRepository;
import com.du.yiit.examReady.utils.ExamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EvaluationWithoutNegativeMarkingStrategy implements EvaluationStrategy {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionRepository questionRepository;

    public EvaluationWithoutNegativeMarkingStrategy(ExamRepository examRepository, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public double evaluate(int examId) {
        Exam exam = examRepository.getById(examId);
        List<Question> questions = questionRepository.findByExamId(examId);
        ExamUtils examUtils=new ExamUtils(questionRepository);

        return examUtils.getNumberOfCorrectAnswers(questions);
    }
}
