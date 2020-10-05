package me.tozl.querydsldemo.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void crud() {
        savePost("jpa");

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);// persist

//        assertThat(entityManager.contains(post)).isTrue();
//        assertThat(entityManager.contains(savedPost)).isTrue();
//        assertThat(savedPost == post);

        Post postUpdate = new Post();
        postUpdate.setId(post.getId());
        postUpdate.setTitle("hibernate");
        Post updatedPost = postRepository.save(postUpdate);// merge

//        assertThat(entityManager.contains(updatedPost)).isTrue();
//        assertThat(entityManager.contains(postUpdate)).isFalse();
//        assertThat(updatedPost == postUpdate);

        /**
         * 아래 postUpdate 같은 경우 persist 객체가 아니기 때문에 setTitle을 감지하지 못한다.
         *
         * 따라서 항상 save method의 리턴 객체를 이용해서 업데이트하도록 하자.
         */
//        postUpdate.setTitle("tozl");

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartsWith() {
        savePost("Spring Data Jpa");

        List<Post> all = postRepository.findByTitleStartsWith("Spring");
        assertThat(all.size()).isEqualTo(1);
    }

    private void savePost(String s) {
        Post post = new Post();
        post.setTitle(s);
        postRepository.save(post);
    }

    @Test
    public void findByTitle() {
        savePost("Spring");
        List<Post> all = postRepository.findByTitle("Spring", JpaSort.unsafe("LENGTH(title)"));
        assertThat(all.size()).isEqualTo(1);
    }
}
