package models.defects;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Defect {
     List<Attachments> attachments;
     List<CustomFields> custom_fields;
     List<Tags> tags;
     int project_id;
     int member_id;
     int id;
     String code;
     String title;
     String actualResult;
     String severity;
     String milestone_id;
     String status;
     String created;
     String updated;
     String created_at;
     String updated_at;
     String resolved_at;
     String external_data;

}
