package apis.weatherapp.metaweather.rest.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"consolidated_weather", "time", "sun_rise", "sun_set", "timezone_name", "parent", "sources",
        "title", "location_type", "woeid", "latt_long", "timezone"})
@Entity
public class WeatherInfo {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("time")
    private String time;
    @JsonProperty("sun_rise")
    private String sunRise;
    @JsonProperty("sun_set")
    private String sunSet;
    @JsonProperty("timezone_name")
    private String timezoneName;

    @JsonProperty("consolidated_weather")
    @OneToMany(mappedBy = "weatherInfo", cascade = ALL, fetch = FetchType.LAZY)
    private List<ConsolidatedWeather> consolidatedWeather;
    @JsonProperty("sources")
    @ManyToMany(mappedBy = "weatherInfos", fetch = FetchType.LAZY, cascade = ALL)
    private List<Source> sources;

    @JsonProperty("title")
    private String title;
    @JsonProperty("location_type")
    private String locationType;
    @JsonProperty("woeid")
    private Long woeid;
    @JsonProperty("latt_long")
    private String lattLong;
    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("consolidated_weather")
    public List<ConsolidatedWeather> getConsolidatedWeather() {
        return consolidatedWeather;
    }

    @JsonProperty("consolidated_weather")
    public void setConsolidatedWeather(List<ConsolidatedWeather> consolidatedWeather) {
        this.consolidatedWeather = consolidatedWeather;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("sun_rise")
    public String getSunRise() {
        return sunRise;
    }

    @JsonProperty("sun_rise")
    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    @JsonProperty("sun_set")
    public String getSunSet() {
        return sunSet;
    }

    @JsonProperty("sun_set")
    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }

    @JsonProperty("timezone_name")
    public String getTimezoneName() {
        return timezoneName;
    }

    @JsonProperty("timezone_name")
    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    @JsonProperty("sources")
    public List<Source> getSources() {
        return sources;
    }

    @JsonProperty("sources")
    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("location_type")
    public String getLocationType() {
        return locationType;
    }

    @JsonProperty("location_type")
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    @JsonProperty("woeid")
    public Long getWoeid() {
        return woeid;
    }

    @JsonProperty("woeid")
    public void setWoeid(Long woeid) {
        this.woeid = woeid;
    }

    @JsonProperty("latt_long")
    public String getLattLong() {
        return lattLong;
    }

    @JsonProperty("latt_long")
    public void setLattLong(String lattLong) {
        this.lattLong = lattLong;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}
