package com.telstra.codechallenge;

import com.intuit.karate.junit5.Karate;

public class FunctionalIT {
  @Karate.Test
  Karate testMicroservice() {
    return Karate.run("microservice").relativeTo(getClass());
  }

  @Karate.Test
  Karate testHelloWorld() {
    return Karate.run("helloworld").relativeTo(getClass());
  }

  @Karate.Test
  Karate testCatFacts() {
    return Karate.run("catfacts").relativeTo(getClass());
  }

  @Karate.Test
  Karate testUser() {
    return Karate.run("user").relativeTo(getClass());
  }

}