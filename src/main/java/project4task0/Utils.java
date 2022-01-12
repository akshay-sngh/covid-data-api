package project4task0;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;

public class Utils {
    public static LocationsWouldAttend jsonToLocationsWouldAttend (String json) {
        LocationsWouldAttend lwa = new LocationsWouldAttend();
        JSONObject obj = new JSONObject(json);
        lwa.setResultsCount(obj.getInt("results_count"));
        lwa.setSampleSize(obj.getDouble("effective_sample_size"));
        JSONObject locationObject = obj.getJSONObject("locations_would_attend");
        HashMap<String, HashMap<String, String>> inputParams = (HashMap) (obj.getJSONObject("input_params")).toMap();
        HashMap<String, HashMap<String, HashMap<String, HashMap<String, Double>>>> locations = (HashMap) (obj.getJSONObject("locations_would_attend")).toMap();

        for (String key : locations.keySet()) {
            HashMap map = locations.get(key).get("weighted");
            Location l = new Location(key, ((BigDecimal)map.get("Yes")).doubleValue(), ((BigDecimal)map.get("No")).doubleValue());
            lwa.getLocations().add(l);
        }

        return lwa;
    }

    public static SurveyResult jsonToSurveyResult (String json) {
        SurveyResult result = new SurveyResult();
        JSONObject obj = new JSONObject(json);
        result.setResultsCount(obj.getInt("results_count"));
        result.setSampleSize(obj.getDouble("effective_sample_size"));
//        result.setSurveyResult(obj.getString("locations_would_attend"));
        result.setSurveyOutcome(json.substring(json.indexOf("locations_would_attend") + "locations_would_attend".length()));


        return result;
    }
}
