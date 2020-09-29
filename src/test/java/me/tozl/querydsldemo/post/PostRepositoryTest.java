package me.tozl.querydsldemo.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void crud() {
        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void save() {
        Post post = new Post();
        post.setId(1l);
        post.setTitle("jpa");
        postRepository.save(post);

//        Post post = new Post();
//        post.setId(1l);
//        post.setTitle("jpa");
//        postRepository.save(post);

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}
