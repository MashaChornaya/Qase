package tests;

import adapters.DefectAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.AllEntitiesResult;
import models.defects.Defect;
import models.defects.NegativeResponse;
import models.defects.PositiveResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;


public class DefectsApiTests {
    DefectAdapter defectAdapter=new DefectAdapter();
    private final static Gson gson=new Gson();


    @Test
    public void getAllDefects(){

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(AllEntitiesResult.builder()
                        .total(2)
                        .filtered(2)
                        .count(2)
                        .entities(Arrays.asList(
                                Defect.builder()
                                        .id(1)
                                        .title("NewDefect")
                                        .actualResult("this")
                                        .status("open")
                                        .milestone_id(null)
                                        .project_id(235608)
                                        .severity("blocker")
                                        .member_id(1)
                                        .attachments(Arrays.asList())
                                        .custom_fields(Arrays.asList())
                                        .external_data("{}")
                                        .resolved_at(null)
                                        .created("2022-09-03 20:55:52")
                                        .updated("2022-09-03 20:55:52")
                                        .created_at("2022-09-03T20:55:52+00:00")
                                        .updated_at("2022-09-03T20:55:52+00:00")
                                        .tags(Arrays.asList())
                                        .build()))
                        .build())
                .build();
        PositiveResponse<AllEntitiesResult<Defect>> actualResponse = gson.fromJson((defectAdapter.getAllDefects(200,"M1")),
                new TypeToken<PositiveResponse<AllEntitiesResult<Defect>>>() {
                }.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }
    @Test
    public void createNewDefect() {
        String testCode="14FGH";
        Defect defect = Defect.builder()
                .title("BIG")
                .actualResult("LOST")
                .severity("7")
                .build();

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(3)
                        .build())
                .build();

        PositiveResponse<Defect> actualResponse = gson.fromJson(
                defectAdapter.createDefect(200, testCode, gson.toJson(defect)),
                new TypeToken<PositiveResponse<Defect>>() {}.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void getSpecificDefectPositiveTest() {

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(1)
                        .title("SWDFG")
                        .actualResult("QASDFVGT54")
                        .status("open")
                        .milestone_id(null)
                        .project_id(235608)
                        .severity("blocker")
                        .member_id(1)
                        .attachments(Arrays.asList())
                        .custom_fields(Arrays.asList())
                        .external_data("{}")
                        .resolved_at(null)
                        .created("2022-09-03 20:55:07")
                        .updated("2022-09-03 20:55:07")
                        .created_at("2022-09-03T20:55:07+00:00")
                        .updated_at("2022-09-03T20:55:07+00:00")
                        .tags(Arrays.asList())
                        .build())
                .build();

        PositiveResponse<Defect> actualResponse = gson.fromJson((defectAdapter.getSpecificDefect(200,"M1",1)),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void getSpecificDefectNegativeTest() {
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("The given route params are invalid")
                .build();
        NegativeResponse actualResponse = gson
                .fromJson((defectAdapter.getSpecificDefect(400,"M1",1)), NegativeResponse.class);
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void deleteDefectPositiveTest() {

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .code("M1")
                        .id(2)
                        .build())
                .build();
        PositiveResponse<Defect> actualResponse = gson.fromJson(((defectAdapter.deleteDefect(200,2,"M1"))),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void deleteDefectNegativeTest() {

        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Project is not found.")
                .build();
        NegativeResponse actualResponse = gson.fromJson((defectAdapter.deleteDefect(404,2,"M1")), NegativeResponse.class);
        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void updateDefectPositiveTest() {
        Defect defect = Defect.builder()
                .title("BEST")
                .actualResult("TW")
                .build();
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id(3)
                        .build())
                .build();
        PositiveResponse<Defect> actualResponse = gson.fromJson((defectAdapter.updateDefect(200,  3, "M1", gson.toJson(defect))),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }
}
