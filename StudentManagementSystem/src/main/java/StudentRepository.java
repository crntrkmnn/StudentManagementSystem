package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class StudentRepository {

    private final MongoCollection<Document> collection;

    public StudentRepository() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        this.collection = database.getCollection("students");

        // Başlangıç verilerini ekle
        if (collection.countDocuments() == 0) { // Eğer koleksiyon boşsa
            collection.insertMany(Arrays.asList(
                    new Document("name", "John Doe").append("age", 20),
                    new Document("name", "Jane Smith").append("age", 22),
                    new Document("name", "Michael Johnson").append("age", 21),
                    new Document("name", "Emily Davis").append("age", 23),
                    new Document("name", "Daniel Brown").append("age", 24),
                    new Document("name", "Laura Wilson").append("age", 25),
                    new Document("name", "James Anderson").append("age", 22),
                    new Document("name", "Olivia Taylor").append("age", 21),
                    new Document("name", "Matthew Thomas").append("age", 23),
                    new Document("name", "Sophia Martinez").append("age", 20)
            ));
        }
    }

    public void addStudent(Student student) {
        collection.insertOne(student.toDocument());
    }

    public List<Student> listStudents() {
        return collection.find().map(Student::fromDocument).into(new java.util.ArrayList<>());
    }

    public void updateStudent(Student student) {
        collection.replaceOne(new Document("_id", student.getId()), student.toDocument());
    }

    public void deleteStudent(String id) {
        collection.deleteOne(new Document("_id", id));
    }
}
