package apis.weatherapp.metaweather.rest.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"weather_state_name", "weather_state_abbr", "wind_direction_compass", "created",
        "applicable_date", "min_temp", "max_temp", "the_temp", "wind_speed", "wind_direction", "air_pressure",
        "humidity", "visibility", "predictability"})
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@consolidatedWeatherId")
public class ConsolidatedWeather {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    @JsonProperty("weather_state_name")
    private String weatherStateName;
    @JsonProperty("weather_state_abbr")
    private String weatherStateAbbr;
    @JsonProperty("wind_direction_compass")
    private String windDirectionCompass;
    @JsonProperty("created")
    private String created;
    @JsonProperty("applicable_date")
    private String applicableDate;
    @JsonProperty("min_temp")
    private Double minTemp;
    @JsonProperty("max_temp")
    private Double maxTemp;
    @JsonProperty("the_temp")
    private Double theTemp;
    @JsonProperty("wind_speed")
    private Double windSpeed;
    @JsonProperty("wind_direction")
    private Double windDirection;
    @JsonProperty("air_pressure")
    private Double airPressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("visibility")
    private Double visibility;
    @JsonProperty("predictability")
    private Integer predictability;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_weather_info")
    private WeatherInfo weatherInfo;

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }


    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("weather_state_name")
    public String getWeatherStateName() {
        return weatherStateName;
    }

    @JsonProperty("weather_state_name")
    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    @JsonProperty("weather_state_abbr")
    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    @JsonProperty("weather_state_abbr")
    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    @JsonProperty("wind_direction_compass")
    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    @JsonProperty("wind_direction_compass")
    public void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("applicable_date")
    public String getApplicableDate() {
        return applicableDate;
    }

    @JsonProperty("applicable_date")
    public void setApplicableDate(String applicableDate) {
        this.applicableDate = applicableDate;
    }

    @JsonProperty("min_temp")
    public Double getMinTemp() {
        return minTemp;
    }

    @JsonProperty("min_temp")
    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    @JsonProperty("max_temp")
    public Double getMaxTemp() {
        return maxTemp;
    }

    @JsonProperty("max_temp")
    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    @JsonProperty("the_temp")
    public Double getTheTemp() {
        return theTemp;
    }

    @JsonProperty("the_temp")
    public void setTheTemp(Double theTemp) {
        this.theTemp = theTemp;
    }

    @JsonProperty("wind_speed")
    public Double getWindSpeed() {
        return windSpeed;
    }

    @JsonProperty("wind_speed")
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @JsonProperty("wind_direction")
    public Double getWindDirection() {
        return windDirection;
    }

    @JsonProperty("wind_direction")
    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    @JsonProperty("air_pressure")
    public Double getAirPressure() {
        return airPressure;
    }

    @JsonProperty("air_pressure")
    public void setAirPressure(Double airPressure) {
        this.airPressure = airPressure;
    }

    @JsonProperty("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("visibility")
    public Double getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("predictability")
    public Integer getPredictability() {
        return predictability;
    }

    @JsonProperty("predictability")
    public void setPredictability(Integer predictability) {
        this.predictability = predictability;
    }

}
