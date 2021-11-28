package pucrs.ages.garbus.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrashReportDTO {
    long id;
    double occupation;
}
