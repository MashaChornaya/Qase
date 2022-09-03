package models.defects;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorField {
    String field, error;
}
