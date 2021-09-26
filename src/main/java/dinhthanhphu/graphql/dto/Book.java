package dinhthanhphu.graphql.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Book {
    private Long id;
    private String name;
    private String genre;
    private Long authorId;
}
