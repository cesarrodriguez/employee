package br.com.luizalabs.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.Immutable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Immutable
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private String department;

	@Override
	public final boolean equals(final Object other) {
		if (!(other instanceof Employee)) {
			return false;
		}

		Employee castOther = (Employee) other;
		return Objects.equals(name, castOther.name) && Objects.equals(email, castOther.email)
				&& Objects.equals(department, castOther.department);
	}

	@Override
	public final int hashCode() {
		return Objects.hash(name, email, department);
	}

}