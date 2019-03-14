package com.nofluffjobs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	public static void main(String[] args) {
		
		List<Integer> l = new ArrayList<>(Arrays.asList(1, 2, 3));
		List<Integer> l2;
		
		Gson gson = new Gson();
		String json = gson.toJson(l);
		
		Type type = new TypeToken<List<Integer>>(){}.getType();
		l2 = gson.fromJson(json, type);
		System.out.println(l2);
		
		
	}
}
