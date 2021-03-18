package pucrs.ages.garbus.Entities;

import lombok.Getter;
import pucrs.ages.garbus.Entities.EntityModel.Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Table(name = "USERS_PROFILE")
class User extends Person {

    @Column(name = "ROLE")
    @NotEmpty
    private String role;

//    @Override
//    public String toString() {
//        return new ToStringCreator(this)
//
//                .append("id", this.getId()).append("new", this.isNew()).append("lastName", this.getLastName())
//                .append("firstName", this.getFirstName()).append("address", this.address).append("city", this.city)
//                .append("telephone", this.telephone).toString();
//    }
}
