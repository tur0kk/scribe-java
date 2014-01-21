package org.scribe.oauth.core.service;

import org.scribe.oauth.core.model.AccessToken;
import org.scribe.oauth.core.model.BaseString;
import org.scribe.oauth.core.model.Signature;

public interface SignatureService
{
  Signature getSignature(BaseString baseString, String apiSecret, AccessToken tokenSecret);
  String getSignatureMethod();
}
