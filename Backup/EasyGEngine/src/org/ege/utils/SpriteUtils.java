package org.ege.utils;


public class SpriteUtils {
	private static final int[] mResultSet = new int[113];
	
	public static int[] processNearestSprite(int direction,int numberOfResult,float originSpriteX,float originSpriteY,float[] spriteList,int spriteSize){
		getNearestSprite(mResultSet, numberOfResult,direction, originSpriteX, originSpriteY, spriteList, spriteSize);
		return mResultSet;
	}

	public static int[] processNearestSprite(int direction,int numberOfResult,float[] originSprite,float[] spriteList,int spriteSize){
		getNearestSprite(mResultSet, numberOfResult,direction, originSprite[0], originSprite[1], spriteList, spriteSize);
		return mResultSet;
	}

	public static int[] processNearestSprite(int numberOfResult,float originSpriteX,float originSpriteY,float[] spriteList,int spriteSize){
		getNearestSprite(mResultSet, numberOfResult, originSpriteX, originSpriteY, spriteList, spriteSize);
		return mResultSet;
	}

	public static int[] processNearestSprite(int numberOfResult,float[] originSprite,float[] spriteList,int spriteSize){
		getNearestSprite(mResultSet, numberOfResult, originSprite[0], originSprite[1], spriteList, spriteSize);
		return mResultSet;
	}
	/***********************************************************
	 * Native method
	 ***********************************************************/
	
	/**
	 * Get the list of nearest sprite to the origin sprite
	 * @Note this is unsafe method process directly with native 
	 * @param resultSet  the result set you want
	 * @param numberOfResult the number of nearest sprite you want
	 * @param direction the direction of sprite
	 * @param originSprite the center of origin sprite
	 * @param spriteList the list of sprite you want to check ( the list of center position of them) 
	 * @param spriteSize the size of that list ( such as you have 10 sprite want to check, so the size is 20)
	 */
	public static native void getNearestSprite(int[] resultSet,int numberOfResult,int direction,float[] originSprite,float[] spriteList,int spriteSize);

	/**
	 * Get the list of nearest sprite to the origin sprite
	 * @Note this is unsafe method process directly with native 
	 * @param resultSet  the result set you want
	 * @param numberOfResult the number of nearest sprite you want
	 * @param direction the direction of sprite
	 * @param originSpriteX the center X of origin sprite
	 * @param originSpriteY the center Y of origin sprite
	 * @param spriteList the list of sprite you want to check ( the list of center position of them) 
	 * @param spriteSize the size of that list ( such as you have 10 sprite want to check, so the size is 20)
	 */
	public static native void getNearestSprite(int[] resultSet,int numberOfResult,int direction,float originSpriteX,float originSpriteY,float[] spriteList,int spriteSize);
	
	/**
	 * Get the list of nearest sprite to the origin sprite
	 * @Note this is unsafe method process directly with native 
	 * @param resultSet  the result set you want
	 * @param numberOfResult the number of nearest sprite you want
	 * @param originSpriteX the center X of origin sprite
	 * @param originSpriteY the center Y of origin sprite
	 * @param spriteList the list of sprite you want to check ( the list of center position of them) 
	 * @param spriteSize the size of that list ( such as you have 10 sprite want to check, so the size is 20)
	 */
	public static native void getNearestSprite(int[] resultSet,int numberOfResult,float originSpriteX,float originSpriteY,float[] spriteList,int spriteSize);

	/**
	 * Get the list of nearest sprite to the origin sprite
	 * @Note this is unsafe method process directly with native 
	 * @param resultSet  the result set you want
	 * @param numberOfResult the number of nearest sprite you want
	 * @param originSprite the center of origin sprite
	 * @param spriteList the list of sprite you want to check ( the list of center position of them) 
	 * @param spriteSize the size of that list ( such as you have 10 sprite want to check, so the size is 20)
	 */
	public static native void getNearestSprite(int[] resultSet,int numberOfResult,float[] originSprite,float[] spriteList,int spriteSize);
}
