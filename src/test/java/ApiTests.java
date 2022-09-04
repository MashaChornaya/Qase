import adapters.DefectAdapter;
import adapters.ProjectAdapter;
import com.google.gson.Gson;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    private final String expectedJson = "{\"status\":true,\"result\":{\"total\":3,\"filtered\":3,\"count\":3,\"entities\":[{\"title\":\"Demo Project\",\"code\":\"DEMO\",\"counts\":{\"cases\":10,\"suites\":3,\"milestones\":2,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}},{\"title\":\"TMS Demo\",\"code\":\"TD\",\"counts\":{\"cases\":2,\"suites\":2,\"milestones\":0,\"runs\":{\"total\":2,\"active\":0},\"defects\":{\"total\":2,\"open\":0}}},{\"title\":\"QA19\",\"code\":\"QA\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}}]}}";
    private final String ACCESS_TOKEN="8a6fa3df11c7b1a33cf8fd7ca1d1f928f40cf2da";
    //"https://github.com/MashaChornaya/Salesforse/pull/1"
    private final static Gson gson=new Gson();
    ProjectAdapter projectAdapter=new ProjectAdapter();
    DefectAdapter defectAdapter=new DefectAdapter();
    @Test
    public void getAllProjectsTest() {
        String responseBody = projectAdapter.getAllProjects(200);
        Assert.assertEquals(responseBody, expectedJson);
    }
    @Test
    public void test1(){
                 given().header("Token",ACCESS_TOKEN )
                .header("Accept","application/json")
                .when()
                .get("https://app.qase.io/project")
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void createProjectTest(){
        String testCode="CODE2";

    Project project=Project.builder()
            .title("FGHBF")
            .code(testCode)
            .description("sdfcs")
            .build();

    ProjectResponse expectedProjectResponseBody= ProjectResponse
            .builder()
            .result(Result
                    .builder()
                    .code(testCode)
                    .build())
            .build();


        String actualResponseBody = projectAdapter.createProject(200, gson.toJson(project));
        Assert.assertEquals(gson.fromJson(actualResponseBody,ProjectResponse.class),expectedProjectResponseBody);
    }
    @Test
    public void getProjectByCodeTest(){
        String testCode="CODE3";
        Project project2=Project.builder()
                .code(testCode)
                .build();

        ProjectResponse expectedProjectResponseBody= ProjectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();
        String actualResponseBody = projectAdapter.getProjectByCode(200, gson.toJson(testCode));
        Assert.assertEquals(gson.fromJson(actualResponseBody,ProjectResponse.class),expectedProjectResponseBody);

    }
    @Test
    public void deleteProjectByCodeTest(){
        String testCode="CODE4";
        Project project3=Project.builder()
                .code(testCode)
                .build();

        ProjectResponse expectedProjectResponseBody= ProjectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();
        String actualResponseBody = projectAdapter.deleteProjectByCode();
        Assert.assertEquals(gson.fromJson(actualResponseBody,ProjectResponse.class),expectedProjectResponseBody);

    }
    @Test
    public void getAllDefectsTest(){
        String testCode="CODE5";

        Defect defect=Defect.builder()
                .code(testCode)
                .limit(99)
                .offset(123)
                .build();

        DefectResponse expectedDefectResponseBody= DefectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();


        String actualResponseBody = defectAdapter.getAllDefects(200);
        Assert.assertEquals(gson.fromJson(actualResponseBody,DefectResponse.class),expectedDefectResponseBody);
    }
    @Test
    public void createNewDefectsTest(){
        String testCode="CODE6";

        Defect defect=Defect.builder()
                .title("QAZ")
                .actualResult("sdfg")
                .severity(2)
                .milestone_id(1)
                .code(testCode)
                .limit(99)
                .offset(123)
                .build();

        DefectResponse expectedDefectResponseBody= DefectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();


        String actualResponseBody = defectAdapter.createDefect(200, gson.toJson(defect));
        Assert.assertEquals(gson.fromJson(actualResponseBody,DefectResponse.class),expectedDefectResponseBody);
    }
    @Test
    public void getSpecificDefectTest(){
        String testCode="CODE7";

        Defect defect=Defect.builder()
                .code(testCode)
                .id(77)
                .build();

        DefectResponse expectedDefectResponseBody= DefectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();


        String actualResponseBody = defectAdapter.getSpecificDefect(200, gson.toJson(testCode));
        Assert.assertEquals(gson.fromJson(actualResponseBody,DefectResponse.class),expectedDefectResponseBody);
    }
    @Test
    public void deleteDefectTest(){
        String testCode="CODE8";

        Defect defect=Defect.builder()
                .code(testCode)
                .id(88)
                .build();

        DefectResponse expectedDefectResponseBody= DefectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();


        String actualResponseBody = defectAdapter.deleteDefect();
        Assert.assertEquals(gson.fromJson(actualResponseBody,DefectResponse.class),expectedDefectResponseBody);
    }
    @Test
    public void updateDefectTest(){
        String testCode="CODE9";

        Defect defect=Defect.builder()
                .code(testCode)
                .id(99)
                .build();

        DefectResponse expectedDefectResponseBody= DefectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testCode)
                        .build())
                .build();

        String actualResponseBody = defectAdapter.updateDefect(200,gson.toJson(testCode),gson.toJson(defect));
        Assert.assertEquals(gson.fromJson(actualResponseBody,DefectResponse.class),expectedDefectResponseBody);
    }

}
