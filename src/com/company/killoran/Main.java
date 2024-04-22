package com.company.killoran;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("JSON with gson example");
        serializeSimple(); //before serializing create a test.json with their data in json format
        deserializeSimple();
    }

    static void serializeSimple() {
        Todo person = new Todo("Walk the dog", false, 0, 3,"dog");
        Todo person2 = new Todo("Walk the dog", false, 0, 3,"dog");
        ArrayList<Todo> list = new ArrayList<>();
        list.add(person);
        list.add(person2);
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("data.json")) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void deserializeSimple() {
        try (FileReader reader = new FileReader("data.json")) {
            // Parse JSON data from the file
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(reader);

            // Convert JSON element to a Java object using Gson
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Todo>>(){}.getType();
            ArrayList<Todo> todo1 = gson.fromJson(jsonElement, type);

            // Print the deserialized object
            System.out.println(todo1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Todo {
    private String body;
    private boolean done;
    private int id;
    private int priority;
    private String title;

    public Todo(String body, boolean done, int id, int priority, String title) {
        this.body = body;
        this.done = done;
        this.id = id;
        this.priority = priority;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "body='" + body + '\'' +
                ", done=" + done +
                ", id=" + id +
                ", priority=" + priority +
                ", title='" + title + '\'' +
                '}';
    }
}