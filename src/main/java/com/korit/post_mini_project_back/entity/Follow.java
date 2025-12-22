package com.korit.post_mini_project_back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    private int followId;
    private int followerId;
    private  int followingId;
}
