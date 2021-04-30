package pucrs.ages.garbus.excpetion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException {
    private String message;
}
