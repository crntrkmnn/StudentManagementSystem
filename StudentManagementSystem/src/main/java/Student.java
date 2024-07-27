package com.example;

import org.bson.Document;

public class Student {
    private String id;
    private String name;
    private int age;

    public Student() {}

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Convert Student object to MongoDB Document
    public Document toDocument() {
        return new Document("name", name)
                .append("age", age);
    }

    // Create Student object from MongoDB Document
    public static Student fromDocument(Document doc) {
        return new Student(
                doc.getObjectId("_id").toString(),
                doc.getString("name"),
                doc.getInteger("age")
        );
    }
}
