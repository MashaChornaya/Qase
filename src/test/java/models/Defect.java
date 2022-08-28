package models;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Defect {
    int limit;
    int offset;
    String code;
    String title;
    String actualResult;
    int severity;
    int milestone_id;
    int id;
}
