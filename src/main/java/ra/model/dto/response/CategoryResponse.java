package ra.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponse {

    private Long categoryId;
    private String nameCategory;
    private Boolean status;

}
