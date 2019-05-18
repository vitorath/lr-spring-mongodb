package pro.dev.vitorat.springmongo.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private String userName;
    private Integer rating;
    private Boolean approved;

}
