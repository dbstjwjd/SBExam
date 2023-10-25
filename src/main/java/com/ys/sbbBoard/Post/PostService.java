package com.ys.sbbBoard.Post;

import com.ys.sbbBoard.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getList() {
        return this.postRepository.findAll();
    }

    public Post getPost(Integer id) {
        Optional<Post> op = this.postRepository.findById(id);
        if (op.isPresent()) {
            return op.get();
        } else {
            throw new DataNotFoundException("post not found");
        }
    }

    public void createPost() {
        Post post = new Post();
        post.setSubject("new title");
        post.setCreateDate(LocalDateTime.now());
        this.postRepository.save(post);
    }

    public void modifyPost(Post post, String subject, String content) {
        post.setSubject(subject);
        post.setContent(content);
        this.postRepository.save(post);
    }
}
