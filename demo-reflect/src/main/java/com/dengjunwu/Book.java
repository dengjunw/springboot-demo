package com.dengjunwu;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Book {
    private final static String TAG = "BookTag";

    private String name;
    private String author;

    public Book(){}

    private Book(String name, String author){
        this.name = name;
        this.author = author;
    }

    private String declaredMethod(Integer index) {
        String string = null;
        switch (index) {
            case 0:
                string = "I am declaredMethod 1 !";
                break;
            case 1:
                string = "I am declaredMethod 2 !";
                break;
            default:
                string = "I am declaredMethod 1 !";
        }

        return string;
    }
}
