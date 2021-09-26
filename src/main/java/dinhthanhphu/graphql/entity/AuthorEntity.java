package dinhthanhphu.graphql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;

    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;
}
