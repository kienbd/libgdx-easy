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

public class btSequentialImpulseConstraintSolver extends btConstraintSolver {
  private long swigCPtr;

  protected btSequentialImpulseConstraintSolver(long cPtr, boolean cMemoryOwn) {
    super(gdxBulletJNI.btSequentialImpulseConstraintSolver_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  public static long getCPtr(btSequentialImpulseConstraintSolver obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        gdxBulletJNI.delete_btSequentialImpulseConstraintSolver(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public btSequentialImpulseConstraintSolver() {
    this(gdxBulletJNI.new_btSequentialImpulseConstraintSolver(), true);
  }

  public long btRand2() {
    return gdxBulletJNI.btSequentialImpulseConstraintSolver_btRand2(swigCPtr, this);
  }

  public int btRandInt2(int n) {
    return gdxBulletJNI.btSequentialImpulseConstraintSolver_btRandInt2(swigCPtr, this, n);
  }

  public void setRandSeed(long seed) {
    gdxBulletJNI.btSequentialImpulseConstraintSolver_setRandSeed(swigCPtr, this, seed);
  }

  public long getRandSeed() {
    return gdxBulletJNI.btSequentialImpulseConstraintSolver_getRandSeed(swigCPtr, this);
  }

}