package com.twuc.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class StoreSystemMessageResponse {
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
}
