package org.scribe.oauth.core.model;

public class Signature
{
  private final String text;

  public Signature(String text)
  {
    this.text = text;
  }

  public String getText()
  {
    return text;
  }

  @Override
  public boolean equals(Object other)
  {
    if (other == null) return false;
    if (other == this) return true;

    if (other instanceof Signature)
    {
      Signature otherSignature = (Signature) other;
      return otherSignature.text.equals(this.text);
    }
    else
    {
      return false;
    }
  }

  @Override
  public int hashCode()
  {
    return text.hashCode();
  }
}
