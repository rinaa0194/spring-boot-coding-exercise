package com.telstra.codechallenge.catfacts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CatFactResponse {
  private List<CatFact> data;
}
