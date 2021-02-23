package utility;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import social.Jsonable;

public class JsonUtil {
	public static Jsonable loadJSON(String filename) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Jsonable toLoad = objectMapper.readValue(new File(filename), Jsonable.class);
		return toLoad;
	}

	public static void saveJSON(String filename, Jsonable toSave) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			objectMapper.writeValue(new File(filename), toSave);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, String> loadMapStringJSON(String filename) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		HashMap<String, String> toLoad = objectMapper.readValue(new File(filename), HashMap.class);
		return toLoad;
	}
}
