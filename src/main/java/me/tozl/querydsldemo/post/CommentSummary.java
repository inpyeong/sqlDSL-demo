package me.tozl.querydsldemo.post;

import org.springframework.beans.factory.annotation.Value;

public interface CommentSummary {

    String getComment();

    int getUp();

    int getDown();

    default String getVotes() {
        return getUp() + " " + getDown();
    }

    // target 은 comment 를 가리킨다.
//    @Value("#{target.up + ' ' + target.down}")
//    String getVotes();

}
