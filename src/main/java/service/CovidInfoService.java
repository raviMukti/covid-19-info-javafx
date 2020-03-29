package service;

import model.CovidByCountry;
import model.CovidTotals;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface CovidInfoService {
    @Headers({
            "X-RapidAPI-Host: covid-19-data.p.rapidapi.com",
            "X-RapidAPI-Key: a6a90b62c8msh8df260442e61041p10ab30jsn3d5f1da6bcff" //secret key ini milik raviMukti dan 2 minggu kemudian akan expired
    })
    @GET("/totals")
    Call<List<CovidTotals>> getTotals();

    @Headers({
            "X-RapidAPI-Host: covid-19-data.p.rapidapi.com",
            "X-RapidAPI-Key: a6a90b62c8msh8df260442e61041p10ab30jsn3d5f1da6bcff" //secret key ini milik raviMukti dan 2 minggu kemudian akan expired
    })
    @GET("/country")
    Call<List<CovidByCountry>> getByCountry(@Query("name") String countryName);
}
