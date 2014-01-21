package org.scribe.oauth.core.model;

import com.sun.xml.internal.rngom.parse.host.Base;

public class BaseString
{
  private static final String FORMAT = "%s&%s&%s";
  private final String contents;
  private final String encodedVerb;
  private final String encodedUrl;
  private final String encodedParameters;

  public BaseString(String encodedVerb, String encodedUrl, String encodedParameters)
  {
    this.encodedVerb = encodedVerb;
    this.encodedUrl = encodedUrl;
    this.encodedParameters = encodedParameters;
    this.contents = String.format(FORMAT, encodedVerb, encodedUrl, encodedParameters);
  }

  public String getContents()
  {
    return contents;
  }

  public String getEncodedVerb()
  {
    return encodedVerb;
  }

  public String getEncodedUrl()
  {
    return encodedUrl;
  }

  public String getEncodedParameters()
  {
    return encodedParameters;
  }

  @Override
  public boolean equals(Object other)
  {
    if (other == null) return false;
    if (other == this) return true;

    if (other instanceof BaseString)
    {
      BaseString otherBaseString = (BaseString) other;
      return otherBaseString.contents.equals(contents);
    }
    else
    {
      return false;
    }
  }

  @Override
  public int hashCode()
  {
    return contents.hashCode();
  }
}
