package fr.uryuker.games.buyeverything.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import fr.uryuker.games.buyeverything.spaces.ASpace;

public class CGsonAdapter implements JsonSerializer<ASpace>, JsonDeserializer<ASpace> {

	@Override
	public ASpace deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
	        final JsonObject jsonObject = json.getAsJsonObject();
	        final String type = jsonObject.get("type").getAsString();
	        final JsonElement element = jsonObject.get("properties");
	 
	        try {
	            return context.deserialize(element, Class.forName("com.googlecode.whiteboard.model." + type));
	        } catch (final ClassNotFoundException cnfe) {
	            throw new JsonParseException("Unknown element type: " + type, cnfe);
	        }
	}

	@Override
	public JsonElement serialize(ASpace src, Type typeOfSrc, JsonSerializationContext context) {
        final JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));
 
        return result;
	}


}
