package org.scribe.oauth.core.model;

import org.scribe.oauth.core.util.PercentEncoder;

/**
 * @author: Pablo Fernandez
 */
public class Parameter implements Comparable<Parameter>
{
  private static final String UTF = "UTF8";

  private final String key;
  private final String value;

  public Parameter(String key, String value)
  {
    this.key = key;
    this.value = value;
  }

  public String asUrlEncodedPair()
  {
    return PercentEncoder.encode(key).concat("=").concat(PercentEncoder.encode(value));
  }
  
  public boolean equals(Object other)
  {
    if(other == null) return false;
    if(other == this) return true;
    if(!(other instanceof Parameter)) return false;
    
    Parameter otherParam = (Parameter) other;
    return otherParam.key.equals(key) && otherParam.value.equals(value);
  }

  public int hashCode()
  {
    return key.hashCode() + value.hashCode();
  }

  public int compareTo(Parameter parameter)
  {
    int keyDiff = key.compareTo(parameter.key);

    return keyDiff != 0 ? keyDiff : value.compareTo(parameter.value);
  }
}
