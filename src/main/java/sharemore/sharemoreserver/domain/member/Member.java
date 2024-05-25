package sharemore.sharemoreserver.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

}
