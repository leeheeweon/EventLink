package com.project.eventlink.member.domain;

import com.project.eventlink.entity.Address;
import com.project.eventlink.cart.domain.Cart;
import com.project.eventlink.entity.ChatRoom;
import com.project.eventlink.item.domain.Item;
import com.project.eventlink.entity.Order;
import com.project.eventlink.entity.Reservation;
import com.project.eventlink.entity.Review;
import com.project.eventlink.entity.Role;
import com.project.eventlink.event.doamin.Event;
import com.project.eventlink.member.dto.JoinForm;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
public class Member extends BCryptPasswordEncoder implements UserDetails {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "NAME")
    private String name;

    @Embedded
    private Address address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성일시

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(JoinForm joinForm) {
        this.memberId = joinForm.getUserId();
        this.password = encode(joinForm.getPassword());
        this.age = joinForm.getAge();
        this.name = joinForm.getName();
        this.address = joinForm.getAddress();
        this.phone = joinForm.getPhone();
        this.email = joinForm.getEmail();
        this.role = joinForm.getRole();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return super.encode(rawPassword);
    }


    public static Member createUserByJoinForm(JoinForm joinForm) {
        return new Member(joinForm);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        if (this.role.equals(Role.PROVIDER)) {
            auth.add(new SimpleGrantedAuthority("ROLE_PROVIDER"));
        } else {
            auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return auth;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
