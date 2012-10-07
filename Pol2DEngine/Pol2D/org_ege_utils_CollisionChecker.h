/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <Sprite/Pol2D.h>
/* Header for class org_ege_utils_CollisionChecker */

#ifndef _Included_org_ege_utils_CollisionChecker
#define _Included_org_ege_utils_CollisionChecker
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_ege_utils_CollisionChecker
 * Method:    setGridData
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_org_ege_utils_CollisionChecker_setGridData
  (JNIEnv *, jclass, jint, jint, jint, jint);

/*
 * Class:     org_ege_utils_CollisionChecker
 * Method:    project
 * Signature: ([FFF)V
 */
JNIEXPORT void JNICALL Java_org_ege_utils_CollisionChecker_project___3FFF
  (JNIEnv *, jclass, jfloatArray, jfloat, jfloat);

/*
 * Class:     org_ege_utils_CollisionChecker
 * Method:    project
 * Signature: (FF)I
 */
JNIEXPORT jint JNICALL Java_org_ege_utils_CollisionChecker_project__FF
  (JNIEnv *, jclass, jfloat, jfloat);

/*
 * Class:     org_ege_utils_CollisionChecker
 * Method:    unproject
 * Signature: ([FII)V
 */
JNIEXPORT void JNICALL Java_org_ege_utils_CollisionChecker_unproject___3FII
  (JNIEnv *, jclass, jfloatArray, jint, jint);

/*
 * Class:     org_ege_utils_CollisionChecker
 * Method:    unproject
 * Signature: ([FI)V
 */
JNIEXPORT void JNICALL Java_org_ege_utils_CollisionChecker_unproject___3FI
  (JNIEnv *, jclass, jfloatArray, jint);

/*
 * Class:     org_ege_utils_CollisionChecker
 * Method:    toGridPos
 * Signature: ([FI)V
 */
JNIEXPORT void JNICALL Java_org_ege_utils_CollisionChecker_toGridPos
  (JNIEnv *, jclass, jfloatArray, jint);

/*
 * Class:     org_ege_utils_CollisionChecker
 * Method:    toMappingId
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_org_ege_utils_CollisionChecker_toMappingId
  (JNIEnv *, jclass, jint, jint);

/*
 * Class:     org_ege_utils_CollisionChecker
 * Method:    checkCollision
 * Signature: ([I[FI[FI)I
 */
JNIEXPORT jint JNICALL Java_org_ege_utils_CollisionChecker_checkCollision
  (JNIEnv *, jclass, jintArray, jfloatArray, jint, jfloatArray, jint);

#ifdef __cplusplus
}
#endif
#endif
