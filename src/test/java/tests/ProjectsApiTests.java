package tests;


import adapters.ProjectAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.AllEntitiesResult;
import models.defects.ErrorField;
import models.defects.NegativeResponse;
import models.defects.PositiveResponse;
import models.project.Counts;
import models.project.Project;
import models.project.Runs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
public class ProjectsApiTests {
    private final String ACCESS_TOKEN="a8700de32601161afac289bb231e151abde7334c";
    ProjectAdapter projectAdapter=new ProjectAdapter();
    private final static Gson gson=new Gson();
    @Test
    public void positiveApiTest(){
        given().header("Token",ACCESS_TOKEN )
                .header("Accept","application/json")
                .when()
                .get("https://app.qase.io/project")
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void getAllProjectsTest() {
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(AllEntitiesResult.builder()
                        .total(2)
                        .filtered(2)
                        .count(2)
                        .entities(Arrays.asList(
                                Project.builder()
                                        .title("asdfg")
                                        .code("qwer")
                                        .counts(Counts.builder()
                                        .runs(Runs.builder()
                                        .build())
                                        .build())
                                        .build(),
                                Project.builder()
                                        .title("yuio")
                                        .code("dcfvhj")
                                        .counts(Counts.builder()
                                        .runs(Runs.builder()
                                        .build())
                                        .build())
                                        .build()))
                        .build())
                .build();
        PositiveResponse<AllEntitiesResult<Project>> actualResponse = gson.fromJson((projectAdapter.getAllProjects(200)),
                new TypeToken<PositiveResponse<AllEntitiesResult<Project>>>() {
                }.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }
    @Test
    public void createProjectPositiveTest() {
        String testCode = "CODE14";

        Project project = Project.builder()
                .title("ADFDSFDSF")
                .code(testCode)
                .description("efsfsdfsdfsdf")
                .build();

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .code(testCode)
                        .build())
                .build();

        PositiveResponse<Project> actualResponse = gson.fromJson(
                projectAdapter.createProject(200, gson.toJson(project)),
                new TypeToken<PositiveResponse<Project>>() {}.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }
    @Test
    public void createProjectNegativeTest() {
        Project project = Project.builder()
                .title("")
                .description("")
                .build();
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Data is invalid.")
                .errorFields(Arrays.asList(
                        ErrorField.builder()
                                .field("title")
                                .error("Title is required.")
                                .build(),
                        ErrorField.builder()
                                .field("code")
                                .error("Project code is required.")
                                .build()
                ))
                .build();
        NegativeResponse actualResponse = gson.fromJson(projectAdapter.createProject(400, gson.toJson(project)), NegativeResponse.class);
        Assert.assertEquals(actualResponse, expectedResponse);
    }
    @Test
    public void getProjectByCodePositiveTest() {
        String testCode = "CODE14";
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .title("Project")
                        .code(testCode)
                        .counts(Counts.builder()
                                .runs(Runs.builder()
                                        .build())
                                .build())
                        .build())
                .build();

        PositiveResponse<Project> actualResponse = gson.fromJson((projectAdapter.getProjectByCode(200, testCode)),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }
    @Test
    public void getProjectByCodeNegativeTest() {
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Project is not found.")
                .build();
        NegativeResponse actualResponse = gson.fromJson((projectAdapter.getProjectByCode(404, "M1")), NegativeResponse.class);
        Assert.assertEquals(actualResponse, expectedResponse);
    }
    @Test
    public void deleteProjectByCodePositiveTest() {
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder().build();
        PositiveResponse<Project> actualResponse = gson.fromJson((projectAdapter.deleteProjectByCode(200,"M1")),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        Assert.assertEquals(actualResponse, expectedResponse);
    }
    @Test
    public void deleteProjectByCodeNegativeTest() {
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Project is not found.")
                .build();
        NegativeResponse actualResponse = gson.fromJson((projectAdapter.deleteProjectByCode(404, "M1")), NegativeResponse.class);
        Assert.assertEquals(actualResponse, expectedResponse);
    }
}
