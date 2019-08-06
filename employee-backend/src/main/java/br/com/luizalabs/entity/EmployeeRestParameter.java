package br.com.luizalabs.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Immutable
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EmployeeRestParameter {

	private Long id;
	@NotEmpty(message = "Please provide a name")
	private String name;
	@NotEmpty(message = "Please provide a email")
	@Email(message = "Email sintax is not valid")
	private String email;
	@NotEmpty(message = "Please provide a department")
	private String department;
	
	@Override
	public final boolean equals(final Object other) {
		if (!(other instanceof EmployeeRestParameter)) {
			return false;
		}
		EmployeeRestParameter castOther = (EmployeeRestParameter) other;
		return  Objects.equals(name, castOther.name) && Objects.equals(email, castOther.email)
				&& Objects.equals(department, castOther.department);
	}
	@Override
	public final int hashCode() {
		return Objects.hash(name, email, department);
	}
	
	public String toJson() throws JsonProcessingException {
		Map<String, Object> data = new HashMap<>();
		data.put("id", id);
		data.put("name", name);
		data.put("email", email);
		data.put("department", department);
		return new ObjectMapper().writeValueAsString(data);
}

}