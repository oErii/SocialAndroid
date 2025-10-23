package com.example.progettosocial.api.dto.response;

import java.util.List;

public class LastPostResponse {
    private List<PostDTO> postList;

    public LastPostResponse(List<PostDTO> postList) {
        this.postList = postList;
    }

    public LastPostResponse(){}

    public List<PostDTO> getPostList() {
        return postList;
    }

    public void setPostList(List<PostDTO> postList) {
        this.postList = postList;
    }
}
