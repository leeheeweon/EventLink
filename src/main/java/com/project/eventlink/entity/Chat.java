package com.project.eventlink.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CHAT")
public class Chat extends BasicEntity{
    @Id
    @Column(name = "CHAT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    @Column(name = "SENDER")
    private String sender;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_ROOT_ID")
    private ChatRoom chatRoom;
}
