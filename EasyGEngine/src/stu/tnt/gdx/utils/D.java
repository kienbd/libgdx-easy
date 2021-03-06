package stu.tnt.gdx.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/** D.java for debug Created on: Jan 5, 2013 Author: Trung */
public final class D {
	public static final int MB = 1;
	public static final int GB = 2;
	public static final int KB = 3;
	public static final int B = 4;
	private static boolean isEnable = true;
	private static eFPSLogger log = null;

	public static float DELAY = 1.3f;

	private static float timer[] = new float[] { -1, -1, -1, -1, -1 };

	private D() {
	}

	public static void switchState() {
		isEnable = !isEnable;
	}

	public static void enable() {
		isEnable = true;
	}

	public static void disable() {
		isEnable = false;
	}

	public static boolean isEnable() {
		return isEnable;
	}

	/********************************************************************
         * 
         ********************************************************************/
	public static void log(String logTitle, String logInfo) {
		if (!isEnable)
			return;
		timer[0] += Gdx.graphics.getDeltaTime();
		if (timer[0] >= DELAY) {
			Gdx.app.log("**Debug log** : " + logTitle + " ", " " + logInfo);
			timer[0] = 0;
		}
	}

	public static void log1(String logTitle, String logInfo) {
		if (!isEnable)
			return;
		timer[1] += Gdx.graphics.getDeltaTime();
		if (timer[1] >= DELAY) {
			Gdx.app.log("**Debug log1** : " + logTitle + " ", " " + logInfo);
			timer[1] = 0;
		}
	}

	public static void log2(String logTitle, String logInfo) {
		if (!isEnable)
			return;

		timer[2] += Gdx.graphics.getDeltaTime();
		if (timer[2] >= DELAY) {
			Gdx.app.log("**Debug log2** : " + logTitle + " ", " " + logInfo);
			timer[2] = 0;
		}
	}

	public static void log3(String logTitle, String logInfo) {
		if (!isEnable)
			return;

		timer[3] += Gdx.graphics.getDeltaTime();
		if (timer[3] >= DELAY) {
			Gdx.app.log("**Debug log3** : " + logTitle + " ", " " + logInfo);
			timer[3] = 0;
		}
	}

	public static void log4(String logTitle, String logInfo) {
		if (!isEnable)
			return;

		timer[4] += Gdx.graphics.getDeltaTime();
		if (timer[4] >= DELAY) {
			Gdx.app.log("**Debug log4** : " + logTitle + " ", " " + logInfo);
			timer[4] = 0;
		}
	}

	/********************************************************************
         * 
         ********************************************************************/

	public static void out(Object obj) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " " + obj);
	}

	public static void out(String string) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " " + string);
	}

	public static void out(int i) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " " + i);
	}

	public static void out(float f) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " " + f);
	}

	public static void out(double d) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " " + d);
	}

	public static void out(boolean b) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " " + b);
	}

	public static void out(long l) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " " + l);
	}

	/********************************************************************
         * 
         ********************************************************************/

	public static void out(String title, int[] i) {
		if (!isEnable)
			return;

		int leng = i.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(i[j]);
		}
		Gdx.app.log("**Print Out** ", title + " : " + tmp.toString());
	}

	public static void out(String title, float[] f) {
		if (!isEnable)
			return;

		int leng = f.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(f[j]);
		}

		Gdx.app.log("**Print Out** ", title + " : " + tmp.toString());
	}

	public static void out(String title, double[] d) {
		if (!isEnable)
			return;

		int leng = d.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(d[j]);
		}

		Gdx.app.log("**Print Out** ", title + " : " + tmp.toString());
	}

	public static void out(String title, boolean[] b) {
		if (!isEnable)
			return;

		int leng = b.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(b[j]);
		}

		Gdx.app.log("**Print Out** ", title + " : " + tmp.toString());
	}

	public static void out(String title, long[] l) {
		if (!isEnable)
			return;

		int leng = l.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(l[j]);
		}
		Gdx.app.log("**Print Out** ", title + " : " + tmp.toString());
	}

	/********************************************************************
         * 
         ********************************************************************/
	public static void out(short[] i) {
		if (!isEnable)
			return;

		int leng = i.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(i[j]);
		}
		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	public static void out(int[] i) {
		if (!isEnable)
			return;

		int leng = i.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(i[j]);
		}
		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	public static void out(float[] f) {
		if (!isEnable)
			return;

		int leng = f.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append("   ");
			tmp.append(f[j]);
		}

		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	public static void out(double[] d) {
		if (!isEnable)
			return;

		int leng = d.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(d[j]);
		}

		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	public static void out(boolean[] b) {
		if (!isEnable)
			return;

		int leng = b.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(b[j]);
		}

		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	public static void out(long[] l) {
		if (!isEnable)
			return;

		int leng = l.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(l[j]);
		}
		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	public static void out(byte[] l) {
		if (!isEnable)
			return;

		int leng = l.length;
		StringBuilder tmp = new StringBuilder();
		for (int j = 0; j < leng; j++) {
			tmp.append(" ");
			tmp.append(l[j]);
		}
		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	/********************************************************************
         * 
         ********************************************************************/

	public static void out(Iterator<Integer> i) {
		if (!isEnable)
			return;

		StringBuilder tmp = new StringBuilder();
		while (i.hasNext()) {
			tmp.append(" ");
			tmp.append(i.next());
		}
		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	public static void out(Iterator<String> i, int t) {
		if (!isEnable)
			return;

		StringBuilder tmp = new StringBuilder();
		while (i.hasNext()) {
			tmp.append("   ");
			tmp.append(i.next());
		}
		Gdx.app.log("**Print Out** ", " " + tmp.toString());
	}

	/********************************************************************
         * 
         ********************************************************************/

	public static void outln(String string) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " \n" + string);
	}

	public static void outln(int i) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " 'n" + i);
	}

	public static void outln(float f) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " \n" + f);
	}

	public static void outln(double d) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " \n" + d);
	}

	public static void outln(long l) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " \n" + l);
	}

	public static void outln(boolean b) {
		if (!isEnable)
			return;

		Gdx.app.log("**Print Out** ", " \n" + b);
	}

	/********************************************************************
         * 
         ********************************************************************/

	public static void ln() {
		if (!isEnable)
			return;

		Gdx.app.log("", "\n");
	}

	/********************************************************************
         * 
         ********************************************************************/

	public static void out(Vector2... v) {
		if (!isEnable)
			return;

		if (v[0] == null)
			D.out("NULL");
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < v.length; i++) {
			tmp.append("\n Vector2[" + i + "]  : " + v[i].x + "   " + v[i].y);
		}
		D.out(tmp.toString());
	}

	public static void out(Vector3... v) {
		if (!isEnable)
			return;

		if (v[0] == null)
			D.out("NULL");
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < v.length; i++) {
			tmp.append("\n Vector3[" + i + "]  : " + v[i].x + "   " + v[i].y
					+ "   " + v[i].z);
		}
		D.out(tmp.toString());
	}

	/********************************************************************
         * 
         ********************************************************************/

	public static void nativeHeap(int type) {
		if (!isEnable)
			return;

		switch (type) {
		case MB:
			Gdx.app.log("Native Heap : ", "" + Gdx.app.getNativeHeap() / 1024
					/ 1024);
			break;
		case GB:
			Gdx.app.log("Native Heap : ", "" + Gdx.app.getNativeHeap() / 1024
					/ 1024 / 1024);
			break;
		case KB:
			Gdx.app.log("Native Heap : ", "" + Gdx.app.getNativeHeap() / 1024);
			break;

		default:
			Gdx.app.log("Native Heap : ", "" + Gdx.app.getNativeHeap() / 1024);
			break;
		}

	}

	public static void javaHeap(int type) {
		if (!isEnable)
			return;

		switch (type) {
		case MB:
			Gdx.app.log("Java Heap   : ", "" + Gdx.app.getJavaHeap() / 1024
					/ 1024);
			break;
		case GB:
			Gdx.app.log("Java Heap   : ", "" + Gdx.app.getJavaHeap() / 1024
					/ 1024 / 1024);
			break;
		case KB:
			Gdx.app.log("Java Heap   : ", "" + Gdx.app.getJavaHeap() / 1024);
			break;

		default:
			Gdx.app.log("Java Heap   : ", "" + Gdx.app.getJavaHeap());
			break;
		}
	}

	public static long jHeap(int type) {
		switch (type) {
		case MB:
			return Gdx.app.getJavaHeap() / 1024 / 1024;
		case KB:
			return Gdx.app.getJavaHeap() / 1024;
		case GB:
			return Gdx.app.getJavaHeap() / 1024l / 1024l / 1024l;
		}
		return Gdx.app.getJavaHeap();
	}

	public static long nHeap(int type) {
		switch (type) {
		case MB:
			return Gdx.app.getNativeHeap() / 1024 / 1024;
		case KB:
			return Gdx.app.getNativeHeap() / 1024;
		case GB:
			return Gdx.app.getNativeHeap() / 1024l / 1024l / 1024l;
		}
		return Gdx.app.getNativeHeap();
	}

	public static void heap(int id) {
		if (!isEnable)
			return;

		Gdx.app.log("Heap " + id + " ", "Java Heap:  " + Gdx.app.getJavaHeap()
				+ "   Native Heap:  " + Gdx.app.getNativeHeap());
	}

	/********************************************************************
         * 
         ********************************************************************/

	public static int fps() {
		if (!isEnable)
			return -1;

		if (log == null)
			log = new eFPSLogger();
		return log.log();
	}

	/******************************************************************** reflection helper ********************************************************************/

	public final static void printConstructors(Class type) {
		Constructor[] allConstructors = type.getDeclaredConstructors();
		for (Constructor ctor : allConstructors) {
			Class<?>[] pType = ctor.getParameterTypes();
			for (int i = 0; i < pType.length; i++) {
				D.out(ctor.toGenericString());

				Type[] gpType = ctor.getGenericParameterTypes();
				for (int j = 0; j < gpType.length; j++) {
					D.out("GenericParameterType" + j + " " + gpType[j]);
				}
				break;
			}
		}
	}

	public final static void shitoverhere() {
		D.out("   Shit over here   ");
	}

	public final static void damnoverhere() {
		D.out("   Damn over here   ");
	}

	/**
	 * A simple helper class to log the frames per seconds achieved. Just invoke
	 * the {@link #log()} method in your rendering method. The output will be
	 * logged once per second.
	 * 
	 * @author mzechner
	 */
	private static class eFPSLogger {
		long startTime;

		public eFPSLogger() {
			startTime = System.currentTimeMillis();
		}

		/** Logs the current frames per second to the console. */
		public int log() {
			if (System.currentTimeMillis() - startTime > 1000) {
				Gdx.app.log("FPSLogger",
						"fps: " + Gdx.graphics.getFramesPerSecond());
				startTime = System.currentTimeMillis();
			}
			return Gdx.graphics.getFramesPerSecond();
		}
	}

}