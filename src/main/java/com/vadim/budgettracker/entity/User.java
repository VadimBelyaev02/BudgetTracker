package com.vadim.budgettracker.entity;

import com.vadim.budgettracker.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "confirmed")
    private boolean confirmed;

    @Column(name = "language")
    private String language;

    @Column(name = "currency")
    private String currency;

    @Column(name = "mode")
    private String mode;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Category> categories;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Operation> operations;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Confirmation confirmation;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdDate=" + createdDate +
                ", role=" + role +
                ", confirmed=" + confirmed +
                ", language='" + language + '\'' +
                ", currency='" + currency + '\'' +
                ", mode='" + mode + '\'' +
                '}';
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
