package adapters;

public class DefectAdapter extends  BaseAdapter{
    private static final String ENDPOINT = "defect";

    public String getAllDefects(int statusCode,String projectCode) {

        return get(ENDPOINT + "/" + projectCode, statusCode);
    }
    public String createDefect(int statusCode, String projectCode, String requestBody) {

        return post(ENDPOINT + "/" + projectCode, statusCode, requestBody);
    }
    public String getSpecificDefect(int statusCode, String projectCode, int id) {
        return get(ENDPOINT + "/" + projectCode + "/" + id, statusCode);
    }
    public String deleteDefect(int statusCode,int id, String projectCode){
    return delete(ENDPOINT + "/" + id + "/" + projectCode, statusCode);
    }
    public String updateDefect(int statusCode,int id,String projectCode,String requestBody) {
        return patch(ENDPOINT + "/" + id + "/" + projectCode, statusCode, requestBody);
    }
}
