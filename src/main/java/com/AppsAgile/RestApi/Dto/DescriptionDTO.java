package com.AppsAgile.RestApi.Dto;

import lombok.Setter;
import lombok.*;

@Data
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class DescriptionDTO {
    private Long id;
    private String role;
    private String besoin;
    private String raison;
}