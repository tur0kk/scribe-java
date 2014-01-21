package org.scribe.oauth.core.model;

public class AccessToken
{
  private final String token;
  private final String secret;

  public static AccessToken Empty = new AccessToken("", "");

  public AccessToken(String token, String secret)
  {
    this.token = token;
    this.secret = secret;
  }

  public String getToken()
  {
    return token;
  }

  public String getSecret()
  {
    return secret;
  }
}
