package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    Author[] authors = {};

    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        } else {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
        }
        return true;
    }

    public Author findByFullName(String name, String lastname) {
        for (Author a : authors) {
            if (a.getName().equals(name) && a.getLastName().equals(lastname)) {
                return a;
            }
        }
        return null;
    }

    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] authors1 = new Author[authors.length - 1];
            for (int i = 0, j = 0; i < authors.length; j++, i++ ) {
                if (!authors[i].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName())) {
                    authors1[j] = authors[i];

                } else {
                    i++;
                }
            }
            authors = authors1;
            return true;
        } else return false;
    }

    public int count() {
        return authors.length;
    }
}
