package pucrs.ages.garbus.excpetion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pucrs.ages.garbus.dtos.ErrorResponse;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException implements Serializable {
    private ErrorResponse error;
}
