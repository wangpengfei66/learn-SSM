package com.kaishengit.hibernate;

import com.kaishengit.pojo.Content;
import com.kaishengit.pojo.Post;
import org.junit.Test;

public class OneToOneTest2 extends BaseTest {

    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("天龙八部");

        Content content = new Content();
        content.setContent("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        content.setPost(post);
        post.setContent(content);

        session.save(post);
        session.save(content);
    }
    @Test
    public void find() {
        Post post = (Post) session.get(Post.class,5);
        System.out.println(post.getTitle());
        //延迟加载
        Content content = post.getContent();
        System.out.println(content.getContent());
    }

}
