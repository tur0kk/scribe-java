package org.scribe.oauth.core.service;

import org.scribe.oauth.core.model.AccessToken;
import org.scribe.oauth.core.model.BaseString;
import org.scribe.oauth.core.model.Signature;
import org.scribe.oauth.core.util.Base64Encoder;
import org.scribe.oauth.core.util.Must;
import org.scribe.oauth.core.util.PercentEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC-SHA1 implementation of {@SignatureService}
 * 
 * @author Pablo Fernandez
 *
 */
public class HMACSha1SignatureService implements SignatureService
{
  private static final String EMPTY_STRING = "";
  private static final String CARRIAGE_RETURN = "\r\n";
  private static final String UTF8 = "UTF-8";
  private static final String HMAC_SHA1 = "HmacSHA1";

  /**
   * {@inheritDoc}
   */
  public Signature getSignature(BaseString baseString, String apiSecret, AccessToken accessToken)
  {
    Must.beNotNull(baseString);
    Must.beNotNull(accessToken);
    Must.beNonEmptyString(baseString.getContents());
    Must.beNonEmptyString(apiSecret);
    try
    {
      String text = doSign(baseString.getContents(), PercentEncoder.encode(apiSecret) + '&' + PercentEncoder.encode(accessToken.getSecret()));
      return new Signature(text);
    } 
    catch (Exception e)
    {
      throw new IllegalStateException("problem generating HMAC-SHA1 signature", e);
    }
  }

  private String doSign(String toSign, String keyString) throws Exception
  {
    SecretKeySpec key = new SecretKeySpec((keyString).getBytes(UTF8), HMAC_SHA1);
    Mac mac = Mac.getInstance(HMAC_SHA1);
    mac.init(key);
    byte[] bytes = mac.doFinal(toSign.getBytes(UTF8));
    return bytesToBase64String(bytes).replace(CARRIAGE_RETURN, EMPTY_STRING);
  }

  private String bytesToBase64String(byte[] bytes)
  {
    return Base64Encoder.getInstance().encode(bytes);
  }

  /**
   * {@inheritDoc}
   */
  public String getSignatureMethod()
  {
    return "HMAC-SHA1";
  }
}
