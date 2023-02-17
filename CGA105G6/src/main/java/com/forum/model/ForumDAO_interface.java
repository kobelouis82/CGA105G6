package com.forum.model;

import java.util.*;

public interface ForumDAO_interface {
    public void insert(ForumVO forumVO);
    public void update(ForumVO forumVO);
    public void delete(Integer forumNo);
    public ForumVO findByPrimaryKey(Integer forumNo);
    public List<ForumVO> getAll();
}
