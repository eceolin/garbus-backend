package pucrs.ages.garbus.excpetion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private String message;
}
