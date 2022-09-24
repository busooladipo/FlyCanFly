package com.fly.flycanfly.services;

import com.fly.flycanfly.entities.Bookmark;

import java.util.List;

public interface BookmarkService {
    Bookmark addBookmark(Bookmark bookmark);

    List<Bookmark> getBookmarkList(String userName);

    void deleteBookmark(Long idBookmark);
}
