package br.com.akaji.dojo.models;

import br.com.akaji.dojo.dtos.QuestionDTO;
import br.com.akaji.dojo.interfaces.Cast;
import br.com.akaji.dojo.interfaces.DataTransferObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "questions")
public final class Question implements Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String description;

    @Column(insertable = false, updatable = false)
    private String type;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Quiz> quiz;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Question parent;

    @OneToMany(mappedBy = "parent")
    private List<Question> children;

    @Override
    public DataTransferObject toDto() {
        return new QuestionDTO(this);
    }
}
