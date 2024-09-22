package com.du.yiit.examReady.question;

import com.du.yiit.examReady.exam.ExamRepository;
import com.du.yiit.examReady.exam.ExamSubmission.SelectedQuestionDTO;
import com.du.yiit.examReady.exam.ExamSubmission.SubmittedExamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ExamRepository examRepository;

    public QuestionService(QuestionRepository questionRepository, ExamRepository examRepository){
        this.questionRepository=questionRepository;
        this.examRepository=examRepository;
    }

    public void createQuestion(Question question, int exam_id){
        question.setExam(this.examRepository.getById(exam_id));
        question.setSelected(null);
        questionRepository.save(question);
    }

    public void updateQuestionAnswers(SubmittedExamDTO submittedExamDTO){
        for(var q : submittedExamDTO.getQuestions()){
            if (q.getSelectedOption() != null) {
                Optional<Question> optionalQuestion = questionRepository.findById(q.getQuestionId());
                var question = optionalQuestion.get();
                question.setSelected(q.getSelectedOption());
                questionRepository.save(question);
            }
        }
    }

    public ArrayList<QuestionWithCorrectDTO> getDTOList(SubmittedExamDTO submittedExamDTO){

        var questions = questionRepository.findByExamId(submittedExamDTO.getExamId());

        var questionsWithCorrectDTOS = new ArrayList<QuestionWithCorrectDTO>();

        for (var q : questions)
        {
            questionsWithCorrectDTOS.add(new QuestionWithCorrectDTO(q));
        }
        return questionsWithCorrectDTOS;
    }
}
