package pl.interal.app.base.data;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authorities {

	@Id
	@Column(nullable = false)
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Roles authority;
}
