package org.scribe.oauth.core.service;

import org.junit.Before;
import org.junit.Test;
import org.scribe.oauth.core.model.BaseString;
import org.scribe.oauth.core.model.Signable;
import org.scribe.oauth.core.model.Signature;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OAuthTest
{
  private Signable signable;
  private OAuth oauth;

  @Before
  public void setup()
  {
    Map<String, String> params = new HashMap<String, String>() {{
      put("key1", "value1");
      put("key2", "value1");
    }};
    signable = new Signable("GET", "http://example.com", params);
    oauth = new OAuth("key", "secret", "http://example.com/callback", new StubTimeService(), new HMACSha1SignatureService());
  }

  @Test
  public void testBaseStringGeneration() throws Exception
  {
    String expected = "GET&http%3A%2F%2Fexample.com&key1%3Dvalue1%26key2%3Dvalue1%26oauth_callback%3Dhttp%253A%252F%252Fexample.com%252Fcallback%26oauth_consumer_key%3Dkey%26oauth_nonce%3Dnonce%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1%26oauth_version%3D1.0";
    BaseString base = oauth.generateBaseString(signable);
    assertEquals("base strings must match", expected, base.getContents());
  }

  @Test
  public void testSignatureGeneration() throws Exception
  {
    Signature expected = new Signature("0ncgfQ/iAvwtLRqU2X+XAs8DrN4=");
    assertEquals(expected, oauth.sign(signable));
  }

  private static class StubTimeService implements  TimestampService
  {

    public Long getTimestamp()
    {
      return 1L;
    }

    public String getNonce()
    {
      return "nonce";
    }
  }
}
