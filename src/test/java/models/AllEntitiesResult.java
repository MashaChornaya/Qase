package models;

import lombok.Builder;
import lombok.Data;
import models.project.Project;

import java.util.List;

@Data
@Builder
public class AllEntitiesResult<T> {
    private List<T> entities;
    private int total,filtered,count;
}
