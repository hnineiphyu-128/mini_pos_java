package com.mmit.utilities;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mmit.model.entity.SaleDetail;
import com.mmit.model.entity.SaleDetailJSON;
import com.mmit.model.entity.Student;


public class JsonConverter {
	private final Gson gson;

    public JsonConverter() {

        gson = new GsonBuilder().create();
    }

//    public String convertToJson(List<CandidateEntity>candidates) {
//
//        JsonArray jarray = gson.toJsonTree(candidates).getAsJsonArray();
//        var jsonObject = new JsonObject();
//        jsonObject.add("candidates", jarray);// use 'candidates' when json retrieve
//        return jsonObject.toString();
//    }
    public String convertToJson(List<?> list) {


        Gson gson = new GsonBuilder().registerTypeAdapter(SaleDetailJSON.class, new JsonSerializer<SaleDetailJSON>() {

			@Override
			public JsonElement serialize(SaleDetailJSON sdj, Type typeOfSrc, JsonSerializationContext context) {
				 JsonObject jsonObject = new JsonObject();
               jsonObject.addProperty("product", sdj.getItem().getName());
               jsonObject.addProperty("price", sdj.getItem().getPrice());
               jsonObject.addProperty("qty", sdj.getSubQty());
				return jsonObject;
			}
		}).create();
        String s = gson.toJson(list);
        
        return s;
    }
}
