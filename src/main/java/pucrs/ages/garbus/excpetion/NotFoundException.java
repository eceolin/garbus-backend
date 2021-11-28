package pucrs.ages.garbus.excpetion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pucrs.ages.garbus.dtos.ErrorResponse;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException implements Serializable {
    private ErrorResponse error;
}
