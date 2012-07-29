/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.5
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix3;

public class btGhostPairCallback extends btOverlappingPairCallback {
  private long swigCPtr;

  protected btGhostPairCallback(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.btGhostPairCallback_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(btGhostPairCallback obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btGhostPairCallback(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public btGhostPairCallback() {
    this(gdxBulletJNI.new_btGhostPairCallback(), true);
  }

}