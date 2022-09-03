package models.project;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Defects {
    @Builder.Default
    private int total=0;
    @Builder.Default
    private int open=0;

}
