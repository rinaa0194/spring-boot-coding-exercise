package com.telstra.codechallenge.catfacts;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatFactsService {

  @Value("${catFacts.base.url}")
  private String catFactsBaseUrl;

  private final RestTemplate restTemplate;

  public CatFactsService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Returns an array of cat facts.
   * Taken from <a href="https://catfact.ninja/facts">https://catfact.ninja/facts</a>.
   * For API documentation see: <a href="https://catfact.ninja/">https://catfact.ninja/</a>.
   *
   * @return - a cat fact list.
   */
  public List<CatFact> getCatFacts() {
    CatFactResponse catFactResponse =
        restTemplate.getForObject(catFactsBaseUrl + "/facts", CatFactResponse.class);
    List<CatFact> catFacts = null;
    if (catFactResponse != null) {
      catFacts = catFactResponse.getData();
    }

    return catFacts;
  }
}
