package br.com.akaji.dojo.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.akaji.dojo.interfaces.DataTransferObject;
import br.com.akaji.dojo.models.Quiz;
import br.com.akaji.dojo.models.Test;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestDTO implements DataTransferObject {
    private Long id;
    private String description;
    private List<DataTransferObject> quiz;

    public TestDTO(Test test) {
        this.id = test.getId();
        this.description = test.getDescription();
        if (test.getQuiz() != null) {
            this.quiz = test.getQuiz().stream().map(Quiz::toDto).collect(Collectors.toList());
        }
    }
}
