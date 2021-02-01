package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = {};

    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    public SchoolBook[] findByName(String name) {
        int count = 0;
        for (SchoolBook sb : schoolBooks) {
            if (sb.getName().equals(name)) {
                count++;
            }
        }
        if (count != 0) {
            SchoolBook[] sb = new SchoolBook[count];
            for (int i = 0, j = 0; i < schoolBooks.length; j++, i++) {
                if (schoolBooks[i].getName().equals(name)) {
                    sb[j] = schoolBooks[i];
                } else i++;
            }
            return sb;
        } else return new SchoolBook[]{};
    }

    public boolean removeByName(String name) {
        int count = 0;
        for (SchoolBook sb : schoolBooks) {
            if (sb.getName().equals(name)) {
                count++;
            }
        }
        if (count != 0) {
            SchoolBook[] sb = new SchoolBook[schoolBooks.length - count];
            for (int i = 0, j = 0; i < schoolBooks.length; j++, i++) {
                if (!schoolBooks[i].getName().equals(name)) {
                    sb[j] = schoolBooks[i];
                } else {
                    i++;
                }
            }
            schoolBooks = sb;
            return true;
        } else {
            return false;
        }
    }

    public int count() {
        return schoolBooks.length;
    }
}
