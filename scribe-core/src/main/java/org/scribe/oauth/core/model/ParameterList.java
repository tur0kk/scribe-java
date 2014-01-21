package org.scribe.oauth.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: Pablo Fernandez
 */
public class ParameterList
{
  private static final String EMPTY_STRING = "";

  private final List<Parameter> params;

  public ParameterList(List<Parameter> params)
  {
    this.params = Collections.unmodifiableList(params);
  }

  public static ParameterList fromMap(Map<String, String> map)
  {
    List<Parameter> params = new ArrayList<Parameter>();
    for(Map.Entry<String, String> entry : map.entrySet())
    {
      params.add(new Parameter(entry.getKey(), entry.getValue()));
    }

    return new ParameterList(params);
  }

  public String asFormUrlEncodedString()
  {
    if (params.size() == 0) return EMPTY_STRING;

    StringBuilder builder = new StringBuilder();
    for(Parameter p : params)
    {
      builder.append('&').append(p.asUrlEncodedPair());
    }
    return builder.toString().substring(1);
  }

  public ParameterList addAll(ParameterList other)
  {
    List<Parameter> params = new ArrayList<Parameter>(other.params.size() + this.params.size());

    for(Parameter p: other.params)
    {
      params.add(p);
    }

    for(Parameter p: this.params)
    {
      params.add(p);
    }

    return new ParameterList(params);
  }

  public ParameterList sort()
  {
    List<Parameter> mutableList = new ArrayList<Parameter>(params);
    Collections.sort(mutableList);
    return new ParameterList(mutableList);
  }
}
