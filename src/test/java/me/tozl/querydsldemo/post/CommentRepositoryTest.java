package me.tozl.querydsldemo.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static me.tozl.querydsldemo.post.CommentSpecs.isBest;
import static me.tozl.querydsldemo.post.CommentSpecs.isGood;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getComment() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setComment("comment");
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

//        Optional<Comment> byId = commentRepository.findById(1l);
//        System.out.println(byId.get().getPost());




//        commentRepository.getById(1l);
//        System.out.println("---------------");
//        commentRepository.findById(1l);


//        List<CommentSummary> byPost_id = commentRepository.findByPost_Id(savedPost.getId());
//        assertThat(byPost_id.get(0).getVotes()).isEqualTo("10 1");

        commentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class).forEach(c -> {
            System.out.println("----------------------");
            System.out.println(c.getComment());
        });
    }

    @Test
    public void specs() {

        Page<Comment> page = commentRepository.findAll(isBest().or(isGood()), PageRequest.of(0, 10));

    }

    @Test
    public void qbe() {
        Comment prove = new Comment();
        prove.setBest(true);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withIgnorePaths("up", "down");
        Example<Comment> example = Example.of(prove, exampleMatcher);

        commentRepository.findAll(example);

    }
}