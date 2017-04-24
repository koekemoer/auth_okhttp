/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.chilkatsoft;

public class CkAuthAws {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CkAuthAws(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CkAuthAws obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        chilkatJNI.delete_CkAuthAws(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public CkAuthAws() {
    this(chilkatJNI.new_CkAuthAws(), true);
  }

  public void LastErrorXml(CkString str) {
    chilkatJNI.CkAuthAws_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorHtml(CkString str) {
    chilkatJNI.CkAuthAws_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorText(CkString str) {
    chilkatJNI.CkAuthAws_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void get_AccessKey(CkString str) {
    chilkatJNI.CkAuthAws_get_AccessKey(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String accessKey() {
    return chilkatJNI.CkAuthAws_accessKey(swigCPtr, this);
  }

  public void put_AccessKey(String newVal) {
    chilkatJNI.CkAuthAws_put_AccessKey(swigCPtr, this, newVal);
  }

  public void get_CanonicalizedResourceV2(CkString str) {
    chilkatJNI.CkAuthAws_get_CanonicalizedResourceV2(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String canonicalizedResourceV2() {
    return chilkatJNI.CkAuthAws_canonicalizedResourceV2(swigCPtr, this);
  }

  public void put_CanonicalizedResourceV2(String newVal) {
    chilkatJNI.CkAuthAws_put_CanonicalizedResourceV2(swigCPtr, this, newVal);
  }

  public void get_DebugLogFilePath(CkString str) {
    chilkatJNI.CkAuthAws_get_DebugLogFilePath(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String debugLogFilePath() {
    return chilkatJNI.CkAuthAws_debugLogFilePath(swigCPtr, this);
  }

  public void put_DebugLogFilePath(String newVal) {
    chilkatJNI.CkAuthAws_put_DebugLogFilePath(swigCPtr, this, newVal);
  }

  public void get_LastErrorHtml(CkString str) {
    chilkatJNI.CkAuthAws_get_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorHtml() {
    return chilkatJNI.CkAuthAws_lastErrorHtml(swigCPtr, this);
  }

  public void get_LastErrorText(CkString str) {
    chilkatJNI.CkAuthAws_get_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorText() {
    return chilkatJNI.CkAuthAws_lastErrorText(swigCPtr, this);
  }

  public void get_LastErrorXml(CkString str) {
    chilkatJNI.CkAuthAws_get_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorXml() {
    return chilkatJNI.CkAuthAws_lastErrorXml(swigCPtr, this);
  }

  public boolean get_LastMethodSuccess() {
    return chilkatJNI.CkAuthAws_get_LastMethodSuccess(swigCPtr, this);
  }

  public void put_LastMethodSuccess(boolean newVal) {
    chilkatJNI.CkAuthAws_put_LastMethodSuccess(swigCPtr, this, newVal);
  }

  public void get_PrecomputedMd5(CkString str) {
    chilkatJNI.CkAuthAws_get_PrecomputedMd5(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String precomputedMd5() {
    return chilkatJNI.CkAuthAws_precomputedMd5(swigCPtr, this);
  }

  public void put_PrecomputedMd5(String newVal) {
    chilkatJNI.CkAuthAws_put_PrecomputedMd5(swigCPtr, this, newVal);
  }

  public void get_PrecomputedSha256(CkString str) {
    chilkatJNI.CkAuthAws_get_PrecomputedSha256(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String precomputedSha256() {
    return chilkatJNI.CkAuthAws_precomputedSha256(swigCPtr, this);
  }

  public void put_PrecomputedSha256(String newVal) {
    chilkatJNI.CkAuthAws_put_PrecomputedSha256(swigCPtr, this, newVal);
  }

  public void get_Region(CkString str) {
    chilkatJNI.CkAuthAws_get_Region(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String region() {
    return chilkatJNI.CkAuthAws_region(swigCPtr, this);
  }

  public void put_Region(String newVal) {
    chilkatJNI.CkAuthAws_put_Region(swigCPtr, this, newVal);
  }

  public void get_SecretKey(CkString str) {
    chilkatJNI.CkAuthAws_get_SecretKey(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String secretKey() {
    return chilkatJNI.CkAuthAws_secretKey(swigCPtr, this);
  }

  public void put_SecretKey(String newVal) {
    chilkatJNI.CkAuthAws_put_SecretKey(swigCPtr, this, newVal);
  }

  public void get_ServiceName(CkString str) {
    chilkatJNI.CkAuthAws_get_ServiceName(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String serviceName() {
    return chilkatJNI.CkAuthAws_serviceName(swigCPtr, this);
  }

  public void put_ServiceName(String newVal) {
    chilkatJNI.CkAuthAws_put_ServiceName(swigCPtr, this, newVal);
  }

  public int get_SignatureVersion() {
    return chilkatJNI.CkAuthAws_get_SignatureVersion(swigCPtr, this);
  }

  public void put_SignatureVersion(int newVal) {
    chilkatJNI.CkAuthAws_put_SignatureVersion(swigCPtr, this, newVal);
  }

  public boolean get_VerboseLogging() {
    return chilkatJNI.CkAuthAws_get_VerboseLogging(swigCPtr, this);
  }

  public void put_VerboseLogging(boolean newVal) {
    chilkatJNI.CkAuthAws_put_VerboseLogging(swigCPtr, this, newVal);
  }

  public void get_Version(CkString str) {
    chilkatJNI.CkAuthAws_get_Version(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String version() {
    return chilkatJNI.CkAuthAws_version(swigCPtr, this);
  }

  public boolean SaveLastError(String path) {
    return chilkatJNI.CkAuthAws_SaveLastError(swigCPtr, this, path);
  }

}