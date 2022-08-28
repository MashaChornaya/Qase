package adapters;

public class DefectAdapter extends  BaseAdapter{
    private static final String ENDPOINT = "defect";

    public String getAllDefects(int statusCode) {

        return get(ENDPOINT, statusCode);
    }

    public String createDefect(int statusCode, String requestBody) {
        return post(ENDPOINT, statusCode, requestBody);
    }

    public String getSpecificDefect(int statusCode, String defectCode) {
        return get(ENDPOINT + "/" + defectCode, statusCode);
    }

    public String deleteDefect() {
        return null;
    }
    public String updateDefect(int statusCode,String defectCode,String requestBody) {
        return post(ENDPOINT+ "/" + defectCode, statusCode, requestBody);
    }
}
