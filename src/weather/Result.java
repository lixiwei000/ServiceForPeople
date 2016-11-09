package weather;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lixiwei on 2016/11/9.
 */
public class Result implements Serializable
{
    List<Results> results;

    public List<Results> getResults()
    {
        return results;
    }



    public void setResults(List<Results> results)
    {
        this.results = results;
    }

    public String[] getRes()
    {
        return results.get(0).getDaily().get(0).getRes();

    }
}

class Results implements Serializable
{
    Location location;
    List<Daily> daily;

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public List<Daily> getDaily()
    {
        return daily;
    }

    public void setDaily(List<Daily> daily)
    {
        this.daily = daily;
    }

    @Override
    public String toString()
    {
        return "Results{" +
                "location=" + location +
                ", daily=" + daily +
                '}';
    }
}

class Location implements Serializable
{
    String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Daily implements Serializable
{
    Date date;
    String text_day;
    String text_night;
    String high;
    String low;
    String precipe;// 降水概率
    String wind_direction;
    String wind_scale;

    public String[] getRes()
    {
        return new String[]{text_day, text_night, high, low, precipe, wind_direction, wind_scale};
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getText_day()
    {
        return text_day;
    }

    public void setText_day(String text_day)
    {
        this.text_day = text_day;
    }

    public String getText_night()
    {
        return text_night;
    }

    public void setText_night(String text_night)
    {
        this.text_night = text_night;
    }

    public String getHigh()
    {
        return high;
    }

    public void setHigh(String high)
    {
        this.high = high;
    }

    public String getLow()
    {
        return low;
    }

    public void setLow(String low)
    {
        this.low = low;
    }

    public String getPrecipe()
    {
        return precipe;
    }

    public void setPrecipe(String precipe)
    {
        this.precipe = precipe;
    }

    public String getWind_direction()
    {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction)
    {
        this.wind_direction = wind_direction;
    }

    public String getWind_scale()
    {
        return wind_scale;
    }

    public void setWind_scale(String wind_scale)
    {
        this.wind_scale = wind_scale;
    }
}