package com.fly.flycanfly.repositories;

import com.fly.flycanfly.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepo extends BookmarkRepoCustom, JpaRepository<Bookmark, Long> {
}
