package com.fly.flycanfly.services.Impl;

import com.fly.flycanfly.entities.Bookmark;
import com.fly.flycanfly.repositories.BookmarkRepo;
import com.fly.flycanfly.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    @Autowired
    private BookmarkRepo bookmarkRepo;

    @Override
    public Bookmark addBookmark(Bookmark bookmark) {
        return bookmarkRepo.save(bookmark);
    }

    @Override
    public List<Bookmark> getBookmarkList(String userName) {
        return bookmarkRepo.getBookmarkList(userName);
    }

    @Override
    public void deleteBookmark(Long idBookmark) {
        bookmarkRepo.deleteById(idBookmark);
    }
}
