/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.chilkatsoft;

public class CkFileAccess {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected CkFileAccess(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(CkFileAccess obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        chilkatJNI.delete_CkFileAccess(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public CkFileAccess() {
    this(chilkatJNI.new_CkFileAccess(), true);
  }

  public void LastErrorXml(CkString str) {
    chilkatJNI.CkFileAccess_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorHtml(CkString str) {
    chilkatJNI.CkFileAccess_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void LastErrorText(CkString str) {
    chilkatJNI.CkFileAccess_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public void get_CurrentDir(CkString str) {
    chilkatJNI.CkFileAccess_get_CurrentDir(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String currentDir() {
    return chilkatJNI.CkFileAccess_currentDir(swigCPtr, this);
  }

  public void get_DebugLogFilePath(CkString str) {
    chilkatJNI.CkFileAccess_get_DebugLogFilePath(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String debugLogFilePath() {
    return chilkatJNI.CkFileAccess_debugLogFilePath(swigCPtr, this);
  }

  public void put_DebugLogFilePath(String newVal) {
    chilkatJNI.CkFileAccess_put_DebugLogFilePath(swigCPtr, this, newVal);
  }

  public boolean get_EndOfFile() {
    return chilkatJNI.CkFileAccess_get_EndOfFile(swigCPtr, this);
  }

  public int get_FileOpenError() {
    return chilkatJNI.CkFileAccess_get_FileOpenError(swigCPtr, this);
  }

  public void get_FileOpenErrorMsg(CkString str) {
    chilkatJNI.CkFileAccess_get_FileOpenErrorMsg(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String fileOpenErrorMsg() {
    return chilkatJNI.CkFileAccess_fileOpenErrorMsg(swigCPtr, this);
  }

  public void get_LastErrorHtml(CkString str) {
    chilkatJNI.CkFileAccess_get_LastErrorHtml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorHtml() {
    return chilkatJNI.CkFileAccess_lastErrorHtml(swigCPtr, this);
  }

  public void get_LastErrorText(CkString str) {
    chilkatJNI.CkFileAccess_get_LastErrorText(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorText() {
    return chilkatJNI.CkFileAccess_lastErrorText(swigCPtr, this);
  }

  public void get_LastErrorXml(CkString str) {
    chilkatJNI.CkFileAccess_get_LastErrorXml(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String lastErrorXml() {
    return chilkatJNI.CkFileAccess_lastErrorXml(swigCPtr, this);
  }

  public boolean get_LastMethodSuccess() {
    return chilkatJNI.CkFileAccess_get_LastMethodSuccess(swigCPtr, this);
  }

  public void put_LastMethodSuccess(boolean newVal) {
    chilkatJNI.CkFileAccess_put_LastMethodSuccess(swigCPtr, this, newVal);
  }

  public boolean get_VerboseLogging() {
    return chilkatJNI.CkFileAccess_get_VerboseLogging(swigCPtr, this);
  }

  public void put_VerboseLogging(boolean newVal) {
    chilkatJNI.CkFileAccess_put_VerboseLogging(swigCPtr, this, newVal);
  }

  public void get_Version(CkString str) {
    chilkatJNI.CkFileAccess_get_Version(swigCPtr, this, CkString.getCPtr(str), str);
  }

  public String version() {
    return chilkatJNI.CkFileAccess_version(swigCPtr, this);
  }

  public boolean AppendAnsi(String text) {
    return chilkatJNI.CkFileAccess_AppendAnsi(swigCPtr, this, text);
  }

  public boolean AppendText(String str, String charset) {
    return chilkatJNI.CkFileAccess_AppendText(swigCPtr, this, str, charset);
  }

  public boolean AppendUnicodeBOM() {
    return chilkatJNI.CkFileAccess_AppendUnicodeBOM(swigCPtr, this);
  }

  public boolean AppendUtf8BOM() {
    return chilkatJNI.CkFileAccess_AppendUtf8BOM(swigCPtr, this);
  }

  public boolean DirAutoCreate(String dirPath) {
    return chilkatJNI.CkFileAccess_DirAutoCreate(swigCPtr, this, dirPath);
  }

  public boolean DirCreate(String dirPath) {
    return chilkatJNI.CkFileAccess_DirCreate(swigCPtr, this, dirPath);
  }

  public boolean DirDelete(String dirPath) {
    return chilkatJNI.CkFileAccess_DirDelete(swigCPtr, this, dirPath);
  }

  public boolean DirEnsureExists(String filePath) {
    return chilkatJNI.CkFileAccess_DirEnsureExists(swigCPtr, this, filePath);
  }

  public void FileClose() {
    chilkatJNI.CkFileAccess_FileClose(swigCPtr, this);
  }

  public boolean FileContentsEqual(String filePath1, String filePath2) {
    return chilkatJNI.CkFileAccess_FileContentsEqual(swigCPtr, this, filePath1, filePath2);
  }

  public boolean FileCopy(String existingFilepath, String newFilepath, boolean failIfExists) {
    return chilkatJNI.CkFileAccess_FileCopy(swigCPtr, this, existingFilepath, newFilepath, failIfExists);
  }

  public boolean FileDelete(String filePath) {
    return chilkatJNI.CkFileAccess_FileDelete(swigCPtr, this, filePath);
  }

  public boolean FileExists(String filePath) {
    return chilkatJNI.CkFileAccess_FileExists(swigCPtr, this, filePath);
  }

  public int FileExists3(String path) {
    return chilkatJNI.CkFileAccess_FileExists3(swigCPtr, this, path);
  }

  public boolean FileOpen(String filePath, long accessMode, long shareMode, long createDisposition, long attributes) {
    return chilkatJNI.CkFileAccess_FileOpen(swigCPtr, this, filePath, accessMode, shareMode, createDisposition, attributes);
  }

  public boolean FileRead(int maxNumBytes, CkByteData outBytes) {
    return chilkatJNI.CkFileAccess_FileRead(swigCPtr, this, maxNumBytes, CkByteData.getCPtr(outBytes), outBytes);
  }

  public boolean FileReadBd(int maxNumBytes, SWIGTYPE_p_CkBinData binData) {
    return chilkatJNI.CkFileAccess_FileReadBd(swigCPtr, this, maxNumBytes, SWIGTYPE_p_CkBinData.getCPtr(binData));
  }

  public boolean FileRename(String existingFilepath, String newFilepath) {
    return chilkatJNI.CkFileAccess_FileRename(swigCPtr, this, existingFilepath, newFilepath);
  }

  public boolean FileSeek(int offset, int origin) {
    return chilkatJNI.CkFileAccess_FileSeek(swigCPtr, this, offset, origin);
  }

  public int FileSize(String filePath) {
    return chilkatJNI.CkFileAccess_FileSize(swigCPtr, this, filePath);
  }

  public boolean FileWrite(CkByteData data) {
    return chilkatJNI.CkFileAccess_FileWrite(swigCPtr, this, CkByteData.getCPtr(data), data);
  }

  public boolean FileWriteBd(SWIGTYPE_p_CkBinData binData, int offset, int numBytes) {
    return chilkatJNI.CkFileAccess_FileWriteBd(swigCPtr, this, SWIGTYPE_p_CkBinData.getCPtr(binData), offset, numBytes);
  }

  public boolean GenBlockId(int index, int length, String encoding, CkString outStr) {
    return chilkatJNI.CkFileAccess_GenBlockId(swigCPtr, this, index, length, encoding, CkString.getCPtr(outStr), outStr);
  }

  public String genBlockId(int index, int length, String encoding) {
    return chilkatJNI.CkFileAccess_genBlockId(swigCPtr, this, index, length, encoding);
  }

  public boolean GetDirectoryName(String path, CkString outStr) {
    return chilkatJNI.CkFileAccess_GetDirectoryName(swigCPtr, this, path, CkString.getCPtr(outStr), outStr);
  }

  public String getDirectoryName(String path) {
    return chilkatJNI.CkFileAccess_getDirectoryName(swigCPtr, this, path);
  }

  public String directoryName(String path) {
    return chilkatJNI.CkFileAccess_directoryName(swigCPtr, this, path);
  }

  public boolean GetExtension(String path, CkString outStr) {
    return chilkatJNI.CkFileAccess_GetExtension(swigCPtr, this, path, CkString.getCPtr(outStr), outStr);
  }

  public String getExtension(String path) {
    return chilkatJNI.CkFileAccess_getExtension(swigCPtr, this, path);
  }

  public String extension(String path) {
    return chilkatJNI.CkFileAccess_extension(swigCPtr, this, path);
  }

  public boolean GetFileName(String path, CkString outStr) {
    return chilkatJNI.CkFileAccess_GetFileName(swigCPtr, this, path, CkString.getCPtr(outStr), outStr);
  }

  public String getFileName(String path) {
    return chilkatJNI.CkFileAccess_getFileName(swigCPtr, this, path);
  }

  public String fileName(String path) {
    return chilkatJNI.CkFileAccess_fileName(swigCPtr, this, path);
  }

  public boolean GetFileNameWithoutExtension(String path, CkString outStr) {
    return chilkatJNI.CkFileAccess_GetFileNameWithoutExtension(swigCPtr, this, path, CkString.getCPtr(outStr), outStr);
  }

  public String getFileNameWithoutExtension(String path) {
    return chilkatJNI.CkFileAccess_getFileNameWithoutExtension(swigCPtr, this, path);
  }

  public String fileNameWithoutExtension(String path) {
    return chilkatJNI.CkFileAccess_fileNameWithoutExtension(swigCPtr, this, path);
  }

  public CkDateTime GetLastModified(String path) {
    long cPtr = chilkatJNI.CkFileAccess_GetLastModified(swigCPtr, this, path);
    return (cPtr == 0) ? null : new CkDateTime(cPtr, true);
  }

  public int GetNumBlocks(int blockSize) {
    return chilkatJNI.CkFileAccess_GetNumBlocks(swigCPtr, this, blockSize);
  }

  public boolean GetTempFilename(String dirPath, String prefix, CkString outStr) {
    return chilkatJNI.CkFileAccess_GetTempFilename(swigCPtr, this, dirPath, prefix, CkString.getCPtr(outStr), outStr);
  }

  public String getTempFilename(String dirPath, String prefix) {
    return chilkatJNI.CkFileAccess_getTempFilename(swigCPtr, this, dirPath, prefix);
  }

  public String tempFilename(String dirPath, String prefix) {
    return chilkatJNI.CkFileAccess_tempFilename(swigCPtr, this, dirPath, prefix);
  }

  public boolean OpenForAppend(String filePath) {
    return chilkatJNI.CkFileAccess_OpenForAppend(swigCPtr, this, filePath);
  }

  public boolean OpenForRead(String filePath) {
    return chilkatJNI.CkFileAccess_OpenForRead(swigCPtr, this, filePath);
  }

  public boolean OpenForReadWrite(String filePath) {
    return chilkatJNI.CkFileAccess_OpenForReadWrite(swigCPtr, this, filePath);
  }

  public boolean OpenForWrite(String filePath) {
    return chilkatJNI.CkFileAccess_OpenForWrite(swigCPtr, this, filePath);
  }

  public boolean ReadBinaryToEncoded(String filePath, String encoding, CkString outStr) {
    return chilkatJNI.CkFileAccess_ReadBinaryToEncoded(swigCPtr, this, filePath, encoding, CkString.getCPtr(outStr), outStr);
  }

  public String readBinaryToEncoded(String filePath, String encoding) {
    return chilkatJNI.CkFileAccess_readBinaryToEncoded(swigCPtr, this, filePath, encoding);
  }

  public boolean ReadBlock(int blockIndex, int blockSize, CkByteData outBytes) {
    return chilkatJNI.CkFileAccess_ReadBlock(swigCPtr, this, blockIndex, blockSize, CkByteData.getCPtr(outBytes), outBytes);
  }

  public boolean ReadEntireFile(String filePath, CkByteData outBytes) {
    return chilkatJNI.CkFileAccess_ReadEntireFile(swigCPtr, this, filePath, CkByteData.getCPtr(outBytes), outBytes);
  }

  public boolean ReadEntireTextFile(String filePath, String charset, CkString outStrFileContents) {
    return chilkatJNI.CkFileAccess_ReadEntireTextFile(swigCPtr, this, filePath, charset, CkString.getCPtr(outStrFileContents), outStrFileContents);
  }

  public String readEntireTextFile(String filePath, String charset) {
    return chilkatJNI.CkFileAccess_readEntireTextFile(swigCPtr, this, filePath, charset);
  }

  public boolean ReassembleFile(String partsDirPath, String partPrefix, String partExtension, String reassembledFilename) {
    return chilkatJNI.CkFileAccess_ReassembleFile(swigCPtr, this, partsDirPath, partPrefix, partExtension, reassembledFilename);
  }

  public int ReplaceStrings(String filePath, String charset, String existingString, String replacementString) {
    return chilkatJNI.CkFileAccess_ReplaceStrings(swigCPtr, this, filePath, charset, existingString, replacementString);
  }

  public boolean SaveLastError(String path) {
    return chilkatJNI.CkFileAccess_SaveLastError(swigCPtr, this, path);
  }

  public boolean SetCurrentDir(String dirPath) {
    return chilkatJNI.CkFileAccess_SetCurrentDir(swigCPtr, this, dirPath);
  }

  public boolean SetFileTimes(String filePath, CkDateTime createTime, CkDateTime lastAccessTime, CkDateTime lastModTime) {
    return chilkatJNI.CkFileAccess_SetFileTimes(swigCPtr, this, filePath, CkDateTime.getCPtr(createTime), createTime, CkDateTime.getCPtr(lastAccessTime), lastAccessTime, CkDateTime.getCPtr(lastModTime), lastModTime);
  }

  public boolean SetLastModified(String filePath, CkDateTime lastModified) {
    return chilkatJNI.CkFileAccess_SetLastModified(swigCPtr, this, filePath, CkDateTime.getCPtr(lastModified), lastModified);
  }

  public boolean SplitFile(String fileToSplit, String partPrefix, String partExtension, int partSize, String destDir) {
    return chilkatJNI.CkFileAccess_SplitFile(swigCPtr, this, fileToSplit, partPrefix, partExtension, partSize, destDir);
  }

  public boolean TreeDelete(String path) {
    return chilkatJNI.CkFileAccess_TreeDelete(swigCPtr, this, path);
  }

  public boolean WriteEntireFile(String filePath, CkByteData fileData) {
    return chilkatJNI.CkFileAccess_WriteEntireFile(swigCPtr, this, filePath, CkByteData.getCPtr(fileData), fileData);
  }

  public boolean WriteEntireTextFile(String filePath, String textData, String charset, boolean includedPreamble) {
    return chilkatJNI.CkFileAccess_WriteEntireTextFile(swigCPtr, this, filePath, textData, charset, includedPreamble);
  }

}
