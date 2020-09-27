package com.github.duorourou.sidecar.favoriteservice.video;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Video {

	private String name;
	private String author;

	public Video check() {
		System.out.println("name " + name + ", author :" + author);
		return this;
	}
}
