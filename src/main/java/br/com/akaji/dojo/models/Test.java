package br.com.akaji.dojo.models;

import br.com.akaji.dojo.dtos.TestDTO;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "tests")
public class Test implements Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    protected String description;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Quiz> quiz;

    public Test() {
        this.quiz = new ArrayList<>();
    }

    public Test(Long id, String description, List<Quiz> quiz) {
        this.id = id;
        this.description = description;
        if (quiz != null) {
            this.quiz = quiz.stream().filter(item -> item.getQuestion().getParent() == null).collect(Collectors.toList());
        }
    }

    @Override
    public DataTransferObject toDto() {
        return new TestDTO(this);
    }

}
