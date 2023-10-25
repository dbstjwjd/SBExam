package com.ys.sbbBoard.Post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String main(Model model) {
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList);
        if (!postList.isEmpty()) {
            model.addAttribute("targetPost", postList.get(0));
        }
        return "board";
    }

    @PostMapping("/create")
    public String create() {
        this.postService.createPost();
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Post post = this.postService.getPost(id);
        model.addAttribute("targetPost", post);
        model.addAttribute("postList", this.postService.getList());
        return "board";
    }

    @PostMapping("/modify/{id}")
    public String modify(@PathVariable("id") Integer id, String subject, String content) {
        Post post = this.postService.getPost(id);
        this.postService.modifyPost(post, subject, content);
        return String.format("redirect:/detail/%s", id);
    }
}
