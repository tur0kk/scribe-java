package org.scribe.oauth.core.model;

import org.scribe.oauth.core.util.Must;

import java.util.Collections;
import java.util.Map;

public class Signable
{
  private final String verb;
  private final String url;
  private final ParameterList params;

  public Signable(String verb, String url)
  {
    this(verb, url, Collections.<String,String>emptyMap());
  }

  public Signable(String verb, String url, Map<String, String> params)
  {
    this(verb, url, ParameterList.fromMap(params));
  }

  public Signable(String verb, String url, ParameterList params)
  {
    Must.beNonEmptyString(verb);
    Must.beNonEmptyString(url);
    this.url = url;
    this.verb = verb;
    this.params = params;
  }

  public String getUrl()
  {
    return url;
  }

  public String getVerb()
  {
    return verb;
  }

  public ParameterList getParams()
  {
    return params;
  }
}
