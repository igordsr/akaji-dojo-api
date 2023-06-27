package br.com.akaji.dojo.dtos;

import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDTO implements DataTransferObject {

    private Long id;

    private String description;

    private List<DataTransferObject> children;

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.description = question.getDescription();
        this.children = question.getChildren().stream().map(Question::toDto).collect(Collectors.toList());
    }
}
