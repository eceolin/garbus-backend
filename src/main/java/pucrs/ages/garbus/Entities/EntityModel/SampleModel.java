package pucrs.ages.garbus.Entities.EntityModel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@MappedSuperclass
public class SampleModel extends BaseModel {

    @Column(name = "SAMPLE_FIELD")
    @NotEmpty
    private String sampleField;

    @Column(name = "NUMBER_FIELD")
    @NotEmpty
    private int numberField;
}


