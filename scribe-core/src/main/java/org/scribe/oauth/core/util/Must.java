package org.scribe.oauth.core.util;

public class Must
{
  private static final String DEFAULT_MESSAGE = "Received an invalid parameter";

  public static void beNonEmptyString(String string)
  {
    beNotNull(string);
    check(string.trim().length() > 0, "received empty string");
  }

  public static void beNotNull(Object any)
  {
    check(any != null, "received null object");
  }

  private static void check(boolean requirements, String error)
  {
    String message = (error == null || error.trim().length() <= 0) ? DEFAULT_MESSAGE : error;
    if (!requirements)
    {
      throw new IllegalArgumentException(message);
    }
  }
}
