package dinhthanhphu.graphql.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusPayload {
    private String msg;
    private Integer code;
}
