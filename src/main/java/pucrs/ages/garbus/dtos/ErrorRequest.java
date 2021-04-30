package pucrs.ages.garbus.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRequest {
    @NotNull private Long trashId;
    private Long typeEventId;
    @NotNull private String login;
    private String others;
}
