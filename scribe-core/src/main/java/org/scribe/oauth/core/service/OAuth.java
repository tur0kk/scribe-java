package org.scribe.oauth.core.service;

import org.scribe.oauth.core.model.*;
import org.scribe.oauth.core.util.Constants;
import org.scribe.oauth.core.util.PercentEncoder;

import java.util.HashMap;
import java.util.Map;

public class OAuth
{
  public static final String OAUTH_VERSION = "1.0";
  private final String apiKey;
  private final String apiSecret;
  private final String callback;
  private final TimestampService timestampService;
  private final SignatureService signatureService;

  public OAuth(String apiKey, String apiSecret, String callback, TimestampService timestampService,
    SignatureService signatureService)
  {
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    this.callback = callback;
    this.signatureService = signatureService;
    this.timestampService = timestampService;
  }

  public Signature sign(Signable signable)
  {
    return sign(signable, AccessToken.Empty);
  }

  public Signature sign(Signable signable, AccessToken token)
  {
    BaseString toSign = generateBaseString(signable);
    return signatureService.getSignature(toSign, apiSecret, token);
  }

  BaseString generateBaseString(Signable signable)
  {
    // combine user (signable) parameters and oauth parameters.
    ParameterList allParameters = getOAuthParameters().addAll(signable.getParams());
    ParameterList sortedParameters = allParameters.sort();

    // percent encode everything.
    String encodedVerb = PercentEncoder.encode(signable.getVerb());
    String encodedUrl = PercentEncoder.encode(signable.getUrl());
    String encodedParameters = PercentEncoder.encode(sortedParameters.asFormUrlEncodedString());
    return new BaseString(encodedVerb, encodedUrl, encodedParameters);
  }

  private ParameterList getOAuthParameters()
  {
    Map<String, String> oAuthParameters = new HashMap<String, String>(){{
      put(Constants.TIMESTAMP, getTimestamp());
      put(Constants.NONCE, getNonce());
      put(Constants.CONSUMER_KEY, apiKey);
      put(Constants.SIGN_METHOD, getSignatureMethod());
      put(Constants.VERSION, OAUTH_VERSION);
      put(Constants.CALLBACK, callback);
      //todo: scope
    }};

    return ParameterList.fromMap(oAuthParameters);
  }

  private String getSignatureMethod()
  {
    return signatureService.getSignatureMethod();
  }

  private String getNonce()
  {
    return timestampService.getNonce();
  }

  private String getTimestamp()
  {
    return timestampService.getTimestamp().toString();
  }
}
