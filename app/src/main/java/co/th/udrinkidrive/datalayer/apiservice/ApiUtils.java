package co.th.udrinkidrive.datalayer.apiservice;


/**
 * Created by programmer on 1/10/18.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://trip.api.udrinkbackend.com/api/";

    public static SOService getSOService() {

        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}