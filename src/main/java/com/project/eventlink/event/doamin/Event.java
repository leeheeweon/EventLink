package com.project.eventlink.event.doamin;

import com.project.eventlink.entity.BasicEntity;
import com.project.eventlink.event.model.UpdateRequestModel;
import com.project.eventlink.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "EVENT")
public class Event extends BasicEntity {
    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "MIN_PRICE")
    private Integer minPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void updateEvent(UpdateRequestModel updateRequestModel) {
        this.name = updateRequestModel.name();
        this.minPrice = updateRequestModel.minPrice();
    }
}
