package com.baedal.store.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "stores")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreEntity {

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

  @Column(nullable = false)
  private String name;

  private String title;

  private String content;

  @Column(nullable = false)
  private String address;

  private String pictureUrl;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private LocalTime openTime;

  @Column(nullable = false)
  private LocalTime closeTime;
}
