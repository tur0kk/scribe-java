package org.scribe.oauth.core.service;

import java.util.Random;

public class SystemTimestampService implements TimestampService
{
  private final Random rand = new Random();

  public Long getTimestamp()
  {
    return System.currentTimeMillis();
  }

  public String getNonce()
  {
    return String.valueOf(rand.nextInt());
  }
}
