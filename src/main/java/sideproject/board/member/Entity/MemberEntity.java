package sideproject.board.member.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.board.common.BaseEntity;
import sideproject.board.member.contoller.requests.CreateMemberRequest;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class MemberEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int age;

	public static MemberEntity convertToEntity(CreateMemberRequest request) {
		return MemberEntity.builder()
			.id(request.getId())
			.email(request.getEmail())
			.password(request.getPassword())
			.name(request.getName())
			.age(request.getAge())
			.build();
	}

}
