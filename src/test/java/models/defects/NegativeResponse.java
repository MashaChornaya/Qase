package models.defects;

import lombok.Builder;
import lombok.Data;
import models.defects.ErrorField;

import java.util.List;

@Data
@Builder
public class NegativeResponse {
    @Builder.Default
    private boolean status = false;
    private String errorMessage;
    private List<ErrorField> errorFields;
}