package io.github.renatoganske.quarkussocial.rest;

import io.github.renatoganske.quarkussocial.domain.model.Post;
import io.github.renatoganske.quarkussocial.domain.model.User;
import io.github.renatoganske.quarkussocial.domain.repository.PostRepository;
import io.github.renatoganske.quarkussocial.domain.repository.UserRepository;
import io.github.renatoganske.quarkussocial.rest.dto.CreatePostRequest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private UserRepository userRepository;
    private PostRepository repository;

    @Inject
    public PostResource(UserRepository userRepository,
                        PostRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @POST
    @Transactional
    public Response savePost(
            @PathParam("userId") Long userId, CreatePostRequest request) {
        User user = userRepository.findById(userId);
        if(user ==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);

        repository.persist(post);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPosts(@PathParam("userId") Long userId) {
        User user = userRepository.findById(userId);
        if(user ==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }
}