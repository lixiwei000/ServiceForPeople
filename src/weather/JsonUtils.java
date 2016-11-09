package weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class JsonUtils
{
	public static <T extends Serializable> T getObject(String str, Class<T> clz) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
				.create();
		T o = gson.fromJson(str, clz);
		return o;
	}

	public static <T extends Serializable> String toJson(T obj) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(obj, obj.getClass());
	}

}

