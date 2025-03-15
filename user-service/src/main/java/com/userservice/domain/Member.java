package com.userservice.domain;

import com.userservice.domain.vo.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long MemberNo;
    @Embedded
    private IdName idName;
    @Embedded
    private PassWord password;
    @Embedded
    private Email email;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Authority> authorities;
    @Embedded
    private Point point;

    public static Member registerMember(IdName idName, PassWord password, Email email) {
        Member newMember = Member.builder()
                .idName(idName)
                .password(password)
                .email(email)
                .point(Point.createPoint())
                .build();
        newMember.addAuthority(new Authority(UserRole.USER));

        return newMember;
    }
    private void addAuthority(Authority authority) {
        if (this.authorities == null) {
            this.authorities = new ArrayList<>();
        }

        this.authorities.add(authority);
    }

    public long savePoint(long point)
    {
        return this.point.addPoint(point);
    }

    public long usePoint(long point) {
        return this.point.removePoint(point);
    }

    public Member login(IdName idNname, PassWord password){
        return this;
    }

    public void logout(IdName idName){
    }
}