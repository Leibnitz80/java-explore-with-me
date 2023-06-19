package ru.practicum.stats_common.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewStats {
    private String app;
    private String uri;
    private Long hits;
}
