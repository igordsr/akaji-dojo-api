package br.com.akaji.dojo.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.Quiz;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizDTO implements DataTransferObject {
    private Long id;
    private DataTransferObject question;
    private List<DataTransferObject> response = new ArrayList<>();
    public QuizDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.question = quiz.getQuestion().toDto();
    }
}
