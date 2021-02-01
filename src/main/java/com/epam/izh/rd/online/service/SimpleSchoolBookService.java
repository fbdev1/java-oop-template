package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;

import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService<SchoolBook> {
    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    public SimpleSchoolBookService() {
    }

    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    @Override
    public boolean save(SchoolBook book) {
        if (authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) == null) {
            return false;
        }
        schoolBookBookRepository.save(book);
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return schoolBookBookRepository.findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] b = schoolBookBookRepository.findByName(name);
        if (b.length != 0) {
            return authorService.findByFullName(b[0].getAuthorName(), b[0].getAuthorLastName());
        } else return null;
    }
}
