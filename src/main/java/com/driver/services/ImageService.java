package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        Blog savedBlog = blogRepository2.save(blog);
        return savedBlog.getImageList().get(savedBlog.getImageList().size()-1);
        //add an image to the blog
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
       Image image = imageRepository2.findById(id).get();
       String imageDimension = image.getDimensions();
       int imageArea = convertToarea(imageDimension);
       int screenArea =convertToarea(screenDimensions);
       return  screenArea/imageArea;
    }
    private int convertToarea(String Dimension) {
        String []arr = Dimension.split("X");
        int l = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        return l*b;
    }
}
