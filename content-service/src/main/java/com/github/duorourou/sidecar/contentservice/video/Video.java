package com.github.duorourou.sidecar.contentservice.video;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Video(@JsonProperty("name") String name, @JsonProperty("author") String author){}
