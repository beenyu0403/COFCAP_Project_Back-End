package proj.been433.cofcapshop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@DiscriminatorValue("C")
@Getter
@Setter
public class Coffee extends Item {
   private String imageName;
   @Embedded
   private Description description;

}
