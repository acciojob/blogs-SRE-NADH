package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        User user = userRepository1.findById(userId).get();
        Blog blog= new Blog();
        blog.setContent(content);
        blog.setTitle(title);
       // LocalDate localDate = LocalDate.now();
        Date date = new Date();
        blog.setPubDate(date);
        blog.setUser(user);
        user.getBlogList().add(blog);
        userRepository1.save(user);
       // Blog blog1 = blogRepository1.save(blog);
        // return the blog without saving
        return blog;
    }

    public void deleteBlog(int blogId){
        blogRepository1.deleteById(blogId);
        //delete blog and corresponding images
    }
}
