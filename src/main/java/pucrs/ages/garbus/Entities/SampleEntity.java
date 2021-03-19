package pucrs.ages.garbus.Entities;

import lombok.Getter;
import org.springframework.core.style.ToStringCreator;
import pucrs.ages.garbus.Entities.EntityModel.SampleModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "TEST_ENTITY")
class SampleEntity extends SampleModel {

    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("id", this.getId())
            .append("new", this.isNew())
            .toString();
    }
}
