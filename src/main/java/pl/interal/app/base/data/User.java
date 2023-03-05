package pl.interal.app.base.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USERS",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
public class User  {


	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "users_id_seq_generator", sequenceName = "users_id_seq", allocationSize = 1)
	private Long id;

	private String password;

	private String email;

	private String username;
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;

	@ManyToOne(optional = false, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
	private Authorities role;

	private boolean enabled = true;

}
