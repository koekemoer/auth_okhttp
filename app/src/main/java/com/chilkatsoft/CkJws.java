/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.chilkatsoft;

public class CkJws {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CkJws(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CkJws obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        chilkatJNI.delete_CkJws(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public CkJws() {
    this(chilkatJNI.new_CkJws(), true);
  }

  public void LastErrorXml(CkString str) {
    chilkatJNI.CkJws_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorHtml(CkString str) {
    chilkatJNI.CkJws_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorText(CkString str) {
    chilkatJNI.CkJws_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void get_DebugLogFilePath(CkString str) {
    chilkatJNI.CkJws_get_DebugLogFilePath(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String debugLogFilePath() {
    return chilkatJNI.CkJws_debugLogFilePath(swigCPtr, this);
  }

  public void put_DebugLogFilePath(String newVal) {
    chilkatJNI.CkJws_put_DebugLogFilePath(swigCPtr, this, newVal);
  }

  public void get_LastErrorHtml(CkString str) {
    chilkatJNI.CkJws_get_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorHtml() {
    return chilkatJNI.CkJws_lastErrorHtml(swigCPtr, this);
  }

  public void get_LastErrorText(CkString str) {
    chilkatJNI.CkJws_get_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorText() {
    return chilkatJNI.CkJws_lastErrorText(swigCPtr, this);
  }

  public void get_LastErrorXml(CkString str) {
    chilkatJNI.CkJws_get_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorXml() {
    return chilkatJNI.CkJws_lastErrorXml(swigCPtr, this);
  }

  public boolean get_LastMethodSuccess() {
    return chilkatJNI.CkJws_get_LastMethodSuccess(swigCPtr, this);
  }

  public void put_LastMethodSuccess(boolean newVal) {
    chilkatJNI.CkJws_put_LastMethodSuccess(swigCPtr, this, newVal);
  }

  public int get_NumSignatures() {
    return chilkatJNI.CkJws_get_NumSignatures(swigCPtr, this);
  }

  public boolean get_PreferCompact() {
    return chilkatJNI.CkJws_get_PreferCompact(swigCPtr, this);
  }

  public void put_PreferCompact(boolean newVal) {
    chilkatJNI.CkJws_put_PreferCompact(swigCPtr, this, newVal);
  }

  public boolean get_PreferFlattened() {
    return chilkatJNI.CkJws_get_PreferFlattened(swigCPtr, this);
  }

  public void put_PreferFlattened(boolean newVal) {
    chilkatJNI.CkJws_put_PreferFlattened(swigCPtr, this, newVal);
  }

  public boolean get_VerboseLogging() {
    return chilkatJNI.CkJws_get_VerboseLogging(swigCPtr, this);
  }

  public void put_VerboseLogging(boolean newVal) {
    chilkatJNI.CkJws_put_VerboseLogging(swigCPtr, this, newVal);
  }

  public void get_Version(CkString str) {
    chilkatJNI.CkJws_get_Version(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String version() {
    return chilkatJNI.CkJws_version(swigCPtr, this);
  }

  public boolean CreateJws(CkString outStr) {
    return chilkatJNI.CkJws_CreateJws(swigCPtr, this, CkString.getCPtr(outStr), outStr);
  }

  public String createJws() {
    return chilkatJNI.CkJws_createJws(swigCPtr, this);
  }

  public boolean CreateJwsSb(CkStringBuilder sbJws) {
    return chilkatJNI.CkJws_CreateJwsSb(swigCPtr, this, CkStringBuilder.getCPtr(sbJws), sbJws);
  }

  public boolean GetPayload(String charset, CkString outStr) {
    return chilkatJNI.CkJws_GetPayload(swigCPtr, this, charset, CkString.getCPtr(outStr), outStr);
  }

  public String getPayload(String charset) {
    return chilkatJNI.CkJws_getPayload(swigCPtr, this, charset);
  }

  public String payload(String charset) {
    return chilkatJNI.CkJws_payload(swigCPtr, this, charset);
  }

  public boolean GetPayloadBd(SWIGTYPE_p_CkBinData binData) {
    return chilkatJNI.CkJws_GetPayloadBd(swigCPtr, this, SWIGTYPE_p_CkBinData.getCPtr(binData));
  }

  public boolean GetPayloadSb(String charset, CkStringBuilder sbPayload) {
    return chilkatJNI.CkJws_GetPayloadSb(swigCPtr, this, charset, CkStringBuilder.getCPtr(sbPayload), sbPayload);
  }

  public CkJsonObject GetProtectedHeader(int index) {
    long cPtr = chilkatJNI.CkJws_GetProtectedHeader(swigCPtr, this, index);
    return (cPtr == 0) ? null : new CkJsonObject(cPtr, true);
  }

  public CkJsonObject GetUnprotectedHeader(int index) {
    long cPtr = chilkatJNI.CkJws_GetUnprotectedHeader(swigCPtr, this, index);
    return (cPtr == 0) ? null : new CkJsonObject(cPtr, true);
  }

  public boolean LoadJws(String jwsStr) {
    return chilkatJNI.CkJws_LoadJws(swigCPtr, this, jwsStr);
  }

  public boolean LoadJwsSb(CkStringBuilder sbJws) {
    return chilkatJNI.CkJws_LoadJwsSb(swigCPtr, this, CkStringBuilder.getCPtr(sbJws), sbJws);
  }

  public boolean SaveLastError(String path) {
    return chilkatJNI.CkJws_SaveLastError(swigCPtr, this, path);
  }

  public boolean SetMacKey(int index, String key, String encoding) {
    return chilkatJNI.CkJws_SetMacKey(swigCPtr, this, index, key, encoding);
  }

  public boolean SetMacKeyBd(int index, SWIGTYPE_p_CkBinData key) {
    return chilkatJNI.CkJws_SetMacKeyBd(swigCPtr, this, index, SWIGTYPE_p_CkBinData.getCPtr(key));
  }

  public boolean SetPayload(String payload, String charset, boolean includeBom) {
    return chilkatJNI.CkJws_SetPayload(swigCPtr, this, payload, charset, includeBom);
  }

  public boolean SetPayloadBd(SWIGTYPE_p_CkBinData binData) {
    return chilkatJNI.CkJws_SetPayloadBd(swigCPtr, this, SWIGTYPE_p_CkBinData.getCPtr(binData));
  }

  public boolean SetPayloadSb(CkStringBuilder sbPayload, String charset, boolean includeBom) {
    return chilkatJNI.CkJws_SetPayloadSb(swigCPtr, this, CkStringBuilder.getCPtr(sbPayload), sbPayload, charset, includeBom);
  }

  public boolean SetPrivateKey(int index, CkPrivateKey privKey) {
    return chilkatJNI.CkJws_SetPrivateKey(swigCPtr, this, index, CkPrivateKey.getCPtr(privKey), privKey);
  }

  public boolean SetProtectedHeader(int index, CkJsonObject json) {
    return chilkatJNI.CkJws_SetProtectedHeader(swigCPtr, this, index, CkJsonObject.getCPtr(json), json);
  }

  public boolean SetPublicKey(int index, CkPublicKey pubKey) {
    return chilkatJNI.CkJws_SetPublicKey(swigCPtr, this, index, CkPublicKey.getCPtr(pubKey), pubKey);
  }

  public boolean SetUnprotectedHeader(int index, CkJsonObject json) {
    return chilkatJNI.CkJws_SetUnprotectedHeader(swigCPtr, this, index, CkJsonObject.getCPtr(json), json);
  }

  public int Validate(int index) {
    return chilkatJNI.CkJws_Validate(swigCPtr, this, index);
  }

}
