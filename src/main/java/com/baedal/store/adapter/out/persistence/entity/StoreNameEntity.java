package com.baedal.store.adapter.out.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "store_name")
@Builder
@Getter
public class StoreNameEntity {

  @Id
  private Long id;

  private String name;

}
