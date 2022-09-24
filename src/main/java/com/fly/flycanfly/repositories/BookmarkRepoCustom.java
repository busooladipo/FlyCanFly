package com.fly.flycanfly.repositories;

import com.fly.flycanfly.entities.Bookmark;

import java.util.List;

public interface BookmarkRepoCustom {
    List<Bookmark> getBookmarkList(String userName);
}
