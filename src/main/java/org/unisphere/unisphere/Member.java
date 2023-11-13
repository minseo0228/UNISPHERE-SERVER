package org.unisphere.unisphere;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Member {

	@Id
	@GeneratedValue
	@Column()
	private Long id;
	private String username;
	private int age;

	public static Member of(String username, int age) {
		Member member = new Member();
		member.setUsername(username);
		member.setAge(age);
		return member;
	}
}
